package mx.com.ferbo.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
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
import mx.com.ferbo.util.SGPException;


@Named(value = "uniformesBean")
@ViewScoped
public class UniformesBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String numeroEmpl;

	private List<CatPrendaDTO> lstPrendasActivas;
	private List<CatTallaDTO> lstTallasActivas;
	private List<Integer> lstCantidad;
	private List<DetSolicitudPrendaDTO> lstSolicitudPrendas;
	
	private Hashtable<String, String> prendasTallasCantidad;
	
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
		// solicitud = new DetSolicitudPrendaDTO();
		
		uniformesDAO = new CatPrendaDAO();
		tallaDAO = new CatTallaDAO();
		empleadoDAO = new EmpleadoDAO();
		detSolicitudPrendaDAO = new DetSolicitudPrendaDAO();

		faceContext = FacesContext.getCurrentInstance();
		httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        this.empleadoSelected = (DetEmpleadoDTO) httpServletRequest.getSession(true).getAttribute("empleado");
        
        solicitud = new DetSolicitudPrendaDTO();
	}
	
	@PostConstruct
    public void init() {
		lstSolicitudPrendas = new ArrayList<>();
		lstPrendasActivas = uniformesDAO.buscarActivo();
		lstTallasActivas = tallaDAO.buscarActivo();
	}
	
	public void preRegistro() {
		solicitud.setFechaCap(new Date());
		solicitud.setIdEmpleadoSol(empleadoSelected.getIdEmpleado());
		
		Optional<DetSolicitudPrendaDTO> elementoEncontrado = lstSolicitudPrendas.stream().filter(sol->sol.getPrenda().getIdPrenda() == solicitud.getPrenda().getIdPrenda()).findAny();
		if (!elementoEncontrado.isPresent()) {
			lstSolicitudPrendas.add(solicitud);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Uniforme Registrado"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Ya ha agregado esa prenda a su solicitud."));
		}
		PrimeFaces.current().ajax().update("form:messages", "form:dt-uniformes", "form:uniformeDialog");
		
		solicitud = new DetSolicitudPrendaDTO();
	}
	
	public void registro () throws IOException {
		for (DetSolicitudPrendaDTO detSolicitudPrendaDTO : lstSolicitudPrendas) {
			try {
				detSolicitudPrendaDAO.guardar(detSolicitudPrendaDTO);
			} catch (SGPException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Solicitud Registrada"));
		FacesContext.getCurrentInstance().getExternalContext().redirect("uniformes.xhtml");
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

	public Hashtable<String, String> getPrendasTallasCantidad() {
		return prendasTallasCantidad;
	}

	public void setPrendasTallasCantidad(Hashtable<String, String> prendasTallasCantidad) {
		this.prendasTallasCantidad = prendasTallasCantidad;
	}

	public DetSolicitudPrendaDTO getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(DetSolicitudPrendaDTO solicitud) {
		this.solicitud = solicitud;
	}

	
}
