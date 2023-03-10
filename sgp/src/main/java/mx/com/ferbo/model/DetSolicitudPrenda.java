package mx.com.ferbo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Gabo
 */
@Entity
@Table(name = "det_solicitud_prenda")
@NamedQueries({
    @NamedQuery(name = "DetSolicitudPrenda.findAll", query = "SELECT d FROM DetSolicitudPrenda d"),
    @NamedQuery(name = "DetSolicitudPrenda.findPrendasIdEmpleado", query = "SELECT NEW mx.com.ferbo.dto.DetSolicitudPrendaDTO("
                                                                    + "p.descripcion, t.descripcion, d.cantidad, d.fecha_cap, d.aprobada) "
                                                                    + "FROM DetSolicitudPrenda d "
                                                                    + "INNER JOIN d.empleadoSol e"
                                                                    + "INNER JOIN d.prenda p"
                                                                    + "INNER JOIN d.talla t"
                                                                    + "WHERE e.idEmpleado = :idEmp;")
})
public class DetSolicitudPrenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_solicitud")
    private Integer idSolicitud;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "aprobada")
    private Short aprobada;
    @Basic(optional = false)
    @Column(name = "fecha_cap")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCap;
    @Column(name = "fecha_mod")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMod;
    @OneToMany(mappedBy = "idSolPrenda")
    private List<DetIncidencia> detIncidenciaList;
    @JoinColumn(name = "id_prenda", referencedColumnName = "id_prenda")
    @ManyToOne(optional = false)
    private CatPrenda idPrenda;
    @JoinColumn(name = "id_empleado_rev", referencedColumnName = "id_empleado")
    @ManyToOne
    private DetEmpleado idEmpleadoRev;
    @JoinColumn(name = "id_empleado_sol", referencedColumnName = "id_empleado")
    @ManyToOne(optional = false)
    private DetEmpleado idEmpleadoSol;
    @JoinColumn(name = "id_talla", referencedColumnName = "id_talla")
    @ManyToOne(optional = false)
    private CatTalla idTalla;

    public DetSolicitudPrenda() {
    }

    public DetSolicitudPrenda(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public DetSolicitudPrenda(Integer idSolicitud, int cantidad, Date fechaCap) {
        this.idSolicitud = idSolicitud;
        this.cantidad = cantidad;
        this.fechaCap = fechaCap;
    }

    public Integer getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
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

    public List<DetIncidencia> getDetIncidenciaList() {
        return detIncidenciaList;
    }

    public void setDetIncidenciaList(List<DetIncidencia> detIncidenciaList) {
        this.detIncidenciaList = detIncidenciaList;
    }

    public CatPrenda getIdPrenda() {
        return idPrenda;
    }

    public void setIdPrenda(CatPrenda idPrenda) {
        this.idPrenda = idPrenda;
    }

    public DetEmpleado getIdEmpleadoRev() {
        return idEmpleadoRev;
    }

    public void setIdEmpleadoRev(DetEmpleado idEmpleadoRev) {
        this.idEmpleadoRev = idEmpleadoRev;
    }

    public DetEmpleado getIdEmpleadoSol() {
        return idEmpleadoSol;
    }

    public void setIdEmpleadoSol(DetEmpleado idEmpleadoSol) {
        this.idEmpleadoSol = idEmpleadoSol;
    }

    public CatTalla getIdTalla() {
        return idTalla;
    }

    public void setIdTalla(CatTalla idTalla) {
        this.idTalla = idTalla;
    }    
}
