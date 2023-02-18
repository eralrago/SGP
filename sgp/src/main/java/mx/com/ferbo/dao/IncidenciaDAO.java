package mx.com.ferbo.dao;

import java.util.Date;
import java.util.List;
import mx.com.ferbo.commons.dao.IBaseDAO;
import mx.com.ferbo.dto.DetIncidenciaDTO;
import mx.com.ferbo.model.CatEstatusIncidencia;
import mx.com.ferbo.model.CatTipoIncidencia;
import mx.com.ferbo.model.DetEmpleado;
import mx.com.ferbo.model.DetIncidencia;
import mx.com.ferbo.model.DetSolicitudPermiso;
import mx.com.ferbo.util.SGPException;

/**
 *
 * @author Gabriel
 */
public class IncidenciaDAO extends IBaseDAO<DetIncidenciaDTO, Integer> {

    @Override
    public DetIncidenciaDTO buscarPorId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DetIncidenciaDTO> buscarTodos() {
        return emSGP.createNamedQuery("DetIncidencia.findAll", DetIncidenciaDTO.class).getResultList();
    }

    @Override
    public List<DetIncidenciaDTO> buscarActivo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DetIncidenciaDTO> buscarPorCriterios(DetIncidenciaDTO e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(DetIncidenciaDTO e) throws SGPException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(DetIncidenciaDTO e) throws SGPException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void guardar(DetIncidenciaDTO e) throws SGPException {
        try {
            emSGP.getTransaction().begin();
            final DetIncidencia incidencia = new DetIncidencia();
            incidencia.setIdSolPermiso(emSGP.getReference(DetSolicitudPermiso.class, e.getDetSolicitudPermisoDTO().getIdSolicitud()));
            incidencia.setIdTipo(emSGP.getReference(CatTipoIncidencia.class, 1));
            incidencia.setVisible((short) 1);
            incidencia.setIdEstatus(emSGP.getReference(CatEstatusIncidencia.class, 1));
            incidencia.setIdEmpleado(emSGP.getReference(DetEmpleado.class, e.getDetEmpleadoDTO().getIdEmpleado()));
            incidencia.setFechaCap(new Date());
            emSGP.persist(incidencia);
            emSGP.getTransaction().commit();

        } catch (Exception ex) {
            emSGP.getTransaction().rollback();
            throw new SGPException("Error al guardar la Incidencia " + ex);
        }
    }
    
}
