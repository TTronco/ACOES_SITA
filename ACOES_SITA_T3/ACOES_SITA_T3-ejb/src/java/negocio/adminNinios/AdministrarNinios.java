/* Tarea 3: Aplicación Completa.
 * @author Grupo 1:
 *          - Tarun D. 
            - Salvador C. F.
            - Iman H. M.
            - Antonio P. C.
 */

package negocio.adminNinios;

import Modelo.Niño;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class AdministrarNinios implements AdministrarNiniosLocal {
    
    @PersistenceContext(unitName = "T3")
    EntityManager em;
    
    @Override
    public void aniadirNinio (Niño n){
        em.persist(n);
    }
    
    @Override
    public List<String> ninios(){
        List<Niño>  lista = em.createNamedQuery("findKid").getResultList();
        List<String> result = new ArrayList<>();
        
        for(Niño n : lista){
            if(n.getApellidos()!= null)
                result.add(n.getCodigo() + " " + n.getNombre() + " " + n.getApellidos());
            else
                result.add(n.getCodigo() + " " + n.getNombre());
        }
            
        return result;
    }
    
    @Override
    public void eliminarNinio(int id) throws AdministrarException{
        Niño n = em.find(Niño.class, id);
        
        if( n == null)
            throw new NinioNoEncontradoException();
        
        em.remove(n);
    }
}
