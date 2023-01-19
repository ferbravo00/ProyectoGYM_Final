/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import dominio.Rutina;
import java.util.List;

/**
 *
 * @author Alumno Mañana
 */
public interface InterfazRutina {
    
    public List<Rutina> findAllRutinas();
    public Rutina findRutinaByNombre(Rutina rutina);
    public Rutina findRutinaById(Rutina rutina);
    public void insertRutina(Rutina rutina);
    public void updateRutina(Rutina rutina);
    public void deleteRutina(Rutina rutina);
    
}
