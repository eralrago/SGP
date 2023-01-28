package mx.com.ferbo.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Gabo
 */
public class DetSolicitudPermisoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idSolicitud;
    private Date fechaCap;
    private Date fechaMod;
    private Date fechaInicio;
    private Date fechaFin;
    private Short aprobada;
    private DetEmpleadoDTO empleadoSol;
    private DetEmpleadoDTO empleadoRev;

    public DetSolicitudPermisoDTO() {
    }
    
    public DetSolicitudPermisoDTO(Integer idSolicitud, Date fechaCap, Date fechaMod, Date fechaInicio, Date fechaFin, Short aprobada) {
        this.idSolicitud = idSolicitud;
        this.fechaCap = fechaCap;
        this.fechaMod = fechaMod;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.aprobada = aprobada;
    }

    public Integer getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Date getFechaCap() {
        return fechaCap;
    }

    public void setFechaCap(Date fechaCap) {
        this.fechaCap = fechaCap;
    }

    public Date getFechaMod() {
        return fechaMod;
    }

    public void setFechaMod(Date fechaMod) {
        this.fechaMod = fechaMod;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Short getAprobada() {
        return aprobada;
    }

    public void setAprobada(Short aprobada) {
        this.aprobada = aprobada;
    }

    public DetEmpleadoDTO getEmpleadoSol() {
        return empleadoSol;
    }

    public void setEmpleadoSol(DetEmpleadoDTO empleadoSol) {
        this.empleadoSol = empleadoSol;
    }

    public DetEmpleadoDTO getEmpleadoRev() {
        return empleadoRev;
    }

    public void setEmpleadoRev(DetEmpleadoDTO empleadoRev) {
        this.empleadoRev = empleadoRev;
    }

}
