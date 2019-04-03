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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author imanb
 */
@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer numSocio;    
    
    @Column(name = "Nombre", nullable = false)
    private String nombre;
 
    @Column(name = "Apellido", nullable = false)
    private String apellido;

    private String estado;
  
    @Column(name = "NIF", nullable = false)
    private String nif;

    @Column(name = "Direccion", nullable = false)
    private String direccion;

    @Column(name = "Poblacion", nullable = false)
    private String poblacion;

    @Column(name = "CodPostal", nullable = false)
    private double codPostal;

    private Double telefonoFijo;
    
    @Column(name = "TlfMovil", nullable = false)
    private double telefonoMovil;

    @Column(name = "Relacion", nullable = false)
    private String relacion;

    private Character certificado;

    @Column(name = "FechaAlta", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;
  
    @Temporal(TemporalType.DATE)
    private Date fechaBaja;
    
    private String observaciones;
    
    @OneToMany(mappedBy = "user")
    private List<Correo> correoList;
    
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "usuarioNumSocio")
    private List<Apadrinar> apadrinarList;

    public Usuario() {
    }

    public Usuario(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public double getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(double codPostal) {
        this.codPostal = codPostal;
    }

    public Double getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(Double telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public double getTelefonoMovil() {
        return telefonoMovil;
    }

    public Integer getNumSocio() {
        return numSocio;
    }

    public void setNumSocio(Integer numSocio) {
        this.numSocio = numSocio;
    }

    public void setTelefonoMovil(double telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public String getRelacion() {
        return relacion;
    }

    public void setRelacion(String relacion) {
        this.relacion = relacion;
    }

    public Character getCertificado() {
        return certificado;
    }

    public void setCertificado(Character certificado) {
        this.certificado = certificado;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
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
        hash += (numSocio != null ? numSocio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.numSocio == null && other.numSocio != null) || (this.numSocio != null && !this.numSocio.equals(other.numSocio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Usuario[ numSocio=" + numSocio + " ]";
    }
    
}
