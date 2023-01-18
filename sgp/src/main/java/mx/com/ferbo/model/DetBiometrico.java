/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.com.ferbo.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Gabo
 */
@Entity
@Table(name = "det_biometrico")
@NamedQueries({
    @NamedQuery(name = "DetBiometrico.findAll", query = "SELECT d FROM DetBiometrico d")})
public class DetBiometrico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_biometrico")
    private Integer idBiometrico;
    @Basic(optional = false)
    @Column(name = "fecha_captura")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCaptura;
    @Basic(optional = false)
    @Column(name = "activo")
    private short activo;
    @Basic(optional = false)
    @Lob
    @Column(name = "huella")
    private byte[] huella;
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    @ManyToOne(optional = false)
    private DetEmpleado idEmpleado;

    public DetBiometrico() {
    }

    public DetBiometrico(Integer idBiometrico) {
        this.idBiometrico = idBiometrico;
    }

    public DetBiometrico(Integer idBiometrico, Date fechaCaptura, short activo, byte[] huella) {
        this.idBiometrico = idBiometrico;
        this.fechaCaptura = fechaCaptura;
        this.activo = activo;
        this.huella = huella;
    }

    public Integer getIdBiometrico() {
        return idBiometrico;
    }

    public void setIdBiometrico(Integer idBiometrico) {
        this.idBiometrico = idBiometrico;
    }

    public Date getFechaCaptura() {
        return fechaCaptura;
    }

    public void setFechaCaptura(Date fechaCaptura) {
        this.fechaCaptura = fechaCaptura;
    }

    public short getActivo() {
        return activo;
    }

    public void setActivo(short activo) {
        this.activo = activo;
    }

    public byte[] getHuella() {
        return huella;
    }

    public void setHuella(byte[] huella) {
        this.huella = huella;
    }

    public DetEmpleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(DetEmpleado idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBiometrico != null ? idBiometrico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetBiometrico)) {
            return false;
        }
        DetBiometrico other = (DetBiometrico) object;
        if ((this.idBiometrico == null && other.idBiometrico != null) || (this.idBiometrico != null && !this.idBiometrico.equals(other.idBiometrico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.com.ferbo.model.DetBiometrico[ idBiometrico=" + idBiometrico + " ]";
    }
    
}
