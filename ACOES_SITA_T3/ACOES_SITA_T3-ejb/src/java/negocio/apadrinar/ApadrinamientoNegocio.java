/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.apadrinar;

import Modelo.Apadrinar;
import Modelo.Niño;
import Modelo.Usuario;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author imanb
 */
@Stateless
public class ApadrinamientoNegocio implements ApadrinamientoNegocioLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    EntityManager em;
    
    @Override
    public void crearNiños(){
        List<String> list_kids = em.createNamedQuery("findKidName").getResultList();
        if(!list_kids.contains("Manolo".toUpperCase())){
            // Creamos tres niños nuevos
            Niño n1 = new Niño("Manolo"); em.persist(n1); 
            Niño n2 = new Niño("Pablo");  em.persist(n2);
            Niño n3 = new Niño("álvaro"); em.persist(n3);

            // Y los ponemos como disponibles.
            Apadrinar a1 = new Apadrinar(15.0, null, n1, null); em.persist(a1);
            Apadrinar a2 = new Apadrinar(15.0, null, n2, null); em.persist(a2);
            Apadrinar a3 = new Apadrinar(15.0, null, n3, null); em.persist(a3);
        }        
    }
    @Override
    public int peticiones(){
        // Las peticiones de apadrinamiento sin asignar por parte de usuarios son las que tienen la columna "niño" a null;
        List<Usuario> list_ap = em.createNamedQuery("findSolicitantes").getResultList();
        int result = list_ap.size();
        
        return result;
    }
    
    @Override 
    public Map<Usuario,Niño> disponiblesAp(){
        List<Usuario> lista_users = em.createNamedQuery("findSolicitantes").getResultList();    
        List<Niño> lista_ninos = em.createNamedQuery("findNinios").getResultList(); 
        Map result = new HashMap<Usuario,Niño>();
        System.out.println(lista_users);
        for(Usuario u : lista_users)
            for(Niño n : lista_ninos)
                result.put(u, n);
        
        return result;
    }
    
    @Override
    public void solicitudApadrinar (Usuario u) throws ApadrinarException{
        //Si el usuario no tiene peticiones de apadrinamiento por gestionar, se procede con la solicitud.
        
        List<Usuario> list_us = em.createNamedQuery("findSolicitantes").getResultList();    
        //System.out.println(list_us);
        if(list_us.contains(u))
            throw new ApadrinarNoGestionadoException();
        
        
        // 1. Creamos una fila en apadrinar para el usuario u, en la cual no tiene ningún niño asociado aún. La fecha está a null porque aún no se ha apadrinado.
                   
        Apadrinar a = new Apadrinar(15.0,null, null,u);
        em.persist(a);  
        
    }
    
    @Override
    public void asignar(Usuario u, Niño n) throws ApadrinarException{
        // Hay dos casos: niño disponible y usuario solicitante.
        
        List<Apadrinar> result = em.createNamedQuery("findPeticiones").getResultList();
        Apadrinar a1 = new Apadrinar();
        Apadrinar a2 = new Apadrinar();
        
        for(Apadrinar a: result){
            if(a.getUsuarioNumSocio() != null && a.getUsuarioNumSocio().equals(u) ){              
                //Definir datos apadrinamiento en la tabla apadrinar.
                a.setNiñoCodigo(n);              
                Date d = new Date();
                a.setFecha_inicio(d);
                em.merge(a);                
                a1 = a;
            }
            if(a.getNiñoCodigo() != null && a.getNiñoCodigo().equals(n) && a.getUsuarioNumSocio() == null)
                em.remove(a);
        }
        
        if(a1 == null)
            throw new ApadrinarException(); 
        // En la tabla apadrinar solo debe haber 1 fila que contiene la información sobre el apadrinamiento.
        // Actualizar la lista de apadrinamientos del usuario.
        
        /*
        List<Apadrinar> lu = u.getApadrinarList();
        lu.add(a1);
        u.setApadrinarList(lu);
        em.refresh(em.merge(u));
        */
        // Actualizar la lista de apadrinamientos del usuario.
       //List<Apadrinar> ln = n.getApadrinarList();
       // ln.add(a1);
       // n.setApadrinarList(ln);
        n.setBeca(true);
        em.refresh(em.merge(n));        
        
    }
    
    @Override 
    public List<Apadrinar> apadrinar(){
        List<Apadrinar> result = em.createNamedQuery("findPeticiones").getResultList();
        return result;
    }
    @Override 
    public List<Usuario> usuariosSolicitantes(){
        List<Usuario> result = em.createNamedQuery("findSolicitantes").getResultList();         
        return result;
    }
    
    @Override 
    public List<Niño> niniosDisponibles(){
        List<Niño> result = em.createNamedQuery("findNinios").getResultList(); 
        return result;
    }
    
}
