/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import dominio.Gymbros;
import dominio.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alumno Mañana
 */
public interface InterfazGymbros {
    public List<Gymbros> amigos = new ArrayList<>();
    public List<Gymbros> amigo = new ArrayList<>();
    public int insertar(Gymbros amigos);
    public int eliminar(Gymbros amigos);
    public List<Gymbros> mostrar() throws SQLException;
    public List<Gymbros> mostrarAmigos(int u) throws SQLException;
}
