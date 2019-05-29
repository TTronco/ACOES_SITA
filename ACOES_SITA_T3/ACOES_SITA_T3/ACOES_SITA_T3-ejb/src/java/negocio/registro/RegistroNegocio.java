/* Tarea 3: Aplicación Completa.
 * @author Grupo 1:
 *          - Tarun D. 
            - Salvador C. F.
            - Iman H. M.
            - Antonio P. C.
 */

package negocio.registro;

import Modelo.Agente;
import Modelo.Apadrinar;
import Modelo.Niño;
import Modelo.Usuario;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    
    @Override
    public void crearAgente(){
        //Método para crear agentes. Va a haber una serie de agentes predefinidos. Para añadir más agentes, crear objetos de clase Agente y persistirlos.
        //Los agentes no se pueden registrar, son creados por ACOES.
        
        List<String> list_users = em.createNamedQuery("findUserName").getResultList();
        if(!list_users.contains("agente".toUpperCase())){
            Agente a1 = new Agente("agente", "pw"); em.persist(a1);
            Agente a2 = new Agente("iman", "pw"); em.persist(a2);
            Agente a3 = new Agente("tarun", "pw"); em.persist(a3);
            Agente a4 = new Agente("antonio", "pw"); em.persist(a4);

            em.flush();

            // Creamos un usuario normal por defecto para pruebas.
            Usuario u = new Usuario("normal", "pw");
            em.persist(u); em.flush();
        }
        
        
        
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
            throw new CuentaInvalidaAgenteException();
        }        
        return ag.get(0);
    }
    
    @Override
    public Usuario refrescarUsuario(Usuario u, String ant_user, String ant_pass) throws CuentaException {
        
        if(u.getNumSocio() != null){ 
            Usuario user = em.find(Usuario.class, u.getNumSocio());
            
            if(user == null)
                throw new CuentaInexistenteException();            
            
            em.refresh(em.merge(u));
            return u;
        }else{
            Usuario user = compruebaLoginNormal(ant_user, ant_pass);
            em.refresh(em.merge(user));
            return user;
        }
        
        
    }
    @Override
    public Agente refrescarAgente(Agente a, String ant_user, String ant_pass) throws CuentaException {
        
         
        
        if(a.getNumSocio() != null){
            Agente ag = em.find(Agente.class, a.getNumSocio());
            if(ag == null){
                throw new CuentaInexistenteException();
            }
            em.refresh(em.merge(a));
            return a;
        }else{
            Agente ag = compruebaLoginAg(ant_user, ant_pass); 
            em.refresh(em.merge(ag));
            return ag;
        }
           
    }
    
    @Override
    public void modificarUsuario(Usuario u, String ant_user, String ant_pass) throws CuentaException{
        
        Usuario user = compruebaLoginNormal(ant_user, ant_pass); 
        List<String> list_users = em.createNamedQuery("findUserName").getResultList();
        if(list_users.contains(u.getUsuario().toUpperCase())&& !ant_user.equalsIgnoreCase(u.getUsuario()))
                throw new CuentaRepetidaException();
        em.merge(u);
        
    }
    
    @Override
    public void modificarAgente(Agente a, String ant_user, String ant_pass) throws CuentaException{
        
        Agente ag = compruebaLoginAg(ant_user, ant_pass); 
        List<String> list_users = em.createNamedQuery("findUserName").getResultList();
        if(list_users.contains(a.getUsuario().toUpperCase()) && !ant_user.equalsIgnoreCase(a.getUsuario()))
                throw new CuentaRepetidaException();
        em.merge(a);
        
    }
    
    @Override
    public void eliminarUsuario(Usuario u) throws CuentaException{
        Usuario user = em.find(Usuario.class, u.getNumSocio());
        compruebaLoginNormal(user.getUsuario(), user.getContrasenia());        
        em.remove(em.merge(user));       
        
    }
    
    
    
}
