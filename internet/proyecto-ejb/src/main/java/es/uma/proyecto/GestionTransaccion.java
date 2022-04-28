package es.uma.proyecto;

import javax.ejb.Local;

import es.uma.proyecto.exceptions.ClienteException;
import es.uma.proyecto.exceptions.CuentaException;

@Local
public interface GestionTransaccion {

	/* REQUISITO 14
	 * La aplicación permitirá a un cliente que sea persona física 
	 * y un autorizado a una cuenta cuyo cliente sea persona jurídica 
	 * realizar transacciones entre cuentas bancarias. 
	 * El usuario necesita escoger una cuenta de origen (de aquellas a las que tiene acceso) 
	 * e indicar una cuenta de destino mediante su IBAN. Internamente Los saldos deberán actualizarse 
	 * adecuadamente en las cuentas de eBury y en las cuentas asociadas a las de eBury.
	 */
	public void transaccion(String id, String ib1, String ib2, double trans) throws ClienteException, CuentaException;
}
