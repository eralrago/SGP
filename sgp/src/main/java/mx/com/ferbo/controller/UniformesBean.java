package mx.com.ferbo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
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
		prendasTallasCantidad = new Hashtable<String, String>();
		
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
	}
	
	@PostConstruct
    public void init() {
		lstSolicitudPrendas = new ArrayList<>();
		lstPrendasActivas = uniformesDAO.buscarActivo();
		lstTallasActivas = tallaDAO.buscarActivo();
	}
	
	public void preRegistro() {
		solicitud = new DetSolicitudPrendaDTO();
		
		this.prendaSelected = uniformesDAO.buscarPorId(prendaSelected.getIdPrenda());
		this.tallaSelected = tallaDAO.buscarPorId(tallaSelected.getIdTalla());
		
		solicitud.setPrenda(prendaSelected);
		solicitud.setTalla(tallaSelected);
		solicitud.setCantidad(cantidadSelected);
		solicitud.setFechaCap(new Date());
		solicitud.setAprobada((short)0);
		solicitud.setIdEmpleadoSol(empleadoSelected.getIdEmpleado());
		
//		if (lstSolicitudPrendas.isEmpty()) {
//			if (lstSolicitudPrendas.contains(prendaSelected.getDescripcion())) {
//				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Ya ha agregado esa prenda a su solicitud."));
//		        PrimeFaces.current().ajax().update("form:messages", "form:dt-uniformes");
//			} if (lstSolicitudPrendas.contains(prendaSelected.getDescripcion()) && lstSolicitudPrendas.contains(cantidadSelected)) {
//				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Ha sobrepasado la cantidad de esa prenda."));
//		        PrimeFaces.current().ajax().update("form:messages", "form:dt-uniformes");
//			} else {
//				lstSolicitudPrendas.add(solicitud);
//			}
//		} else {
//			if (lstSolicitudPrendas.contains(prendaSelected.getDescripcion())) {
//				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Ya ha agregado esa prenda a su solicitud."));
//		        PrimeFaces.current().ajax().update("form:messages", "form:dt-uniformes");
//			} else {
//				lstSolicitudPrendas.add(solicitud);
//			}
//		}

		// lstSolicitudPrendas.add(solicitud);
		if (lstSolicitudPrendas.isEmpty()) {
			lstSolicitudPrendas.add(solicitud);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Uniforme Registrado"));
	        PrimeFaces.current().ajax().update("form:messages", "form:dt-uniformes");
		} else {
			for(DetSolicitudPrendaDTO detSolicitudPrendaDTO : lstSolicitudPrendas) {
				if(detSolicitudPrendaDTO.getPrenda().getDescripcion().equals(prendaSelected.getDescripcion())) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Ya ha agregado esa prenda a su solicitud."));
			        PrimeFaces.current().ajax().update("form:messages", "form:dt-uniformes");
			        break;
				} else {
					lstSolicitudPrendas.add(solicitud);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Uniforme Registrado"));
			        PrimeFaces.current().ajax().update("form:messages", "form:dt-uniformes");
			        break;
				}
			}
		}
		
		
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

	
}
