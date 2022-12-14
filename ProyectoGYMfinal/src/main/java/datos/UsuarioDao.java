/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import dominio.Usuario;
//import manejoArchivos.ManejoArchivos;
import java.sql.Connection;
import static datos.Conexion.getConnection;
import static datos.Conexion.close;
import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.time.LocalDate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alumno Mañana
 */
public class UsuarioDao implements InterfazUsuario{
    private static final String SQL_SELECT = "SELECT * FROM Usuario";
    private static final String SQL_SELECTID = "SELECT * FROM Usuario WHERE idUsuario=?";
    private static final String SQL_SELECTNAME = "SELECT idUsuario FROM Usuario WHERE Nombre=?";
    private static final String SQL_INSERT = "INSERT INTO Usuario (Nombre, Correo, Clave, Gimnasio, Edad, Altura, Peso, Foto, FechaAlta) VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE Usuario SET Nombre=?, Correo=?, Clave=?, Gimnasio=?, Edad=?, Altura=?, Peso=?, Foto=? WHERE idUsuario=?";
    private static final String SQL_DELETE = "DELETE FROM Usuario WHERE idUsuario=?";
    
    
    public List<Usuario> usuarios = new ArrayList<>();
    
    public int insertar(Usuario usuarios){
            Connection conn = null;
            PreparedStatement stmt = null;
            int registros = 0;
            
            try {
                conn = getConnection();
                stmt =conn.prepareStatement(SQL_INSERT);
                
                stmt.setString(1, usuarios.getNombre());
                stmt.setString(2, usuarios.getCorreo());
                stmt.setString(3, usuarios.getClave());
                stmt.setString(4, usuarios.getGimnasio());
                stmt.setInt(5, usuarios.getEdad());
                stmt.setInt(6, usuarios.getAltura());
                stmt.setInt(7, usuarios.getPeso());
                stmt.setString(8, usuarios.getFoto());
                stmt.setDate(9, usuarios.getFechaAlta());
                //stmt.setDate(9, (Date) Date.from(usuarios.getFechaAlta().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                
                registros = stmt.executeUpdate();
                
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }finally{
                try {
                    close(stmt);
                    close(conn);
                } catch (SQLException ex) {
                    ex.printStackTrace(System.out);
                }
            }
            return registros;
        }
    
        
    public int actualizar(Usuario usuarios){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = getConnection();
            stmt =conn.prepareStatement(SQL_UPDATE);

            stmt.setString(1, usuarios.getNombre());
            stmt.setString(2, usuarios.getCorreo());
            stmt.setString(3, usuarios.getClave());
            stmt.setString(4, usuarios.getGimnasio());
            stmt.setInt(5, usuarios.getEdad());
            stmt.setInt(6, usuarios.getAltura());
            stmt.setInt(7, usuarios.getPeso());
            stmt.setString(8, usuarios.getFoto());
            stmt.setInt(9, usuarios.getIdUsuario());

            registros = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }


    public int eliminar(int usuarios){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = getConnection();
            stmt =conn.prepareStatement(SQL_DELETE);

            stmt.setInt(1, usuarios);

            registros = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    
    public List<Usuario> seleccionar() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        conn = getConnection();
        stmt =conn.prepareStatement(SQL_SELECT);
        rs = stmt.executeQuery();
        
        while(rs.next()){
            int idUsuario = rs.getInt("idUsuario");
            String Nombre = rs.getString("Nombre");
            String Correo = rs.getString("Correo");
            String Clave = rs.getString("Clave");
            String Gimnasio = rs.getString("Gimnasio");
            int Edad = rs.getInt("Edad");
            int Altura = rs.getInt("Altura");
            int Peso = rs.getInt("Peso");
            String Foto = rs.getString("Foto");
            Date FechaAlta = rs.getDate("FechaAlta");
            
            
           
            
            usuarios.add(new Usuario (idUsuario, Nombre, Correo, Clave, Gimnasio, Edad, Altura, Peso, Foto, FechaAlta));
        }
        
        close(rs);
        close(stmt);
        close(conn);
        return usuarios;
    }
    
    public List<Usuario> mostrarId(int u) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        conn = getConnection();
        stmt =conn.prepareStatement(SQL_SELECTID);
        stmt.setInt(1, u);
        rs = stmt.executeQuery();
        
        while(rs.next()){
            int idUsuario = rs.getInt("idUsuario");
            String Nombre = rs.getString("Nombre");
            String Correo = rs.getString("Correo");
            String Clave = rs.getString("Clave");
            String Gimnasio = rs.getString("Gimnasio");
            int Edad = rs.getInt("Edad");
            int Altura = rs.getInt("Altura");
            int Peso = rs.getInt("Peso");
            String Foto = rs.getString("Foto");
            Date FechaAlta = rs.getDate("FechaAlta");
            
            
           
            
            usuarios.add(new Usuario (idUsuario, Nombre, Correo, Clave, Gimnasio, Edad, Altura, Peso, Foto, FechaAlta));
        }
        
        close(rs);
        close(stmt);
        close(conn);
        return usuarios;
    }
    
    public int mostrarNombre(String u) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        conn = getConnection();
        stmt =conn.prepareStatement(SQL_SELECTNAME);
        stmt.setString(1, u);
        rs = stmt.executeQuery();
        
        while(rs.next()){
            int idUsuario = rs.getInt("idUsuario");
            return idUsuario;
            //usuarios.add(new Usuario (idUsuario));
        }
        
        close(rs);
        close(stmt);
        close(conn);
        return 0;
    }

    public int comprobar(String nombre, String clave){
        try {
            int num = seleccionar().size();     //Lo he tenido que meter en una variable para que funcione...
            for (int i = 0; i < num; i++) {
                //System.out.println(seleccionar().size());
                if(seleccionar().get(i).getNombre().equalsIgnoreCase(nombre) && seleccionar().get(i).getClave().equalsIgnoreCase(clave)){
                    return 1;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return 0;  
    }
      
    public int comprobar(String nombre){
        try {
            int num = seleccionar().size();     //Lo he tenido que meter en una variable para que funcione...
            for (int i = 0; i < num; i++) {
                if(seleccionar().get(i).getNombre().equalsIgnoreCase(nombre)){
                    return 1;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return 0;
    }
    
    
    public void actualizarArchivoUsuarios(){
        
        String contenido ="";
        //File archivo = new File("src/main/java/datos/usuario.txt");
        try {
            int num = seleccionar().size();
            for (int i = 0; i < num; i++) {
            
            contenido += (seleccionar().get(i)+"\n");
            }
//            ManejoArchivos.escribir("usuario.txt",contenido);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public String cifrarMD5(String input) throws Exception {
        String md5 = null;
        if (null == input)
            return null;
        try {
            // Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");
            // Update input string in message digest
            digest.update(input.getBytes(), 0, input.length());
            // Converts message digest value in base 16 (hex)
            md5 = new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {

            throw e;
        }
        return md5;
    }
    
//    public boolean compararMD5(String orig, String compare){
//        String md5 = null;
//        try{
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            md.update(compare.getBytes());
//            byte[] digest = md.digest();
//            md5 = new BigInteger(1, digest).toString(16);
//
//            return md5.equals(orig);
//
//        } catch (NoSuchAlgorithmException e) {
//            //return false;
//        }
//
//        return false;
//    }
    
    
}
