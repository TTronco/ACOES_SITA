/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registro;

import java.io.*;
import Modelo.Agente;
import Modelo.Usuario;
import autenticacion.ControlAutorizacion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Iman Hasnaouia Meskini
 */
@Named(value = "registro")
@RequestScoped
public class Registro{
    
    private Usuario nuevo;
    private String repass;   
    private boolean check;
    
    private List<Usuario> usuarios;
    boolean reg_ok;
    
    @Inject
    private ControlAutorizacion ctrl;
    
    /**
     * Creates a new instance of Registro
     */
    public Registro()  {
        
        // 1. Inicializamos a modo ficticio los usuarios que están ya creados y registrados.
        nuevo = new Usuario();
        usuarios = new ArrayList<Usuario>(); 
        usuarios.add(new Usuario("normal", "pw"));
        usuarios.add(new Usuario("u1", "pw"));
        usuarios.add(new Usuario("u2", "pw"));
        usuarios.add(new Usuario("u3", "pw"));       
    }
    
    
    public String getRepass() {
        return repass;
    }

    public void setRepass(String repass) {
        this.repass = repass;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    
    public Usuario getNuevo() {
        return nuevo;
    }

    public void setNuevo(Usuario nuevo) {
        this.nuevo = nuevo;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean isReg_ok() {
        return reg_ok;
    }

    public void setReg_ok(boolean reg_ok) {
        this.reg_ok = reg_ok;
    }

    public ControlAutorizacion getCtrl() {
        return ctrl;
    }

    public void setCtrl(ControlAutorizacion ctrl) {
        this.ctrl = ctrl;
    }
    
 
    
    public String registrarse(){ 
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage fm = null;
        //1. Verificar que el nombre de usuario introducido no existe ya.
        boolean exists = false;
        for(Usuario u: usuarios){
           if(nuevo.getUsuario().equals(u.getUsuario())){
                    exists = true;
                    break;
           }
        }
        if(exists){
            fm = new FacesMessage("El nombre de usuario introducido ya existe.");
            ctx.addMessage("Nombre usuario >", fm);
        }
        //2. Verificar que la contraseña se ha introducido bien.
        boolean pw_ok = true;
         if (!nuevo.getContrasenia().equals(repass)) {
            pw_ok = false;
            fm = new FacesMessage("Las contraseñas deben coincidir.");
            ctx.addMessage("Repita contraseña >", fm);
            
         }
        //3. Verificar que se han aceptado los términos.
        if(!check){
            fm = new FacesMessage("Debe aceptar los términos y condiciones.");
            ctx.addMessage("registro:check", fm);
        }
            
       
       // Creamos al usuario en caso de que se cumplan las condiciones adecuadas.
       if(!exists && check && pw_ok){          
           usuarios.add(nuevo);
           reg_ok= true;
       }else{
           reg_ok = false;
           return null;
       }
        
       return "exitoRegistro.xhtml";
    }
}





