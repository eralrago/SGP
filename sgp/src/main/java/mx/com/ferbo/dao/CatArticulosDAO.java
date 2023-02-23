package mx.com.ferbo.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import mx.com.ferbo.commons.dao.IBaseDAO;
import mx.com.ferbo.dto.CatArticuloDTO;
import mx.com.ferbo.model.CatArticulo;
import mx.com.ferbo.util.SGPException;

@Stateless
@LocalBean
public class CatArticulosDAO extends IBaseDAO<CatArticuloDTO, Integer>{

	@Override
	public CatArticuloDTO buscarPorId(Integer id) {
		return emSGP.createNamedQuery("CatArticulo.findById", CatArticuloDTO.class).setParameter("idArticulo", id).getSingleResult();
	}

	@Override
	public List<CatArticuloDTO> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CatArticuloDTO> buscarActivo() {
		return emSGP.createNamedQuery("CatArticulo.findAllActive", CatArticuloDTO.class).getResultList();
	}

	@Override
	public List<CatArticuloDTO> buscarPorCriterios(CatArticuloDTO e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizar(CatArticuloDTO e) throws SGPException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(CatArticuloDTO e) throws SGPException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void guardar(CatArticuloDTO e) throws SGPException {
		// TODO Auto-generated method stub
		
	}

	

}
