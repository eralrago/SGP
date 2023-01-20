package mx.com.ferbo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Gabo
 */
@Entity
@Table(name = "det_empleado")
public class DetEmpleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_empleado")
    private Integer idEmpleado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "num_empleado")
    private String numEmpleado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "primer_ap")
    private String primerAp;
    @Size(max = 45)
    @Column(name = "segundo_ap")
    private String segundoAp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Size(max = 18)
    @Column(name = "curp")
    private String curp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "rfc")
    private String rfc;
    @Size(max = 45)
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Size(max = 45)
    @Column(name = "nss")
    private String nss;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private short activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpleado")
    private List<BitacoraInventario> bitacoraInventarioList;
    @OneToMany(mappedBy = "idEmpleado")
    private List<BitacoraCatPerfil> bitacoraCatPerfilList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpleadoSol")
    private List<DetSolicitudPermiso> detSolicitudPermisoList;
    @OneToMany(mappedBy = "idEmpleadoRev")
    private List<DetSolicitudPermiso> detSolicitudPermisoList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpleado")
    private List<DetBiometrico> detBiometricoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpleado")
    private List<DetRegistro> detRegistroList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpleadoSol")
    private List<DetSolicitudArticulo> detSolicitudArticuloList;
    @OneToMany(mappedBy = "idEmpleadoRev")
    private List<DetSolicitudArticulo> detSolicitudArticuloList1;
    @OneToMany(mappedBy = "idEmpleadoRev")
    private List<DetSolicitudPrenda> detSolicitudPrendaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpleadoSol")
    private List<DetSolicitudPrenda> detSolicitudPrendaList1;
    @JoinColumn(name = "id_area", referencedColumnName = "id_area")
    @ManyToOne
    private CatArea idArea;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa")
    @ManyToOne(optional = false)
    private CatEmpresa idEmpresa;
    @JoinColumn(name = "id_perfil", referencedColumnName = "id_perfil")
    @ManyToOne(optional = false)
    private CatPerfil idPerfil;
    @JoinColumn(name = "id_planta", referencedColumnName = "id_planta")
    @ManyToOne
    private CatPlanta idPlanta;
    @JoinColumn(name = "id_puesto", referencedColumnName = "id_puesto")
    @ManyToOne(optional = false)
    private CatPuesto idPuesto;

    public DetEmpleado() {
    }

    public DetEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public DetEmpleado(Integer idEmpleado, String numEmpleado, String nombre, String primerAp, Date fechaNacimiento, Date fechaRegistro, String rfc, Date fechaIngreso, short activo) {
        this.idEmpleado = idEmpleado;
        this.numEmpleado = numEmpleado;
        this.nombre = nombre;
        this.primerAp = primerAp;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaRegistro = fechaRegistro;
        this.rfc = rfc;
        this.fechaIngreso = fechaIngreso;
        this.activo = activo;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNumEmpleado() {
        return numEmpleado;
    }

    public void setNumEmpleado(String numEmpleado) {
        this.numEmpleado = numEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerAp() {
        return primerAp;
    }

    public void setPrimerAp(String primerAp) {
        this.primerAp = primerAp;
    }

    public String getSegundoAp() {
        return segundoAp;
    }

    public void setSegundoAp(String segundoAp) {
        this.segundoAp = segundoAp;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public short getActivo() {
        return activo;
    }

    public void setActivo(short activo) {
        this.activo = activo;
    }

    public List<BitacoraInventario> getBitacoraInventarioList() {
        return bitacoraInventarioList;
    }

    public void setBitacoraInventarioList(List<BitacoraInventario> bitacoraInventarioList) {
        this.bitacoraInventarioList = bitacoraInventarioList;
    }

    public List<BitacoraCatPerfil> getBitacoraCatPerfilList() {
        return bitacoraCatPerfilList;
    }

    public void setBitacoraCatPerfilList(List<BitacoraCatPerfil> bitacoraCatPerfilList) {
        this.bitacoraCatPerfilList = bitacoraCatPerfilList;
    }

    public List<DetSolicitudPermiso> getDetSolicitudPermisoList() {
        return detSolicitudPermisoList;
    }

    public void setDetSolicitudPermisoList(List<DetSolicitudPermiso> detSolicitudPermisoList) {
        this.detSolicitudPermisoList = detSolicitudPermisoList;
    }

    public List<DetSolicitudPermiso> getDetSolicitudPermisoList1() {
        return detSolicitudPermisoList1;
    }

    public void setDetSolicitudPermisoList1(List<DetSolicitudPermiso> detSolicitudPermisoList1) {
        this.detSolicitudPermisoList1 = detSolicitudPermisoList1;
    }

    public List<DetBiometrico> getDetBiometricoList() {
        return detBiometricoList;
    }

    public void setDetBiometricoList(List<DetBiometrico> detBiometricoList) {
        this.detBiometricoList = detBiometricoList;
    }

    public List<DetRegistro> getDetRegistroList() {
        return detRegistroList;
    }

    public void setDetRegistroList(List<DetRegistro> detRegistroList) {
        this.detRegistroList = detRegistroList;
    }

    public List<DetSolicitudArticulo> getDetSolicitudArticuloList() {
        return detSolicitudArticuloList;
    }

    public void setDetSolicitudArticuloList(List<DetSolicitudArticulo> detSolicitudArticuloList) {
        this.detSolicitudArticuloList = detSolicitudArticuloList;
    }

    public List<DetSolicitudArticulo> getDetSolicitudArticuloList1() {
        return detSolicitudArticuloList1;
    }

    public void setDetSolicitudArticuloList1(List<DetSolicitudArticulo> detSolicitudArticuloList1) {
        this.detSolicitudArticuloList1 = detSolicitudArticuloList1;
    }

    public List<DetSolicitudPrenda> getDetSolicitudPrendaList() {
        return detSolicitudPrendaList;
    }

    public void setDetSolicitudPrendaList(List<DetSolicitudPrenda> detSolicitudPrendaList) {
        this.detSolicitudPrendaList = detSolicitudPrendaList;
    }

    public List<DetSolicitudPrenda> getDetSolicitudPrendaList1() {
        return detSolicitudPrendaList1;
    }

    public void setDetSolicitudPrendaList1(List<DetSolicitudPrenda> detSolicitudPrendaList1) {
        this.detSolicitudPrendaList1 = detSolicitudPrendaList1;
    }

    public CatArea getIdArea() {
        return idArea;
    }

    public void setIdArea(CatArea idArea) {
        this.idArea = idArea;
    }

    public CatEmpresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(CatEmpresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public CatPerfil getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(CatPerfil idPerfil) {
        this.idPerfil = idPerfil;
    }

    public CatPlanta getIdPlanta() {
        return idPlanta;
    }

    public void setIdPlanta(CatPlanta idPlanta) {
        this.idPlanta = idPlanta;
    }

    public CatPuesto getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(CatPuesto idPuesto) {
        this.idPuesto = idPuesto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpleado != null ? idEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetEmpleado)) {
            return false;
        }
        DetEmpleado other = (DetEmpleado) object;
        if ((this.idEmpleado == null && other.idEmpleado != null) || (this.idEmpleado != null && !this.idEmpleado.equals(other.idEmpleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.com.ferbo.model.DetEmpleado[ idEmpleado=" + idEmpleado + " ]";
    }
    
}
