/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.login;

import Modelo.Agente;
import Modelo.Usuario;
import javax.ejb.Local;
import negocio.registro.CuentaException;

/**
 *
 * @author imanb
 */
@Local
public interface LoginNegocioLocal {
    public void crearAgente();
    public Usuario compruebaLoginNormal(String user, String password) throws CuentaException;
    public Agente compruebaLoginAg(String user, String password) throws CuentaException;
    public Usuario refrescarUsuario(Usuario u) throws CuentaException;
    
}
