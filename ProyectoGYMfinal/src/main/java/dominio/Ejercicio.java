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
public class Ejercicio {
    private int idEjercicio;
    private String Nombre;
    private String foto;
    private String Descripcion;
    private String ParteCuerpo;

    public Ejercicio(int idEjercicio, String Nombre, String foto, String Descripcion, String ParteCuerpo) {
        this.idEjercicio = idEjercicio;
        this.Nombre = Nombre;
        this.foto = foto;
        this.Descripcion = Descripcion;
        this.ParteCuerpo = ParteCuerpo;
    }

    public Ejercicio(String Nombre, String foto, String Descripcion, String ParteCuerpo) {
        this.Nombre = Nombre;
        this.foto = foto;
        this.Descripcion = Descripcion;
        this.ParteCuerpo = ParteCuerpo;
    }

    public Ejercicio(String ParteCuerpo) {
        this.ParteCuerpo = ParteCuerpo;
    }

    public Ejercicio() {
    }

    
    
    
    public int getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(int idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getParteCuerpo() {
        return ParteCuerpo;
    }

    public void setParteCuerpo(String ParteCuerpo) {
        this.ParteCuerpo = ParteCuerpo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.idEjercicio;
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
        final Ejercicio other = (Ejercicio) obj;
        if (this.idEjercicio != other.idEjercicio) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ejercicio{idEjercicio=").append(idEjercicio);
        sb.append(", Nombre=").append(Nombre);
        sb.append(", foto=").append(foto);
        sb.append(", Descripcion=").append(Descripcion);
        sb.append(", ParteCuerpo=").append(ParteCuerpo);
        sb.append('}');
        return sb.toString();
    }
    
    
    
}
