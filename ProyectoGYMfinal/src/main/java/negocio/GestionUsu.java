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
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
/**
 *
 * @author Alumno Mañana
 */
public class GestionUsu{
    private InterfazUsuario datos;

    public GestionUsu() {
        this.datos = new UsuarioDao();
    }
    
    public boolean comprobar(Usuario usu){
        int num = this.datos.findAllUsuarios().size();     //Lo he tenido que meter en una variable para que funcione...
        for (int i = 0; i < num; i++) {
            //System.out.println(seleccionar().size());
            if(this.datos.findAllUsuarios().get(i).getNombre().equalsIgnoreCase(usu.getNombre()) && this.datos.findAllUsuarios().get(i).getClave().equalsIgnoreCase(usu.getClave())){
                return true;
            }
        }
    return false;  
    }
      
    
 
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
