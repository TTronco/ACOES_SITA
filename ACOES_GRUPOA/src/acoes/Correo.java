/* Tarea 1: Modelo E/R y entidades JPA. Sistemas de Informacion para Internet.
 * @author Grupo 1:
 *          - Tarun D. 
            - Salvador C. F.
            - Iman H. M.
            - Antonio P. C.
 */

package acoes;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Correo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codigo;   
    
    private String tipo;
    
    private boolean moderado;
    
    private String contenido;
    
    @ManyToOne
    private Usuario user;
    
    @ManyToOne
    private Niño nene;

    
    //Constructors

    public Correo() {
    }

    public Correo(String tipo, boolean moderado, String contenido) {
        this.tipo = tipo;
        this.moderado = moderado;
        this.contenido = contenido;
    }
       
    
    // Getters & setters  
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean getModerado() {
        return moderado;
    }

    public void setModerado(boolean moderado) {
        this.moderado = moderado;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Niño getNene() {
        return nene;
    }

    public void setNene(Niño nene) {
        this.nene = nene;
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
        return "entities.Correo[ codigo=" + codigo + " ]";
    }
    
}
