package es.uma.proyecto;

import javax.ejb.Local;

import es.uma.proyecto.exceptions.AdministrativoException;
import es.uma.proyecto.exceptions.ClienteException;
import es.uma.proyecto.exceptions.UsuarioException;

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
    
    
    /**
     * Metodo para dar de alta a un cliente (Individual o PersonaAutorizada)
     * Devuelve si el usuario se ha dado correctamente de alta 
     */
    public boolean darAltaCliente(Cliente cliente, boolean individual) throws ClienteException;
    
    
    
    /**
     * Metodo para dar de Baja al cliente
     * Elimina la posibilidad del usuario de conectarse (no será ni individual ni persona autorizada)
     */
    public boolean darBajaCliente(Cliente cliente) throws ClienteException;
    
    
    /**
     * Metodo para que el administrativo modifique los datos de un cliente
     * @param es el objeto del cliente a modificar
     * Devuelve el cliente modificado
     */
    public Cliente modificarCliente(Cliente cliente);
    
}
