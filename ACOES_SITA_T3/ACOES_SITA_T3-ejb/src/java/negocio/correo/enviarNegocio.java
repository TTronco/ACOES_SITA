/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

/**
 *
 * @author imanb
 */
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
}
