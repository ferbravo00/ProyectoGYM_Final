/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dominio.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alumno Mañana
 */
@Local
public interface InterfazGestionUsu {
    public List<Usuario> listarUsuarios();
    
    public Usuario encontrarUsuarioPorID(Usuario usuario);
    
    public Usuario econtrarUsuarioPorCorreo(Usuario usuario);
    
    public Usuario econtrarUsuarioPorNombre(Usuario usuario);
    
    public void registrarUsuario(Usuario usuario);
    
    public void modificarUsuario(Usuario usuario);
    
    public void eliminarUsuario(Usuario usuario);
    
    public boolean comprobar(Usuario usu);
    
    public List<Usuario> buscarUsu(Usuario usu);
    
    public List<Usuario> listarAmigos(Usuario usuario);
    
    public String cifrarMD5(String input) throws Exception;
    
}
