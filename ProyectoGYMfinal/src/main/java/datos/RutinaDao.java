/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import dominio.Ejercicio;
import dominio.Rutina;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alumno Mañana
 */
public class RutinaDao implements InterfazRutina{
    
    @PersistenceContext(unitName="GymPU")
    EntityManager em;
    
    
    @Override
    public List<Rutina> findAllRutinas() {
        
        return em.createNamedQuery("Rutina.findAll").getResultList();
    }
    
    @Override
    public Rutina findRutinaByNombre(Rutina rutina) {
        return em.find(Rutina.class, rutina.getNombre());
    }
    
    @Override
    public Rutina findRutinaByIdUsu(Rutina rutina) {
        return em.find(Rutina.class, rutina.getUsuario());
    }
    
    @Override
    public void insertEjercicio(Rutina rutina) {
        em.persist(rutina);
    }
    
    @Override
    public void updateRutina(Rutina rutina){
        // Sincroniza cualquier modificamos que hayamos hecho de la persona en la BD
        em.merge(rutina);
    }
    
    @Override
    public void deleteRutina(Rutina rutina) {
        // 1. actualizamos el estado del objeto en la base de datos => se borra.
        em.remove(em.merge(rutina));
    }
    
}
