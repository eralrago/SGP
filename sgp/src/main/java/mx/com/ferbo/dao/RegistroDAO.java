package mx.com.ferbo.dao;

import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import mx.com.ferbo.commons.dao.IBaseDAO;
import mx.com.ferbo.dto.DetRegistroDTO;
import mx.com.ferbo.model.CatEstatusRegistro;
import mx.com.ferbo.model.DetEmpleado;
import mx.com.ferbo.model.DetRegistro;
import mx.com.ferbo.util.SGPException;

/**
 *
 * @author Gabo
 */
@LocalBean
@Stateless
public class RegistroDAO extends IBaseDAO<DetRegistroDTO, Integer> {

    @Override
    public DetRegistroDTO buscarPorId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DetRegistroDTO> buscarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DetRegistroDTO> buscarActivo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DetRegistroDTO> buscarPorCriterios(DetRegistroDTO e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(DetRegistroDTO e) throws SGPException {
        DetRegistro registro = new DetRegistro();
        try {
            emSGP.getTransaction().begin();
            registro = emSGP.getReference(DetRegistro.class, e.getIdRegistro());
            registro.setFechaSalida(e.getFechaSalida());
            emSGP.merge(registro);
            emSGP.getTransaction().commit();
            emSGP.detach(registro);
        } catch (Exception ex) {
            emSGP.getTransaction().rollback();
            throw new SGPException("Error al actualizar al fecha de salida del empleado");
        }
    }

    @Override
    public void eliminar(DetRegistroDTO e) throws SGPException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void guardar(DetRegistroDTO e) throws SGPException {
    	final DetRegistro registro = new DetRegistro();
    	try {
    		emSGP.getTransaction().begin();
    		registro.setIdEmpleado(e.getDetEmpleadoDTO() != null ? emSGP.getReference(DetEmpleado.class, e.getDetEmpleadoDTO().getIdEmpleado()) : null);
    		registro.setFechaEntrada(e.getFechaEntrada());
    		registro.setFechaSalida(e.getFechaSalida());
    		registro.setIdEstatus(e.getDetEmpleadoDTO() != null ? emSGP.getReference(CatEstatusRegistro.class, e.getCatEstatusRegistroDTO().getIdEstatus()) : null);
    		emSGP.merge(registro);
            emSGP.getTransaction().commit();
            emSGP.detach(registro);
    	} catch (Exception ex) {
    		emSGP.getTransaction().rollback();
            throw new SGPException("Error al guardar el registro");
    	}
    }

    public List<DetRegistroDTO> consultaRegistrosPorIdEmp(Integer idEmpleado) {
        List<DetRegistroDTO> registros = emSGP.createNamedQuery("DetRegistro.findByIdEmp", DetRegistroDTO.class).setParameter("idEmp", idEmpleado)
                .getResultList();
        return registros;
    }
    
    public List<DetRegistroDTO> buscarPorIdFechaEntrada(Integer id, String fechaEntrada) {
        List<DetRegistroDTO> registros = emSGP.createNamedQuery("DetRegistro.findByIdEmpActivo", DetRegistroDTO.class).setParameter("idEmp", id).setParameter("fechaEntrada", fechaEntrada).getResultList();
        return registros;
    }
}
