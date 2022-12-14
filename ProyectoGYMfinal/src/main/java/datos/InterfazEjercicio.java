/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import dominio.Ejercicio;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alumno Mañana
 */
public interface InterfazEjercicio {
    public List<Ejercicio> ejercicio = new ArrayList<>();
    public int insertar(Ejercicio ejercicio);
    public int actualizar(Ejercicio ejercicio);
    public int eliminar(int ejercicio);
    public List<Ejercicio> mostrar() throws SQLException;
    public List<Ejercicio> mostrarCuerpo(String u) throws SQLException;
}
