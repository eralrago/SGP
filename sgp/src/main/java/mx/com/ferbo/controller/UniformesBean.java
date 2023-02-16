package mx.com.ferbo.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import mx.com.ferbo.dao.CatPrendaDAO;
import mx.com.ferbo.dao.DetSolicitudPrendaDAO;
import mx.com.ferbo.dao.EmpleadoDAO;
import mx.com.ferbo.dto.CatPrendaDTO;
import mx.com.ferbo.dto.DetEmpleadoDTO;
import mx.com.ferbo.dto.DetSolicitudPrendaDTO;
import mx.com.ferbo.model.CatPrenda;
import mx.com.ferbo.util.SGPException;


@Named(value = "uniformesBean")
@ViewScoped
public class UniformesBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String numeroEmpl;

	private List<CatPrendaDTO> lstPrendasActivas;
	private List<CatPrendaDTO> selectedPrendas;
	
	private DetEmpleadoDTO empleadoSelected;
	private DetSolicitudPrendaDTO solicitud;
	
	private CatPrendaDAO uniformesDAO;
	private final EmpleadoDAO empleadoDAO;
	private DetSolicitudPrendaDAO detSolicitudPrendaDAO;
	
	private CatPrendaDTO prendaSelected;
	
	public UniformesBean() {
		empleadoSelected = new DetEmpleadoDTO();
		lstPrendasActivas = new ArrayList<>();
		selectedPrendas = new ArrayList<>();
		solicitud = new DetSolicitudPrendaDTO();
		uniformesDAO = new CatPrendaDAO();
		empleadoDAO = new EmpleadoDAO();
		detSolicitudPrendaDAO = new DetSolicitudPrendaDAO();
		// setNumeroEmpl("0006");
		prendaSelected = new CatPrendaDTO();
	}
	
	@PostConstruct
    public void init() {
		empleadoSelected = empleadoDAO.buscarPorNumEmpl(getNumeroEmpl());
		lstPrendasActivas = uniformesDAO.buscarActivo();
	}
	
	public String getDeleteButtonMessage() {
		if (hasSelectedPrendas()) {
            int size = this.selectedPrendas.size();
            return size > 1 ? size + " Prendas Seleccionada" : "1 Prenda Seleccionada";
        }
        return "Agregar";
	}

	public boolean hasSelectedPrendas() {
        return this.selectedPrendas != null && !this.selectedPrendas.isEmpty();
    }
	
	public void registro() {
		for(CatPrendaDTO prenda : selectedPrendas) {
//			System.out.println(prenda.getDescripcion());
			solicitud.setIdPrenda(prenda.getIdPrenda());
			solicitud.setIdEmpleadoSol(empleadoSelected.getIdEmpleado());
			solicitud.setCantidad(1);
			solicitud.setAprobada((short) 0);
			solicitud.setFechaCap(new Date());
			solicitud.setFechaMod(null);
			solicitud.setIdEmpleadoRev(null);
			try {
				detSolicitudPrendaDAO.guardar(solicitud);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro exitoso."));
				FacesContext.getCurrentInstance().getExternalContext().redirect("uniformes.xhtml");
			} catch (SGPException | IOException e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al Registrar la Solicitud"));
			}
			
		}
	}

	public List<CatPrendaDTO> getLstPrendasActivas() {
		return lstPrendasActivas;
	}

	public void setLstPrendasActivas(List<CatPrendaDTO> lstPrendasActivas) {
		this.lstPrendasActivas = lstPrendasActivas;
	}

	public List<CatPrendaDTO> getSelectedPrendas() {
		return selectedPrendas;
	}

	public void setSelectedPrendas(List<CatPrendaDTO> selectedPrendas) {
		this.selectedPrendas = selectedPrendas;
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

	
}
