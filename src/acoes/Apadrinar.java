/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acoes;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author imanb
 */
@Entity
@Table(name = "APADRINAR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Apadrinar.findAll", query = "SELECT a FROM Apadrinar a")
    , @NamedQuery(name = "Apadrinar.findByNumSocio1", query = "SELECT a FROM Apadrinar a WHERE a.numSocio1 = :numSocio1")
    , @NamedQuery(name = "Apadrinar.findByApadrinarId", query = "SELECT a FROM Apadrinar a WHERE a.apadrinarId = :apadrinarId")
    , @NamedQuery(name = "Apadrinar.findByDonacion", query = "SELECT a FROM Apadrinar a WHERE a.donacion = :donacion")})
public class Apadrinar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "NUM_SOCIO1")
    private String numSocio1;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "APADRINAR_ID")
    private Double apadrinarId;
    @Basic(optional = false)
    @Column(name = "DONACION")
    private double donacion;
    @JoinColumn(name = "NI\u00d1O_CODIGO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Niño niñoCodigo;
    @JoinColumn(name = "USUARIO_NUM_SOCIO", referencedColumnName = "NUM_SOCIO")
    @ManyToOne(optional = false)
    private Usuario usuarioNumSocio;

    public Apadrinar() {
    }

    public Apadrinar(Double apadrinarId) {
        this.apadrinarId = apadrinarId;
    }

    public Apadrinar(Double apadrinarId, String numSocio1, double donacion) {
        this.apadrinarId = apadrinarId;
        this.numSocio1 = numSocio1;
        this.donacion = donacion;
    }

    public String getNumSocio1() {
        return numSocio1;
    }

    public void setNumSocio1(String numSocio1) {
        this.numSocio1 = numSocio1;
    }

    public Double getApadrinarId() {
        return apadrinarId;
    }

    public void setApadrinarId(Double apadrinarId) {
        this.apadrinarId = apadrinarId;
    }

    public double getDonacion() {
        return donacion;
    }

    public void setDonacion(double donacion) {
        this.donacion = donacion;
    }

    public Niño getNiñoCodigo() {
        return niñoCodigo;
    }

    public void setNiñoCodigo(Niño niñoCodigo) {
        this.niñoCodigo = niñoCodigo;
    }

    public Usuario getUsuarioNumSocio() {
        return usuarioNumSocio;
    }

    public void setUsuarioNumSocio(Usuario usuarioNumSocio) {
        this.usuarioNumSocio = usuarioNumSocio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (apadrinarId != null ? apadrinarId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Apadrinar)) {
            return false;
        }
        Apadrinar other = (Apadrinar) object;
        if ((this.apadrinarId == null && other.apadrinarId != null) || (this.apadrinarId != null && !this.apadrinarId.equals(other.apadrinarId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "acoes.Apadrinar[ apadrinarId=" + apadrinarId + " ]";
    }
    
}
