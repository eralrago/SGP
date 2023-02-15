package mx.com.ferbo.dto;

import java.io.Serializable;

/**
 *
 * @author Gabriel
 */
public class CatTipoIncidenciaDTO implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer idTipo;
    private String descripcion;
    
    public CatTipoIncidenciaDTO(){
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
