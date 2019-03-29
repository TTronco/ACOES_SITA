/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acoes;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author imanb
 */
@Entity
@Table(name = "AGENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Agente.findAll", query = "SELECT a FROM Agente a")
    , @NamedQuery(name = "Agente.findByNumSocio", query = "SELECT a FROM Agente a WHERE a.numSocio = :numSocio")
    , @NamedQuery(name = "Agente.findByIdCentro", query = "SELECT a FROM Agente a WHERE a.idCentro = :idCentro")})
public class Agente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NUM_SOCIO")
    private String numSocio;
    @Basic(optional = false)
    @Column(name = "ID_CENTRO")
    private String idCentro;
    @JoinColumn(name = "NUM_SOCIO", referencedColumnName = "NUM_SOCIO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario;
    @OneToMany(mappedBy = "agenteNumSocio")
    private List<Niño> niñoList;

    public Agente() {
    }

    public Agente(String numSocio) {
        this.numSocio = numSocio;
    }

    public Agente(String numSocio, String idCentro) {
        this.numSocio = numSocio;
        this.idCentro = idCentro;
    }

    public String getNumSocio() {
        return numSocio;
    }

    public void setNumSocio(String numSocio) {
        this.numSocio = numSocio;
    }

    public String getIdCentro() {
        return idCentro;
    }

    public void setIdCentro(String idCentro) {
        this.idCentro = idCentro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public List<Niño> getNiñoList() {
        return niñoList;
    }

    public void setNiñoList(List<Niño> niñoList) {
        this.niñoList = niñoList;
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
        if (!(object instanceof Agente)) {
            return false;
        }
        Agente other = (Agente) object;
        if ((this.numSocio == null && other.numSocio != null) || (this.numSocio != null && !this.numSocio.equals(other.numSocio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "acoes.Agente[ numSocio=" + numSocio + " ]";
    }
    
}
