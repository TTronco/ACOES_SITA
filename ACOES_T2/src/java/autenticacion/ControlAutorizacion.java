/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
   
    
    private boolean ag = false;

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

    public Usuario getAgente() {
        return agente;
    }

   
    public String home() {
        // Implementar el método
        // Devuelve la página Home dependiendo del rol del usuario
        // Si no hay usuario debe devolver la página de login
        // Si el usuario es el administrador debe devolver la página admin.xhtml
        // Si el usuario es un usuario normal debe devolver la página normal.xhtml
        
        String page = "login.xhtml";
        if(usuario != null && !ag){
            page = "socio.xhtml";
        }else if(agente != null && ag){            
            page = "Agente.xhtml";
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