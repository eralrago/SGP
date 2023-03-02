package mx.com.ferbo.controller;


import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

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
	private String strCumpleanios;
	private DateFormat dateFormat;
	private Date fechaActual;
	private LocalDate currentDate;
	private LocalDate fechaCumpleanios;
	private String strFechaActual;
	private String strFechaCumpleanios;
	
	private FacesContext faceContext;
    private HttpServletRequest httpServletRequest;
	
	public BienvenidaBean() {
		empleadoDAO = new EmpleadoDAO();
		empleadoSelected = new DetEmpleadoDTO();
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		fechaActual = new Date();
		strFechaActual = getDateFormat().format(getFechaActual());
		currentDate = LocalDate.parse(strFechaActual);
		strCumpleanios = "";
	}
	
	@PostConstruct
    public void init() {
		faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        this.empleadoSelected = (DetEmpleadoDTO) httpServletRequest.getSession(true).getAttribute("empleado");  
        empleadoLogeado();
    }

	public String pasoDeEmpleado (DetEmpleadoDTO detEmpleadoDTO) {
		empleadoSelected = detEmpleadoDTO;
		return "protected/registroAsistencia.xhtml";
	}
	
	public void empleadoLogeado() {
		int diaActual = currentDate.getDayOfMonth();
		int mesActual = currentDate.getMonthValue();
		strFechaCumpleanios = getDateFormat().format(empleadoSelected.getFechaNacimiento());
		fechaCumpleanios = LocalDate.parse(strFechaCumpleanios);
		int diaCumpleanios = fechaCumpleanios.getDayOfMonth();
		int mesCumpleanios = fechaCumpleanios.getMonthValue();
		if(diaActual == diaCumpleanios && mesActual == mesCumpleanios) {
			strCumpleanios = "¡Feliz Cumpleaños " + empleadoSelected.getNombre() + " " + empleadoSelected.getPrimerAp() + " " + empleadoSelected.getSegundoAp() + "!";
		} 
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

	public String getStrCumpleanios() {
		return strCumpleanios;
	}

	public void setStrCumpleanios(String strCumpleanios) {
		this.strCumpleanios = strCumpleanios;
	}

	public DateFormat getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	public Date getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}

	public LocalDate getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(LocalDate currentDate) {
		this.currentDate = currentDate;
	}

	public String getStrFechaActual() {
		return strFechaActual;
	}

	public void setStrFechaActual(String strFechaActual) {
		this.strFechaActual = strFechaActual;
	}

	
	
	
}
