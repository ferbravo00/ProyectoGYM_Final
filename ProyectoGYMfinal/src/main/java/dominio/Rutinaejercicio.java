/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * @author Fer
 */
@Entity
@Table(name = "rutinaejercicio")
@NamedQueries({
    @NamedQuery(name = "Rutinaejercicio.findAll", query = "SELECT r FROM Rutinaejercicio r"),
    @NamedQuery(name = "Rutinaejercicio.findByIdRutinaEjercicio", query = "SELECT r FROM Rutinaejercicio r WHERE r.idRutinaEjercicio = :idRutinaEjercicio"),
    @NamedQuery(name = "Rutinaejercicio.findByFecha", query = "SELECT r FROM Rutinaejercicio r WHERE r.fecha = :fecha")})
public class Rutinaejercicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRutinaEjercicio")
    private Integer idRutinaEjercicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "ejercicio_idEjercicio", referencedColumnName = "idEjercicio")
    @ManyToOne(optional = false)
    private Ejercicio ejercicioidEjercicio;
    @JoinColumn(name = "rutina_idRutina", referencedColumnName = "idRutina")
    @ManyToOne(optional = false)
    private Rutina rutinaidRutina;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rutinaejercicioidRutinaEjercicio")
    private List<Series> seriesList;

    public Rutinaejercicio() {
    }

    public Rutinaejercicio(Integer idRutinaEjercicio) {
        this.idRutinaEjercicio = idRutinaEjercicio;
    }

    public Rutinaejercicio(Integer idRutinaEjercicio, Date fecha) {
        this.idRutinaEjercicio = idRutinaEjercicio;
        this.fecha = fecha;
    }

    public Integer getIdRutinaEjercicio() {
        return idRutinaEjercicio;
    }

    public void setIdRutinaEjercicio(Integer idRutinaEjercicio) {
        this.idRutinaEjercicio = idRutinaEjercicio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Ejercicio getEjercicioidEjercicio() {
        return ejercicioidEjercicio;
    }

    public void setEjercicioidEjercicio(Ejercicio ejercicioidEjercicio) {
        this.ejercicioidEjercicio = ejercicioidEjercicio;
    }

    public Rutina getRutinaidRutina() {
        return rutinaidRutina;
    }

    public void setRutinaidRutina(Rutina rutinaidRutina) {
        this.rutinaidRutina = rutinaidRutina;
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
        hash += (idRutinaEjercicio != null ? idRutinaEjercicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rutinaejercicio)) {
            return false;
        }
        Rutinaejercicio other = (Rutinaejercicio) object;
        if ((this.idRutinaEjercicio == null && other.idRutinaEjercicio != null) || (this.idRutinaEjercicio != null && !this.idRutinaEjercicio.equals(other.idRutinaEjercicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.Rutinaejercicio[ idRutinaEjercicio=" + idRutinaEjercicio + " ]";
    }
    
}
