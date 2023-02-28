package mx.com.ferbo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import mx.com.ferbo.dao.CatAreaDAO;
import mx.com.ferbo.dao.CatEmpresaDAO;
import mx.com.ferbo.dao.CatPerfilDAO;
import mx.com.ferbo.dao.CatPlantaDAO;
import mx.com.ferbo.dao.CatPuestoDAO;
import mx.com.ferbo.dao.EmpleadoDAO;
import mx.com.ferbo.dto.CatAreaDTO;
import mx.com.ferbo.dto.CatEmpresaDTO;
import mx.com.ferbo.dto.CatPerfilDTO;
import mx.com.ferbo.dto.CatPlantaDTO;
import mx.com.ferbo.dto.CatPuestoDTO;
import mx.com.ferbo.dto.DetEmpleadoDTO;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.event.CaptureEvent;

/**
 *
 * @author Gabo
 */
@Named(value = "registroEmpleadosBean")
@ViewScoped
public class RegistroEmpleadosBean implements Serializable {

    private Logger log = LogManager.getRootLogger();
    private DetEmpleadoDTO empleadoSelected;

    private List<DetEmpleadoDTO> lstEmpleados;
    private List<DetEmpleadoDTO> lstEmpleadosSelected;
    private List<CatEmpresaDTO> lstCatEmpresa;
    private List<CatPerfilDTO> lstCatPerfil;
    private List<CatPlantaDTO> lstCatPlanta;
    private List<CatPuestoDTO> lstCatPuesto;
    private List<CatAreaDTO> lstCatArea;

    private final CatEmpresaDAO catEmpresaDAO;
    private final CatPerfilDAO catPerfilDAO;
    private final CatPlantaDAO catPlantaDAO;
    private final CatPuestoDAO catPuestoDAO;
    private final CatAreaDAO catAreaDAO;
    private final EmpleadoDAO empleadoDAO;
    

    public RegistroEmpleadosBean() {
        log.info("--Iniciando desde el constructor--");
        catEmpresaDAO = new CatEmpresaDAO();
        catPerfilDAO = new CatPerfilDAO();
        catPlantaDAO = new CatPlantaDAO();
        catPuestoDAO = new CatPuestoDAO();
        catAreaDAO = new CatAreaDAO();
        empleadoDAO = new EmpleadoDAO();
        empleadoSelected = new DetEmpleadoDTO();
        lstEmpleados = new ArrayList<>();
        lstEmpleadosSelected = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        log.info("--Iniciando desde el postconstruct--");
        try {
            lstCatEmpresa = catEmpresaDAO.buscarActivo();
            lstCatPerfil = catPerfilDAO.buscarActivo();
            lstCatPlanta = catPlantaDAO.buscarActivo();
            lstCatPuesto = catPuestoDAO.buscarActivo();
            lstCatArea = catAreaDAO.buscarActivo();
            consultaEmpleados();
        } catch (Exception ex) {
            log.info(ex);
        }
    }
    
    /*
     * Método para consultar a los empleados
     */
    private void consultaEmpleados() {
        lstEmpleados = empleadoDAO.buscarActivo();
    }

    /*
     * Método para obtener el mensaje a mostrar en el botón eliminar
     */
    public String getMensajeBotonEliminar() {
        if (isEmpleadoSeleccionado()) {
            int size = this.lstEmpleadosSelected.size();
            return size > 1 ? size + " empleados seleccionados" : "1 empleado seleccionado";
        }

        return "Eliminar";
    }

    /*
     * Método para eliminar verificar la seleccioón de n empleados
     */
    public boolean isEmpleadoSeleccionado() {
        return this.lstEmpleadosSelected != null && !this.lstEmpleadosSelected.isEmpty();
    }

    /*
     * Método para inicializar objeto empleado
     */
    public void agregarEmpleado() {
        this.empleadoSelected = new DetEmpleadoDTO();
    }

    /*
     * Método para guardar empleado
     */
    public void guardarEmpleado() {
        if (this.empleadoSelected.getIdEmpleado() == null) {
            try {
                empleadoDAO.guardar(empleadoSelected);
                consultaEmpleados();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empleado agregado"));
            } catch (Exception ex) {
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al guardar empleado"));
            }

        } else {
            try {
                empleadoDAO.actualizar(empleadoSelected);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empleado editado"));
            } catch (Exception ex) {
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al guardar empleado"));
            }
        }

        PrimeFaces.current().executeScript("PF('dialogEmpleado').hide()");
        PrimeFaces.current().ajax().update("formRegistroEmpleado:messages", "formRegistroEmpleado:dtEmpleados");
    }

    /*
     * Método para eliminar listado de empleados
     */
    public void eliminaEmpleadosSeleccionados() {
        try {
            for (DetEmpleadoDTO empleado : lstEmpleadosSelected) {
                empleadoDAO.eliminar(empleado);
            }
            consultaEmpleados();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empleados Eliminados"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al eliminar uno o varios empleados"));
        }

        PrimeFaces.current().ajax().update("formRegistroEmpleado:messages", "formRegistroEmpleado:dtEmpleados");
        this.lstEmpleadosSelected.clear();
    }

    /*
     * Método para eliminar 1 empleado
     */
    public void eliminaEmpleado() {
        try {
            empleadoDAO.eliminar(empleadoSelected);
            consultaEmpleados();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empleado Eliminado"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al eliminar al empleado"));
        }

        PrimeFaces.current().ajax().update("formRegistroEmpleado:messages", "formRegistroEmpleado:dtEmpleados");
    }

    /*
     * Método para redirigir al kárdex
     */
    public void redirectKardex() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("kardexEmpleado.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No es posible acceder al recurso"));
            PrimeFaces.current().ajax().update("formRegistroEmpleado:messages");
        }

    }
    
    public void oncapture(CaptureEvent captureEvent) {
        empleadoSelected.setFotografia("data:image/jpeg;base64," + Base64.getEncoder().encodeToString(captureEvent.getData()));
    }    

//<editor-fold defaultstate="collapsed" desc="Getters&Setters">
    public List<CatEmpresaDTO> getLstCatEmpresa() {
        return lstCatEmpresa;
    }

    public void setLstCatEmpresa(List<CatEmpresaDTO> lstCatEmpresa) {
        this.lstCatEmpresa = lstCatEmpresa;
    }

    public DetEmpleadoDTO getEmpleadoSelected() {
        return empleadoSelected;
    }

    public void setEmpleadoSelected(DetEmpleadoDTO empleadoSelected) {
        this.empleadoSelected = empleadoSelected;
    }

    public List<CatPerfilDTO> getLstCatPerfil() {
        return lstCatPerfil;
    }

    public void setLstCatPerfil(List<CatPerfilDTO> lstCatPerfil) {
        this.lstCatPerfil = lstCatPerfil;
    }

    public List<CatPlantaDTO> getLstCatPlanta() {
        return lstCatPlanta;
    }

    public void setLstCatPlanta(List<CatPlantaDTO> lstCatPlanta) {
        this.lstCatPlanta = lstCatPlanta;
    }

    public List<CatPuestoDTO> getLstCatPuesto() {
        return lstCatPuesto;
    }

    public void setLstCatPuesto(List<CatPuestoDTO> lstCatPuesto) {
        this.lstCatPuesto = lstCatPuesto;
    }

    public List<CatAreaDTO> getLstCatArea() {
        return lstCatArea;
    }

    public void setLstCatArea(List<CatAreaDTO> lstCatArea) {
        this.lstCatArea = lstCatArea;
    }

    public List<DetEmpleadoDTO> getLstEmpleados() {
        return lstEmpleados;
    }

    public void setLstEmpleados(List<DetEmpleadoDTO> lstEmpleados) {
        this.lstEmpleados = lstEmpleados;
    }

    public List<DetEmpleadoDTO> getLstEmpleadosSelected() {
        return lstEmpleadosSelected;
    }

    public void setLstEmpleadosSelected(List<DetEmpleadoDTO> lstEmpleadosSelected) {
        this.lstEmpleadosSelected = lstEmpleadosSelected;
    }

//</editor-fold> 
}
