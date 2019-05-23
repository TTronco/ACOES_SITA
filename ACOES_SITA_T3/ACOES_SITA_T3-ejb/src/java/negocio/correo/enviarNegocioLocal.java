/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.correo;

import Modelo.Apadrinar;
import Modelo.Correo;
import Modelo.Niño;
import Modelo.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author imanb
 */
@Local
public interface enviarNegocioLocal {
    public void guardarCarta(Correo c, String n, Usuario u) throws CorreoException;
    public List<String> lista_ninos(Usuario u);
}
