/* Tarea 3: Aplicación Completa.
 * @author Grupo 1:
 *          - Tarun D. 
            - Salvador C. F.
            - Iman H. M.
            - Antonio P. C.
 */
package negocio.moderar;

import Modelo.Correo;
import Modelo.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



@Stateless
public class moderarNegocio implements moderarNegocioLocal {
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext
    EntityManager em;

    /**
     * @return the number of mails that have not been moderated
     */
    @Override
    public int notificaciones(){
        return em.createNamedQuery("correoNoModerado").getResultList().size();
    }
    
    @Override
    public List<Correo> listaCorreos() {
        List<Correo> lc = em.createNamedQuery("todoCorreo").getResultList();
        return lc;
    } 
    
    @Override
    public void validarCorreo(Correo c){
        em.merge(c);
    }
    
    @Override
    public void rechazarCorreo(Correo c){
        em.merge(c);
    }
}
