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
    
    private String nombre;
 
    private String apellido1;
  
    private String apellido2;
    
    private String nif;

    private String direccion;

    private String poblacion;

    private Integer codPostal;
    
    private Integer telefonoMovil;
    
    private String usuario;
    private String contrasenia;
    
    private Usuario nuevo;

    
    
    /**
     * Creates a new instance of Registro
     */
    public Registro()  {
        
        // 1. Creamos un usuario con dos datos proporcionados.
        this.nuevo = new Usuario (nombre, apellido1, apellido2, nif, direccion, poblacion, codPostal, telefonoMovil, usuario, contrasenia);
        
    }
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public Integer getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(Integer codPostal) {
        this.codPostal = codPostal;
    }

    public Integer getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(Integer telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Usuario getNuevo() {
        return nuevo;
    }

    public void setNuevo(Usuario nuevo) {
        this.nuevo = nuevo;
    }
    
    
    
    

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public String registrarse (){
       
        FacesContext ctx = FacesContext.getCurrentInstance();
        //1. Comprobamos si el usuario está registrado.
            File archivo = null;
            FileReader fr = null;
            BufferedReader br = null;
            boolean ok = true;
            try {

             archivo = new File ("users.txt");
             fr = new FileReader (archivo);
             br = new BufferedReader(fr);

             // Lectura del fichero

             String line; 
             while((line=br.readLine())!=null){
                 Scanner sc = new Scanner(line);
                 sc.useDelimiter(",");
                 if(sc.hasNext()){
                     String l = sc.next();
                      System.out.println(line);
                     if(l.equals(usuario)){
                         ok = false;
                         break;
                     }
                 }
             }

          }
          catch(Exception e){
             e.printStackTrace();
          }finally{

             try{                    
                if( null != fr ){   
                   fr.close();     
                }                  
             }catch (Exception e2){ 
                e2.printStackTrace();
             }
          }
        
        //2. Registramos al usuario si no lo estaba antes.
         
            if(ok){
                // Guardamos esos datos en un fichero a modo de base de datos para consultarlos en el login.
                FileWriter fichero = null;
                PrintWriter pw = null;
                try{
                    fichero = new FileWriter("users.txt");
                    pw = new PrintWriter(fichero);
                    pw.println(nombre + " " + apellido1 + " " +  apellido2 + " " +  nif + " " +  direccion + " " +  poblacion + " " +  codPostal + " " +  telefonoMovil + " " +  usuario + " " +  contrasenia);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                   try {

                   if (null != fichero)
                      fichero.close();
                   } catch (Exception e2) {
                      e2.printStackTrace();
                   }
                }
                
        }else{
             ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El usuario " + usuario + " introducido ya está registrado.", "El usuario " + usuario + "  introducido ya está registrado."));
        }
           
         
       
        
        
        
        return "login.xhtml";
    }
}





