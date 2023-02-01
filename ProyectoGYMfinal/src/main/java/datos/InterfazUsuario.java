/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import dominio.Usuario;
import java.util.List;

/**
 *
 * @author Alumno Mañana
 */
public interface InterfazUsuario {
    public List<Usuario> findAllUsuarios();
    public Usuario findUsuarioByID(Usuario usuario);
    public Usuario findUsuarioByNombre(Usuario usuario);
    public Usuario findUsuarioByCorreo(Usuario usuario);
    public void insertUsuario(Usuario usuario);
    public void updateUsuario(Usuario usuario);
    public void deleteUsuario(Usuario usuario);
    public List<Usuario> listarGymbros(Usuario usuario);
}
