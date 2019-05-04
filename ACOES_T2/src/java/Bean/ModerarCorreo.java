/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Modelo.Correo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author salvi
 */

@Named(value = "ModerarCorreo")
@SessionScoped
public class ModerarCorreo implements Serializable{

    private List<Correo> correos;
    
    /**
     * Simulates a mail database
     */
    public ModerarCorreo() {
        correos = new ArrayList<Correo>();
        correos.add(new Correo(1, "Carta", false, "carta"));
        correos.add(new Correo(2, "Paquete", false, "ropa"));
        correos.add(new Correo(3, "Paquete", false, "comida"));
    }
    
    // Getter and Setters
    public List<Correo> getCorreos() {
        return correos;
    }

    public void setCorreos(List<Correo> correos) {
        this.correos = correos;
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
}