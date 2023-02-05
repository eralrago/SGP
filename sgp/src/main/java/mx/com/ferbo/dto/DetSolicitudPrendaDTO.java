package mx.com.ferbo.dto;

import java.io.Serializable;
import java.util.Date;

import mx.com.ferbo.model.CatPrenda;
import mx.com.ferbo.model.DetEmpleado;

public class DetSolicitudPrendaDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idSolicitud;
	private Integer idPrenda;
	private int cantidad;
	private Short aprobada;
	private Date fechaCap;
	private Date fechaMod;
	private Integer idEmpleadoSol;
	private Integer idEmpleadoRev;
	
	public DetSolicitudPrendaDTO() {
		
	}

	public DetSolicitudPrendaDTO(Integer idSolicitud, Integer idPrenda, int cantidad, Short aprobada, Date fechaCap,
			Date fechaMod, Integer idEmpleadoSol, Integer idEmpleadoRev) {
		super();
		this.idSolicitud = idSolicitud;
		this.idPrenda = idPrenda;
		this.cantidad = cantidad;
		this.aprobada = aprobada;
		this.fechaCap = fechaCap;
		this.fechaMod = fechaMod;
		this.idEmpleadoSol = idEmpleadoSol;
		this.idEmpleadoRev = idEmpleadoRev;
	}

	public Integer getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(Integer idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public Integer getIdPrenda() {
		return idPrenda;
	}

	public void setIdPrenda(Integer idPrenda) {
		this.idPrenda = idPrenda;
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
