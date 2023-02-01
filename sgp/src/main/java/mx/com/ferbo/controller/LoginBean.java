package mx.com.ferbo.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import mx.com.ferbo.dao.EmpleadoDAO;
import mx.com.ferbo.dao.RegistroDAO;
import mx.com.ferbo.dto.CatEstatusRegistroDTO;
import mx.com.ferbo.dto.DetEmpleadoDTO;
import mx.com.ferbo.dto.DetRegistroDTO;
import mx.com.ferbo.dto.CatEstatusRegistroDTO;
import mx.com.ferbo.model.DetEmpleado;
import mx.com.ferbo.util.SGPException;

@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {
	
	Logger log = LogManager.getRootLogger();
	private DetEmpleadoDTO empleadoSelected;
	private DetEmpleadoDTO detEmpleadoDTO;
	private DetRegistroDTO registroEmpleado;
	private CatEstatusRegistroDTO catEstatusRegistro;
	private String numEmpleado;
	
	private List<DetEmpleadoDTO> lstEmpleados;
	
	private Integer contador;
	private FacesContext faceContext;
    private HttpServletRequest httpServletRequest;
	
	private final EmpleadoDAO empleadoDAO;
	private final RegistroDAO registroDAO;
	
	public LoginBean() {
		empleadoDAO = new EmpleadoDAO();
		registroDAO = new RegistroDAO();
        empleadoSelected = new DetEmpleadoDTO();
        lstEmpleados = new ArrayList<>();
        detEmpleadoDTO = new DetEmpleadoDTO();
        registroEmpleado = new DetRegistroDTO();
        catEstatusRegistro = new CatEstatusRegistroDTO();
	}

	@PostConstruct
    public void init() {
    	contador = 0;
    	consultaEmpleados();
    }
	
	/*
     * Método para consultar a los empleados
     */
    private void consultaEmpleados() {
        lstEmpleados = empleadoDAO.buscarActivo();
    }
	
    /**
     * Realizar consulta en DAO por numero Empleado
     * @throws IOException
     */
	public void login() throws IOException {
		empleadoSelected = empleadoDAO.buscarPorNumEmpl(numEmpleado);
		if (contador <= 3) {
			if(empleadoSelected != null) {
				registroEmpleado.setDetEmpleadoDTO(empleadoSelected);
		        registroEmpleado.setFechaEntrada(new Date());
		        registroEmpleado.setFechaSalida(null);
		        catEstatusRegistro.setIdEstatus(1);
		        registroEmpleado.setCatEstatusRegistroDTO(catEstatusRegistro);;
		        try {
					registroDAO.guardar(registroEmpleado);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Acceso correcto", null));
			        FacesContext.getCurrentInstance().getExternalContext().redirect(new BienvenidaBean().empleadoLogeado());
				} catch (SGPException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            } else {
            	empleadoSelected = new DetEmpleadoDTO();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Verifique su usuario."));
                contador++;
			}
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Contacte al Administrador."));
        }
	}

	public DetEmpleadoDTO getEmpleadoSelected() {
		return empleadoSelected;
	}

	public void setEmpleadoSelected(DetEmpleadoDTO empleadoSelected) {
		this.empleadoSelected = empleadoSelected;
	}

	public DetEmpleadoDTO getDetEmpleadoDTO() {
		return detEmpleadoDTO;
	}

	public void setDetEmpleadoDTO(DetEmpleadoDTO detEmpleadoDTO) {
		this.detEmpleadoDTO = detEmpleadoDTO;
	}

	public String getNumEmpleado() {
		return numEmpleado;
	}

	public void setNumEmpleado(String numEmpleado) {
		this.numEmpleado = numEmpleado;
	}

	public List<DetEmpleadoDTO> getLstEmpleados() {
		return lstEmpleados;
	}

	public void setLstEmpleados(List<DetEmpleadoDTO> lstEmpleados) {
		this.lstEmpleados = lstEmpleados;
	}

	public EmpleadoDAO getEmpleadoDAO() {
		return empleadoDAO;
	}

	public CatEstatusRegistroDTO getCatEstatusRegistro() {
		return catEstatusRegistro;
	}

	public void setCatEstatusRegistro(CatEstatusRegistroDTO catEstatusRegistro) {
		this.catEstatusRegistro = catEstatusRegistro;
	}

		
}
