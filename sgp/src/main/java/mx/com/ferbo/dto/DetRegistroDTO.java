package mx.com.ferbo.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Gabo
 */
public class DetRegistroDTO implements Serializable {

    private Integer idRegistro;
    private Date fechaEntrada;
    private Date fechaSalida;

    public DetRegistroDTO() {
    }

    public DetRegistroDTO(Integer idRegistro, Date fechaEntrada, Date fechaSalida) {
        this.idRegistro = idRegistro;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
    }

    public Integer getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

}
