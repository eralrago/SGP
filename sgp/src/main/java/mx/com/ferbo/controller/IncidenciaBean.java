package mx.com.ferbo.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import mx.com.ferbo.dao.IncidenciaDAO;
import mx.com.ferbo.dto.DetIncidenciaDTO;

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
    private List<DetIncidenciaDTO> lstIncidencias;

    public IncidenciaBean() {
        incidenciaDAO = new IncidenciaDAO();
    }

    @PostConstruct
    public void init() {
        lstIncidencias = incidenciaDAO.buscarTodos();
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

}
