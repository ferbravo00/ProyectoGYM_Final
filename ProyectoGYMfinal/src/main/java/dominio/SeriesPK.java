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
public class SeriesPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idSeries")
    private int idSeries;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RutinaEjercicio_idRutinaEjercicio")
    private int rutinaEjercicioidRutinaEjercicio;

    public SeriesPK() {
    }

    public SeriesPK(int idSeries, int rutinaEjercicioidRutinaEjercicio) {
        this.idSeries = idSeries;
        this.rutinaEjercicioidRutinaEjercicio = rutinaEjercicioidRutinaEjercicio;
    }

    public int getIdSeries() {
        return idSeries;
    }

    public void setIdSeries(int idSeries) {
        this.idSeries = idSeries;
    }

    public int getRutinaEjercicioidRutinaEjercicio() {
        return rutinaEjercicioidRutinaEjercicio;
    }

    public void setRutinaEjercicioidRutinaEjercicio(int rutinaEjercicioidRutinaEjercicio) {
        this.rutinaEjercicioidRutinaEjercicio = rutinaEjercicioidRutinaEjercicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idSeries;
        hash += (int) rutinaEjercicioidRutinaEjercicio;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeriesPK)) {
            return false;
        }
        SeriesPK other = (SeriesPK) object;
        if (this.idSeries != other.idSeries) {
            return false;
        }
        if (this.rutinaEjercicioidRutinaEjercicio != other.rutinaEjercicioidRutinaEjercicio) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.SeriesPK[ idSeries=" + idSeries + ", rutinaEjercicioidRutinaEjercicio=" + rutinaEjercicioidRutinaEjercicio + " ]";
    }
    
}
