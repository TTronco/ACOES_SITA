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

/**
 *
 * @author francis
 */
@Named(value = "controlAutorizacion")
@SessionScoped
public class ControlAutorizacion implements Serializable {

    private Usuario usuario;
    private Agente agente;
    
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

    public Agente getAgente() {
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

    /**
     * Creates a new instance of ControlAutorizacion
     */
    public ControlAutorizacion() {
    }
}
