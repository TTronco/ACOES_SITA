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

    private Usuario user; //Usuario que solicita apadrinar
    private List<Niño> ninos;    // Niño que es apadrinado.
    private List<Apadrinar> ap_lista;    // Niño que es apadrinado.
    private Agente ag;    // Agente que asigna al niño.
    
    private String usuario;
    private String contrasenia;
    
    private Apadrinar ap;
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

   
    
    /*
    public String consultar(){
        FacesContext ctx = FacesContext.getCurrentInstance();
        if(ctrl.getAgente() != null){
            //Cogemos al agente actual de la sesión y le asignamos los niños disponibles.
            ag = ctrl.getAgente();
            ag.setNiñoList(ninos);
            Niño por_apadrinar = null;
            // Comprobamos si hay niños a la espera de ser apadrinados. Si lo hay, se coge el primero de la lista.
            for (Niño n : ninos) {
                if(n.getApadrinarList() != null){
                    por_apadrinar = n;
                    break;
                }
            }
            
            // Consultar usuarios en la base de datos, dejar para EJB.
            if(por_apadrinar != null){
                // Asignar niño.
                //Creamos un usuario ficticio, ya que no podemos aún consultar la base de datos.
                user = new Usuario("normal", "boniatos"); 
                Date fecha = new Date();        
                Apadrinar a1 = new Apadrinar(15.0,fecha , null, user);
                ap_lista.add(a1);
                user.setApadrinarList(ap_lista);
                boolean asig = false;
                for(Apadrinar a : user.getApadrinarList()){
                    if(a.getNiñoCodigo() == null){
                        a.setNiñoCodigo(por_apadrinar); 
                        asig = true;
                        break;
                    }
                }
                if(!asig){
                    ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ningún socio quiere apadrinar.","Ningún socio quiere apadrinar." )); 
                }
            }else{
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ningún niño está disponible.","Ningún niño está disponible." )); 
        
            }
            
        }
        return null;
    }
    */
   
    
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
