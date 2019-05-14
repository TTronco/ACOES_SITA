/* Tarea 1: Modelo E/R y entidades JPA. Sistemas de Informacion para Internet.
 * @author Grupo 1:
 *          - Tarun D. 
            - Salvador C. F.
            - Iman H. M.
            - Antonio P. C.
 */

package Modelo;

import java.util.List;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import static javax.persistence.DiscriminatorType.STRING;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="DISC", discriminatorType=STRING)
@DiscriminatorValue("AG")
@NamedQuery(name = "findAgente", query="SELECT u FROM Agente u where u.usuario = :fuser and u.contrasenia= :fpass")
public class Agente extends Usuario {

    private static final long serialVersionUID = 1L;
    private String idCentro;
    private List<Niño> niñoList;
    
    
    //Constructors

    public Agente(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;        
    }

    public Agente(List<Niño> niñoList) {
        this.niñoList = niñoList;
    }

    public Agente() {
        
    }
        
    // Getters & setters  
    
    public String getIdCentro() {
        return idCentro;
    }

    public void setIdCentro(String idCentro) {
        this.idCentro = idCentro;
    }

    public List<Niño> getNiñoList() {
        return niñoList;
    }

    public void setNiñoList(List<Niño> niñoList) {
        this.niñoList = niñoList;
    }
    
    
}
