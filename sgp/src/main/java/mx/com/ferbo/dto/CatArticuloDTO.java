package mx.com.ferbo.dto;

import java.io.Serializable;

public class CatArticuloDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idArticulo;
    private String descripcion;
    private short activo;

    public CatArticuloDTO() {
    }

    public CatArticuloDTO(Integer idArticulo, String descripcion, short activo) {
		super();
		this.idArticulo = idArticulo;
		this.descripcion = descripcion;
		this.activo = activo;
	}

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
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
