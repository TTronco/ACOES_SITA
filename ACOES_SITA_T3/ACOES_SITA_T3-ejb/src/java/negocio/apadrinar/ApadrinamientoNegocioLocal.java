/* Tarea 3: Aplicación Completa.
 * @author Grupo 1:
 *          - Tarun D. 
            - Salvador C. F.
            - Iman H. M.
            - Antonio P. C.
 */
package negocio.apadrinar;

import Modelo.Apadrinar;
import Modelo.Niño;
import Modelo.Usuario;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;


@Local
public interface ApadrinamientoNegocioLocal {
    public void crearNiños();
    public int peticiones();
    public Map<Usuario,Niño> disponiblesAp()  throws ApadrinarException;
    public void solicitudApadrinar (Usuario u) throws ApadrinarException;
    public void asignar(Usuario u, Niño n) throws ApadrinarException;
    public void desapadrinar(Usuario u) throws ApadrinarException;
    /*
    public List<Apadrinar> apadrinar();
    public List<Usuario> usuariosSolicitantes();
    public List<Niño> niniosDisponibles();
    */
}
