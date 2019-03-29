/* Tarea 1: Modelo E/R y entidades JPA. Sistemas de Informacion para Internet.
 * @author Grupo 1:
 *          - Tarun D. 
            - Salvador C. F.
            - Iman H. M.
            - Antonio P. C.
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


@Entity
@Table(name = "CORREO")
@XmlRootElement
/*
@NamedQueries({
    @NamedQuery(name = "Correo.findAll", query = "SELECT c FROM Correo c")
    , @NamedQuery(name = "Correo.findByCodigo", query = "SELECT c FROM Correo c WHERE c.codigo = :codigo")
    , @NamedQuery(name = "Correo.findByTipo", query = "SELECT c FROM Correo c WHERE c.tipo = :tipo")
    , @NamedQuery(name = "Correo.findByModerado", query = "SELECT c FROM Correo c WHERE c.moderado = :moderado")
    , @NamedQuery(name = "Correo.findByNumSocio11", query = "SELECT c FROM Correo c WHERE c.numSocio11 = :numSocio11")
    , @NamedQuery(name = "Correo.findByContenido", query = "SELECT c FROM Correo c WHERE c.contenido = :contenido")})
*/
public class Correo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private String codigo;
    @Basic(optional = false)
    @Column(name = "TIPO")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "MODERADO")
    private Character moderado;
    @Basic(optional = false)
    @Column(name = "NUM_SOCIO11")
    private String numSocio11;
    @Basic(optional = false)
    @Column(name = "CONTENIDO")
    private String contenido;
    @JoinColumn(name = "NI\u00d1O_CODIGO", referencedColumnName = "CODIGO")
    @ManyToOne
    private Niño niñoCodigo;
    @JoinColumn(name = "USUARIO_NUM_SOCIO", referencedColumnName = "NUM_SOCIO")
    @ManyToOne
    private Usuario usuarioNumSocio;

    public Correo() {
    }

    public Correo(String codigo) {
        this.codigo = codigo;
    }

    public Correo(String codigo, String tipo, Character moderado, String numSocio11, String contenido) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.moderado = moderado;
        this.numSocio11 = numSocio11;
        this.contenido = contenido;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Character getModerado() {
        return moderado;
    }

    public void setModerado(Character moderado) {
        this.moderado = moderado;
    }

    public String getNumSocio11() {
        return numSocio11;
    }

    public void setNumSocio11(String numSocio11) {
        this.numSocio11 = numSocio11;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
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
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Correo)) {
            return false;
        }
        Correo other = (Correo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "acoes.Correo[ codigo=" + codigo + " ]";
    }
    
}
