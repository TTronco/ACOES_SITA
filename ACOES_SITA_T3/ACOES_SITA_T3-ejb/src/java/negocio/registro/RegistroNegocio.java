/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.registro;

import Modelo.Usuario;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author imanb
 */

@Stateless
public class RegistroNegocio implements RegistroNegocioLocal {

    @PersistenceContext(unitName = "T3")
    private EntityManager em;

    @Override
    public void registrarUsuario(Usuario u) throws CuentaException{
                
        List<String> list_users = em.createNamedQuery("findUserName").getResultList();

        for(String us : list_users){            
            if(us.equalsIgnoreCase(u.getUsuario())){
                throw new CuentaRepetidaException();
            }
        }
        
              
        
        em.persist(u);  
    }
    
    
}
