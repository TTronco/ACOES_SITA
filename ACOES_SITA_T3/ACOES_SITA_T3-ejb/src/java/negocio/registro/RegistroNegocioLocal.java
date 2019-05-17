/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.registro;

import Modelo.Agente;
import Modelo.Usuario;
import javax.ejb.Local;

/**
 *
 * @author imanb
 */
@Local
public interface RegistroNegocioLocal {
    public void registrarUsuario(Usuario u)throws CuentaException;
    public void crearAgente();
    public Usuario compruebaLoginNormal(String user, String password) throws CuentaException;
    public Agente compruebaLoginAg(String user, String password) throws CuentaException;
    public Usuario refrescarUsuario(Usuario u, String ant_user, String ant_pass) throws CuentaException;
    public Agente refrescarAgente(Agente a, String ant_user, String ant_pass) throws CuentaException;
    public void modificarUsuario(Usuario u, String ant_user, String ant_pass) throws CuentaException;
    public void modificarAgente(Agente a, String ant_user, String ant_pass) throws CuentaException;
}
