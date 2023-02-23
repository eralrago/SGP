package mx.com.ferbo.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


public class DetSolicitudArticuloDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idSolicitud;
    private int cantidad;
    private Short aprobada;
    private Date fechaCap;
    private Date fechaMod;
    private CatArticuloDTO articulo;
    private Integer idEmpleadoSol;
    private Integer idEmpleadoRev;

    public DetSolicitudArticuloDTO() {
    	articulo = new CatArticuloDTO();
    }

    public DetSolicitudArticuloDTO(Integer idSolicitud, int cantidad, Short aprobada, Date fechaCap, Date fechaMod,
			CatArticuloDTO articulo, Integer idEmpleadoSol, Integer idEmpleadoRev) {
		super();
		this.idSolicitud = idSolicitud;
		this.cantidad = cantidad;
		this.aprobada = aprobada;
		this.fechaCap = fechaCap;
		this.fechaMod = fechaMod;
		this.articulo = articulo;
		this.idEmpleadoSol = idEmpleadoSol;
		this.idEmpleadoRev = idEmpleadoRev;
	}

	public Integer getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(Integer idSolicitud) {
		this.idSolicitud = idSolicitud;
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

	public CatArticuloDTO getArticulo() {
		return articulo;
	}

	public void setArticulo(CatArticuloDTO articulo) {
		this.articulo = articulo;
	}

	public Integer getIdEmpleadoSol() {
		return idEmpleadoSol;
	}

	public void setIdEmpleadoSol(Integer idEmpleadoSol) {
		this.idEmpleadoSol = idEmpleadoSol;
	}

	public Integer getIdEmpleadoRev() {
		return idEmpleadoRev;
	}

	public void setIdEmpleadoRev(Integer idEmpleadoRev) {
		this.idEmpleadoRev = idEmpleadoRev;
	}

}
