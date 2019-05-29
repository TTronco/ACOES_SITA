/* Tarea 3: Aplicación Completa.
 * @author Grupo 1:
 *          - Tarun D. 
            - Salvador C. F.
            - Iman H. M.
            - Antonio P. C.
 */
package negocio.correo;

import Modelo.Agente;
import Modelo.Apadrinar;
import Modelo.Correo;
import Modelo.Niño;
import Modelo.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class enviarNegocio implements enviarNegocioLocal {

    @PersistenceContext(unitName = "T3")
    EntityManager em;
    
    @Override
    public void guardarCarta(Correo c, String n, Usuario u) throws CorreoException{
        
        
        
        if(n == null)
            throw new ListaVaciaException();
        
        int kid_code = Integer.parseInt(n.substring(0,1));
        
        Niño ninio = em.find(Niño.class, kid_code);
        
        // Usuario válido y niño válido, guardar carta.
        c.setNene(ninio); c.setUser(u);
        Date hoy = new Date();
        c.setFecha_envio(hoy);
        
        /*
        List<Correo> lista = u.getCorreoList();
        lista.add(c);
        u.setCorreoList(lista);        
        em.merge(u);
        
        lista = ninio.getCorreoList();
        lista.add(c);
        ninio.setCorreoList(lista);        
        em.merge(n);
        */
        em.persist(c);
        
        
        
    }
    
    @Override
    public List<String> lista_ninos(Usuario u){
        Query q = em.createNamedQuery("findUserApList");
        q.setParameter("fsocio", u.getNumSocio());       
        List<Niño> lista =  q.getResultList();
        
        List<String> result = new ArrayList<>();
        System.out.println(u.getApadrinarList());        
        for(Niño n : lista)
            result.add(n.getCodigo() +  " " + n.getNombre());
         
        return result;
    }
    
    @Override
    public void enviarContenedor(Correo c){
        em.persist(c);
    }
}
