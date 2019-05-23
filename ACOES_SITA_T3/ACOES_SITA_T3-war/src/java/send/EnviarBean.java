/* Tarea 2: Vista JSF.
 * @author Grupo 1:
 *          - Tarun D. 
            - Salvador C. F.
            - Iman H. M.
            - Antonio P. C.
 */
package send;

import Modelo.*;
import autenticacion.ControlAutorizacion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import negocio.correo.CorreoException;
import negocio.correo.ListaVaciaException;
import negocio.correo.enviarNegocioLocal;

/**
 *
 * @author devna
 */
//@Named(value="enviarBean")
@ManagedBean(name="enviarBean")
@RequestScoped
public class EnviarBean implements Serializable {

    private Usuario user;
    private Agente ag;
    
    private String usuario;
    private String nin;
    private String correo;
    
    private String selectedItem;
    
    @Inject 
    private ControlAutorizacion ctrl;
    
    @EJB
    private enviarNegocioLocal negocio;
    
    public EnviarBean(){
        
    }
    
    public String getUsuario(){
        return usuario;
    }
    
    public String getUsuarioSesion() {
        return ctrl.getUsuario().getNombre();
    }
    
    public void setUsuario(String usuario){
        this.usuario = usuario;
    }
    
    public String getNino(){
        return nin;
    }
    
    public void setNino(String nino){
        this.nin = nino;
    }
    
    public String getCorreo(){
        return correo;
    }
    
    public void setCorreo(String correo){
        this.correo = correo;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public String getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }    

   
    
    
    public List<String> lista_ninos(){
        return negocio.lista_ninos(ctrl.getUsuario());
    }
    
    public String guardar(){
        FacesMessage fm = null;
        FacesContext ctx = FacesContext.getCurrentInstance();
        System.out.println(correo);
        Correo c = new Correo("Carta", false, correo);
        Usuario u  = ctrl.getUsuario();
        
        try{
            negocio.guardarCarta(c, selectedItem, u);
        }catch(ListaVaciaException e){
            fm = new FacesMessage("No puede enviar ninguna carta, ya que no tiene ningún niño apadrinado. ");
            ctx.addMessage("", fm);
            return null;
        }catch(CorreoException e){
            fm = new FacesMessage("Error");
            ctx.addMessage("", fm);
            return null;
        }
        
        return "graciasEnviarCarta.xhtml"; 
        /*
        boolean ok = false;
        for(Niño n : ninos){
            if (nin.equals(n.getNombre())) {
                ok = true;
                break;
            }
        }
        
     //   if(usuario.equals(ctrl.getUsuario().getUsuario())){
        if(ok){*/
        
        /*
            user = ctrl.getUsuario();
            
            Correo c = new Correo("",false,correo);
            
            if(user.getCorreoList() == null){
                List<Correo> cor = new ArrayList<>();
                cor.add(c);
                user.setCorreoList(cor);
            }else{
                user.getCorreoList().add(c);
            }
            
             return "graciasEnviarCarta.xhtml";           
        }else{
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al enviar la carta. Inténtelo de nuevo","Error al enviar la carta. Inténtelo de nuevo"));
        }
        
        return null;
        */
    }
    
    public String contenedor(boolean aceptar){
        if (aceptar){
            return "index.xhtml";
        }else{
            return null;
        }
    }
}