/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registro;

import Modelo.Usuario;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author imanb
 */
@Named(value = "controlRegistro")
@Dependent
public class ControlRegistro implements Serializable{
    
    private Usuario nuevo;

    public Usuario getNuevo() {
        return nuevo;
    }

    public void setNuevo(Usuario nuevo) {
        this.nuevo = nuevo;
    }
    
    
}
