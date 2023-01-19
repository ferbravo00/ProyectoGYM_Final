/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Alumno Mañana
 */
@Entity
@Table(name = "rutinaejercicio")
@NamedQueries({
    @NamedQuery(name = "Rutinaejercicio.findAll", query = "SELECT r FROM Rutinaejercicio r"),
    @NamedQuery(name = "Rutinaejercicio.findByIdRutinaEjercicio", query = "SELECT r FROM Rutinaejercicio r WHERE r.rutinaejercicioPK.idRutinaEjercicio = :idRutinaEjercicio"),
    @NamedQuery(name = "Rutinaejercicio.findByEjercicioidEjercicio", query = "SELECT r FROM Rutinaejercicio r WHERE r.rutinaejercicioPK.ejercicioidEjercicio = :ejercicioidEjercicio"),
    @NamedQuery(name = "Rutinaejercicio.findByRutinaidRutina", query = "SELECT r FROM Rutinaejercicio r WHERE r.rutinaejercicioPK.rutinaidRutina = :rutinaidRutina"),
    @NamedQuery(name = "Rutinaejercicio.findByFecha", query = "SELECT r FROM Rutinaejercicio r WHERE r.fecha = :fecha")})
public class Rutinaejercicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RutinaejercicioPK rutinaejercicioPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "Ejercicio_idEjercicio", referencedColumnName = "idEjercicio", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ejercicio ejercicio;
    @JoinColumn(name = "Rutina_idRutina", referencedColumnName = "idRutina", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Rutina rutina;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rutinaejercicio")
    private List<Series> seriesList;

    public Rutinaejercicio() {
    }

    public Rutinaejercicio(RutinaejercicioPK rutinaejercicioPK) {
        this.rutinaejercicioPK = rutinaejercicioPK;
    }

    public Rutinaejercicio(RutinaejercicioPK rutinaejercicioPK, Date fecha) {
        this.rutinaejercicioPK = rutinaejercicioPK;
        this.fecha = fecha;
    }

    public Rutinaejercicio(int idRutinaEjercicio, int ejercicioidEjercicio, int rutinaidRutina) {
        this.rutinaejercicioPK = new RutinaejercicioPK(idRutinaEjercicio, ejercicioidEjercicio, rutinaidRutina);
    }

    public RutinaejercicioPK getRutinaejercicioPK() {
        return rutinaejercicioPK;
    }

    public void setRutinaejercicioPK(RutinaejercicioPK rutinaejercicioPK) {
        this.rutinaejercicioPK = rutinaejercicioPK;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Ejercicio getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
    }

    public Rutina getRutina() {
        return rutina;
    }

    public void setRutina(Rutina rutina) {
        this.rutina = rutina;
    }

    public List<Series> getSeriesList() {
        return seriesList;
    }

    public void setSeriesList(List<Series> seriesList) {
        this.seriesList = seriesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rutinaejercicioPK != null ? rutinaejercicioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rutinaejercicio)) {
            return false;
        }
        Rutinaejercicio other = (Rutinaejercicio) object;
        if ((this.rutinaejercicioPK == null && other.rutinaejercicioPK != null) || (this.rutinaejercicioPK != null && !this.rutinaejercicioPK.equals(other.rutinaejercicioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.Rutinaejercicio[ rutinaejercicioPK=" + rutinaejercicioPK + " ]";
    }
    
}
