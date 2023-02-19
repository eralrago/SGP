package mx.com.ferbo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.PrimeFaces;

import mx.com.ferbo.dao.CatPrendaDAO;
import mx.com.ferbo.dao.CatTallaDAO;
import mx.com.ferbo.dao.DetSolicitudPrendaDAO;
import mx.com.ferbo.dao.EmpleadoDAO;
import mx.com.ferbo.dto.CatPrendaDTO;
import mx.com.ferbo.dto.CatTallaDTO;
import mx.com.ferbo.dto.DetEmpleadoDTO;
import mx.com.ferbo.dto.DetSolicitudPrendaDTO;


@Named(value = "uniformesBean")
@RequestScoped
public class UniformesBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String numeroEmpl;

	private List<CatPrendaDTO> lstPrendasActivas;
	private List<CatTallaDTO> lstTallasActivas;
	private List<Integer> lstCantidad;
	private List<DetSolicitudPrendaDTO> lstSolicitudPrendas;
	
	private DetEmpleadoDTO empleadoSelected;
	private DetSolicitudPrendaDTO solicitud;
	
	private CatPrendaDAO uniformesDAO;
	private final EmpleadoDAO empleadoDAO;
	private DetSolicitudPrendaDAO detSolicitudPrendaDAO;
	private CatTallaDAO tallaDAO;
	
	private CatPrendaDTO prendaSelected;
	private CatTallaDTO tallaSelected;
	private Integer cantidadSelected;
	
	private FacesContext faceContext;
	private HttpServletRequest httpServletRequest;
	
	public UniformesBean() {
		lstPrendasActivas = new ArrayList<>();
		lstTallasActivas = new ArrayList<>();
		lstCantidad = new ArrayList<>(
	            Arrays.asList(1, 2, 3));
		
		empleadoSelected = new DetEmpleadoDTO();
		prendaSelected = new CatPrendaDTO();
		tallaSelected = new CatTallaDTO();
		cantidadSelected = 0;
		solicitud = new DetSolicitudPrendaDTO();
		
		uniformesDAO = new CatPrendaDAO();
		tallaDAO = new CatTallaDAO();
		empleadoDAO = new EmpleadoDAO();
		detSolicitudPrendaDAO = new DetSolicitudPrendaDAO();

		faceContext = FacesContext.getCurrentInstance();
		httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        this.empleadoSelected = (DetEmpleadoDTO) httpServletRequest.getSession(true).getAttribute("empleado");
	}
	
	@PostConstruct
    public void init() {
		lstSolicitudPrendas = new ArrayList<>();
		lstPrendasActivas = uniformesDAO.buscarActivo();
		lstTallasActivas = tallaDAO.buscarActivo();
	}
	
	public void preRegistro() {
		solicitud.setIdPrenda(prendaSelected.getIdPrenda());
		solicitud.setCantidad(cantidadSelected);
		solicitud.setIdEmpleadoSol(empleadoSelected.getIdEmpleado());
		solicitud.setIdTalla(tallaSelected.getIdTalla());
		solicitud.setFechaCap(new Date());
		solicitud.setAprobada((short)0);
		lstSolicitudPrendas.add(solicitud);
		
		
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Uniforme Registrtado"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-uniformes");
	}

	public List<CatPrendaDTO> getLstPrendasActivas() {
		return lstPrendasActivas;
	}

	public void setLstPrendasActivas(List<CatPrendaDTO> lstPrendasActivas) {
		this.lstPrendasActivas = lstPrendasActivas;
	}

	public DetEmpleadoDTO getEmpleadoSelected() {
		return empleadoSelected;
	}

	public void setEmpleadoSelected(DetEmpleadoDTO empleadoSelected) {
		this.empleadoSelected = empleadoSelected;
	}

	public CatPrendaDAO getUniformesDAO() {
		return uniformesDAO;
	}

	public void setUniformesDAO(CatPrendaDAO uniformesDAO) {
		this.uniformesDAO = uniformesDAO;
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

	public CatPrendaDTO getPrendaSelected() {
		return prendaSelected;
	}

	public void setPrendaSelected(CatPrendaDTO prendaSelected) {
		this.prendaSelected = prendaSelected;
	}

	public List<CatTallaDTO> getLstTallasActivas() {
		return lstTallasActivas;
	}

	public void setLstTallasActivas(List<CatTallaDTO> lstTallasActivas) {
		this.lstTallasActivas = lstTallasActivas;
	}

	public CatTallaDTO getTallaSelected() {
		return tallaSelected;
	}

	public void setTallaSelected(CatTallaDTO tallaSelected) {
		this.tallaSelected = tallaSelected;
	}

	public List<Integer> getLstCantidad() {
		return lstCantidad;
	}

	public void setLstCantidad(List<Integer> lstCantidad) {
		this.lstCantidad = lstCantidad;
	}

	public Integer getCantidadSelected() {
		return cantidadSelected;
	}

	public void setCantidadSelected(Integer cantidadSelected) {
		this.cantidadSelected = cantidadSelected;
	}

	public List<DetSolicitudPrendaDTO> getLstSolicitudPrendas() {
		return lstSolicitudPrendas;
	}

	public void setLstSolicitudPrendas(List<DetSolicitudPrendaDTO> lstSolicitudPrendas) {
		this.lstSolicitudPrendas = lstSolicitudPrendas;
	}

	
}
