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
    public Rutina findRutinaById(Rutina rutina) {
        return em.find(Rutina.class, rutina.getIdRutina());
    }
    
    @Override
    public void insertRutina(Rutina rutina) {
        em.persist(rutina);
    }
    
    @Override
    public void updateRutina(Rutina rutina){
        em.merge(rutina);
    }
    
    @Override
    public void deleteRutina(Rutina rutina) {
        em.remove(em.merge(rutina));
    }
    
}
