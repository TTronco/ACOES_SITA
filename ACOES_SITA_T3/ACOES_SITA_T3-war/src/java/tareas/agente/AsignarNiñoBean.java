/* Tarea 2: Vista JSF.
 * @author Grupo 1:
 *          - Tarun D. 
            - Salvador C. F.
            - Iman H. M.
            - Antonio P. C.
 */
package tareas.agente;

import Modelo.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import negocio.apadrinar.ApadrinamientoNegocioLocal;
import negocio.apadrinar.ApadrinarException;


@Named(value = "asignarNiñoBean")
@ManagedBean
@SessionScoped
public class AsignarNiñoBean implements Serializable{
  

    
    private String user;
    private String kid;
    
   @EJB
   private ApadrinamientoNegocioLocal negocio;
   
    public AsignarNiñoBean(){
        /*
            // Lista de usuarios que quieren apadrinar.
            usuarios = new ArrayList<>();
            usuarios.add(new Usuario("Pepe", "El chispas", "4545454"));
            usuarios.add(new Usuario("Manuel", "Dominguez", "3849"));
            usuarios.add(new Usuario("Manuela", "Piña", "380i9899"));

            //Lista de niños que esperan ser apadrinados.
            ninos = new ArrayList<>();
            ninos.add(new Niño("Manolo")); 
            ninos.add(new Niño("Pablo")); 
            
            Date fecha = new Date();
            apadrinar = new ArrayList<>(); 
            apadrinar.add(new Apadrinar(15, fecha, ninos.get(0), null));
            apadrinar.add(new Apadrinar(15, fecha, ninos.get(1), null));
            apadrinar.add(new Apadrinar(15, fecha, null, usuarios.get(0)));   
            apadrinar.add(new Apadrinar(15, fecha, null, usuarios.get(1)));    
            apadrinar.add(new Apadrinar(15, fecha, null, usuarios.get(2)));    
        */
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }
    
    
    public List<Apadrinar> listaApadrinados(){
        return negocio.apadrinar();
    }

    public Map<Usuario,Niño> listaDisponibles(){
        return negocio.disponiblesAp();
    }

    
    public List<Usuario> usuariosSolicitantes() {
        // Lista de usuarios que quieren apadrinar
        System.out.println(negocio.usuariosSolicitantes());
        return negocio.usuariosSolicitantes();
    }    

    public List<Niño> niniosDisponibles() {
        // Lista de niños que esperan ser apadrinados.
        System.out.println(negocio.niniosDisponibles());
        return negocio.niniosDisponibles();
    }  
    
      
    public String showMessage() {
       if(numPeticiones() > 0)
           return "padrinoNino.xhtml";
       else
           return null;
        
    }
    
    public int numPeticiones(){
        negocio.crearNiños(); //Inicializar datos niños bd;
        return negocio.peticiones();
    }
    
    public void asignar(Usuario u, Niño n){  
        FacesMessage fm = null;
        FacesContext ctx = FacesContext.getCurrentInstance();
        
        try{
            negocio.asignar(u, n);
        }catch(ApadrinarException e){
            fm = new FacesMessage("Ha habido un problema al asignar, inténtelo de nuevo más tarde.");
            ctx.addMessage("", fm);
        }
        
        /*
        Usuario u = ap.getUsuarioNumSocio();
        Niño n = ap.getNiñoCodigo();
        
        if(ap.getUsuarioNumSocio() != null){
             user = ap.getUsuarioNumSocio().getNombre();
             apadrinar.remove(ap);
        }else if(ap.getNiñoCodigo() != null){
             kid = ap.getNiñoCodigo().getNombre();
             apadrinar.remove(ap);
        }        
        
        
        if(u!=null){
            usuarios.remove(u);
        }else{        
           
            //No hay padrino asociado
            for(Usuario u1: usuarios){
                if(u1.getNombre().equals((user))){
                    usuarios.remove(u1);
                    break;
                }
            }
        }
        
        if(n!= null){
            ninos.remove(n);
        }else{
            
            for(Niño n1: ninos){
                if(n1.getNombre().equals(kid)){
                    ninos.remove(n1);
                    break;
                }
            }           
                    
                    
        }
           
       
        
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
                "El usuario " + user + " ha sido asignado a " + kid + ".", "El usuario " + user + " ha sido asignado a " + kid + "."));
        
    }    
        
        */
    }    
    
    
    }
        


