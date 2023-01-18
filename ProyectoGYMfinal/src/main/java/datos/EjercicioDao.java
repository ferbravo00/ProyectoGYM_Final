/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;


import dominio.Ejercicio;
import dominio.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alumno Mañana
 */
public class EjercicioDao implements InterfazEjercicio{
    
   @PersistenceContext(unitName="GymPU")
    EntityManager em;
    
    
    @Override
    public List<Ejercicio> findAllEjercicios() {
        // Creamos un NamedQuery, y el listado lo leemos con getResultList
        // Por lo que estamos escribiendo menos código
        return em.createNamedQuery("Ejercicio.findAll").getResultList();
    }

    @Override
    public Ejercicio findEjercicioByID(Ejercicio ejercicio) {
        // Especificamos la clase que queremos buscar y luego el campo por el 
        // que queremos buscar
        return em.find(Ejercicio.class, ejercicio.getIdEjercicio());
    }

    @Override
    public Ejercicio findEjercicioByNombre(Ejercicio ejercicio) {
        return em.find(Ejercicio.class, ejercicio.getNombre());
    }
    
    @Override
    public Ejercicio findByParteCuerpo(Ejercicio ejercicio) {
        return em.find(Ejercicio.class, ejercicio.getParteCuerpo());
    }
    
   @Override
    public void insertEjercicio(Ejercicio ejercicio) {
        em.persist(ejercicio);
    }
    
    @Override
    public void updateEjercicio(Ejercicio ejercicio){
        // Sincroniza cualquier modificamos que hayamos hecho de la persona en la BD
        em.merge(ejercicio);
    }
    
    @Override
    public void deleteEjercicio(Ejercicio ejercicio) {
        // 1. actualizamos el estado del objeto en la base de datos => se borra.
        em.remove(em.merge(ejercicio));
    }
        
}
