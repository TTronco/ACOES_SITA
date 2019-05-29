/* Tarea 2: Vista JSF.
 * @author Grupo 1:
 *          - Tarun D. 
            - Salvador C. F.
            - Iman H. M.
            - Antonio P. C.
 */
package apadrinar;

import Modelo.Agente;
import Modelo.Apadrinar;
import Modelo.Niño;
import Modelo.Usuario;
import autenticacion.ControlAutorizacion;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import negocio.apadrinar.ApadrinamientoNegocioLocal;
import negocio.apadrinar.ApadrinarException;
import negocio.apadrinar.ApadrinarNoGestionadoException;
import negocio.apadrinar.NiniosNoDisponiblesException;
import negocio.registro.CuentaException;
import negocio.registro.CuentaInvalidaException;
import negocio.registro.RegistroNegocioLocal;

@Named(value = "apadrinarB")
@RequestScoped
public class ApadrinarBean {
  
    
    private String usuario;
    private String contrasenia;
    
    private boolean check;
    
    @Inject
    private ControlAutorizacion ctrl;  
    
    @EJB
    private ApadrinamientoNegocioLocal negocio;
    
    @EJB
    private RegistroNegocioLocal neg;
    
    public ApadrinarBean() {
        
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
    
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    
    public String notificar(){
        
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage fm = null;
        
        
        if(ctrl.getUsuario()!=null){
            try{
                //1. Doble comprobación de usuario. Si está logeado, sus datos de sesión se mantienen en controlAutorización.
                neg.compruebaLoginNormal(usuario, contrasenia);

                // 2. Si todo es correcto, el usuario debe pasar a la lista de usuarios que quieren apadrinar.
                if(check){                
                    negocio.solicitudApadrinar(ctrl.getUsuario());
                    return "gracias.xhtml";
                }else{
                    fm = new FacesMessage("Debe aceptar los términos y condiciones.");
                    ctx.addMessage("apadrinarB:check", fm);                
                }
            }catch(CuentaInvalidaException e){
                fm = new FacesMessage("El usuario o contraseña fueron mal introducidas. ");
                ctx.addMessage(usuario, fm);
            }catch(CuentaException e){

            }catch(ApadrinarNoGestionadoException e){
                fm= new FacesMessage("Tiene aún peticiones de apadrinamiento sin gestionar, espere a que un agente gestione su petición anterior.");
                ctx.addMessage("", fm);
            }catch(ApadrinarException e){

            }
        }
        
        return null;      
    }
    
    public String desapadrinar(boolean seguro){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage fm = null;
        if(seguro){
            try{
                negocio.desapadrinar(ctrl.getUsuario());
                return "index.xhtml";
            }catch(NiniosNoDisponiblesException e){
                fm = new FacesMessage("No puede desapadrinar si no tiene a ningún niño apadrinado.");
                ctx.addMessage("", fm);
            }catch(ApadrinarException e){
                fm = new FacesMessage("Error inesperado. Inténtelo de nuevo más tarde");
                ctx.addMessage("", fm);
            }
            return null;
        }else{
            return null;
        }
    }
}
