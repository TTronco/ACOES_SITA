/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autenticacion;

import Modelo.Usuario;
import Modelo.Agente;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * 
 * Iman Hasnaouia Meskini
 */
@Named(value = "login")
@RequestScoped
public class Login {

    private String usuario;
    private String contrasenia;
    private List<Usuario> usuarios;
    private List<Agente> agentes;
    
    @Inject
    private ControlAutorizacion ctrl;

    /**
     * Creates a new instance of Login
     */
    public Login() {
        //Se simula la tabla de usuarios con una lista de usuarios.
        usuarios = new ArrayList<Usuario>();  
        usuarios.add(new Usuario("normal", "boniatos"));
        //Se simula la tabla de agentes con una lista de agentes. Los agentes no se pueden registrar, son creados por ACOES.
        agentes = new ArrayList<Agente>();
        agentes.add(new Agente("iman", "boniato"));
        agentes.add(new Agente("tarun", "yo"));
        agentes.add(new Agente("antonio", "ñocos"));
        
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String autenticar() {
        // Implementar este método
        FacesContext ctx = FacesContext.getCurrentInstance();
        // 1. Comprobar si el usuario se encuentra en la BD:
        Usuario user = null; Agente ag = null; 
        boolean ok = false; 
        boolean agente = false;
        
        
        for (Usuario us: usuarios) {
            if(us.getUsuario().equals(usuario)){
                ok = true;
                user = us;
                break;
            }
        }
        if(!ok){
            // 2. Comprobar si el usuario es un agente.
            
            for (Agente a: agentes) {
                if(a.getUsuario().equals(usuario)){
                    ok = true;
                    ag = a; agente = true;
                    break;
                }
            }
             
        }      
        if(!ok){
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El usuario " + usuario + " introducido no existe", "El usuario " + usuario + "  introducido no existe"));
        }else{
            // 2. Comprobar que la contraseña es correcta.
            if(agente){
                if(ok && ag.getContrasenia().equals(contrasenia)){
                    ctrl.setUsuario(ag);
                    ctrl.setAg(true);
                    return ctrl.home();
                }else{            
                    if(ok)
                        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Contraseña incorrecta " + ".", "Contraseña incorrecta.")); 
                    ctrl.setUsuario(null);
                }
            }else{
                if(ok && user.getContrasenia().equals(contrasenia)){
                    ctrl.setUsuario(user);
                    ctrl.setAg(false);
                    return ctrl.home();
                }else{            
                    if(ok)
                        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Contraseña incorrecta " + ".", "Contraseña incorrecta.")); 
                    ctrl.setUsuario(null);

                }
            }
            
        }
        
       return null;
    }
}
