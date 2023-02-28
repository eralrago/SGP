package mx.com.ferbo.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.PrimeFaces;

import mx.com.ferbo.dao.CatArticulosDAO;
import mx.com.ferbo.dao.DetSolicitudArticulosDAO;
import mx.com.ferbo.dao.EmpleadoDAO;
import mx.com.ferbo.dto.CatArticuloDTO;
import mx.com.ferbo.dto.DetEmpleadoDTO;
import mx.com.ferbo.dto.DetSolicitudArticuloDTO;
import mx.com.ferbo.util.SGPException;


@Named(value = "articuloOficinasBean")
@ViewScoped
public class ArticulosOficinaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String numeroEmpl;

	private List<CatArticuloDTO> lstArticulosActivas;
	private List<Integer> lstCantidad;
	private List<DetSolicitudArticuloDTO> lstSolicitudArticulos;
	
	private DetEmpleadoDTO empleadoSelected;
	private DetSolicitudArticuloDTO solicitud;
	
	private CatArticulosDAO articulosDAO;
	private final EmpleadoDAO empleadoDAO;
	private DetSolicitudArticulosDAO detSolicitudArticulosDAO;
	
	private CatArticuloDTO ArticuloSelected;
	private Integer cantidadSelected;
	
	private FacesContext faceContext;
	private HttpServletRequest httpServletRequest;
	
	public ArticulosOficinaBean() {
		lstArticulosActivas = new ArrayList<>();
		lstCantidad = new ArrayList<>(
	            Arrays.asList(1, 2, 3));
		
		empleadoSelected = new DetEmpleadoDTO();
		ArticuloSelected = new CatArticuloDTO();
		cantidadSelected = 0;
		
		articulosDAO = new CatArticulosDAO();
		empleadoDAO = new EmpleadoDAO();
		detSolicitudArticulosDAO = new DetSolicitudArticulosDAO();

		faceContext = FacesContext.getCurrentInstance();
		httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        this.empleadoSelected = (DetEmpleadoDTO) httpServletRequest.getSession(true).getAttribute("empleado");
        
        solicitud = new DetSolicitudArticuloDTO();
	}
	
	@PostConstruct
    public void init() {
		lstSolicitudArticulos = new ArrayList<>();
		lstArticulosActivas = articulosDAO.buscarActivo();
	}
	
	public void preRegistro() {
		solicitud.setFechaCap(new Date());
		solicitud.setIdEmpleadoSol(empleadoSelected.getIdEmpleado());
		
//		Optional<DetSolicitudArticuloDTO> elementoEncontrado = lstSolicitudArticulos.stream().filter(sol->sol.getArticulo().getIdArticulo() == solicitud.getArticulo().getIdArticulo()).findAny();
//		if (!elementoEncontrado.isPresent()) {
//			lstSolicitudArticulos.add(solicitud);
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Uniforme Registrado"));
//		} else {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Ya ha agregado esa Articulo a su solicitud."));
//		}
		lstSolicitudArticulos.add(solicitud);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Articulo Registrado"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-articuloOficinas", "form:articuloOficinaDialog");
		solicitud = new DetSolicitudArticuloDTO();
	}
	
	public void registro () throws IOException {
		for (DetSolicitudArticuloDTO detSolicitudArticuloDTO : lstSolicitudArticulos) {
			try {
				detSolicitudArticulosDAO.guardar(detSolicitudArticuloDTO);
			} catch (SGPException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		FacesContext.getCurrentInstance().getExternalContext().redirect("articulosTrabajo.xhtml");
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Solicitud Registrada"));
	}

	public List<CatArticuloDTO> getLstArticulosActivas() {
		return lstArticulosActivas;
	}

	public void setLstArticulosActivas(List<CatArticuloDTO> lstArticulosActivas) {
		this.lstArticulosActivas = lstArticulosActivas;
	}

	public DetEmpleadoDTO getEmpleadoSelected() {
		return empleadoSelected;
	}

	public void setEmpleadoSelected(DetEmpleadoDTO empleadoSelected) {
		this.empleadoSelected = empleadoSelected;
	}

	public CatArticulosDAO getUniformesDAO() {
		return articulosDAO;
	}

	public void setUniformesDAO(CatArticulosDAO uniformesDAO) {
		this.articulosDAO = uniformesDAO;
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

	public CatArticuloDTO getArticuloSelected() {
		return ArticuloSelected;
	}

	public void setArticuloSelected(CatArticuloDTO ArticuloSelected) {
		this.ArticuloSelected = ArticuloSelected;
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

	public List<DetSolicitudArticuloDTO> getLstSolicitudArticulos() {
		return lstSolicitudArticulos;
	}

	public void setLstSolicitudArticulos(List<DetSolicitudArticuloDTO> lstSolicitudArticulos) {
		this.lstSolicitudArticulos = lstSolicitudArticulos;
	}

	public DetSolicitudArticuloDTO getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(DetSolicitudArticuloDTO solicitud) {
		this.solicitud = solicitud;
	}
	
}
