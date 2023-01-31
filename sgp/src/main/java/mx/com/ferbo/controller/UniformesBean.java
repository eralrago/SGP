package mx.com.ferbo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import mx.com.ferbo.dao.CatPrendaDAO;
import mx.com.ferbo.dao.EmpleadoDAO;
import mx.com.ferbo.dto.CatPrendaDTO;
import mx.com.ferbo.dto.DetEmpleadoDTO;
import mx.com.ferbo.model.CatPrenda;


@Named(value = "uniformes")
@ViewScoped
public class UniformesBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String numeroEmpl;

	private List<CatPrendaDTO> prendas;
	
	private DetEmpleadoDTO empleadoSelected;
	private CatPrendaDTO selectedPrenda;
	
	private List<CatPrendaDTO> selectedPrendas;
	
	private CatPrendaDAO uniformesDAO;
	private final EmpleadoDAO empleadoDAO;
	
	public UniformesBean() {
		empleadoSelected = new DetEmpleadoDTO();
		prendas = new ArrayList<>();
		selectedPrenda = new CatPrendaDTO();
		selectedPrendas = new ArrayList<>();
		uniformesDAO = new CatPrendaDAO();
		empleadoDAO = new EmpleadoDAO();
		setNumeroEmpl("0006");
	}
	
	@PostConstruct
    public void init() {
		empleadoSelected = empleadoDAO.buscarPorNumEmpl(getNumeroEmpl());
		prendas = uniformesDAO.buscarActivo();
	}

	public List<CatPrendaDTO> getPrendas() {
		return prendas;
	}

	public void setPrendas(List<CatPrendaDTO> prendas) {
		this.prendas = prendas;
	}

	public CatPrendaDTO getSelectedPrenda() {
		return selectedPrenda;
	}

	public void setSelectedPrenda(CatPrendaDTO selectedPrenda) {
		this.selectedPrenda = selectedPrenda;
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
	
	
}
