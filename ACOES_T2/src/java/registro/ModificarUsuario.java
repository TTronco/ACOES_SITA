/* Tarea 2: Vista JSF.
 * @author Grupo 1:
 *          - Tarun D. 
            - Salvador C. F.
            - Iman H. M.
            - Antonio P. C.
 */
package registro;

import java.io.*;
import Modelo.Agente;
import Modelo.Usuario;
import autenticacion.ControlAutorizacion;
import java.io.Serializable;
import java.util.Scanner;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author TODO IMAN 
 */
@Named(value = "EditarPerfil")
@RequestScoped

public class ModificarUsuario{
    
    private Usuario user;
    private Agente agente;
    
    private String usuario;
    private String contrasenia;
    private String anterior;
    
    private String repass;
    //private Usuario nuevo;
    
    @Inject
    private ControlAutorizacion ctrl;
    
   
    /**
     * Creates a new instance of Registro
     */
    
    
    public ModificarUsuario()  {
       user = new Usuario();
       agente = new Agente();        
    }

    public String getAnterior() {
        return anterior;
    }

    public void setAnterior(String anterior) {
        
        this.anterior = anterior;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public String getRepass() {
        return repass;
    }

    public void setRepass(String repass) {
        this.repass = repass;
    }

    public Agente getAgente() {
        return agente;
    }

    public void setAgente(Agente agente) {
        this.agente = agente;
    }
    
   

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public ControlAutorizacion getCtrl() {
        return ctrl;
    }

    public void setCtrl(ControlAutorizacion ctrl) {
        this.ctrl = ctrl;
    }
    
   
    /*
         public Usuario getNuevo() {
            return nuevo;
        }
        public void setNuevo(Usuario nuevo) {
        this.nuevo = nuevo;
        }
    */
    
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
   
       
    public String EditarPerfil (){
        FacesMessage fm = null;
        FacesContext ctx = FacesContext.getCurrentInstance();
        
        // 1. Comprobar que la contraseña anterior fue introducida correctamente.
        if(ctrl.getUsuario()!= null){
              
            if(anterior.equals(ctrl.getUsuario().getContrasenia())){
                
                // 2. Validamos la nueva contraseña.
                if (user.getContrasenia().equals(repass)) {
                    return "index.xhtml";
                }else{
                    fm = new FacesMessage("Las nuevas contraseñas introducidas no coinciden.");
                    ctx.addMessage("", fm);

                }
            }else{
                fm = new FacesMessage("La contraseña anterior y la introducida no coinciden.");
                ctx.addMessage("EditarpPerfil:anterior", fm);
            }
        }else if(ctrl.getAgente()!= null){
             
             if(anterior.equals(ctrl.getAgente().getContrasenia())){
                // 2. Validamos la nueva contraseña.
                if (agente.getContrasenia().equals(repass)) {
                    return "index.xhtml";
                }else{
                    fm = new FacesMessage("Las nuevas contraseñas introducidas no coinciden.");
                    ctx.addMessage("", fm);

                }
            }else{
                fm = new FacesMessage("La contraseña anterior y la introducida no coinciden.");
                ctx.addMessage("EditarpPerfil:anterior", fm);
            }
        }
                       
        return null;
    }
}