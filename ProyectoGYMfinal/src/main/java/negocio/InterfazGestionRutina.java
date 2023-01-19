/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dominio.Rutina;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alumno Mañana
 */
@Local
public interface InterfazGestionRutina {
    
    public List<Rutina> listarRutinas();
    
    public Rutina encontrarRutinaPorID(Rutina rutina);
    
    public Rutina econtrarRutinaPorNombre(Rutina rutina);
    
    public void registrarRutina(Rutina rutina);
    
    public void modificarRutina(Rutina rutina);
    
    public void eliminarRutina(Rutina rutina);
    
}
