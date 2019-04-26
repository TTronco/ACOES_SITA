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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Apadrinar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    // Cantidad donada por el padrino.
    private double donacion;    
    
    // Fechas para tener el control de cuándo fue apadrinado el niño.
    @Temporal(TemporalType.DATE)
    private Date fecha_inicio;
    
    @Temporal(TemporalType.DATE)
    private Date fecha_fin;
     
   
    @JoinColumn(name = "niño_codigo",nullable = false)
    @ManyToOne (optional = false)
    private Niño niñoCodigo;
    
    @JoinColumn(name = "usuario_numSocio", nullable = false)
    @ManyToOne (optional = false)
    private Usuario usuarioNumSocio;
    
    //Constructors

    public Apadrinar() {
    }

    public Apadrinar(double donacion, Date fecha_inicio, Niño niñoCodigo, Usuario usuarioNumSocio) {
        this.donacion = donacion;
        this.fecha_inicio = fecha_inicio;
        this.niñoCodigo = niñoCodigo;
        this.usuarioNumSocio = usuarioNumSocio;
    }
    
    
    
    // Getters & setters  
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Apadrinar)) {
            return false;
        }
        Apadrinar other = (Apadrinar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Apadrinar[ id=" + id + " ]";
    }
    
}
