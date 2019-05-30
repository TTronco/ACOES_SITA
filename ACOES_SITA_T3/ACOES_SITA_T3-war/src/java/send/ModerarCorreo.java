/* Tarea 2: Vista JSF.
 * @author Grupo 1:
 *          - Tarun D. 
            - Salvador C. F.
            - Iman H. M.
            - Antonio P. C.
 */


package send;

import Modelo.Correo;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.enterprise.context.SessionScoped;
import negocio.moderar.moderarNegocioLocal;

@Named(value = "ModerarCorreo")
@SessionScoped
public class ModerarCorreo implements Serializable{

    private String contenido;
    
    @EJB
    private moderarNegocioLocal negocio;
    
    /**
     * @return correos de la bd
     */
    public List<Correo> getCorreos() {
        return negocio.listaCorreos();
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
    /**
     * @return the number of mails that have not been moderated
     */
    public int numNotificaciones(){
        return negocio.notificaciones();
    }
    
    public void validar(Correo selectedCorreo){
        selectedCorreo.setModerado(true);
        selectedCorreo.setValido(true);
        negocio.validarCorreo(selectedCorreo);
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
                "El correo con código " + selectedCorreo.getCodigo() + 
                        " ha sido validado", null));
    }
    
    public void rechazar(Correo selectedCorreo){
        selectedCorreo.setModerado(true);
        selectedCorreo.setValido(false);
        negocio.rechazarCorreo(selectedCorreo);
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
                "El correo con código " + selectedCorreo.getCodigo() + 
                        " ha sido rechazado", null));
    }
    
    public String mostrarContenido(Correo selectedCorreo){
        this.contenido = selectedCorreo.getContenido();
        return "contenidoCarta";
    }
}