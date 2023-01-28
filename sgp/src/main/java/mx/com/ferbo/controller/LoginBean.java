package mx.com.ferbo.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import mx.com.ferbo.dao.EmpleadoDAO;
import mx.com.ferbo.dto.DetEmpleadoDTO;
import mx.com.ferbo.model.DetEmpleado;

@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {
	
	Logger log = LogManager.getRootLogger();
	private DetEmpleadoDTO empleadoSelected;
	private DetEmpleadoDTO detEmpleadoDTO;
	private String numEmpleado;
	
	private List<DetEmpleadoDTO> lstEmpleados;
	
	private Integer contador;
	private FacesContext faceContext;
    private HttpServletRequest httpServletRequest;
	
	private final EmpleadoDAO empleadoDAO;
	
	public LoginBean() {
		empleadoDAO = new EmpleadoDAO();
        empleadoSelected = new DetEmpleadoDTO();
        lstEmpleados = new ArrayList<>();
        detEmpleadoDTO = new DetEmpleadoDTO();
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
	
	public void login() throws IOException {
		FacesMessage message = null;
		empleadoSelected.setNumEmpleado(numEmpleado);
		if (contador <= 3) {
			DetEmpleadoDTO x = lstEmpleados.stream().filter(s -> {return s.getNumEmpleado().equals(numEmpleado);}).findAny().orElse(new DetEmpleadoDTO());
			if(numEmpleado.equals(x.getNumEmpleado()) && x.getNumEmpleado() != null) {
				faceContext = FacesContext.getCurrentInstance();
                httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
//	                httpServletRequest.getSession(true).setAttribute("idCliente", cliente.getIdCliente());                
                log.info("Entrando al caso de exito");
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Acceso correcto", null);
                FacesContext.getCurrentInstance().addMessage(null, message);
                faceContext.getExternalContext().redirect("dashboard.xhtml");
            } else {
//	                cliente = new Cliente();
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Verifique su usuario.", null);
                FacesContext.getCurrentInstance().addMessage(null, message);
                contador++;
			}
			//}
			/*if(lstEmpleados.contains(message)) {
            	faceContext = FacesContext.getCurrentInstance();
                httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
//                httpServletRequest.getSession(true).setAttribute("idCliente", cliente.getIdCliente());                
                log.info("Entrando al caso de exito");
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Acceso correcto", null);
                FacesContext.getCurrentInstance().addMessage(null, message);
                faceContext.getExternalContext().redirect("dashboard.xhtml");
            } else {
//                cliente = new Cliente();
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Verifique su usuario.", null);
                FacesContext.getCurrentInstance().addMessage(null, message);
                contador++;
            }*/
        } else {
        	message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Contacte al Administrador.", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
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

		
}
