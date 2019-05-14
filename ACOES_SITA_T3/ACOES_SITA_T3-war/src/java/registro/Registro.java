/* Tarea 2: Vista JSF.
 * @author Grupo 1:
 *          - Tarun D. 
            - Salvador C. F.
            - Iman H. M.
            - Antonio P. C.
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
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import negocio.registro.CheckException;
import negocio.registro.CuentaException;
import negocio.registro.CuentaRepetidaException;
import negocio.registro.RegistroNegocio;
import negocio.registro.RegistroNegocioLocal;
import java.util.Random;

@Named(value = "registro")
@RequestScoped
public class Registro{
    
    private Usuario nuevo;
    private String repass;   
    private boolean check;
    
    private List<Usuario> usuarios;
    boolean reg_ok;
    
   
    
    @EJB
    private RegistroNegocioLocal negocio;
    /**
     * Creates a new instance of Registro
     */
    public Registro()  {
        
        // 1. Inicializamos a modo ficticio los usuarios que están ya creados y registrados.
        nuevo = new Usuario();
       
        Random r = new Random(); int num = r.nextInt(100000);
        //nuevo.setNumSocio(num);
        
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

 
    
 
    
    public String registrarse(){ 
        FacesMessage fm = null;
         try {
            if (!nuevo.getContrasenia().equals(repass)) {
                // Comprobar que se ha introducido bien la contraseña
                fm = new FacesMessage("Las contraseñas deben coincidir");
                FacesContext.getCurrentInstance().addMessage("registro:repass", fm);
                return null;
            }
            
            //Verificar que se han aceptado los términos.
            if(!check){
                throw new CheckException();                
            }
            
            negocio.registrarUsuario(nuevo);
            reg_ok = true;
            return "exitoRegistro.xhtml";
            
        } catch (CuentaRepetidaException e) {
            fm = new FacesMessage("El nombre de usuario introducido ya existe.");
            FacesContext.getCurrentInstance().addMessage("registro:user", fm);
            
        } catch (CheckException e){
            fm = new FacesMessage("Debe aceptar los términos y condiciones.");
            FacesContext.getCurrentInstance().addMessage("registro:check", fm);
        }catch (CuentaException e) {
        }
        return null;
                
        
        
            
       
       
       
    }
}





