package mx.com.ferbo.dto;

import java.io.Serializable;

public class CatPrendaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer idPrenda;    
    private String descripcion;    
    private short activo;
    
	public CatPrendaDTO() {
	}

	public CatPrendaDTO(Integer idPrenda, String descripcion, short activo) {
		this.idPrenda = idPrenda;
		this.descripcion = descripcion;
		this.activo = activo;
	}

	public Integer getIdPrenda() {
		return idPrenda;
	}

	public void setIdPrenda(Integer idPrenda) {
		this.idPrenda = idPrenda;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public short getActivo() {
		return activo;
	}

	public void setActivo(short activo) {
		this.activo = activo;
	}

    
}
