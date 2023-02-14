/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import datos.InterfazEjercicio;
import datos.InterfazRutina;
import dominio.Ejercicio;
import java.sql.Array;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Alumno Mañana
 */
@Stateless
public class GestionEjer implements InterfazGestionEjer{
    @Inject    
    private InterfazEjercicio ejercicioDao;
    
    @Override
    public List<Ejercicio> listarEjercicios() {       
        return ejercicioDao.findAllEjercicios();
    }

    @Override
    public Ejercicio encontrarEjercicioPorID(Ejercicio ejercicio) {
        return ejercicioDao.findEjercicioByID(ejercicio);
    }

    @Override
    public Ejercicio econtrarEjercicioPorNombre(Ejercicio ejercicio) { 
        return ejercicioDao.findEjercicioByNombre(ejercicio);
    }
    
    @Override
    public void registrarEjercicio(Ejercicio ejercicio) {
        ejercicioDao.insertEjercicio(ejercicio);
    }

    @Override
    public void modificarEjercicio(Ejercicio ejercicio) {
        ejercicioDao.updateEjercicio(ejercicio);
    }

    @Override
    public void eliminarEjercicio(Ejercicio ejercicio) {
        ejercicioDao.deleteEjercicio(ejercicio);
    }
   
    @Override
    public List<Ejercicio> buscarEjer(Ejercicio ejer){
        List<Ejercicio> listadoEjer = null;
        for (int i=0; i<listarEjercicios().size();i++) {
            if (listarEjercicios().get(i).getNombre().toLowerCase().contains(ejer.getNombre().toLowerCase())){
                listadoEjer.add(listarEjercicios().get(i));
            }
        }
        return listadoEjer;
    }
    
}
