package mx.com.ferbo.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@Named(value = "mbLogin")
@SessionScoped
public class Login implements Serializable {
	
	private Integer contador;
	private Integer id;
	private FacesContext faceContext;
    private HttpServletRequest httpServletRequest;
	
	Logger log = LogManager.getRootLogger();
	
	@PostConstruct
    public void init() {
    	contador = 0;
    }
	
	public void login() throws IOException {
		FacesMessage message = null;
		if (contador <= 3) {
//        	IClienteContactoManager clienteContactoManager = new ClienteContactoManager();
//        	cliente = clienteContactoManager.getCliente(clienteContacto);
//            if (cliente != null) {
			if (id == 0000) {
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
            }
        } else {
        	message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Contacte al Administrador.", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
}
