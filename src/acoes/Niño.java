/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author imanb
 */
@Entity
@Table(name = "NI\u00d1O")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ni\u00f1o.findAll", query = "SELECT n FROM Ni\u00f1o n")
    , @NamedQuery(name = "Ni\u00f1o.findByCodigo", query = "SELECT n FROM Ni\u00f1o n WHERE n.codigo = :codigo")
    , @NamedQuery(name = "Ni\u00f1o.findByNombre", query = "SELECT n FROM Ni\u00f1o n WHERE n.nombre = :nombre")
    , @NamedQuery(name = "Ni\u00f1o.findByApellidos", query = "SELECT n FROM Ni\u00f1o n WHERE n.apellidos = :apellidos")
    , @NamedQuery(name = "Ni\u00f1o.findByApellido2", query = "SELECT n FROM Ni\u00f1o n WHERE n.apellido2 = :apellido2")
    , @NamedQuery(name = "Ni\u00f1o.findByEstado", query = "SELECT n FROM Ni\u00f1o n WHERE n.estado = :estado")
    , @NamedQuery(name = "Ni\u00f1o.findByBeca", query = "SELECT n FROM Ni\u00f1o n WHERE n.beca = :beca")
    , @NamedQuery(name = "Ni\u00f1o.findBySexo", query = "SELECT n FROM Ni\u00f1o n WHERE n.sexo = :sexo")
    , @NamedQuery(name = "Ni\u00f1o.findByFoto", query = "SELECT n FROM Ni\u00f1o n WHERE n.foto = :foto")
    , @NamedQuery(name = "Ni\u00f1o.findByFechaNacimiento", query = "SELECT n FROM Ni\u00f1o n WHERE n.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "Ni\u00f1o.findByFechaEntrada", query = "SELECT n FROM Ni\u00f1o n WHERE n.fechaEntrada = :fechaEntrada")
    , @NamedQuery(name = "Ni\u00f1o.findByFechaAlta", query = "SELECT n FROM Ni\u00f1o n WHERE n.fechaAlta = :fechaAlta")
    , @NamedQuery(name = "Ni\u00f1o.findByCentro", query = "SELECT n FROM Ni\u00f1o n WHERE n.centro = :centro")
    , @NamedQuery(name = "Ni\u00f1o.findByObservaciones", query = "SELECT n FROM Ni\u00f1o n WHERE n.observaciones = :observaciones")})
public class Niño implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private String codigo;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Basic(optional = false)
    @Column(name = "APELLIDO2")
    private String apellido2;
    @Column(name = "ESTADO")
    private String estado;
    @Basic(optional = false)
    @Column(name = "BECA")
    private Character beca;
    @Basic(optional = false)
    @Column(name = "SEXO")
    private String sexo;
    @Column(name = "FOTO")
    private String foto;
    @Basic(optional = false)
    @Column(name = "FECHA_NACIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @Column(name = "FECHA_ENTRADA")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrada;
    @Column(name = "FECHA_ALTA")
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;
    @Basic(optional = false)
    @Column(name = "CENTRO")
    private String centro;
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @OneToMany(mappedBy = "ni\u00f1oCodigo")
    private List<Correo> correoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ni\u00f1oCodigo")
    private List<Apadrinar> apadrinarList;
    @JoinColumn(name = "AGENTE_NUM_SOCIO", referencedColumnName = "NUM_SOCIO")
    @ManyToOne
    private Agente agenteNumSocio;

    public Niño() {
    }

    public Niño(String codigo) {
        this.codigo = codigo;
    }

    public Niño(String codigo, String nombre, String apellidos, String apellido2, Character beca, String sexo, Date fechaNacimiento, Date fechaEntrada, String centro) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.apellido2 = apellido2;
        this.beca = beca;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaEntrada = fechaEntrada;
        this.centro = centro;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
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

    @XmlTransient
    public List<Correo> getCorreoList() {
        return correoList;
    }

    public void setCorreoList(List<Correo> correoList) {
        this.correoList = correoList;
    }

    @XmlTransient
    public List<Apadrinar> getApadrinarList() {
        return apadrinarList;
    }

    public void setApadrinarList(List<Apadrinar> apadrinarList) {
        this.apadrinarList = apadrinarList;
    }

    public Agente getAgenteNumSocio() {
        return agenteNumSocio;
    }

    public void setAgenteNumSocio(Agente agenteNumSocio) {
        this.agenteNumSocio = agenteNumSocio;
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
        if (!(object instanceof Niño)) {
            return false;
        }
        Niño other = (Niño) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "acoes.Ni\u00f1o[ codigo=" + codigo + " ]";
    }
    
}
