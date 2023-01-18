/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mx.com.ferbo.commons.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Gabo
 */
public abstract class IBaseDAO<E, ID> {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgpPU");
    
    @PersistenceContext(unitName = "sgpPU")
    protected EntityManager emSGP = emf.createEntityManager();

    public abstract E buscarPorId(ID id);

    public abstract List<E> buscarTodos();

    public abstract List<E> buscarPorCriterios(E e);

    public abstract void actualizar(E e);

    public abstract void guardar(E e);
}
