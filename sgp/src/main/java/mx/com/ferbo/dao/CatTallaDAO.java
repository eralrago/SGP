package mx.com.ferbo.dao;

import java.util.List;

import mx.com.ferbo.commons.dao.IBaseDAO;
import mx.com.ferbo.dto.CatTallaDTO;
import mx.com.ferbo.util.SGPException;

public class CatTallaDAO extends IBaseDAO<CatTallaDTO, Integer>{

	@Override
	public CatTallaDTO buscarPorId(Integer id) {
		return emSGP.createNamedQuery("CatTalla.findForId", CatTallaDTO.class).setParameter("idTalla", id).getSingleResult();
	}

	@Override
	public List<CatTallaDTO> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CatTallaDTO> buscarActivo() {
		return emSGP.createNamedQuery("CatTalla.findAllActive", CatTallaDTO.class).getResultList();
	}

	@Override
	public List<CatTallaDTO> buscarPorCriterios(CatTallaDTO e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizar(CatTallaDTO e) throws SGPException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(CatTallaDTO e) throws SGPException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void guardar(CatTallaDTO e) throws SGPException {
		// TODO Auto-generated method stub
		
	}

}
