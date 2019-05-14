/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.login;

import Modelo.Agente;
import Modelo.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import negocio.registro.CuentaException;
import negocio.registro.CuentaInexistenteException;
import negocio.registro.CuentaInvalidaException;

/**
 *
 * @author imanb
 */
@Stateless
public class LoginNegocio implements LoginNegocioLocal {
    @PersistenceContext(unitName = "T3")
    private EntityManager em;
    
    
    @Override
    public void crearAgente(){
        //Método para crear agentes. Va a haber una serie de agentes predefinidos. Para añadir más agentes, crear objetos de clase Agente y persistirlos.
        //Los agentes no se pueden registrar, son creados por ACOES.
        
        Agente a1 = new Agente("agente", "pw"); em.persist(a1);
        Agente a2 = new Agente("iman", "pw"); em.persist(a2);
        Agente a3 = new Agente("tarun", "pw"); em.persist(a3);
        Agente a4 = new Agente("antonio", "pw"); em.persist(a4);
        
        em.flush();
        
        // Creamos un usuario normal por defecto para pruebas.
        Usuario u = new Usuario("normal", "pw");
        em.persist(u); em.flush();
        
    }
    @Override
    public Usuario compruebaLoginNormal(String user, String password) throws CuentaException {        
        // Solo existe un nombre de usuario, comprobamos solo los usuarios normales.
        Query q = em.createNamedQuery("findUser");
        q.setParameter("fuser", user);
        q.setParameter("fpass", password);
        List<Usuario> us =  q.getResultList();
        
        if(us.isEmpty()){
            throw new CuentaInvalidaException();
        }
        
        return us.get(0);
        
    }
    
    @Override
    public Agente compruebaLoginAg(String user, String password) throws CuentaException {
        // Solo existe un nombre de usuario, comprobamos solo los agentes.
        Query q = em.createNamedQuery("findAgente");
        q.setParameter("fuser", user);
        q.setParameter("fpass", password);
        List<Agente> ag =  q.getResultList();
        
        if(ag.isEmpty()){
            throw new CuentaInvalidaException();
        }        
        return ag.get(0);
    }
    
     @Override
    public Usuario refrescarUsuario(Usuario u) throws CuentaException {
        try{
            compruebaLoginNormal(u.getUsuario(), u.getContrasenia());
            compruebaLoginAg(u.getUsuario(), u.getContrasenia());            
        }catch(CuentaInvalidaException e){
            
        }
                
        Usuario user = em.find(Usuario.class, u.getNumSocio());
        em.refresh(user);
        return user;
    }

    

    
}
