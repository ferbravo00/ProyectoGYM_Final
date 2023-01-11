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
public class RutinaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idRutina")
    private int idRutina;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Usuario_idUsuario")
    private int usuarioidUsuario;

    public RutinaPK() {
    }

    public RutinaPK(int idRutina, int usuarioidUsuario) {
        this.idRutina = idRutina;
        this.usuarioidUsuario = usuarioidUsuario;
    }

    public int getIdRutina() {
        return idRutina;
    }

    public void setIdRutina(int idRutina) {
        this.idRutina = idRutina;
    }

    public int getUsuarioidUsuario() {
        return usuarioidUsuario;
    }

    public void setUsuarioidUsuario(int usuarioidUsuario) {
        this.usuarioidUsuario = usuarioidUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idRutina;
        hash += (int) usuarioidUsuario;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RutinaPK)) {
            return false;
        }
        RutinaPK other = (RutinaPK) object;
        if (this.idRutina != other.idRutina) {
            return false;
        }
        if (this.usuarioidUsuario != other.usuarioidUsuario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.RutinaPK[ idRutina=" + idRutina + ", usuarioidUsuario=" + usuarioidUsuario + " ]";
    }
    
}
