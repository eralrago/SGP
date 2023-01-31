package mx.com.ferbo.controller;


import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import mx.com.ferbo.dao.EmpleadoDAO;
import mx.com.ferbo.dto.DetEmpleadoDTO;

@Named(value = "bienvenidaBean")
@SessionScoped
public class BienvenidaBean implements Serializable {
	
	private DetEmpleadoDTO empleadoSelected;
	private final EmpleadoDAO empleadoDAO;
	private String numeroEmpl;
	
	/*private FacesContext faceContext;
    private HttpServletRequest httpServletRequest;*/
	
	public BienvenidaBean() {
		empleadoDAO = new EmpleadoDAO();
		empleadoSelected = new DetEmpleadoDTO();
		setNumeroEmpl("0006");
	}
	
	@PostConstruct
    public void init() {
		/*faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        this.empleadoSelected = (DetEmpleado) httpServletRequest.getSession(true).getAttribute("nombre");*/
		// System.out.println(empleadoSelected.getNumEmpleado());
		empleadoLogeado();
    }

	public String pasoDeEmpleado (DetEmpleadoDTO detEmpleadoDTO) {
		empleadoSelected = detEmpleadoDTO;
		return "protected/registroAsistencia.xhtml";
	}
	
	public String empleadoLogeado() {
		// DetEmpleadoDTO empleadoSeleccionado = new DetEmpleadoDTO();
		// String numeroEmpl = getEmpleadoDAO();
		empleadoSelected = empleadoDAO.buscarPorNumEmpl(getNumeroEmpl());
		return "protected/registroAsistencia.xhtml";
	}

	public DetEmpleadoDTO getEmpleadoSelected() {
		return empleadoSelected;
	}

	public void setEmpleadoSelected(DetEmpleadoDTO empleadoSelected) {
		this.empleadoSelected = empleadoSelected;
	}

	public EmpleadoDAO getEmpleadoDAO() {
		return empleadoDAO;
	}

	public String getNumeroEmpl() {
		return numeroEmpl;
	}

	public void setNumeroEmpl(String numeroEmpl) {
		this.numeroEmpl = numeroEmpl;
	}
	
	
}
