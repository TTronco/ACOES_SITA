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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.enterprise.context.SessionScoped;

@Named(value = "ModerarCorreo")
@SessionScoped
public class ModerarCorreo implements Serializable{

    private List<Correo> correos;
    private String contenido;
    
    /**
     * Simulates a mail database
     */
    public ModerarCorreo() {
        correos = new ArrayList<Correo>();
        correos.add(new Correo(1, "Carta", false, "Hola Manolo, te enviamos comida y unos libros."));
        correos.add(new Correo(2, "Paquete", false, "ropa"));
        correos.add(new Correo(3, "Carta", false, 
                "Cómo estás Pepe? Te enviamos ropa."
                        + "hghkabsdjkbas aschchalkassc asjljjhas "
                        + "ajssbjkabsccs askjbkjassb asskjkjas "
                        + "kjkahssjbas asiuasbccuiasc assiubcuiasbcca asiuccui uasbscuib"
                        + "aabidbcuiabccuias iuaubiucbassc"));
    }
    
    // Getter and Setters
    public List<Correo> getCorreos() {
        return correos;
    }

    public void setCorreos(List<Correo> correos) {
        this.correos = correos;
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
    public int notificaciones(){
        Iterator<Correo> iter = correos.iterator();
        int contador = 0;
        Correo aux;
        
        while(iter.hasNext()) {
            aux = iter.next();
            if(!aux.getModerado()){ contador++; }      
        }
        return contador;
    }
    
    public void validar(Correo selectedCorreo){
        FacesContext ctx = FacesContext.getCurrentInstance();
        selectedCorreo.setModerado(true);
        selectedCorreo.setValido(true);
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
                "El correo con código " + selectedCorreo.getCodigo() + 
                        " ha sido validado", null));
    }
    
    public void rechazar(Correo selectedCorreo){
        FacesContext ctx = FacesContext.getCurrentInstance();
        selectedCorreo.setModerado(true);
        selectedCorreo.setValido(false);
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
                "El correo con código " + selectedCorreo.getCodigo() + 
                        " ha sido rechazado", null));
    }
    
    public String mostrarContenido(Correo selectedCorreo){
        this.contenido = selectedCorreo.getContenido();
        return "contenidoCarta";
    }
}