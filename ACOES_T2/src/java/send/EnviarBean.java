/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Modelo.*;
import autenticacion.ControlAutorizacion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.inject.Named;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;

/**
 *
 * @author devna
 */
@Named(value="enviarBean")
@RequestScoped
public class EnviarBean implements Serializable {

    private Usuario user;
    private List<Niño> ninos;
    private List<Correo> correos;
    private Agente ag;
    
    private String usuario;
    private String nin;
    private String correo;
    
    @Inject 
    private ControlAutorizacion ctrl;
    
    public EnviarBean(){
        List<Niño> nin = new ArrayList<>();
        nin = null;
        this.ninos = nin;
        List<Apadrinar> apad = new ArrayList<>();
        apad = null;
        List<Niño> lista_ninos= new ArrayList<>();
        Niño n1 = new Niño("Manolo", "Abandonado","",apad);
        Niño n2 = new Niño("Pablo", "Atún", "",apad);
        Niño n3 = new Niño("Antonio", "Pérez", "",apad);
        Niño n4 = new Niño("Tarun", "Vaca", "",apad);
        Niño n5 = new Niño("Iman", "Nevera", "",apad);
        lista_ninos.add(n1); lista_ninos.add(n2); lista_ninos.add(n3); lista_ninos.add(n4); lista_ninos.add(n5);
        this.ninos = lista_ninos;
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
    
    public String guardar(){
        FacesContext ctx = FacesContext.getCurrentInstance();
        
        boolean ok = false;
        for(Niño n : ninos){
            if (nin.equals(n.getNombre())) {
                ok = true;
                break;
            }
        }
        
     //   if(usuario.equals(ctrl.getUsuario().getUsuario())){
        if(ok){
            user = ctrl.getUsuario();
            
            Correo c = new Correo("",false,correo);
            
            if(user.getCorreoList() == null){
                List<Correo> cor = new ArrayList<>();
                cor.add(c);
                user.setCorreoList(cor);
            }else{
                user.getCorreoList().add(c);
            }
            
             return "gracias.xhtml";           
        }else{
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al enviar la carta. Inténtelo de nuevo","Error al enviar la carta. Inténtelo de nuevo"));
        }
        
        return null;
    }
    
}