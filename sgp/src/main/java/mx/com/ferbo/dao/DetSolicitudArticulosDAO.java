package mx.com.ferbo.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import mx.com.ferbo.commons.dao.IBaseDAO;
import mx.com.ferbo.dto.DetSolicitudArticuloDTO;
import mx.com.ferbo.model.CatArticulo;
import mx.com.ferbo.model.DetEmpleado;
import mx.com.ferbo.model.DetSolicitudArticulo;
import mx.com.ferbo.util.SGPException;

@Stateless
@LocalBean
public class DetSolicitudArticulosDAO extends IBaseDAO<DetSolicitudArticuloDTO, Integer> implements Serializable{

	@Override
	public DetSolicitudArticuloDTO buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DetSolicitudArticuloDTO> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DetSolicitudArticuloDTO> buscarActivo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DetSolicitudArticuloDTO> buscarPorCriterios(DetSolicitudArticuloDTO e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizar(DetSolicitudArticuloDTO e) throws SGPException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(DetSolicitudArticuloDTO e) throws SGPException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void guardar(DetSolicitudArticuloDTO e) throws SGPException {
		DetSolicitudArticulo registro = new DetSolicitudArticulo();
		try {
			emSGP.getTransaction().begin();
			registro.setCantidad(e.getCantidad());
			//registro.setAprobada(e.getAprobada());
			registro.setFechaCap(e.getFechaCap());
			//registro.setFechaMod(e.getFechaMod());
			registro.setIdArticulo(emSGP.getReference(CatArticulo.class, e.getArticulo().getIdArticulo()));
			registro.setIdEmpleadoRev(null);
			registro.setIdEmpleadoSol(emSGP.getReference(DetEmpleado.class, e.getIdEmpleadoSol()));
			emSGP.persist(registro);
            emSGP.getTransaction().commit();
            e.setIdSolicitud(registro.getIdSolicitud());
		} catch (Exception ex) {
    		emSGP.getTransaction().rollback();
            throw new SGPException("Error al guardar la solicitud de prenda");
    	}
		
	}

}
