/* Tarea 2: Vista JSF.
 * @author Grupo 1:
 *          - Tarun D. 
            - Salvador C. F.
            - Iman H. M.
            - Antonio P. C.
 */
package autenticacion;

import java.io.*;
import Modelo.Usuario;
import Modelo.Agente;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import negocio.login.LoginNegocioLocal;
import negocio.registro.CuentaException;
import negocio.registro.CuentaInvalidaException;

@Named(value = "login")
@RequestScoped
public class Login {

    private String usuario;
    private String contrasenia;
    private List<Usuario> usuarios;
    private List<Agente> agentes;
    
    @Inject
    private ControlAutorizacion ctrl;
    
    @EJB
    private LoginNegocioLocal negocio;
    
    
    public Login() {
               
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
        //Crear usuarios ficticios de tipo Agente.
        negocio.crearAgente(); 
        
        boolean ok = false;
        // Comprobar si es un agente
        FacesMessage fm = null;
        try{
            //Comprobamos que el usuario que se conecta es normal
            Usuario u = negocio.compruebaLoginNormal(usuario, contrasenia);   
            ok = true;
            ctrl.setUsuario(negocio.refrescarUsuario(u));            
            ctrl.setAg(false);
            return ctrl.home();
        }catch (CuentaInvalidaException e){
            // Quien se conecta o usa valores de autenticación incorrectos, o es un agente.
            
        }catch(CuentaException e){
            
        }finally{
            try{
                Agente a = negocio.compruebaLoginAg(usuario, contrasenia);
                ok = true;
                ctrl.setAg(true);
                ctrl.setAgente((Agente) negocio.refrescarUsuario(a));
            }catch (CuentaInvalidaException e){
                if(!ok){
                    // Quien se conecta o usa valores de autenticación incorrectos.
                    fm = new FacesMessage("Usuario u contraseña incorrectos.");
                    FacesContext.getCurrentInstance().addMessage("registro:user", fm);
                }
                    
            }catch(CuentaException e){

            }
        }
                
       return null;
    }
}
