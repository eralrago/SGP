package mx.com.ferbo.dao;

import java.util.List;
import mx.com.ferbo.commons.dao.IBaseDAO;
import mx.com.ferbo.dto.CatTipoSolicitudDTO;
import mx.com.ferbo.util.SGPException;

/**
 *
 * @author Gabriel
 */
public class CatTipoSolicitudDAO extends IBaseDAO<CatTipoSolicitudDTO, Integer>{

    @Override
    public CatTipoSolicitudDTO buscarPorId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<CatTipoSolicitudDTO> buscarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<CatTipoSolicitudDTO> buscarActivo() {
        return emSGP.createNamedQuery("CatTipoSolicitud.findByActive", CatTipoSolicitudDTO.class).getResultList();
    }

    @Override
    public List<CatTipoSolicitudDTO> buscarPorCriterios(CatTipoSolicitudDTO e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(CatTipoSolicitudDTO e) throws SGPException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(CatTipoSolicitudDTO e) throws SGPException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void guardar(CatTipoSolicitudDTO e) throws SGPException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
