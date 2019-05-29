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
import java.util.HashMap;
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
import negocio.apadrinar.NiniosNoDisponiblesException;


@Named(value = "asignarNiñoBean")
@ManagedBean
@SessionScoped
public class AsignarNiñoBean implements Serializable{
  

    
    private String user;
    private String kid;
    
   @EJB
   private ApadrinamientoNegocioLocal negocio;
   
    public AsignarNiñoBean(){
       
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
    
   
    public Map<Usuario,Niño> listaDisponibles(){
        Map<Usuario,Niño> result  = new HashMap<>();
        try{
            result = negocio.disponiblesAp();
            return result;
        }catch(NiniosNoDisponiblesException e){
            FacesMessage fm = new FacesMessage("No hay niños disponibles para asignar.");
            FacesContext ctx = FacesContext.getCurrentInstance();     
            ctx.addMessage("ninio", fm);
        }catch(ApadrinarException e){
            
        }
        return null;
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
        
       
    }    
    
    
    }
        


