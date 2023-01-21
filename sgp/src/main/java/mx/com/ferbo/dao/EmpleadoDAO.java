package mx.com.ferbo.dao;

import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import mx.com.ferbo.commons.dao.IBaseDAO;
import mx.com.ferbo.dto.DetEmpleadoDTO;
import mx.com.ferbo.model.CatArea;
import mx.com.ferbo.model.CatEmpresa;
import mx.com.ferbo.model.CatPerfil;
import mx.com.ferbo.model.CatPlanta;
import mx.com.ferbo.model.CatPuesto;
import mx.com.ferbo.model.DetEmpleado;
import mx.com.ferbo.util.SGPException;

/**
 *
 * @author Gabo
 */
@Stateless
@LocalBean
public class EmpleadoDAO extends IBaseDAO<DetEmpleadoDTO, Integer> {

    @Override
    public DetEmpleadoDTO buscarPorId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DetEmpleadoDTO> buscarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DetEmpleadoDTO> buscarPorCriterios(DetEmpleadoDTO e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(DetEmpleadoDTO e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void guardar(DetEmpleadoDTO e) throws SGPException {
        final DetEmpleado empleado = new DetEmpleado();
        try {
            emSGP.getTransaction().begin();
            empleado.setNombre(e.getNombre());
            empleado.setPrimerAp(e.getPrimerAp());
            empleado.setSegundoAp(e.getSegundoAp());
            empleado.setCurp(e.getCurp());
            empleado.setFechaNacimiento(e.getFechaNacimiento());
            empleado.setCorreo(e.getCorreo());
            empleado.setRfc(e.getRfc());
            empleado.setNss(e.getNss());
            empleado.setFechaIngreso(e.getFechaIngreso());
            empleado.setIdPerfil(e.getCatPerfilDTO() != null ? emSGP.getReference(CatPerfil.class, e.getCatPerfilDTO().getIdPerfil()) : null);
            empleado.setIdEmpresa(e.getCatEmpresaDTO() != null ? emSGP.getReference(CatEmpresa.class, e.getCatEmpresaDTO().getIdEmpresa()) : null);
            empleado.setIdPuesto(e.getCatPuestoDTO() != null ? emSGP.getReference(CatPuesto.class, e.getCatPuestoDTO().getIdPuesto()) : null);
            empleado.setIdArea(e.getCatAreaDTO() != null ? emSGP.getReference(CatArea.class, e.getCatAreaDTO().getIdArea()) : null);
            empleado.setIdPlanta(e.getCatPlantaDTO() != null ? emSGP.getReference(CatPlanta.class, e.getCatPlantaDTO().getIdPlanta()) : null);
            empleado.setFechaRegistro(new Date());
            empleado.setActivo((short) 1);
            empleado.setNumEmpleado(String.format("%0" + 4 + "d", (Integer) emSGP.createNamedQuery("DetEmpleado.getNumEmpleado").getSingleResult() + 1));
            emSGP.persist(empleado);
            emSGP.getTransaction().commit();
            e.setIdEmpleado(empleado.getIdEmpleado());
        } catch (Exception ex) {
            emSGP.getTransaction().rollback();
            throw new SGPException("Error al guardar empleado");
        }

    }

}
