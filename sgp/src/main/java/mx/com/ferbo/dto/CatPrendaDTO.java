package mx.com.ferbo.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class CatPrendaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idPrenda;
    private String descripcion;
    private BigDecimal precio;
    private Integer cantidadMax;
    private short activo;

    public CatPrendaDTO() {
    }
    
    public CatPrendaDTO(Integer idPrenda, String descripcion, short activo) {
        this.idPrenda = idPrenda;
        this.descripcion = descripcion;
        this.activo = activo;
    }
    
    public CatPrendaDTO(Integer idPrenda, String descripcion, BigDecimal precio, short activo) {
        this.idPrenda = idPrenda;
        this.descripcion = descripcion;
        this.precio = precio;
        this.activo = activo;
    }

    public CatPrendaDTO(Integer idPrenda, String descripcion, BigDecimal precio, Integer cantidadMax, short activo) {
        this.idPrenda = idPrenda;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidadMax = cantidadMax;
        this.activo = activo;
    }

    public Integer getIdPrenda() {
        return idPrenda;
    }

    public void setIdPrenda(Integer idPrenda) {
        this.idPrenda = idPrenda;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    
    public Integer getCantidadMax() {
        return cantidadMax;
    }

    public void setCantidadMax(Integer cantidadMax) {
        this.cantidadMax = cantidadMax;
    }

    public short getActivo() {
        return activo;
    }

    public void setActivo(short activo) {
        this.activo = activo;
    }

}
