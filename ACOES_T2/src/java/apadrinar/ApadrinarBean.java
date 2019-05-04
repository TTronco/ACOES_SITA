/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apadrinar;

import Modelo.Agente;
import Modelo.Apadrinar;
import Modelo.Niño;
import Modelo.Usuario;
import autenticacion.ControlAutorizacion;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author imanb
 */
@Named(value = "apadrinarB")
@RequestScoped
public class ApadrinarBean {

    private Usuario user; //Usuario que solicita apadrinar
    private List<Niño> ninos;    // Niño que es apadrinado.
    private List<Apadrinar> ap_lista;    // Niño que es apadrinado.
    private Agente ag;    // Agente que asigna al niño.
    
    private String usuario;
    private String contrasenia;
    
    private Apadrinar ap;
    private boolean check;
    
    @Inject
    private ControlAutorizacion ctrl;  
    /**
     * Creates a new instance of Apadrinar
     */
    
    public ApadrinarBean() {
        
        // Inicializamos una lista de apadrinar a null, que nos permitirá saber si un niño está apadrinado.
       
        List<Apadrinar> apadrinarList= new ArrayList<>();
        apadrinarList = null;
        this.ap_lista = apadrinarList;
        
        
        // Creamos una lista de niños que inicialmente no están apadrinados.
        List<Niño> lista_ninos= new ArrayList<>();
        Niño n1 = new Niño("Manolo", "Abandonado", "", apadrinarList);
        Niño n2 = new Niño("Pablo", "Atún", "", apadrinarList);
        Niño n3 = new Niño("Antonio", "Pérez", "", apadrinarList);
        Niño n4 = new Niño("Tarun", "Vaca", "", apadrinarList);
        Niño n5 = new Niño("Iman", "Nevera", "", apadrinarList);
        lista_ninos.add(n1); lista_ninos.add(n2); lista_ninos.add(n3); lista_ninos.add(n4); lista_ninos.add(n5);
        this.ninos = lista_ninos;
        
        
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
    
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

   
    
    
    public String consultar(){
        FacesContext ctx = FacesContext.getCurrentInstance();
        if(ctrl.getAgente() != null){
            //Cogemos al agente actual de la sesión y le asignamos los niños disponibles.
            ag = (Agente) ctrl.getAgente();
            ag.setNiñoList(ninos);
            Niño por_apadrinar = null;
            // Comprobamos si hay niños a la espera de ser apadrinados. Si lo hay, se coge el primero de la lista.
            for (Niño n : ninos) {
                if(n.getApadrinarList() != null){
                    por_apadrinar = n;
                    break;
                }
            }
            
            // Consultar usuarios en la base de datos, dejar para EJB.
            if(por_apadrinar != null){
                // Asignar niño.
                //Creamos un usuario ficticio, ya que no podemos aún consultar la base de datos.
                user = new Usuario("normal", "boniatos"); 
                Date fecha = new Date();        
                Apadrinar a1 = new Apadrinar(15.0,fecha , null, user);
                ap_lista.add(a1);
                user.setApadrinarList(ap_lista);
                boolean asig = false;
                for(Apadrinar a : user.getApadrinarList()){
                    if(a.getNiñoCodigo() == null){
                        a.setNiñoCodigo(por_apadrinar); 
                        asig = true;
                        break;
                    }
                }
                if(!asig){
                    ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ningún socio quiere apadrinar.","Ningún socio quiere apadrinar." )); 
                }
            }else{
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ningún niño está disponible.","Ningún niño está disponible." )); 
        
            }
            
        }
        return null;
    }
    
   
    
    public String notificar(){
        
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage fm = null;
        //1. Doble comprobación de usuario. Si está logeado, sus datos de sesión se mantienen en controlAutorización.
        if(usuario.equals(ctrl.getUsuario().getUsuario()) && contrasenia.equals(ctrl.getUsuario().getContrasenia())){
            
            if(check){
                user = ctrl.getUsuario();
                //2. Creamos un objeto de apadrinar que aún no está relacionado por ningún niño.
                Date fecha = new Date();        
                Apadrinar a1 = new Apadrinar(15.0,fecha , null, user);
                if(user.getApadrinarList() == null){
                    //primer niño que apadrina.                
                    List<Apadrinar> apadrinarList= new ArrayList<>();
                    apadrinarList.add(a1);
                    user.setApadrinarList(apadrinarList);
                }else{
                    // no es el primer niño que apadrina, hay que reutilizar la lista.
                    user.getApadrinarList().add(a1);
                }

                // Depende de los agentes lo que queda.           

                // 3. Devolver página.
               
                return "gracias.xhtml";
            }else{
                fm = new FacesMessage("Debe aceptar los términos y condiciones.");
                ctx.addMessage("apadrinarB:check", fm);
            }
            
            
        }else{
            fm = new FacesMessage("Usuario o contraseña incorrectos. Introdúzcalos de nuevo.");
            ctx.addMessage("apadrinarB:usuario", fm);            
        }
        
        return null;
    }
    
}