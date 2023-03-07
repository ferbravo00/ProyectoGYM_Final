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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 *
 * @author Fer
 */
@Entity
@Table(name = "ejercicio")
@NamedQueries({
    @NamedQuery(name = "Ejercicio.findAll", query = "SELECT e FROM Ejercicio e"),
    @NamedQuery(name = "Ejercicio.findByIdEjercicio", query = "SELECT e FROM Ejercicio e WHERE e.idEjercicio = :idEjercicio"),
    @NamedQuery(name = "Ejercicio.findByNombre", query = "SELECT e FROM Ejercicio e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Ejercicio.findByDescripcion", query = "SELECT e FROM Ejercicio e WHERE e.descripcion = :descripcion"),
    @NamedQuery(name = "Ejercicio.findByParteCuerpo", query = "SELECT e FROM Ejercicio e WHERE e.parteCuerpo = :parteCuerpo")})
public class Ejercicio implements Serializable {

    @Size(max = 45)
    @Column(name = "Nombre")
    private String nombre;
    @Lob
    @Column(name = "Foto")
    private byte[] foto;
    @Size(max = 500)
    @Column(name = "Descripcion")
    private String descripcion;
    @Size(max = 45)
    @Column(name = "ParteCuerpo")
    private String parteCuerpo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ejercicioidEjercicio")
    private List<Rutinaejercicio> rutinaejercicioList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEjercicio")
    private Integer idEjercicio;
    @Transient
    private String fotobase64;
    

    public Ejercicio() {
    }

    public Ejercicio(Integer idEjercicio) {
        this.idEjercicio = idEjercicio;
    }
   
    
    public Ejercicio(String nombre, byte[] foto, String descripcion, String parteCuerpo) {
        this.nombre = nombre;
        this.foto = foto;
        this.descripcion = descripcion;
        this.parteCuerpo = parteCuerpo;
    }
    
    public Ejercicio(Integer idEjercicio, String nombre, byte[] foto, String descripcion, String parteCuerpo) {
        this.idEjercicio = idEjercicio;
        this.nombre = nombre;
        this.foto = foto;
        this.descripcion = descripcion;
        this.parteCuerpo = parteCuerpo;
    }

    
    public Integer getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(Integer idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    
    public String getFotobase64() {
        return fotobase64;
    }

    public void setFotobase64(String fotobase64) {
        this.fotobase64 = fotobase64;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEjercicio != null ? idEjercicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ejercicio)) {
            return false;
        }
        Ejercicio other = (Ejercicio) object;
        if ((this.idEjercicio == null && other.idEjercicio != null) || (this.idEjercicio != null && !this.idEjercicio.equals(other.idEjercicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.Ejercicio[ idEjercicio=" + idEjercicio + " ]";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getParteCuerpo() {
        return parteCuerpo;
    }

    public void setParteCuerpo(String parteCuerpo) {
        this.parteCuerpo = parteCuerpo;
    }

    public List<Rutinaejercicio> getRutinaejercicioList() {
        return rutinaejercicioList;
    }

    public void setRutinaejercicioList(List<Rutinaejercicio> rutinaejercicioList) {
        this.rutinaejercicioList = rutinaejercicioList;
    }
    
}
