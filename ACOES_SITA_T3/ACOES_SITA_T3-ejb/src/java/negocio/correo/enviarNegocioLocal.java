/* Tarea 3: Aplicación Completa.
 * @author Grupo 1:
 *          - Tarun D. 
            - Salvador C. F.
            - Iman H. M.
            - Antonio P. C.
 */
package negocio.correo;

import Modelo.Apadrinar;
import Modelo.Correo;
import Modelo.Niño;
import Modelo.Usuario;
import java.util.List;
import javax.ejb.Local;

@Local
public interface enviarNegocioLocal {
    public void guardarCarta(Correo c, String n, Usuario u) throws CorreoException;
    public List<String> lista_ninos(Usuario u);
    public void enviarContenedor(Correo c);
}
