/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Alumno Mañana
 */
@Embeddable
public class RutinaejercicioPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idRutinaEjercicio")
    private int idRutinaEjercicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Ejercicio_idEjercicio")
    private int ejercicioidEjercicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Rutina_idRutina")
    private int rutinaidRutina;

    public RutinaejercicioPK() {
    }

    public RutinaejercicioPK(int idRutinaEjercicio, int ejercicioidEjercicio, int rutinaidRutina) {
        this.idRutinaEjercicio = idRutinaEjercicio;
        this.ejercicioidEjercicio = ejercicioidEjercicio;
        this.rutinaidRutina = rutinaidRutina;
    }

    public int getIdRutinaEjercicio() {
        return idRutinaEjercicio;
    }

    public void setIdRutinaEjercicio(int idRutinaEjercicio) {
        this.idRutinaEjercicio = idRutinaEjercicio;
    }

    public int getEjercicioidEjercicio() {
        return ejercicioidEjercicio;
    }

    public void setEjercicioidEjercicio(int ejercicioidEjercicio) {
        this.ejercicioidEjercicio = ejercicioidEjercicio;
    }

    public int getRutinaidRutina() {
        return rutinaidRutina;
    }

    public void setRutinaidRutina(int rutinaidRutina) {
        this.rutinaidRutina = rutinaidRutina;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idRutinaEjercicio;
        hash += (int) ejercicioidEjercicio;
        hash += (int) rutinaidRutina;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RutinaejercicioPK)) {
            return false;
        }
        RutinaejercicioPK other = (RutinaejercicioPK) object;
        if (this.idRutinaEjercicio != other.idRutinaEjercicio) {
            return false;
        }
        if (this.ejercicioidEjercicio != other.ejercicioidEjercicio) {
            return false;
        }
        if (this.rutinaidRutina != other.rutinaidRutina) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.RutinaejercicioPK[ idRutinaEjercicio=" + idRutinaEjercicio + ", ejercicioidEjercicio=" + ejercicioidEjercicio + ", rutinaidRutina=" + rutinaidRutina + " ]";
    }
    
}
