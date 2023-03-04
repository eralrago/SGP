package mx.com.ferbo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import mx.com.ferbo.dao.CatTipoSolicitudDAO;
import mx.com.ferbo.dao.EmpleadoDAO;
import mx.com.ferbo.dao.IncidenciaDAO;
import mx.com.ferbo.dto.CatTipoSolicitudDTO;
import mx.com.ferbo.dto.DetEmpleadoDTO;
import mx.com.ferbo.dto.DetIncidenciaDTO;
import mx.com.ferbo.util.SGPException;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Gabriel
 */
@Named("incidenciaBean")
@ViewScoped
public class IncidenciaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private IncidenciaDAO incidenciaDAO;
    private DetIncidenciaDTO incidenciaSelected;
    private Date fechaSeleccionada;
    private List<DetIncidenciaDTO> lstIncidencias;
    private List<CatTipoSolicitudDTO> lstTipoSol;
    private CatTipoSolicitudDAO catTipoSolicitudDAO;
    private List<Date> lstRangoRegistro;
    private List<Integer> invalidDays;
    private Date minDate;
    
    private DetEmpleadoDTO empleadoSelected;
    private HttpServletRequest httpServletRequest;
    private final EmpleadoDAO empleadoDAO;

    public IncidenciaBean() {
        incidenciaDAO = new IncidenciaDAO();
        catTipoSolicitudDAO = new CatTipoSolicitudDAO();
        minDate = new Date();
        
        empleadoDAO = new EmpleadoDAO();
        httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        this.empleadoSelected = (DetEmpleadoDTO) httpServletRequest.getSession(true).getAttribute("empleado");
        
        empleadoSelected = empleadoDAO.buscarPorId(empleadoSelected.getIdEmpleado());
    }

    @PostConstruct
    public void init() {
        consultaIncidencias();
        lstTipoSol = catTipoSolicitudDAO.buscarActivo();
        invalidDays = Arrays.asList(0);
    }

    private void consultaIncidencias() {
        lstIncidencias = incidenciaDAO.buscarTodos();
    }

    public void visualizaDialog() {
        switch (incidenciaSelected.getCatTipoIncidenciaDTO().getIdTipo()) {
            case 1:
                if (incidenciaSelected.getDetSolicitudPermisoDTO().getCatTipoSolicitud().getIdTipoSolicitud() == 1) {
                    fechaSeleccionada = incidenciaSelected.getDetSolicitudPermisoDTO().getFechaInicio();
                } else {
                    lstRangoRegistro = Arrays.asList(incidenciaSelected.getDetSolicitudPermisoDTO().getFechaInicio(), incidenciaSelected.getDetSolicitudPermisoDTO().getFechaFin());
                }

                PrimeFaces.current().executeScript("PF('dialogPersmisos').show();");
                break;
            default:
                System.out.println("ERROR: seleccione opción");
        }

    }

    public void guardarEstatusIncidencia(boolean aprobada) {
        
        incidenciaSelected.setDetEmpleadoRevDTO(new DetEmpleadoDTO(empleadoSelected.getIdEmpleado()));
        try {
            incidenciaSelected.getCatEstatusIncidenciaDTO().setIdEstatus(aprobada ? 2 : 3);
            incidenciaDAO.actualizar(incidenciaSelected);

            String mensaje = "";
            if (incidenciaSelected.getCatEstatusIncidenciaDTO().getIdEstatus() == 1) {
                mensaje = "Solicitud aprobada correctamente";
            } else {
                mensaje = "Solicitud rechazada correctamente";
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(mensaje));
        } catch (SGPException ex) {
            System.out.println("Error " + ex);
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al actualizar la solicitud"));
        }
        consultaIncidencias();
        PrimeFaces.current().ajax().update("formIncidencias:messages", "formIncidencias:dtIncidencias");
        PrimeFaces.current().executeScript("PF('dialogPersmisos').hide()");
    }

    public DetIncidenciaDTO getIncidenciaSelected() {
        return incidenciaSelected;
    }

    public void setIncidenciaSelected(DetIncidenciaDTO incidenciaSelected) {
        this.incidenciaSelected = incidenciaSelected;
    }

    public List<DetIncidenciaDTO> getLstIncidencias() {
        return lstIncidencias;
    }

    public void setLstIncidencias(List<DetIncidenciaDTO> lstIncidencias) {
        this.lstIncidencias = lstIncidencias;
    }

    public Date getFechaSeleccionada() {
        return fechaSeleccionada;
    }

    public void setFechaSeleccionada(Date fechaSeleccionada) {
        this.fechaSeleccionada = fechaSeleccionada;
    }

    public List<Date> getLstRangoRegistro() {
        return lstRangoRegistro;
    }

    public void setLstRangoRegistro(List<Date> lstRangoRegistro) {
        this.lstRangoRegistro = lstRangoRegistro;
    }

    public List<Integer> getInvalidDays() {
        return invalidDays;
    }

    public void setInvalidDays(List<Integer> invalidDays) {
        this.invalidDays = invalidDays;
    }

    public List<CatTipoSolicitudDTO> getLstTipoSol() {
        return lstTipoSol;
    }

    public void setLstTipoSol(List<CatTipoSolicitudDTO> lstTipoSol) {
        this.lstTipoSol = lstTipoSol;
    }

    public Date getMinDate() {
        return minDate;
    }

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }

    public DetEmpleadoDTO getEmpleadoSelected() {
        return empleadoSelected;
    }

    public void setEmpleadoSelected(DetEmpleadoDTO empleadoSelected) {
        this.empleadoSelected = empleadoSelected;
    }
    
}
