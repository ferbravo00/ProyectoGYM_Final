/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @NamedQuery(name = "Series.findByIdSeries", query = "SELECT s FROM Series s WHERE s.idSeries = :idSeries"),
    @NamedQuery(name = "Series.findByNumeroSerie", query = "SELECT s FROM Series s WHERE s.numeroSerie = :numeroSerie"),
    @NamedQuery(name = "Series.findByRepeticiones", query = "SELECT s FROM Series s WHERE s.repeticiones = :repeticiones"),
    @NamedQuery(name = "Series.findByPeso", query = "SELECT s FROM Series s WHERE s.peso = :peso")})
public class Series implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSeries")
    private Integer idSeries;
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
    @JoinColumn(name = "rutinaejercicio_idRutinaEjercicio", referencedColumnName = "idRutinaEjercicio")
    @ManyToOne(optional = false)
    private Rutinaejercicio rutinaejercicioidRutinaEjercicio;

    public Series() {
    }

    public Series(Integer idSeries) {
        this.idSeries = idSeries;
    }

    public Series(Integer idSeries, int numeroSerie, int repeticiones, int peso) {
        this.idSeries = idSeries;
        this.numeroSerie = numeroSerie;
        this.repeticiones = repeticiones;
        this.peso = peso;
    }

    public Integer getIdSeries() {
        return idSeries;
    }

    public void setIdSeries(Integer idSeries) {
        this.idSeries = idSeries;
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

    public Rutinaejercicio getRutinaejercicioidRutinaEjercicio() {
        return rutinaejercicioidRutinaEjercicio;
    }

    public void setRutinaejercicioidRutinaEjercicio(Rutinaejercicio rutinaejercicioidRutinaEjercicio) {
        this.rutinaejercicioidRutinaEjercicio = rutinaejercicioidRutinaEjercicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSeries != null ? idSeries.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Series)) {
            return false;
        }
        Series other = (Series) object;
        if ((this.idSeries == null && other.idSeries != null) || (this.idSeries != null && !this.idSeries.equals(other.idSeries))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.Series[ idSeries=" + idSeries + " ]";
    }
    
}
