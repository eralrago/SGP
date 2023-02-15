package mx.com.ferbo.dto;

import java.io.Serializable;

/**
 *
 * @author Gabriel
 */
public class DetIncidenciaDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idIncidencia;
    private Integer idEmpleado;
    private Short visible;
    private CatEstatusIncidenciaDTO catEstatusIncidenciaDTO;
    private CatTipoIncidenciaDTO catTipoIncidenciaDTO;
    private DetSolicitudArticuloDTO detSolicitudArticuloDTO;
    private DetSolicitudPermisoDTO detSolicitudPermisoDTO;
    private DetSolicitudPrendaDTO detSolicitudPrendaDTO;

    public DetIncidenciaDTO() {
    }

    public Integer getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(Integer idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Short getVisible() {
        return visible;
    }

    public void setVisible(Short visible) {
        this.visible = visible;
    }

    public CatEstatusIncidenciaDTO getCatEstatusIncidenciaDTO() {
        return catEstatusIncidenciaDTO;
    }

    public void setCatEstatusIncidenciaDTO(CatEstatusIncidenciaDTO catEstatusIncidenciaDTO) {
        this.catEstatusIncidenciaDTO = catEstatusIncidenciaDTO;
    }

    public CatTipoIncidenciaDTO getCatTipoIncidenciaDTO() {
        return catTipoIncidenciaDTO;
    }

    public void setCatTipoIncidenciaDTO(CatTipoIncidenciaDTO catTipoIncidenciaDTO) {
        this.catTipoIncidenciaDTO = catTipoIncidenciaDTO;
    }

    public DetSolicitudArticuloDTO getDetSolicitudArticuloDTO() {
        return detSolicitudArticuloDTO;
    }

    public void setDetSolicitudArticuloDTO(DetSolicitudArticuloDTO detSolicitudArticuloDTO) {
        this.detSolicitudArticuloDTO = detSolicitudArticuloDTO;
    }

    public DetSolicitudPermisoDTO getDetSolicitudPermisoDTO() {
        return detSolicitudPermisoDTO;
    }

    public void setDetSolicitudPermisoDTO(DetSolicitudPermisoDTO detSolicitudPermisoDTO) {
        this.detSolicitudPermisoDTO = detSolicitudPermisoDTO;
    }

    public DetSolicitudPrendaDTO getDetSolicitudPrendaDTO() {
        return detSolicitudPrendaDTO;
    }

    public void setDetSolicitudPrendaDTO(DetSolicitudPrendaDTO detSolicitudPrendaDTO) {
        this.detSolicitudPrendaDTO = detSolicitudPrendaDTO;
    }
    
    
    
}
