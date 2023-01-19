/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import datos.InterfazRutina;
import dominio.Rutina;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Alumno Mañana
 */
public class GestionRutina implements InterfazGestionRutina{
    @Inject    
    private InterfazRutina rutinaDao;
    
    @Override
    public List<Rutina> listarRutinas() {       
        return rutinaDao.findAllRutinas();
    }

    @Override
    public Rutina encontrarRutinaPorID(Rutina rutina) {
        return rutinaDao.findRutinaById(rutina);
    }

    @Override
    public Rutina econtrarRutinaPorNombre(Rutina rutina) { 
        return rutinaDao.findRutinaByNombre(rutina);
    }
    
    @Override
    public void registrarRutina(Rutina rutina) {
        rutinaDao.insertRutina(rutina);
    }

    @Override
    public void modificarRutina(Rutina rutina) {
        rutinaDao.updateRutina(rutina);
    }

    @Override
    public void eliminarRutina(Rutina rutina) {
        rutinaDao.deleteRutina(rutina);
    }
    
}
