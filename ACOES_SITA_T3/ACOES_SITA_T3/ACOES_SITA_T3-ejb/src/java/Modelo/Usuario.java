/* Tarea 1: Modelo E/R y entidades JPA. Sistemas de Informacion para Internet.

* @author Grupo 1:

*          - Tarun D.

            - Salvador C. F.

            - Iman H. M.

            - Antonio P. C.

*/

 

package Modelo;

 

import java.io.Serializable;

import java.util.Date;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;

import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.persistence.OneToMany;

import javax.persistence.Temporal;

import javax.persistence.TemporalType;

 

 

@Entity
@NamedQueries({
    @NamedQuery(name = "findUserName", query="SELECT upper(u.usuario) FROM Usuario u "),
    @NamedQuery(name = "findUserAll", query="SELECT u FROM Usuario u "),
    @NamedQuery(name = "findUser", query="SELECT u FROM Usuario u where u.usuario = :fuser and u.contrasenia= :fpass ")    
})

public class Usuario implements Serializable {

 

    private static final long serialVersionUID = 1L;

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer numSocio;   

    

    protected String nombre;

    protected String apellidos;

 

    protected String estado;

 

    protected String nif;

 

    protected String direccion;

 

    protected String poblacion;

 

    protected String codPostal; //cambio double -> integer

 

    protected String telefonoFijo; //cambio double -> integer

   

    protected String telefonoMovil; //cambio double -> integer

 

    protected String relacion;

 

    protected Character certificado;

 

    @Temporal(TemporalType.DATE)

    protected Date fechaAlta;

 

    @Temporal(TemporalType.DATE)

    protected Date fechaBaja;

   

    protected String observaciones;

   

    /* LOGIN */

    protected String usuario;

    protected String contrasenia;

   

    @OneToMany(mappedBy = "user")

    private List<Correo> correoList;

   

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioNumSocio")
    private List<Apadrinar> apadrinarList;

 

    public Usuario() {

    }

   

    public Usuario(String usuario, String contrasenia) {

        this.usuario = usuario;

        this.contrasenia = contrasenia;       

    }

   

    public Usuario(String nombre, String apellidos, String nif) {

        this.nombre = nombre;

        this.apellidos = apellidos;

        this.nif = nif;

    }

 

    public Usuario(String nombre, String apellidos, String nif, String direccion, String poblacion, String codPostal, String telefonoMovil, String usuario, String contrasenia) {

        this.nombre = nombre;

        this.apellidos = apellidos;

        this.nif = nif;

        this.direccion = direccion;

        this.poblacion = poblacion;

        this.codPostal = codPostal;

        this.telefonoMovil = telefonoMovil;

        this.usuario = usuario;

        this.contrasenia = contrasenia;

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

 

    public String getCodPostal() {

        return codPostal;

    }

 

    public void setCodPostal(String codPostal) {

        this.codPostal = codPostal;

    }

 

    public String getTelefonoFijo() {

        return telefonoFijo;

    }

 

    public void setTelefonoFijo(String telefonoFijo) {

        this.telefonoFijo = telefonoFijo;

    }

 

    public String getTelefonoMovil() {

        return telefonoMovil;

    }

 

    public Integer getNumSocio() {

        return numSocio;

    }

 

    public void setNumSocio(Integer numSocio) {

        this.numSocio = numSocio;

    }

 

    public void setTelefonoMovil(String telefonoMovil) {

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

 

    public String getUsuario() {

        return usuario;

    }

 

    public void setUsuario(String usuario) {

        this.usuario = usuario;

    }

 

    public String getContrasenia() {

        return contrasenia;

    }

 

    public void setContrasenia(String contrasenia) {

        this.contrasenia = contrasenia;

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