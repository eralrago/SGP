package mx.com.ferbo.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
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

/**
 *
 * @author Gabo
 */
@Named(value = "kardexBean")
@SessionScoped
public class KardexBean implements Serializable {

    private DetEmpleadoDTO empleadoSelected;

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

    private Logger log = LogManager.getRootLogger();

    public KardexBean() {
        log.info("--Iniciando desde el constructor--");
        catEmpresaDAO = new CatEmpresaDAO();
        catPerfilDAO = new CatPerfilDAO();
        catPlantaDAO = new CatPlantaDAO();
        catPuestoDAO = new CatPuestoDAO();
        catAreaDAO = new CatAreaDAO();
        empleadoDAO = new EmpleadoDAO();
//        empleadoSelected = empleadoDAO.buscarPorId(1);
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
        } catch (Exception ex) {
            log.info(ex);
        }
    }

//<editor-fold defaultstate="collapsed" desc="Getters&Setters">
    public DetEmpleadoDTO getEmpleadoSelected() {
        return empleadoSelected;
    }

    public void setEmpleadoSelected(DetEmpleadoDTO empleadoSelected) {
        this.empleadoSelected = empleadoSelected;
    }

    public List<CatEmpresaDTO> getLstCatEmpresa() {
        return lstCatEmpresa;
    }

    public void setLstCatEmpresa(List<CatEmpresaDTO> lstCatEmpresa) {
        this.lstCatEmpresa = lstCatEmpresa;
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
    //</editor-fold>

}
