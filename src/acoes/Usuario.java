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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "USUARIO")
@XmlRootElement
/*
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByNumSocio", query = "SELECT u FROM Usuario u WHERE u.numSocio = :numSocio")
    , @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "Usuario.findByApellidos", query = "SELECT u FROM Usuario u WHERE u.apellidos = :apellidos")
    , @NamedQuery(name = "Usuario.findByApellido2", query = "SELECT u FROM Usuario u WHERE u.apellido2 = :apellido2")
    , @NamedQuery(name = "Usuario.findByEstado", query = "SELECT u FROM Usuario u WHERE u.estado = :estado")
    , @NamedQuery(name = "Usuario.findByNif", query = "SELECT u FROM Usuario u WHERE u.nif = :nif")
    , @NamedQuery(name = "Usuario.findByDireccion", query = "SELECT u FROM Usuario u WHERE u.direccion = :direccion")
    , @NamedQuery(name = "Usuario.findByPoblacion", query = "SELECT u FROM Usuario u WHERE u.poblacion = :poblacion")
    , @NamedQuery(name = "Usuario.findByCodPostal", query = "SELECT u FROM Usuario u WHERE u.codPostal = :codPostal")
    , @NamedQuery(name = "Usuario.findByTelefonoFijo", query = "SELECT u FROM Usuario u WHERE u.telefonoFijo = :telefonoFijo")
    , @NamedQuery(name = "Usuario.findByTelefonoMovil", query = "SELECT u FROM Usuario u WHERE u.telefonoMovil = :telefonoMovil")
    , @NamedQuery(name = "Usuario.findByRelacion", query = "SELECT u FROM Usuario u WHERE u.relacion = :relacion")
    , @NamedQuery(name = "Usuario.findByCertificado", query = "SELECT u FROM Usuario u WHERE u.certificado = :certificado")
    , @NamedQuery(name = "Usuario.findByFechaAlta", query = "SELECT u FROM Usuario u WHERE u.fechaAlta = :fechaAlta")
    , @NamedQuery(name = "Usuario.findByFechaBaja", query = "SELECT u FROM Usuario u WHERE u.fechaBaja = :fechaBaja")
    , @NamedQuery(name = "Usuario.findByObservaciones", query = "SELECT u FROM Usuario u WHERE u.observaciones = :observaciones")})
*/
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NUM_SOCIO")
    private String numSocio;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "APELLIDO1")
    private String apellido1;
    @Basic(optional = false)
    @Column(name = "APELLIDO2")
    private String apellido2;
    @Column(name = "ESTADO")
    private String estado;
    @Basic(optional = false)
    @Column(name = "NIF")
    private String nif;
    @Basic(optional = false)
    @Column(name = "DIRECCION")
    private String direccion;
    @Basic(optional = false)
    @Column(name = "POBLACION")
    private String poblacion;
    @Basic(optional = false)
    @Column(name = "COD_POSTAL")
    private double codPostal;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TELEFONO_FIJO")
    private Double telefonoFijo;
    @Basic(optional = false)
    @Column(name = "TELEFONO_MOVIL")
    private double telefonoMovil;
    @Basic(optional = false)
    @Column(name = "RELACION")
    private String relacion;
    @Column(name = "CERTIFICADO")
    private Character certificado;
    @Basic(optional = false)
    @Column(name = "FECHA_ALTA")
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;
    @Column(name = "FECHA_BAJA")
    @Temporal(TemporalType.DATE)
    private Date fechaBaja;
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @OneToMany(mappedBy = "usuarioNumSocio")
    private List<Correo> correoList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Agente agente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioNumSocio")
    private List<Apadrinar> apadrinarList;

    public Usuario() {
    }

    public Usuario(String numSocio) {
        this.numSocio = numSocio;
    }

    public Usuario(String numSocio, String nombre, String apellido1, String apellido2, String nif, String direccion, String poblacion, double codPostal, double telefonoMovil, String relacion, Date fechaAlta) {
        this.numSocio = numSocio;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.nif = nif;
        this.direccion = direccion;
        this.poblacion = poblacion;
        this.codPostal = codPostal;
        this.telefonoMovil = telefonoMovil;
        this.relacion = relacion;
        this.fechaAlta = fechaAlta;
    }

    public String getNumSocio() {
        return numSocio;
    }

    public void setNumSocio(String numSocio) {
        this.numSocio = numSocio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
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

    @XmlTransient
    public List<Correo> getCorreoList() {
        return correoList;
    }

    public void setCorreoList(List<Correo> correoList) {
        this.correoList = correoList;
    }

    public Agente getAgente() {
        return agente;
    }

    public void setAgente(Agente agente) {
        this.agente = agente;
    }

    @XmlTransient
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
        return "acoes.Usuario[ numSocio=" + numSocio + " ]";
    }
    
}
