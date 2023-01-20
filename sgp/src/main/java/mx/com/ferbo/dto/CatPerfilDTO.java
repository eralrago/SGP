package mx.com.ferbo.dto;

import mx.com.ferbo.model.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Gabo
 */
public class CatPerfilDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idPerfil;
    private String descripcion;
    private Short activo;

    public CatPerfilDTO() {
    }

    public CatPerfilDTO(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public CatPerfilDTO(Integer idPerfil, String descripcion, Short activo) {
        this.idPerfil = idPerfil;
        this.descripcion = descripcion;
        this.activo = activo;
    }
    

    public Integer getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Short getActivo() {
        return activo;
    }

    public void setActivo(Short activo) {
        this.activo = activo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPerfil != null ? idPerfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatPerfil)) {
            return false;
        }
        CatPerfilDTO other = (CatPerfilDTO) object;
        if ((this.idPerfil == null && other.idPerfil != null) || (this.idPerfil != null && !this.idPerfil.equals(other.idPerfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.com.ferbo.model.CatPerfil[ idPerfil=" + idPerfil + " ]";
    }

}
