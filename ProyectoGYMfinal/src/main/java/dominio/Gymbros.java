/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

/**
 *
 * @author Alumno Mañana
 */
public class Gymbros {
    private int idUsuario;
    private int idUsuarioAmigo;

    public Gymbros(int idUsuario, int idUsuarioAmigo) {
        this.idUsuario = idUsuario;
        this.idUsuarioAmigo = idUsuarioAmigo;
    }

    public Gymbros(int idUsuarioAmigo) {
        this.idUsuarioAmigo = idUsuarioAmigo;
    }

    public Gymbros() {
    }

    
    
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdUsuarioAmigo() {
        return idUsuarioAmigo;
    }

    public void setIdUsuarioAmigo(int idUsuarioAmigo) {
        this.idUsuarioAmigo = idUsuarioAmigo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.idUsuario;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Gymbros other = (Gymbros) obj;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{idUsuario=").append(idUsuario);
        sb.append(", idUsuarioAmigo=").append(idUsuarioAmigo);
        sb.append('}');
        return sb.toString();
    }
    
    
    
}
