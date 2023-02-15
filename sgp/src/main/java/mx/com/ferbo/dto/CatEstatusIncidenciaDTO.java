package mx.com.ferbo.dto;

import java.io.Serializable;

/**
 *
 * @author Gabriel
 */
public class CatEstatusIncidenciaDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idEstatus;
    private String descripcion;
    private short activo;

    public CatEstatusIncidenciaDTO() {
    }

    public Integer getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Integer idEstatus) {
        this.idEstatus = idEstatus;
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
