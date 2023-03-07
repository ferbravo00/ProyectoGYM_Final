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
import javax.ejb.Local;

/**
 *
 * @author Alumno Mañana
 */
@Local
public interface InterfazEjercicio {
    public List<Ejercicio> findAllEjercicios();
    public Ejercicio findEjercicioByID(Ejercicio ejercicio);
    public Ejercicio findEjercicioByNombre(Ejercicio ejercicio);
    public List<Ejercicio> findEjercicioByBuscar(Ejercicio ejercicio);
    public Ejercicio findByParteCuerpo(Ejercicio ejercicio);
    public void insertEjercicio(Ejercicio ejercicio);
    public void updateEjercicio(Ejercicio ejercicio);
    public void deleteEjercicio(Ejercicio ejercicio);

    
}
