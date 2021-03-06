/* Tarea 2: Vista JSF.
 * @author Grupo 1:
 *          - Tarun D. 
            - Salvador C. F.
            - Iman H. M.
            - Antonio P. C.
 */
package autenticacion;

import Modelo.Agente;
import Modelo.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;

@Named(value = "controlAutorizacion")
@SessionScoped
public class ControlAutorizacion implements Serializable {

    private Usuario usuario;
    private Agente agente;
    
    private boolean ag;
    
    public String getNombre(){
        if(usuario!= null){
            return usuario.getNombre();
        }else{
            return agente.getNombre();
        }        
    }
    
    public String getApellidos(){
        if(usuario!= null){
           return usuario.getApellidos();
        }else{
           return agente.getApellidos();
        }      
        
    }
    
    public String getNif(){
        if(usuario!= null){
           return usuario.getNif();
        }else{
           return agente.getNif();
        }  
        
    }
    
    public String getDireccion(){
        if(usuario!= null){
           return usuario.getDireccion();
        }else{
           return agente.getDireccion();
        }  
        
    }
    
    public String getPoblacion(){
        if(usuario!= null){
           return usuario.getPoblacion();
        }else{
           return agente.getPoblacion();
        }  
        
    }
    
    public String getCodigoPostal(){
        if(usuario!= null){
           return usuario.getCodPostal();
        }else{
           return agente.getCodPostal();
        } 
        
    }
    
    public String getTelefonoMovil(){
        if(usuario!= null){
           return usuario.getTelefonoMovil();
        }else{
           return agente.getTelefonoMovil();
        } 
        
    }
    public String getUsuarioNombre(){
        if(usuario!= null){
           return usuario.getUsuario();
        }else{
           return agente.getUsuario();           
        }         
    }
   
    public String getIdCentro(){
        return agente.getIdCentro();
    }
    
    

    public boolean isAg() {
        return ag;
    }

    public void setAg(boolean ag) {
        this.ag = ag;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setAgente(Agente agente) {
        this.agente = agente;
    }

    public Agente getAgente() {
        return agente;
    }

   
    public String home() {        
        
        String page = "login.xhtml";
        if(usuario != null || agente != null){
            page = "index.xhtml";
        }
        
        return page;
    }
    
    
    
 
    public String logout()
    {
        // Destruye la sesión (y con ello, el ámbito de este bean)
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().invalidateSession();
        usuario = null;
        return "login.xhtml";
    }

    /* Creates a new instance of ControlAutorizacion
     */
    public ControlAutorizacion() {
    }
}