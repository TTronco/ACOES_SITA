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
    
    private String usuario;
    
    private String correo;
    
    private String selectedItem;
    private String contenido;
    private String cantidad;
    
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

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    
    
    
    public List<String> lista_ninos(){
        return negocio.lista_ninos(ctrl.getUsuario());
    }
    
    public String guardar(){
        FacesMessage fm = null;
        FacesContext ctx = FacesContext.getCurrentInstance();
    
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
                
        
    }
    
    public String contenedor(boolean aceptar){
        if (aceptar){
            FacesMessage fm = null;
            FacesContext ctx = FacesContext.getCurrentInstance();
            try{
                int cant = Integer.parseInt(cantidad); 
                if(cant == 0){
                    fm = new FacesMessage("Introduzca un número mayor que cero.");
                    ctx.addMessage(cantidad, fm);
                    return null;
                }
                Correo c = new Correo("Contenedor", "Cantidad: " + cant + " Contenido: " + contenido);
                Date d = new Date();
                c.setFecha_envio(d);
                
                negocio.enviarContenedor(c);
            }catch(NumberFormatException e){
                fm = new FacesMessage("Introduzca un número válido en cantidad por favor.");
                ctx.addMessage(cantidad, fm);
                return null;
            }
            return "index.xhtml";
        }else{
            return null;
        }
    }
}