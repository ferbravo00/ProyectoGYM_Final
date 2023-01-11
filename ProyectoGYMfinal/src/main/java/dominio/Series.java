/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Alumno Mañana
 */
@Entity
@Table(name = "series")
@NamedQueries({
    @NamedQuery(name = "Series.findAll", query = "SELECT s FROM Series s"),
    @NamedQuery(name = "Series.findByIdSeries", query = "SELECT s FROM Series s WHERE s.seriesPK.idSeries = :idSeries"),
    @NamedQuery(name = "Series.findByNumeroSerie", query = "SELECT s FROM Series s WHERE s.numeroSerie = :numeroSerie"),
    @NamedQuery(name = "Series.findByRepeticiones", query = "SELECT s FROM Series s WHERE s.repeticiones = :repeticiones"),
    @NamedQuery(name = "Series.findByPeso", query = "SELECT s FROM Series s WHERE s.peso = :peso"),
    @NamedQuery(name = "Series.findByRutinaEjercicioidRutinaEjercicio", query = "SELECT s FROM Series s WHERE s.seriesPK.rutinaEjercicioidRutinaEjercicio = :rutinaEjercicioidRutinaEjercicio")})
public class Series implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SeriesPK seriesPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NumeroSerie")
    private int numeroSerie;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Repeticiones")
    private int repeticiones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Peso")
    private int peso;
    @JoinColumn(name = "RutinaEjercicio_idRutinaEjercicio", referencedColumnName = "idRutinaEjercicio", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Rutinaejercicio rutinaejercicio;

    public Series() {
    }

    public Series(SeriesPK seriesPK) {
        this.seriesPK = seriesPK;
    }

    public Series(SeriesPK seriesPK, int numeroSerie, int repeticiones, int peso) {
        this.seriesPK = seriesPK;
        this.numeroSerie = numeroSerie;
        this.repeticiones = repeticiones;
        this.peso = peso;
    }

    public Series(int idSeries, int rutinaEjercicioidRutinaEjercicio) {
        this.seriesPK = new SeriesPK(idSeries, rutinaEjercicioidRutinaEjercicio);
    }

    public SeriesPK getSeriesPK() {
        return seriesPK;
    }

    public void setSeriesPK(SeriesPK seriesPK) {
        this.seriesPK = seriesPK;
    }

    public int getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(int numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public Rutinaejercicio getRutinaejercicio() {
        return rutinaejercicio;
    }

    public void setRutinaejercicio(Rutinaejercicio rutinaejercicio) {
        this.rutinaejercicio = rutinaejercicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seriesPK != null ? seriesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Series)) {
            return false;
        }
        Series other = (Series) object;
        if ((this.seriesPK == null && other.seriesPK != null) || (this.seriesPK != null && !this.seriesPK.equals(other.seriesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.Series[ seriesPK=" + seriesPK + " ]";
    }
    
}
