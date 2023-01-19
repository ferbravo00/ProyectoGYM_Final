/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Alumno Mañana
 */
@Entity
@Table(name = "rutina")
@NamedQueries({
    @NamedQuery(name = "Rutina.findAll", query = "SELECT r FROM Rutina r"),
    @NamedQuery(name = "Rutina.findByIdRutina", query = "SELECT r FROM Rutina r WHERE r.rutinaPK.idRutina = :idRutina"),
    @NamedQuery(name = "Rutina.findByNombre", query = "SELECT r FROM Rutina r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "Rutina.findByUsuarioidUsuario", query = "SELECT r FROM Rutina r WHERE r.rutinaPK.usuarioidUsuario = :usuarioidUsuario")})
public class Rutina implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RutinaPK rutinaPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rutina")
    private List<Rutinaejercicio> rutinaejercicioList;
    @JoinColumn(name = "Usuario_idUsuario", referencedColumnName = "idUsuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Rutina() {
    }

    public Rutina(RutinaPK rutinaPK) {
        this.rutinaPK = rutinaPK;
    }

    public Rutina(RutinaPK rutinaPK, String nombre) {
        this.rutinaPK = rutinaPK;
        this.nombre = nombre;
    }

    public Rutina(int idRutina, int usuarioidUsuario) {
        this.rutinaPK = new RutinaPK(idRutina, usuarioidUsuario);
    }

    public RutinaPK getRutinaPK() {
        return rutinaPK;
    }

    public void setRutinaPK(RutinaPK rutinaPK) {
        this.rutinaPK = rutinaPK;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rutinaPK != null ? rutinaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rutina)) {
            return false;
        }
        Rutina other = (Rutina) object;
        if ((this.rutinaPK == null && other.rutinaPK != null) || (this.rutinaPK != null && !this.rutinaPK.equals(other.rutinaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.Rutina[ rutinaPK=" + rutinaPK + " ]";
    }
    
}
