package mx.com.ferbo.dto;

import java.io.Serializable;
import java.util.Date;

public class DetSolicitudPrendaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idSolicitud;
    private CatPrendaDTO prenda;
    private int cantidad;
    private Short aprobada;
    private Date fechaCap;
    private Date fechaMod;
    private DetEmpleadoDTO empleadoSol;
    private DetEmpleadoDTO empleadoRev;
    private CatTallaDTO talla;
    

    public DetSolicitudPrendaDTO() {
        talla = new CatTallaDTO();
        prenda = new CatPrendaDTO();
        empleadoSol = new DetEmpleadoDTO();
        empleadoRev = new DetEmpleadoDTO();
    }

    public DetSolicitudPrendaDTO(Integer idSolicitud, CatPrendaDTO prenda, int cantidad, Short aprobada, Date fechaCap,
            Date fechaMod, DetEmpleadoDTO empleadoSol, DetEmpleadoDTO empleadoRev, CatTallaDTO talla) {
        super();
        this.idSolicitud = idSolicitud;
        this.prenda = prenda;
        this.cantidad = cantidad;
        this.aprobada = aprobada;
        this.fechaCap = fechaCap;
        this.fechaMod = fechaMod;
        this.empleadoSol = empleadoSol;
        this.empleadoRev = empleadoRev;
        this.talla = talla;
    }

    public Integer getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public CatPrendaDTO getPrenda() {
        return prenda;
    }

    public void setPrenda(CatPrendaDTO prenda) {
        this.prenda = prenda;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Short getAprobada() {
        return aprobada;
    }

    public void setAprobada(Short aprobada) {
        this.aprobada = aprobada;
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

    public CatTallaDTO getTalla() {
        return talla;
    }

    public void setTalla(CatTallaDTO talla) {
        this.talla = talla;
    }

}
