package mx.com.ferbo.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;

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
import mx.com.ferbo.util.SGPException;

@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    Logger log = LogManager.getRootLogger();
    private DetEmpleadoDTO empleadoSelected;
    private DetEmpleadoDTO detEmpleadoDTO;
    private List<DetEmpleadoDTO> lstEmpleados;

    private List<DetRegistroDTO> lstregistroEmpleados;
    private DetRegistroDTO registroEmpleado;

    private CatEstatusRegistroDTO catEstatusRegistro;

    private String numEmpleado;
    private String idEmpleado;
    private String strDiaHoy;
    private Integer contador;
    private Date diaHoy;
    private Date diaSistema;
    private Calendar cDiaHoy;
    private Calendar cDiaSistema;
    private SimpleDateFormat sDFormat;
    private SimpleDateFormat sDFormatHMS;

    private FacesContext faceContext;
    private HttpServletRequest httpServletRequest;
    private HttpSession session;

    private final EmpleadoDAO empleadoDAO;
    private final RegistroDAO registroDAO;

    public LoginBean() {
        empleadoDAO = new EmpleadoDAO();
        registroDAO = new RegistroDAO();
        empleadoSelected = new DetEmpleadoDTO();
        lstEmpleados = new ArrayList<>();
        detEmpleadoDTO = new DetEmpleadoDTO();
        lstregistroEmpleados = new ArrayList<>();
        registroEmpleado = new DetRegistroDTO();
        catEstatusRegistro = new CatEstatusRegistroDTO();
        diaHoy = new Date();
        cDiaHoy = Calendar.getInstance(TimeZone.getTimeZone("America/Mexico_City"));
        cDiaSistema = Calendar.getInstance(TimeZone.getTimeZone("America/Mexico_City"));
        sDFormat = new SimpleDateFormat("yyyy-MM-dd");
        sDFormatHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        strDiaHoy = sDFormat.format(diaHoy) + "%";
        diaSistema = new Date();
    }

    @PostConstruct
    public void init() {
        contador = 0;
    }

    /**
     * Realizar consulta en DAO por numero Empleado
     *
     * @throws IOException
     */
    public void login() throws IOException {
        cDiaHoy.setTime(diaHoy);
        diaHoy = cDiaHoy.getTime();

        empleadoSelected = empleadoDAO.buscarPorNumEmpl(numEmpleado);
        lstregistroEmpleados = registroDAO.buscarPorIdFechaEntrada(empleadoSelected.getIdEmpleado(), strDiaHoy);

        String registro = (lstregistroEmpleados.isEmpty()) ? "Entrada" : "Salida";
        if (contador <= 3) {
            if (empleadoSelected != null) {
                /* Código para saber si llega a tiempo */
                cDiaSistema.setTime(diaSistema);
                String strDiaSistema = sDFormatHMS.format(diaHoy);
                cDiaHoy.setTime(diaSistema);
                /* Declarando la Hora y Minutos para retardos */
                cDiaHoy.set(Calendar.HOUR_OF_DAY, 7);
                cDiaHoy.set(Calendar.MINUTE, 10);
                Date diaHoyActual = cDiaHoy.getTime();
                String strDiaHoyActual = sDFormatHMS.format(diaHoyActual);
                int result = strDiaSistema.compareTo(strDiaHoyActual);

                switch (registro) {
                    case "Entrada":
                        registroEmpleado.setDetEmpleadoDTO(empleadoSelected);
                        registroEmpleado.setFechaEntrada(new Date());
                        registroEmpleado.setFechaSalida(null);
                        if (result > 0) {
                            catEstatusRegistro.setIdEstatus(2);
                        } else {
                            catEstatusRegistro.setIdEstatus(1);
                        }
                        registroEmpleado.setCatEstatusRegistroDTO(catEstatusRegistro);
                        try {
                            registroDAO.guardar(registroEmpleado);

                            //en caso de que todas las validaciones se encuentren correctas, se procederá a registrar
                            //el usuario en sesión y redirigir a la página de bienvenida.
                            httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                            httpServletRequest.getSession(true).setAttribute("empleado", empleadoSelected);
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Acceso correcto", null));
                            FacesContext.getCurrentInstance().getExternalContext().redirect("protected/registroAsistencia.xhtml");
                        } catch (SGPException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        break;
                    case "Salida":
                        try {
                            registroEmpleado = lstregistroEmpleados.get(lstregistroEmpleados.size() - 1);
                            registroEmpleado.setDetEmpleadoDTO(lstregistroEmpleados.get(lstregistroEmpleados.size() - 1).getDetEmpleadoDTO());                            lstregistroEmpleados.get(lstregistroEmpleados.size() - 1);
                            registroEmpleado.setFechaSalida(new Date());
                            registroDAO.actualizar(registroEmpleado);
                            httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                            httpServletRequest.getSession(true).setAttribute("empleado", empleadoSelected);
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Acceso correcto", null));
                            FacesContext.getCurrentInstance().getExternalContext().redirect("protected/registroAsistencia.xhtml");
                        } catch (SGPException ex) {
                            // TODO Auto-generated catch block
                            ex.printStackTrace();
                        }
                        break;

                    default:
                        break;
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

    public void logout() {
        DetEmpleadoDTO empleado = null;

        try {
            empleado = (DetEmpleadoDTO) session.getAttribute("usuario");
            // log.info("El usuario intenta finalizar su sesión: " + empleado.getUsuario());
            session.setAttribute("usuario", null);
            session.setAttribute("idCliente", null);
            session.invalidate();
            faceContext.getExternalContext().redirect("login.xhtml");
        } catch (Exception ex) {
            log.error("Problema en el cierre de sesión del usuario...", ex);
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

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Date getDiaHoy() {
        return diaHoy;
    }

    public void setDiaHoy(Date diaHoy) {
        this.diaHoy = diaHoy;
    }

    public List<DetRegistroDTO> getLstregistroEmpleados() {
        return lstregistroEmpleados;
    }

    public void setLstregistroEmpleados(List<DetRegistroDTO> lstregistroEmpleados) {
        this.lstregistroEmpleados = lstregistroEmpleados;
    }

    public DetRegistroDTO getRegistroEmpleado() {
        return registroEmpleado;
    }

    public void setRegistroEmpleado(DetRegistroDTO registroEmpleado) {
        this.registroEmpleado = registroEmpleado;
    }

}
