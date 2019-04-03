/* Tarea 1: Modelo E/R y entidades JPA. Sistemas de Informacion para Internet.
 * @author Grupo 1:
 *          - Tarun D. 
            - Salvador C. F.
            - Iman H. M.
            - Antonio P. C.
 */

package acoes;

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
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Alumno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codigo;
    
    @Column(name = "Nombre", nullable = false)
    private String nombre;
    
    @Column(name = "Apellidos", nullable = false)
    private String apellidos;
    
    private String estado;
   
    @Column(name = "Beca", nullable = false)
    private Character beca;
    
    @Column(name = "Sexo", nullable = false)
    private String sexo;
    
    private String foto;
   
    @Temporal(TemporalType.DATE)
    @Column(name = "FechaNacimiento", nullable = false)
    private Date fechaNacimiento;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "FechaEntrada", nullable = false)
    private Date fechaEntrada;
   
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;
   
    @Column(name = "Centro", nullable = false)
    private String centro;
    
    private String observaciones;
    
    @OneToMany(mappedBy = "alumno")
    private List<Correo> correoList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alumnoCodigo")
    private List<Apadrinar> apadrinarList;

    //Constructors

    public Alumno() {
    }

    public Alumno(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }
    
       
    // Getters & setters  
    
    
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Character getBeca() {
        return beca;
    }

    public void setBeca(Character beca) {
        this.beca = beca;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public List<Correo> getCorreoList() {
        return correoList;
    }

    public void setCorreoList(List<Correo> correoList) {
        this.correoList = correoList;
    }

    public List<Apadrinar> getApadrinarList() {
        return apadrinarList;
    }

    public void setApadrinarList(List<Apadrinar> apadrinarList) {
        this.apadrinarList = apadrinarList;
    }
        
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alumno)) {
            return false;
        }
        Alumno other = (Alumno) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }
    
    
    @Override
    public String toString() {
        return "entities.Alumno[ codigo=" + codigo + " ]";
    }
    
}
