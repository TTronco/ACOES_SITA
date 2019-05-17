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
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import negocio.login.LoginNegocio;
import negocio.login.LoginNegocioLocal;
import negocio.registro.CuentaException;
import negocio.registro.CuentaRepetidaException;
import negocio.registro.RegistroNegocioLocal;


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
    
    @EJB
    private RegistroNegocioLocal negocio;
       
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
                    
                        Usuario actual = ctrl.getUsuario();
                        String user_ant = actual.getUsuario();
                        String pass_ant = actual.getContrasenia();
                        // 1. Comprobamos los campos que se han introducido, y los modificamos en el objeto usuario de la sesión.
                        if(!user.getNombre().equals("")){
                            actual.setNombre(user.getNombre());
                        }                        
                        if(!user.getApellidos().equals("")){
                            actual.setApellidos(user.getApellidos());
                        }                        
                        if(!user.getNif().equals("")){
                            actual.setNif(user.getNif());
                        }
                        if(!user.getDireccion().equals("")){
                            actual.setDireccion(user.getDireccion());
                        }
                        if(!user.getPoblacion().equals("")){
                            actual.setPoblacion(user.getPoblacion());
                        }
                        if(!user.getCodPostal().equals("")){
                            actual.setCodPostal(user.getCodPostal());
                        }
                        if(!user.getTelefonoMovil().equals("")){
                            actual.setTelefonoMovil(user.getTelefonoMovil());
                        }
                        if(!user.getUsuario().equals("")){
                            actual.setUsuario(user.getUsuario());
                        }
                        if(!user.getContrasenia().equals("")){                            
                            actual.setContrasenia(user.getContrasenia());
                        }
                    try{   
                        // Ahora el usuario actual ya está actualizado, solo hay que modificarlo.
                        negocio.modificarUsuario(actual, user_ant, pass_ant);
                        negocio.refrescarUsuario(actual, user_ant, pass_ant);
                        
                            
                    }catch(CuentaRepetidaException e){
                        fm = new FacesMessage("El nombre de usuario introducido ya existe.");
                        ctx.addMessage("", fm);
                        return null;
                    }catch(CuentaException e){
                        //e.printStackTrace();
                    }
                    
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
                        Agente actual = ctrl.getAgente();
                        String user_ant = actual.getUsuario();
                        String pass_ant = actual.getContrasenia();
                        // 1. Comprobamos los campos que se han introducido, y los modificamos en el objeto usuario de la sesión.
                        if(!agente.getNombre().equals("")){
                            actual.setNombre(agente.getNombre());
                        }                        
                        if(!agente.getApellidos().equals("")){
                            actual.setApellidos(agente.getApellidos());
                        }                        
                        if(!agente.getNif().equals("")){
                            actual.setNif(agente.getNif());
                        }
                        if(!agente.getDireccion().equals("")){
                            actual.setDireccion(agente.getDireccion());
                        }
                        if(!agente.getPoblacion().equals("")){
                            actual.setPoblacion(agente.getPoblacion());
                        }
                        if(!agente.getCodPostal().equals("")){
                            actual.setCodPostal(agente.getCodPostal());
                        }
                        if(!agente.getTelefonoMovil().equals("")){
                            actual.setTelefonoMovil(agente.getTelefonoMovil());
                        }
                        if(!agente.getUsuario().equals("")){
                            actual.setUsuario(agente.getUsuario());
                        }
                        if(!agente.getContrasenia().equals("")){                            
                            actual.setContrasenia(agente.getContrasenia());
                        }
                    try{    
                        // Ahora el usuario actual ya está actualizado, solo hay que modificarlo.
                        negocio.modificarUsuario(actual, user_ant, pass_ant);
                        negocio.refrescarUsuario(actual, user_ant, pass_ant);
                        
                            
                    }catch(CuentaRepetidaException e){
                        fm = new FacesMessage("El nombre de usuario introducido ya existe.");
                        ctx.addMessage("", fm);
                        return null;
                    }catch(CuentaException e){
                        //e.printStackTrace();
                    }
                    
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
    
     public String eliminarUsuario(){
        FacesMessage fm = null;
        FacesContext ctx = FacesContext.getCurrentInstance();
        
         try{
             if(ctrl.getUsuario() != null){
             Usuario u = ctrl.getUsuario();
             ctrl.logout();
             negocio.eliminarUsuario(u);
             return "index.xhtml";
         }
         }catch(CuentaException e){
            fm = new FacesMessage("Ha habido un problema al eliminar su usuario. ");
            ctx.addMessage("", fm);
         }
         
         
        return null;
    }
}

   

