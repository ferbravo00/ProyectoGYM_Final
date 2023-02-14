/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Fer
 */
@Entity
@Table(name = "rutina")
@NamedQueries({
    @NamedQuery(name = "Rutina.findAll", query = "SELECT r FROM Rutina r"),
    @NamedQuery(name = "Rutina.findByIdRutina", query = "SELECT r FROM Rutina r WHERE r.idRutina = :idRutina"),
    @NamedQuery(name = "Rutina.findByUser", query = "SELECT r FROM Rutina r WHERE r.usuarioidUsuario = :usuarioidUsuario"),
    @NamedQuery(name = "Rutina.findByNombre", query = "SELECT r FROM Rutina r WHERE r.nombre = :nombre")})
public class Rutina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idRutina")
    private Integer idRutina;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rutinaidRutina")
    private List<Rutinaejercicio> rutinaejercicioList;
    @JoinColumn(name = "usuario_idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false)
    private Usuario usuarioidUsuario;

    public Rutina() {
    }

    public Rutina(Integer idRutina) {
        this.idRutina = idRutina;
    }

    public Rutina(Integer idRutina, String nombre) {
        this.idRutina = idRutina;
        this.nombre = nombre;
    }

    public Integer getIdRutina() {
        return idRutina;
    }

    public void setIdRutina(Integer idRutina) {
        this.idRutina = idRutina;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Rutinaejercicio> getRutinaejercicioList() {
        return rutinaejercicioList;
    }

    public void setRutinaejercicioList(List<Rutinaejercicio> rutinaejercicioList) {
        this.rutinaejercicioList = rutinaejercicioList;
    }

    public Usuario getUsuarioidUsuario() {
        return usuarioidUsuario;
    }

    public void setUsuarioidUsuario(Usuario usuarioidUsuario) {
        this.usuarioidUsuario = usuarioidUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRutina != null ? idRutina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rutina)) {
            return false;
        }
        Rutina other = (Rutina) object;
        if ((this.idRutina == null && other.idRutina != null) || (this.idRutina != null && !this.idRutina.equals(other.idRutina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.Rutina[ idRutina=" + idRutina + " ]";
    }
    
}
