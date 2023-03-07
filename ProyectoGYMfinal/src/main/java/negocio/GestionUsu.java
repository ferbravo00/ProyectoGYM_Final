/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;
import datos.*;
import dominio.Usuario;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Alumno Mañana
 */
@Stateless
public class GestionUsu implements InterfazGestionUsu{
    @Inject    
    private InterfazUsuario usuarioDao;

    @Override
    public List<Usuario> listarUsuarios() { 
    System.out.println("Lista de usuarios");
        return usuarioDao.findAllUsuarios();
    }
    
    @Override
    public List<Usuario> listarAmigos(Usuario usuario) {       
        return usuarioDao.listarGymbros(usuario);
    }

    @Override
    public Usuario encontrarUsuarioPorID(Usuario usuario) {
        return usuarioDao.findUsuarioByID(usuario);
    }

    @Override
    public Usuario econtrarUsuarioPorCorreo(Usuario usuario) { 
        return usuarioDao.findUsuarioByCorreo(usuario);
    }
    
    @Override
    public Usuario econtrarUsuarioPorNombre(Usuario usuario) { 
        return usuarioDao.findUsuarioByNombre(usuario);
    }

    @Override
    public void registrarUsuario(Usuario usuario) {
        usuarioDao.insertUsuario(usuario);
    }

    @Override
    public void modificarUsuario(Usuario usuario) {
        usuarioDao.updateUsuario(usuario);
    }

    @Override
    public void eliminarUsuario(Usuario usuario) {
        usuarioDao.deleteUsuario(usuario);
    }
    
    @Override
    public boolean comprobar(Usuario usu){
        int num = listarUsuarios().size();     //Lo he tenido que meter en una variable para que funcione...
        for (int i = 0; i < num; i++) {
            //System.out.println(seleccionar().size());
            if(this.listarUsuarios().get(i).getCorreo().equalsIgnoreCase(usu.getCorreo()) && listarUsuarios().get(i).getClave().equalsIgnoreCase(usu.getClave())){
                
                return true;
            }
        }
    return false;  
    }
    
    @Override
    public boolean comprobarAdmin(Usuario usu){

        if(usu.getCorreo().equalsIgnoreCase("fer@gmail.com") && usu.getClave().equalsIgnoreCase("fer"))return true;
        else return false; 
 
    }
      
    @Override
    public List<Usuario> buscarUsu(Usuario usu){
        List<Usuario> listadoUsu = null;
        for (int i=0; i<listarUsuarios().size();i++) {
            if (listarUsuarios().get(i).getNombre().toLowerCase().contains(usu.getNombre().toLowerCase())){
                listadoUsu.add(listarUsuarios().get(i));
            }
        }
        return listadoUsu;
    }
    
    
    @Override
    public String cifrarMD5(String input) throws Exception {
        String md5 = null;
        if (null == input)
            return null;
        // Create MessageDigest object for MD5
        MessageDigest digest = MessageDigest.getInstance("MD5");
        // Update input string in message digest
        digest.update(input.getBytes(), 0, input.length());
        // Converts message digest value in base 16 (hex)
        md5 = new BigInteger(1, digest.digest()).toString(16);
        
        return md5;
    }
    
    
    
}
