/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autenticacion;

import Modelo.Usuario;
import Modelo.Correo;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author devna
 */
@Named(value="enviar")
@RequestScoped
public class EnviarBean {
    
    private String usuario;
    private List<Usuario> usuarios;
    private List<Correo> correos;
    
   public EnviarBean(){
       
   }
   
   public String getUsuario(){
       return usuario;
   }
   
   public void setUsuario(String usuario){
       this.usuario = usuario;
   }
   
   public void guardarEnvio(){
   
       Usuario user = null;
       boolean ok = false;
       
       for(Usuario us: usuarios){
           if(us.getUsuario().equals(usuario)){
               user = us;
               ok = true;
               break;
           }
       }
   }
  
}
