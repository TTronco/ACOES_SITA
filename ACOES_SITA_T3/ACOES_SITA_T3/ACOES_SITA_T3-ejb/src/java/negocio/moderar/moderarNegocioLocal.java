/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.moderar;

import Modelo.Correo;
import java.util.List;
import javax.ejb.Local;

/* Tarea 3: Aplicación Completa.
 * @author Grupo 1:
 *          - Tarun D. 
            - Salvador C. F.
            - Iman H. M.
            - Antonio P. C.
 */
@Local
public interface moderarNegocioLocal {
    public int notificaciones();
    public List<Correo> listaCorreos();
    public void validarCorreo(Correo c);
    public void rechazarCorreo(Correo c);
}
