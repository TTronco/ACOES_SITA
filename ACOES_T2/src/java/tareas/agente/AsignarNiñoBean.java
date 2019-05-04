/* Tarea 2: Vista JSF.
 * @author Grupo 1:
 *          - Tarun D. 
            - Salvador C. F.
            - Iman H. M.
            - Antonio P. C.
 */
package tareas.agente;

import Modelo.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;


@Named(value = "asignarNiñoBean")
@ManagedBean
@SessionScoped
public class AsignarNiñoBean implements Serializable{
  
    private List<Apadrinar> apadrinar;
    private List<Niño> ninos;
    private List<Usuario> usuarios;
    
    private String user;
    private String kid;
    
    public AsignarNiñoBean(){
        
        // Lista de usuarios que quieren apadrinar.
        usuarios = new ArrayList<>();
        usuarios.add(new Usuario("Pepe", "El chispas", "4545454"));
        usuarios.add(new Usuario("Manuel", "Dominguez", "3849"));
        usuarios.add(new Usuario("Manuela", "Piña", "380i9899"));
        
        //Lista de niños que esperan ser apadrinados.
        ninos = new ArrayList<>();
        ninos.add(new Niño("Manolo")); 
        ninos.add(new Niño("Pablo")); 
        
        Date fecha = new Date();
        apadrinar = new ArrayList<>(); 
        apadrinar.add(new Apadrinar(15, fecha, ninos.get(0), null));
        apadrinar.add(new Apadrinar(15, fecha, ninos.get(1), null));
        apadrinar.add(new Apadrinar(15, fecha, null, usuarios.get(0)));   
        apadrinar.add(new Apadrinar(15, fecha, null, usuarios.get(1)));    
        apadrinar.add(new Apadrinar(15, fecha, null, usuarios.get(2)));    
        
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }
    
    
    public List<Apadrinar> getApadrinados(){
        return apadrinar;
    }

    public List<Apadrinar> getApadrinar() {
        return apadrinar;
    }

    public void setApadrinar(List<Apadrinar> apadrinar) {
        this.apadrinar = apadrinar;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Niño> getNinos() {
        return ninos;
    }

    public void setNinos(List<Niño> ninos) {
        this.ninos = ninos;
    }
    
    
      
    public String showMessage() {
        
       return "padrinoNino.xhtml";
        
    }
    
    
    public void asignar(Apadrinar ap){
        
        Usuario u = ap.getUsuarioNumSocio();
        Niño n = ap.getNiñoCodigo();
        
        if(ap.getUsuarioNumSocio() != null){
             user = ap.getUsuarioNumSocio().getNombre();
             apadrinar.remove(ap);
        }else if(ap.getNiñoCodigo() != null){
             kid = ap.getNiñoCodigo().getNombre();
             apadrinar.remove(ap);
        }        
        
        
        if(u!=null){
            usuarios.remove(u);
        }else{        
           
            //No hay padrino asociado
            for(Usuario u1: usuarios){
                if(u1.getNombre().equals((user))){
                    usuarios.remove(u1);
                    break;
                }
            }
        }
        
        if(n!= null){
            ninos.remove(n);
        }else{
            
            for(Niño n1: ninos){
                if(n1.getNombre().equals(kid)){
                    ninos.remove(n1);
                    break;
                }
            }           
                    
                    
        }
           
       
        
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
                "El usuario " + user + " ha sido asignado a " + kid + ".", "El usuario " + user + " ha sido asignado a " + kid + "."));
        
    }    

}
/*
                            <h:commandButton value="Asignar" action="#{asignarNiñoBean.asignar(apadrinar)}"/> 
*/