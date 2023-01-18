/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.com.ferbo.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import mx.com.ferbo.dao.CatEmpresaDAO;
import mx.com.ferbo.dto.CatEmpresaDTO;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author Gabo
 */
@Named(value = "registroEmpleadosBean")
@SessionScoped
public class RegistroEmpleadosBean implements Serializable {

    private Logger log = LogManager.getRootLogger();
    private List<CatEmpresaDTO> lstCatEmpresa;

    private CatEmpresaDAO catEmpresaDAO;

    public RegistroEmpleadosBean() {
        log.info("--Iniciando desde el constructor--");
        catEmpresaDAO = new CatEmpresaDAO();
    }

    @PostConstruct
    public void init() {
        log.info("--Iniciando desde el postconstruct--");
        try {
            lstCatEmpresa = catEmpresaDAO.buscarTodos();
        } catch (Exception ex) {
            log.info(ex);
        }
    }

    public List<CatEmpresaDTO> getLstCatEmpresa() {
        return lstCatEmpresa;
    }

    public void setLstCatEmpresa(List<CatEmpresaDTO> lstCatEmpresa) {
        this.lstCatEmpresa = lstCatEmpresa;
    }

}
