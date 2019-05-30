/* Tarea 3: Aplicación Completa.
 * @author Grupo 1:
 *          - Tarun D. 
            - Salvador C. F.
            - Iman H. M.
            - Antonio P. C.
 */

package tareas.agente;

import Modelo.Niño;
import autenticacion.ControlAutorizacion;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import negocio.adminNinios.AdministrarException;
import negocio.adminNinios.AdministrarNiniosLocal;
import negocio.adminNinios.NinioNoEncontradoException;


@Named(value = "adminNinio")
@RequestScoped
public class AdminNinio {
    
    private Niño nuevo;
    private String fecha_nacimiento;
    
    
    @Inject
    private ControlAutorizacion ctrl;
    
    @EJB
    private AdministrarNiniosLocal negocio;
                                    
   
    public AdminNinio() {
        nuevo = new Niño();
    }

    public Niño getNuevo() {
        return nuevo;
    }

    public void setNuevo(Niño nuevo) {
        this.nuevo = nuevo;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }
    
    public List<String> ninios(){
        return negocio.ninios();
    }
    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
    
    private boolean fecha_valida(int dia, int mes, int año){
        boolean result = true;
        
        if(dia <= 0 || dia > 31)
            result = false;
        if(mes<=0 || mes >12)
            result = false;
        
        Date hoy = new Date(); int actual_year = hoy.getYear();
        if(actual_year - año > 18)
            result = false;
        
        if(mes == 2 && dia > 28)
            result = false;
        
        if( (mes == 4 || mes ==6 || mes==9 || mes==11) && mes>30)
            result = false;
        
        
        return result;
    }
    
    public String aniadir(){
        FacesMessage fm = null;
        FacesContext ctx = FacesContext.getCurrentInstance();
        try{
            Scanner sc = new Scanner(fecha_nacimiento);
            sc.useDelimiter("/");
            
            if(sc.hasNext()){
                int dia = Integer.parseInt(sc.next());
                int mes = Integer.parseInt(sc.next());
                int año = Integer.parseInt(sc.next()); 
                
                if(!fecha_valida(dia,mes,año)){
                    
                    fm = new FacesMessage("La fecha introducida no es correcta.");
                    ctx.addMessage(fecha_nacimiento, fm);
                    return null;
                }
                    
            }
            
        }catch(NumberFormatException e){
            fm = new FacesMessage("Debe introducir valores numéricos en la fecha.");
            ctx.addMessage(fecha_nacimiento, fm);
            return null;
        }catch(NoSuchElementException e){
            fm = new FacesMessage("Por favor, introduza la fecha en el formato DD/MM/FF");
            ctx.addMessage(fecha_nacimiento, fm);
            return null;
        }
        
        Date fecha = new Date(fecha_nacimiento);
        nuevo.setFechaNacimiento(fecha);
        
        Date hoy = new Date();
        nuevo.setFechaEntrada(hoy); nuevo.setFechaAlta(hoy);
        
        if(ctrl.getAgente() != null)
            nuevo.setCentro(ctrl.getAgente().getIdCentro());
        
        negocio.aniadirNinio(nuevo);
        
        return "adm_ninos.xhtml";
    }
    
    public void eliminar(boolean seguro, String kid){
        if(seguro){
            FacesMessage fm = null;
            FacesContext ctx = FacesContext.getCurrentInstance();
            try{
                Scanner sc = new Scanner(kid);
                sc.useDelimiter(" ");
                int code = 0;
                if(sc.hasNext())
                    code = Integer.parseInt(sc.next());
                
                negocio.eliminarNinio(code);
                
                String name = "";
                
                if(sc.hasNext())
                    name = sc.next();
                
                fm = new FacesMessage("Se ha eliminado el niño " + name + " con código " + code);
                ctx.addMessage(fecha_nacimiento, fm);   
            }catch(NumberFormatException e){
                fm = new FacesMessage("Fallo inesperado.");
                ctx.addMessage(fecha_nacimiento, fm);                
            }catch(NinioNoEncontradoException e){
                fm = new FacesMessage("Fallo inesperado.");
                ctx.addMessage(fecha_nacimiento, fm);    
            }catch(AdministrarException e){
                fm = new FacesMessage("Fallo inesperado.");
                ctx.addMessage(fecha_nacimiento, fm);    
            }
            
        }
    }
}
