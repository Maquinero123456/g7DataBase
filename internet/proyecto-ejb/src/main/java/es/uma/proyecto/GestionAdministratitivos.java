package es.uma.proyecto;

import javax.ejb.Local;

import es.uma.proyecto.entidades.Cliente;
import es.uma.proyecto.entidades.Empresa;
import es.uma.proyecto.entidades.PersonaAutorizada;
import es.uma.proyecto.entidades.Usuario;
import es.uma.proyecto.exceptions.AdministrativoException;
import es.uma.proyecto.exceptions.AutorizacionException;
import es.uma.proyecto.exceptions.ClienteException;
import es.uma.proyecto.exceptions.CuentaException;
import es.uma.proyecto.exceptions.PersonaAutorizadaException;

@Local
public interface GestionAdministratitivos {
   
	
	/** REQUISITO 1
     * Metodo para iniciar sesion
     * Si el usuario y la contraseña coinciden con la de la base de datos
     * Devuelve el usuario
     * @param usuario Usuario del administrativo
     * @param password Contraseña del administrativo
     * @throws AdministrativoException Se lanza si no se encuentra el usuario o la contraseña no coincide 
     */
    public Usuario iniciarSesion(String usuario, String password) throws AdministrativoException;
    
    
    /** REQUISITO 2
     * Metodo para dar de Alta al cliente
     * @param le pasamos el ID del cliente para buscarlo en la base de datos
     * @throws ClienteException en que caso de que el cliente en cuestion no exista)
     */
    public void darAltaCliente(String id) throws ClienteException;
    
    
     
    /** REQUISITO 4
     * Metodo para dar de Baja al cliente
     * @param le pasamos el ID del cliente para buscarlo en la base de datos
     * @throws ClienteException en que caso de que el cliente en cuestion no exista)
     */
    public void darBajaCliente(String id) throws ClienteException;
    
    
    /** REQUISITO 3
     * Metodo para que el administrativo modifique los datos de un cliente
     * @param cliente es el objeto del cliente a modificar
     */
    public void modificarCliente(Cliente cliente) throws ClienteException;
 
    
    
    /** REQUISITO 5
     * La aplicación permitirá a un administrativo la apertura de una cuenta.
     * Será necesario que haya más de una cuenta externa en el caso de una cuenta agrupada con varias divisas.
     * @param le pasamos el iban
     * @param y el tipo de cuenta agrupado o segregada
     * @throws CuentaException en caso de que ya existe una cuenta con ese iban
     */
    public void aperturaCuenta(String iban, String tipo) throws CuentaException, AdministrativoException;
    
    
    
    /** REQUISITO 6
     La aplicación permitirá a un administrativo añadir personas autorizadas a las cuentas que pertenezcan a cliente 
     que son personas jurídicas. Las personas autorizadas serán las que podrán entrar en la aplicación para realizar
     operaciones con la cuenta.
     * @throws AutorizacionException
     */
    public void addAutorizados(long idEmp, long idPer, String tipo) throws ClienteException, PersonaAutorizadaException, AutorizacionException;
    
    
    /** REQUISITO 7
     La aplicación permitirá a un administrativo modificar los datos de las personas autorizadas 
     a operar con cuentas de clientes que son personas jurídicas.
     */
    public void modificarAutorizado(PersonaAutorizada persona) throws PersonaAutorizadaException;
    
    
    /** REQUISITO 8
     * La aplicación permitirá a un administrativo dar de baja a personas autorizadas 
     * a operar con cuentas cuyos clientes sean personas jurídicas. 
     * Estas personas no se eliminan del sistema, ya que podría ser necesario que la información 
     * conste para alguna auditoría o informe. 
     * Una persona autorizada que esté de baja no puede acceder a la cuenta en la que se encontraba
     * autorizada.
     * @param id de la empresa
     * @param id de la persona autorizada que daremos de baja
     * @throws AutorizacionException
     */
    public void eliminarAutorizado(long idEmpresa, long idPersona) throws ClienteException, PersonaAutorizadaException, AutorizacionException;
    
    
    /** REQUISITO 9
     * La aplicación permitirá a un administrativo cerrar una cuenta bancaria. 
     * Solo se puede cerrar una cuenta que tenga saldo 0 (en todas sus divisas). 
     * Una cuenta cerrada no se elimina, por si es necesario reportarla en algún informe.
    */
    public void cerrarCuenta(String iban) throws CuentaException;
}
