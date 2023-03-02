package mx.com.ferbo.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
    private Date fechaSeleccionada;
    
    // Obteniendo Empleado
    private DetEmpleadoDTO empleadoSelected;
    private FacesContext faceContext;
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
        invalidDays.add(0);
        
        empleadoSelected = new DetEmpleadoDTO();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        this.empleadoSelected = (DetEmpleadoDTO) httpServletRequest.getSession(true).getAttribute("empleado");
    }

    @PostConstruct
    public void init() {
        evento = new DefaultScheduleEvent();
        lstTipoSol = catTipoSolicitudDAO.buscarActivo();
        lstRegistros = registroDAO.consultaRegistrosPorIdEmp(empleadoSelected.getIdEmpleado());
        lstIncidencias = incidenciaDAO.consultaPorIdEmpleado(empleadoSelected.getIdEmpleado());
        generaEventosRegistros(lstRegistros);
        generaEventosIncidencias(lstIncidencias);
        lstSolicitudes = solicitudPermisoDAO.consultaPorIdEmpleado(empleadoSelected.getIdEmpleado());
    }

    private void generaEventosRegistros(List<DetRegistroDTO> registros) {

        for (DetRegistroDTO registro : registros) {

            DefaultScheduleEvent eventoEntrada = DefaultScheduleEvent.builder()
                    .title("Entrada " + sdf.format(registro.getFechaEntrada()))
                    .startDate(convertirDateToLocalDateTime(registro.getFechaEntrada()))
                    .endDate(convertirDateToLocalDateTime(registro.getFechaEntrada()))
                    .description(null)
                    .backgroundColor(findBgColor(registro.getCatEstatusRegistroDTO().getIdEstatus()))
                    .dynamicProperty("estatus", registro.getCatEstatusRegistroDTO().getDescripcion())
                    .build();

            calendario.addEvent(eventoEntrada);

            if (registro.getFechaSalida() != null) {
                DefaultScheduleEvent eventoSalida = DefaultScheduleEvent.builder()
                        .title("Salida " + sdf.format(registro.getFechaSalida()))
                        .startDate(convertirDateToLocalDateTime(registro.getFechaSalida()))
                        .endDate(convertirDateToLocalDateTime(registro.getFechaSalida()))
                        .description(sdf.format(registro.getFechaSalida()))
                        .backgroundColor(findBgColor(registro.getCatEstatusRegistroDTO().getIdEstatus()))
                        .dynamicProperty("estatus", registro.getCatEstatusRegistroDTO().getDescripcion())
                        .build();

                calendario.addEvent(eventoSalida);
            }
        }

    }
    
    public void generaEventosIncidencias(List<DetIncidenciaDTO> incidencias){
        for (DetIncidenciaDTO incidencia : incidencias) {
           DefaultScheduleEvent eventoEntrada = DefaultScheduleEvent.builder()
                    .title(incidencia.getDetSolicitudPermisoDTO().getCatTipoSolicitud().getDescripcion())
                    .startDate(convertirDateToLocalDateTime(incidencia.getDetSolicitudPermisoDTO().getFechaInicio()))
                    .endDate(convertirDateToLocalDateTime(incidencia.getDetSolicitudPermisoDTO().getFechaFin()))
                    .allDay(true)
                    .description(null)
                    .dynamicProperty("tipoSolicitud", incidencia.getDetSolicitudPermisoDTO().getCatTipoSolicitud().getDescripcion())
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
            case 2:
                color = "#ef6262";
                break;
            default:
                color = "#689F38";
        }
        return color;
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

    public void cancelaSolicitud() {
        System.out.println("Cancelar");

    }

    public void inicializaSolicitud() {
        solicitudSelected = new DetSolicitudPermisoDTO();
    }

    public void actualizaCalendarioSeleccionado() {
        switch (solicitudSelected.getCatTipoSolicitud().getIdTipoSolicitud()) {
            case 1://PERMISO
            case 3://INCAPACIDAD
                fechaSeleccionada = solicitudSelected.getFechaInicio();
                break;
            default:
                lstRangoRegistro = Arrays.asList(solicitudSelected.getFechaInicio(), solicitudSelected.getFechaFin());
        }

        PrimeFaces.current().executeScript("PF('dialogVacacionesView').show();");
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

}
