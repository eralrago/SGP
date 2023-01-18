/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.com.ferbo.dto;

import java.io.Serializable;

/**
 *
 * @author Gabo
 */
public class CatEmpresaDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idEmpresa;
    private String descripcion;
    private short activo;

    public CatEmpresaDTO() {
    }

    public CatEmpresaDTO(Integer idEmpresa, String descripcion, short activo) {
        this.idEmpresa = idEmpresa;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
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
