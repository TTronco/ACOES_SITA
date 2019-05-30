/* Tarea 3: Aplicación Completa.
 * @author Grupo 1:
 *          - Tarun D. 
            - Salvador C. F.
            - Iman H. M.
            - Antonio P. C.
 */
package negocio.adminNinios;

import Modelo.Niño;
import java.util.List;
import javax.ejb.Local;


@Local
public interface AdministrarNiniosLocal {
    public void aniadirNinio (Niño n);
    public List<String> ninios();
    public void eliminarNinio(int id) throws AdministrarException;
}
