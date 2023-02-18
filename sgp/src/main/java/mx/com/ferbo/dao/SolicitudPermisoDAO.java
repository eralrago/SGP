package mx.com.ferbo.dao;

import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import mx.com.ferbo.commons.dao.IBaseDAO;
import mx.com.ferbo.dto.DetEmpleadoDTO;
import mx.com.ferbo.dto.DetIncidenciaDTO;
import mx.com.ferbo.dto.DetSolicitudPermisoDTO;
import mx.com.ferbo.model.CatEstatusIncidencia;
import mx.com.ferbo.model.CatTipoIncidencia;
import mx.com.ferbo.model.CatTipoSolicitud;
import mx.com.ferbo.model.DetEmpleado;
import mx.com.ferbo.model.DetIncidencia;
import mx.com.ferbo.model.DetSolicitudPermiso;
import mx.com.ferbo.util.SGPException;

/**
 *
 * @author Gabo
 */
@LocalBean
@Stateless
public class SolicitudPermisoDAO extends IBaseDAO<DetSolicitudPermisoDTO, Integer> {
    
    @Override
    public DetSolicitudPermisoDTO buscarPorId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public List<DetSolicitudPermisoDTO> buscarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public List<DetSolicitudPermisoDTO> buscarActivo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public List<DetSolicitudPermisoDTO> buscarPorCriterios(DetSolicitudPermisoDTO e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void actualizar(DetSolicitudPermisoDTO e) throws SGPException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void eliminar(DetSolicitudPermisoDTO e) throws SGPException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void guardar(DetSolicitudPermisoDTO e) throws SGPException {
        
        final DetSolicitudPermiso solicitud = new DetSolicitudPermiso();
        /*Se guarda Solicitud*/
        try {
            emSGP.getTransaction().begin();
            solicitud.setFechaCap(new Date());
            solicitud.setFechaInicio(e.getFechaInicio());
            solicitud.setFechaFin(e.getFechaFin());
            solicitud.setIdTipoSolicitud(emSGP.getReference(CatTipoSolicitud.class, e.getCatTipoSolicitud().getIdTipoSolicitud()));
            solicitud.setIdEmpleadoSol(emSGP.getReference(DetEmpleado.class, e.getEmpleadoSol().getIdEmpleado()));
            emSGP.persist(solicitud);
            emSGP.getTransaction().commit();
            e.setIdSolicitud(solicitud.getIdSolicitud());
            e.getCatTipoSolicitud().setDescripcion(solicitud.getIdTipoSolicitud().getDescripcion());
            
            IncidenciaDAO incidenciaDAO = new IncidenciaDAO();
            DetIncidenciaDTO incidencia = new DetIncidenciaDTO();
            incidencia.setDetSolicitudPermisoDTO(new DetSolicitudPermisoDTO(solicitud.getIdSolicitud()));
            incidencia.setDetEmpleadoDTO(new DetEmpleadoDTO(e.getEmpleadoSol().getIdEmpleado()));
            incidenciaDAO.guardar(incidencia);
            
        } catch (Exception ex) {
            emSGP.getTransaction().rollback();
            throw new SGPException("Error al guardar la solicitud");
        }
        
    }
    
    public List<DetSolicitudPermisoDTO> consultaPorIdEmpleado(Integer idEmpleado) {
        List<DetSolicitudPermisoDTO> listado = emSGP.createNamedQuery("DetSolicitudPermiso.findByIdEmp", DetSolicitudPermisoDTO.class)
                .setParameter("idEmp", idEmpleado)
                .getResultList();
        return listado;
    }
    
}
