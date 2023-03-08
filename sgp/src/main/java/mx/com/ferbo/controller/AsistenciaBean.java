package mx.com.ferbo.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import mx.com.ferbo.dao.*;
import mx.com.ferbo.dto.*;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.*;

/**
 *
 * @author Gabo
 */
@Named(value = "asistenciaBean")
@ViewScoped
public class AsistenciaBean implements Serializable {

    private ScheduleModel calendario;
    private ScheduleEvent evento;
    private RegistroDAO registroDAO;
    private SolicitudPermisoDAO solicitudPermisoDAO;
    private CatTipoSolicitudDAO catTipoSolicitudDAO;
    private DetSolicitudPermisoDTO solicitudSelected;
    private IncidenciaDAO incidenciaDAO;
    private final SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");
    private final Date minDate = new Date();

    private List<DetRegistroDTO> lstRegistros;
    private List<DetSolicitudPermisoDTO> lstSolicitudes;
    private List<CatTipoSolicitudDTO> lstTipoSol;
    private List<DetIncidenciaDTO> lstIncidencias;
    private List<Integer> invalidDays;
    private List<Date> lstRangoRegistro;
    private List<SelectItem> lstTipoSolSelect;
    private Date fechaSeleccionada;

    // Obteniendo Empleado
    private DetEmpleadoDTO empleadoSelected;
    private HttpServletRequest httpServletRequest;

    public AsistenciaBean() {
        calendario = new DefaultScheduleModel();
        registroDAO = new RegistroDAO();
        solicitudPermisoDAO = new SolicitudPermisoDAO();
        catTipoSolicitudDAO = new CatTipoSolicitudDAO();
        incidenciaDAO = new IncidenciaDAO();
        inicializaSolicitud();
        sdf.setTimeZone(TimeZone.getTimeZone(ZoneId.of("America/Mexico_City").normalized()));
        lstRangoRegistro = new ArrayList<>();
        invalidDays = new ArrayList<>();
        lstTipoSolSelect = new ArrayList<>();
        invalidDays.add(0);

        empleadoSelected = new DetEmpleadoDTO();
        httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        this.empleadoSelected = (DetEmpleadoDTO) httpServletRequest.getSession(true).getAttribute("empleado");
    }

    @PostConstruct
    public void init() {
        evento = new DefaultScheduleEvent();
        lstTipoSol = catTipoSolicitudDAO.buscarActivo();
        lstTipoSol.forEach((CatTipoSolicitudDTO tipo) -> {
            lstTipoSolSelect.add(new SelectItem(tipo.getIdTipoSolicitud(),
                    tipo.getDescripcion(),
                    tipo.getIdTipoSolicitud() == 3 ? "Solo 1 día" : tipo.getIdTipoSolicitud() == 4 ? "Más de 1 día" : null));
        });

        lstRegistros = registroDAO.consultaRegistrosPorIdEmp(empleadoSelected.getIdEmpleado());
        lstIncidencias = incidenciaDAO.consultaPorIdEmpleado(empleadoSelected.getIdEmpleado());
        generaEventosRegistros(lstRegistros);
        generaEventosIncidencias(lstIncidencias);
        lstSolicitudes = solicitudPermisoDAO.consultaPorIdEmpleado(empleadoSelected.getIdEmpleado());
    }

    private void generaEventosRegistros(List<DetRegistroDTO> registros) {

        for (DetRegistroDTO registro : registros) {

            //Evento de entrada
            DefaultScheduleEvent eventoEntrada = DefaultScheduleEvent.builder()
                    .title("Entrada " + sdf.format(registro.getFechaEntrada()))
                    .startDate(convertirDateToLocalDateTime(registro.getFechaEntrada()))
                    .endDate(convertirDateToLocalDateTime(registro.getFechaEntrada()))
                    .description(null)
                    .backgroundColor(findBgColor(registro.getCatEstatusRegistroDTO().getIdEstatus()))
                    .dynamicProperty("estatus", registro.getCatEstatusRegistroDTO().getDescripcion())
                    .build();

            calendario.addEvent(eventoEntrada);

            //Genera horario de comida
            DefaultScheduleEvent eventoComida = DefaultScheduleEvent.builder()
                    .title("Comida\n 2:00 PM - 03:00 PM")
                    .startDate(convertirDateToLocalDateTime(generaHorarioComida(true, registro.getFechaEntrada())))
                    .endDate(convertirDateToLocalDateTime(generaHorarioComida(false, registro.getFechaEntrada())))
                    .description(null)
                    .styleClass(estiloByTipo(0))
                    .dynamicProperty("tipoSolicitud", "Horario de comida")
                    .dynamicProperty("idTipoSolicitud", 3)
                    .build();
            calendario.addEvent(eventoComida);

            //Evento de salida
            if (registro.getFechaSalida() != null) {
                DefaultScheduleEvent eventoSalida = DefaultScheduleEvent.builder()
                        .title("Salida " + sdf.format(registro.getFechaSalida()))
                        .startDate(convertirDateToLocalDateTime(registro.getFechaSalida()))
                        .endDate(convertirDateToLocalDateTime(registro.getFechaSalida()))
                        .description(sdf.format(registro.getFechaSalida()))
                        .dynamicProperty("estatus", registro.getCatEstatusRegistroDTO().getDescripcion())
                        .build();

                calendario.addEvent(eventoSalida);
            }
        }

    }

    private void generaEventosIncidencias(List<DetIncidenciaDTO> incidencias) {
        for (DetIncidenciaDTO incidencia : incidencias) {
            DefaultScheduleEvent eventoEntrada = DefaultScheduleEvent.builder()
                    .title(incidencia.getDetSolicitudPermisoDTO().getCatTipoSolicitud().getDescripcion())
                    .startDate(convertirDateToLocalDateTime(incidencia.getDetSolicitudPermisoDTO().getFechaInicio()))
                    .endDate(convertirDateToLocalDateTime(incidencia.getDetSolicitudPermisoDTO().getFechaFin()))
                    .allDay(true)
                    .description(null)
                    .styleClass(estiloByTipo(incidencia.getDetSolicitudPermisoDTO().getCatTipoSolicitud().getIdTipoSolicitud()))
                    .dynamicProperty("tipoSolicitud", incidencia.getDetSolicitudPermisoDTO().getCatTipoSolicitud().getDescripcion())
                    .dynamicProperty("idTipoSolicitud", incidencia.getDetSolicitudPermisoDTO().getCatTipoSolicitud().getIdTipoSolicitud())
                    .build();
            calendario.addEvent(eventoEntrada);
        }
    }

    public LocalDateTime convertirDateToLocalDateTime(Date fecha) {
        return fecha.toInstant()
                .atZone(ZoneId.of("America/Mexico_City"))
                .toLocalDateTime();
    }

    public void eventoSeleccionado(SelectEvent<ScheduleEvent> selectEvent) {
        evento = selectEvent.getObject();
    }

    public void diaSeleccionado(SelectEvent<LocalDateTime> selectEvent) {
        evento = DefaultScheduleEvent.builder().startDate(selectEvent.getObject()).endDate(selectEvent.getObject()).build();
    }

    private String findBgColor(Integer idEstatus) {
        String color = null;
        switch (idEstatus) {
            case 1:
                color = "#689F38";
                break;
            case 2:
                color = "#ef6262";
                break;
            default:
                color = "#00000000";
                break;
        }
        return color;
    }

    private String estiloByTipo(int idTipoEvento) {
        String estilo;
        switch (idTipoEvento) {
            case 1:
                estilo = "ferbo-evento-permiso";
                break;
            case 2:
                estilo = "ferbo-evento-vacaciones";
                break;
            case 3:
                estilo = "ferbo-evento-incapacidad-c";
                break;
            case 4:
                estilo = "ferbo-evento-incapacidad-l";
                break;
            default:
                estilo = "ferbo-evento-comida";
                break;
        }
        return estilo;
    }

    public void guardaSolicitud() {

        try {
            if (fechaSeleccionada != null) {
                solicitudSelected.setFechaInicio(fechaSeleccionada);
                solicitudSelected.setFechaFin(fechaSeleccionada);

            } else {
                solicitudSelected.setFechaInicio(lstRangoRegistro.get(0));
                solicitudSelected.setFechaFin(lstRangoRegistro.size() > 1 ? lstRangoRegistro.get(1) : lstRangoRegistro.get(0));
            }
            solicitudSelected.setEmpleadoSol(new DetEmpleadoDTO(empleadoSelected.getIdEmpleado()));
            solicitudPermisoDAO.guardar(solicitudSelected);

            lstSolicitudes.add(solicitudSelected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Solicitud registrada"));

        } catch (Exception ex) {
            System.out.println("Error " + ex);
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al registrar la solicitud"));
        }
        inicializaSolicitud();
        fechaSeleccionada = null;
        lstRangoRegistro.clear();
        PrimeFaces.current().executeScript("PF('dialogVacaciones').hide()");
        PrimeFaces.current().ajax().update("formActividades:messages", "formActividades:tabView:dtSolicitudes");
    }

    public void inicializaSolicitud() {
        solicitudSelected = new DetSolicitudPermisoDTO();
    }

    public void actualizaCalendarioSeleccionado() {
        switch (solicitudSelected.getCatTipoSolicitud().getIdTipoSolicitud()) {
            case 1://PERMISO
            case 3://INCAPACIDAD CORTA
                fechaSeleccionada = solicitudSelected.getFechaInicio();
                break;
            default:
                lstRangoRegistro = Arrays.asList(solicitudSelected.getFechaInicio(), solicitudSelected.getFechaFin());
        }

        PrimeFaces.current().executeScript("PF('dialogVacacionesView').show();");
    }

    private Date generaHorarioComida(boolean inicio, Date fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);

        if (inicio) {
            calendar.set(Calendar.HOUR_OF_DAY, 14);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
        } else {
            calendar.set(Calendar.HOUR_OF_DAY, 15);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
        }
        return calendar.getTime();
    }

    public ScheduleModel getCalendario() {
        return calendario;
    }

    public void setCalendario(ScheduleModel calendario) {
        this.calendario = calendario;
    }

    public ScheduleEvent getEvento() {
        return evento;
    }

    public void setEvento(ScheduleEvent evento) {
        this.evento = evento;
    }

    public Date getMinDate() {
        return minDate;
    }

    public List<DetSolicitudPermisoDTO> getLstSolicitudes() {
        return lstSolicitudes;
    }

    public void setLstSolicitudes(List<DetSolicitudPermisoDTO> lstSolicitudes) {
        this.lstSolicitudes = lstSolicitudes;
    }

    public DetSolicitudPermisoDTO getSolicitudSelected() {
        return solicitudSelected;
    }

    public void setSolicitudSelected(DetSolicitudPermisoDTO solicitudSelected) {
        this.solicitudSelected = solicitudSelected;
    }

    public List<Integer> getInvalidDays() {
        return invalidDays;
    }

    public List<CatTipoSolicitudDTO> getLstTipoSol() {
        return lstTipoSol;
    }

    public List<Date> getLstRangoRegistro() {
        return lstRangoRegistro;
    }

    public void setLstRangoRegistro(List<Date> lstRangoRegistro) {
        this.lstRangoRegistro = lstRangoRegistro;
    }

    public Date getFechaSeleccionada() {
        return fechaSeleccionada;
    }

    public void setFechaSeleccionada(Date fechaSeleccionada) {
        this.fechaSeleccionada = fechaSeleccionada;
    }

    public DetEmpleadoDTO getEmpleadoSelected() {
        return empleadoSelected;
    }

    public void setEmpleadoSelected(DetEmpleadoDTO empleadoSelected) {
        this.empleadoSelected = empleadoSelected;
    }

    public List<SelectItem> getLstTipoSolSelect() {
        return lstTipoSolSelect;
    }

    public void setLstTipoSolSelect(List<SelectItem> lstTipoSolSelect) {
        this.lstTipoSolSelect = lstTipoSolSelect;
    }

}
