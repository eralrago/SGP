package mx.com.ferbo.dao;

import java.util.List;

import mx.com.ferbo.commons.dao.IBaseDAO;
import mx.com.ferbo.dto.DetSolicitudPrendaDTO;
import mx.com.ferbo.model.CatPrenda;
import mx.com.ferbo.model.DetEmpleado;
import mx.com.ferbo.model.DetSolicitudPrenda;
import mx.com.ferbo.util.SGPException;

public class DetSolicitudPrendaDAO extends IBaseDAO<DetSolicitudPrendaDTO, Integer>{

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
		final DetSolicitudPrenda registro = new DetSolicitudPrenda();
		final CatPrenda prenda = new CatPrenda();
		final DetEmpleado empleado = new DetEmpleado();
		prenda.setIdPrenda(e.getIdPrenda());
		empleado.setIdEmpleado(e.getIdEmpleadoSol());
		try {
			emSGP.getTransaction().begin();
			registro.setIdPrenda(prenda);
			registro.setCantidad(e.getCantidad());
			registro.setAprobada(e.getAprobada());
			registro.setFechaCap(e.getFechaCap());
			registro.setFechaMod(e.getFechaMod());
			registro.setIdEmpleadoSol(empleado);
			registro.setIdEmpleadoRev(null);
			emSGP.persist(registro);
            emSGP.getTransaction().commit();
		} catch (Exception ex) {
    		emSGP.getTransaction().rollback();
            throw new SGPException("Error al guardar la solicitud de prenda");
    	}
		
	}

}
