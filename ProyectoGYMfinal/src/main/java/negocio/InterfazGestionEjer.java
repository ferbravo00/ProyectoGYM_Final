/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dominio.Ejercicio;
import java.util.List;

/**
 *
 * @author Alumno Mañana
 */
public interface InterfazGestionEjer {
    public List<Ejercicio> listarEjercicios();
    
    public Ejercicio encontrarEjercicioPorID(Ejercicio ejercicio);
    
    public Ejercicio econtrarEjercicioPorNombre(Ejercicio ejercicio);
    
    public void registrarEjercicio(Ejercicio ejercicio);
    
    public void modificarEjercicio(Ejercicio ejercicio);
    
    public void eliminarEjercicio(Ejercicio ejercicio);
}
