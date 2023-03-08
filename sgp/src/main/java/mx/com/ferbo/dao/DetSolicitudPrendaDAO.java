package mx.com.ferbo.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import mx.com.ferbo.commons.dao.IBaseDAO;
import mx.com.ferbo.dto.DetSolicitudPrendaDTO;
import mx.com.ferbo.model.CatPrenda;
import mx.com.ferbo.model.CatTalla;
import mx.com.ferbo.model.DetEmpleado;
import mx.com.ferbo.model.DetSolicitudPrenda;
import mx.com.ferbo.util.SGPException;

@Stateless
@LocalBean
public class DetSolicitudPrendaDAO extends IBaseDAO<DetSolicitudPrendaDTO, Integer> implements Serializable{

	@Override
	public DetSolicitudPrendaDTO buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DetSolicitudPrendaDTO> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DetSolicitudPrendaDTO> buscarActivo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DetSolicitudPrendaDTO> buscarPorCriterios(DetSolicitudPrendaDTO e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizar(DetSolicitudPrendaDTO e) throws SGPException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(DetSolicitudPrendaDTO e) throws SGPException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void guardar(DetSolicitudPrendaDTO e) throws SGPException {
		DetSolicitudPrenda registro = new DetSolicitudPrenda();
		try {
			emSGP.getTransaction().begin();
			registro.setCantidad(e.getCantidad());
			// registro.setAprobada(e.getAprobada());
			registro.setFechaCap(e.getFechaCap());
			// registro.setFechaMod(e.getFechaMod());
			registro.setIdPrenda(emSGP.getReference(CatPrenda.class, e.getPrenda().getIdPrenda()));
			registro.setIdEmpleadoRev(null);
			registro.setIdEmpleadoSol(emSGP.getReference(DetEmpleado.class, e.getIdEmpleadoSol()));
			registro.setIdTalla(emSGP.getReference(CatTalla.class, e.getTalla().getIdTalla()));
			emSGP.persist(registro);
            emSGP.getTransaction().commit();
            e.setIdSolicitud(registro.getIdSolicitud());
		} catch (Exception ex) {
    		emSGP.getTransaction().rollback();
            throw new SGPException("Error al guardar la solicitud de prenda");
    	}
		
	}

}
