package es.uma.proyecto;

import javax.ejb.Local;

import es.uma.proyecto.exceptions.AdministrativoException;

@Local
public interface GestionAdministratitivos {
    /**
     * Metodo para iniciar sesion
     * Si el usuario y la contraseña coinciden con la de la base de datos
     * Devuelve el usuario
     * @param usuario Usuario del administrativo
     * @param password Contraseña del administrativo
     * @throws AdministrativoException Se lanza si no se encuentra el usuario o la contraseña
     *  no coincide 
     */
    
    
    public Usuario iniciarSesion(String usuario, String password) throws AdministrativoException;
}
