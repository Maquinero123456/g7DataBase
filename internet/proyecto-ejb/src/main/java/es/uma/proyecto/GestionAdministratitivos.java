package es.uma.proyecto;

import javax.ejb.Local;

import es.uma.proyecto.exceptions.AdministrativoException;
import es.uma.proyecto.exceptions.AutorizacionException;
import es.uma.proyecto.exceptions.ClienteException;
import es.uma.proyecto.exceptions.CuentaException;
import es.uma.proyecto.exceptions.PersonaAutorizadaException;

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
    public void darAltaCliente(Cliente cliente) throws ClienteException;
    
    
    
    /**
     * Metodo para dar de Baja al cliente
     * Elimina la posibilidad del usuario de conectarse (no será ni individual ni persona autorizada)
     */
    public void darBajaCliente(Cliente cliente) throws ClienteException;
    
    
    /**
     * Metodo para que el administrativo modifique los datos de un cliente
     * @param cliente es el objeto del cliente a modificar
     * Devuelve el cliente modificado
     */
    public Cliente modificarCliente(Cliente cliente) throws ClienteException;
 
    
    
    /**
     *La aplicación permitirá a un administrativo la apertura de una cuenta. 
     *La cuenta podrá ser agrupada (pooled) o segregada (segregated). En ambos casos la(s) cuenta(s) 
     *externa(s) asociada(s) se añade(n) como información, no se hace nada más. 
     *Será necesario que haya más de una cuenta externa en el caso de una cuenta agrupada con varias divisas.
     */
    public void aperturaCuenta();
    
    /**
     La aplicación permitirá a un administrativo añadir personas autorizadas a las cuentas que pertenezcan a cliente 
     que son personas jurídicas. Las personas autorizadas serán las que podrán entrar en la aplicación para realizar
     operaciones con la cuenta.
     * @throws AutorizacionException
     */
    public void addAutorizados(Empresa empresa, PersonaAutorizada persona, String tipo) throws ClienteException, PersonaAutorizadaException, AutorizacionException;
    
    
    /** 
     La aplicación permitirá a un administrativo modificar los datos de las personas autorizadas 
     a operar con cuentas de clientes que son personas jurídicas.
     */
    public void modificarAutorizado(PersonaAutorizada persona) throws PersonaAutorizadaException;
    
    
    /**
     * La aplicación permitirá a un administrativo dar de baja a personas autorizadas 
     * a operar con cuentas cuyos clientes sean personas jurídicas. 
     * Estas personas no se eliminan del sistema, ya que podría ser necesario que la información 
     * conste para alguna auditoría o informe. 
     * Una persona autorizada que esté de baja no puede acceder a la cuenta en la que se encontraba
     * autorizada.
     * @throws AutorizacionException
     */
    public void eliminarAutorizado(Empresa empresa, PersonaAutorizada persona) throws ClienteException, PersonaAutorizadaException, AutorizacionException;
    
    
    /**
     * La aplicación permitirá a un administrativo cerrar una cuenta bancaria. 
     * Solo se puede cerrar una cuenta que tenga saldo 0 (en todas sus divisas). 
     * Una cuenta cerrada no se elimina, por si es necesario reportarla en algún informe.
    */
    public void cerrarCuenta() throws CuentaException;
}
