/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import dominio.Usuario;
//import manejoArchivos.ManejoArchivos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alumno Mañana
 */
public class UsuarioDao implements InterfazUsuario{
   
    @PersistenceContext(unitName="GymPU")
    EntityManager em;
    
    
    @Override
    public List<Usuario> findAllUsuarios() {
        // Creamos un NamedQuery, y el listado lo leemos con getResultList
        // Por lo que estamos escribiendo menos código
        return em.createNamedQuery("Usuario.findAll").getResultList();
    }
    
    @Override
    public List<Usuario> listarGymbros(Usuario usuario) {
        // Lista los amigos
        return usuario.getUsuarioList1();
    }

    @Override
    public Usuario findUsuarioByID(Usuario usuario) {
        // Especificamos la clase que queremos buscar y luego el campo por el 
        // que queremos buscar
        return em.find(Usuario.class, usuario.getIdUsuario());
    }

    @Override
    public Usuario findUsuarioByNombre(Usuario usuario) {
        return em.find(Usuario.class, usuario.getNombre());
    }
    
    @Override
    public Usuario findUsuarioByCorreo(Usuario usuario) {
        return em.find(Usuario.class, usuario.getCorreo());
    }
    
   @Override
    public void insertUsuario(Usuario usuario) {
        em.persist(usuario);
    }
    
    @Override
    public void updateUsuario(Usuario usuario){
        // Sincroniza cualquier modificamos que hayamos hecho de la persona en la BD
        em.merge(usuario);
    }
    
    @Override
    public void deleteUsuario(Usuario usuario) {
        // 1. actualizamos el estado del objeto en la base de datos => se borra.
        em.remove(em.merge(usuario));
    }
        
    
    
    
}
