/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.apadrinar;

import Modelo.Apadrinar;
import Modelo.Niño;
import Modelo.Usuario;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author imanb
 */
@Local
public interface ApadrinamientoNegocioLocal {
    public void crearNiños();
    public int peticiones();
    public Map<Usuario,Niño> disponiblesAp();
    public void solicitudApadrinar (Usuario u) throws ApadrinarException;
    public void asignar(Usuario u, Niño n) throws ApadrinarException;
    public List<Apadrinar> apadrinar();
    public List<Usuario> usuariosSolicitantes();
    public List<Niño> niniosDisponibles();
    
}
