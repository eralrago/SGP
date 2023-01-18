/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.com.ferbo.model;

import java.io.Serializable;
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
import javax.persistence.Table;

/**
 *
 * @author Gabo
 */
@Entity
@Table(name = "det_incidencia")
@NamedQueries({
    @NamedQuery(name = "DetIncidencia.findAll", query = "SELECT d FROM DetIncidencia d")})
public class DetIncidencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_incidencia")
    private Integer idIncidencia;
    @Column(name = "id_empleado")
    private Integer idEmpleado;
    @Column(name = "visible")
    private Short visible;
    @JoinColumn(name = "id_estatus", referencedColumnName = "id_estatus")
    @ManyToOne
    private CatEstatusIncidencia idEstatus;
    @JoinColumn(name = "id_tipo", referencedColumnName = "id_tipo")
    @ManyToOne
    private CatTipoIncidencia idTipo;
    @JoinColumn(name = "id_sol_articulo", referencedColumnName = "id_solicitud")
    @ManyToOne
    private DetSolicitudArticulo idSolArticulo;
    @JoinColumn(name = "id_sol_permiso", referencedColumnName = "id_solicitud")
    @ManyToOne
    private DetSolicitudPermiso idSolPermiso;
    @JoinColumn(name = "id_sol_prenda", referencedColumnName = "id_solicitud")
    @ManyToOne
    private DetSolicitudPrenda idSolPrenda;

    public DetIncidencia() {
    }

    public DetIncidencia(Integer idIncidencia) {
        this.idIncidencia = idIncidencia;
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

    public CatEstatusIncidencia getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(CatEstatusIncidencia idEstatus) {
        this.idEstatus = idEstatus;
    }

    public CatTipoIncidencia getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(CatTipoIncidencia idTipo) {
        this.idTipo = idTipo;
    }

    public DetSolicitudArticulo getIdSolArticulo() {
        return idSolArticulo;
    }

    public void setIdSolArticulo(DetSolicitudArticulo idSolArticulo) {
        this.idSolArticulo = idSolArticulo;
    }

    public DetSolicitudPermiso getIdSolPermiso() {
        return idSolPermiso;
    }

    public void setIdSolPermiso(DetSolicitudPermiso idSolPermiso) {
        this.idSolPermiso = idSolPermiso;
    }

    public DetSolicitudPrenda getIdSolPrenda() {
        return idSolPrenda;
    }

    public void setIdSolPrenda(DetSolicitudPrenda idSolPrenda) {
        this.idSolPrenda = idSolPrenda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIncidencia != null ? idIncidencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetIncidencia)) {
            return false;
        }
        DetIncidencia other = (DetIncidencia) object;
        if ((this.idIncidencia == null && other.idIncidencia != null) || (this.idIncidencia != null && !this.idIncidencia.equals(other.idIncidencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.com.ferbo.model.DetIncidencia[ idIncidencia=" + idIncidencia + " ]";
    }
    
}
