package es.uma.proyecto;

import javax.ejb.Local;

import es.uma.proyecto.entidades.Usuario;
import es.uma.proyecto.exceptions.UsuarioException;

@Local
public interface GestionCuentasUsuarios {

    /**
     * Crea un usuario
     * @param usuario Nombre de la cuenta
     * @param password Contraseña
     * @return Devuelve el usuario si se crea
     * @throws UsuarioException Se lanzara si el usuario ya existe
     */
	
    public void CrearUsuario(Usuario user) throws UsuarioException;

    
    /* REQUISITO 10 */
    public Usuario getUsuario(String nombre) throws UsuarioException;
    
    public Usuario iniciarSesion(String nombre, String password) throws UsuarioException;
}
