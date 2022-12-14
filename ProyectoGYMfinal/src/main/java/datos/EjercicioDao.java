/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;
import dominio.Ejercicio;
import dominio.Gymbros;
import dominio.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alumno Mañana
 */
public class EjercicioDao implements InterfazEjercicio{
    
    private static final String SQL_SELECTCUERPO = "SELECT Nombre, Foto, Descripcion, ParteCuerpo FROM ejercicio where ParteCuerpo=?";
    private static final String SQL_SELECT = "SELECT * FROM ejercicio";
    private static final String SQL_UPDATE = "UPDATE ejercicio SET Nombre=?, Foto=?, Descripcion=?, ParteCuerpo=? WHERE idEjercicio=?";
    private static final String SQL_INSERT = "INSERT INTO ejercicio (idEjercicio,Nombre, Foto, Descripcion, ParteCuerpo) VALUE (?, ?, ?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM ejercicio WHERE idEjercicio=?";
    
    public List<Ejercicio> ejercicio = new ArrayList<>();
    
    
    public int insertar(Ejercicio ejercicio){
            Connection conn = null;
            PreparedStatement stmt = null;
            int registros = 0;
            
            try {
                conn = getConnection();
                stmt =conn.prepareStatement(SQL_INSERT);

                stmt.setInt(1, ejercicio.getIdEjercicio());
                stmt.setString(2, ejercicio.getNombre());
                stmt.setString(3, ejercicio.getFoto());
                stmt.setString(4, ejercicio.getDescripcion());
                stmt.setString(5, ejercicio.getParteCuerpo());
                
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
    
    
    
    public int actualizar(Ejercicio ejercicio){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = getConnection();
            stmt =conn.prepareStatement(SQL_UPDATE);

            stmt.setInt(5, ejercicio.getIdEjercicio());
            stmt.setString(1, ejercicio.getNombre());
            stmt.setString(2, ejercicio.getFoto());
            stmt.setString(3, ejercicio.getDescripcion());
            stmt.setString(4, ejercicio.getParteCuerpo());

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

    
    
    public int eliminar(int ejercicio){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = getConnection();
            stmt =conn.prepareStatement(SQL_DELETE);

            stmt.setInt(1, ejercicio);

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
    

    public List<Ejercicio> mostrar() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        conn = getConnection();
        stmt =conn.prepareStatement(SQL_SELECT);
        rs = stmt.executeQuery();
        
        while(rs.next()){
            int idEjercicio = rs.getInt("idEjercicio");
            String Nombre = rs.getString("Nombre");
            String Foto = rs.getString("Foto");
            String Descripcion = rs.getString("Descripcion");
            String ParteCuerpo = rs.getString("ParteCuerpo");

            ejercicio.add(new Ejercicio (idEjercicio,Nombre, Foto, Descripcion, ParteCuerpo));
        }
        
        close(rs);
        close(stmt);
        close(conn);
        return ejercicio;
    }
    
    public List<Ejercicio> mostrarCuerpo(String u) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        conn = getConnection();
        stmt =conn.prepareStatement(SQL_SELECTCUERPO);
        stmt.setString(1, u);
        rs = stmt.executeQuery();
                
                
        while(rs.next()){
            String Nombre = rs.getString("Nombre");
            String Foto = rs.getString("Foto");
            String Descripcion = rs.getString("Descripcion");
            String ParteCuerpo = rs.getString("ParteCuerpo");

            ejercicio.add(new Ejercicio (Nombre, Foto, Descripcion, ParteCuerpo));
        }
        
        close(rs);
        close(stmt);
        close(conn);
        return ejercicio;
    }
    
}
