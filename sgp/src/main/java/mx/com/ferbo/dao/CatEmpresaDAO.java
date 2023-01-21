package mx.com.ferbo.dao;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import mx.com.ferbo.commons.dao.IBaseDAO;
import mx.com.ferbo.dto.CatEmpresaDTO;

/**
 *
 * @author Gabo
 */
@Stateless
@LocalBean
public class CatEmpresaDAO extends IBaseDAO<CatEmpresaDTO, Integer> {

    @Override
    public CatEmpresaDTO buscarPorId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<CatEmpresaDTO> buscarTodos() {
        return emSGP.createNamedQuery("CatEmpresa.findAll", CatEmpresaDTO.class).getResultList();
    }

    @Override
    public List<CatEmpresaDTO> buscarPorCriterios(CatEmpresaDTO e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(CatEmpresaDTO e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void guardar(CatEmpresaDTO e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<CatEmpresaDTO> buscarActivo() {
        return emSGP.createNamedQuery("CatEmpresa.findByActive", CatEmpresaDTO.class).getResultList();
    }

    @Override
    public void eliminar(CatEmpresaDTO e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
