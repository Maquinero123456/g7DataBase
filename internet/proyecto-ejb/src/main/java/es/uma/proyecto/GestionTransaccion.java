package es.uma.proyecto;

import javax.ejb.Local;

import es.uma.proyecto.exceptions.ClienteException;
import es.uma.proyecto.exceptions.CuentaException;
import es.uma.proyecto.exceptions.IndividualException;
import es.uma.proyecto.exceptions.SaldoException;

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
	public void transaccionIndividual(String identificacion, String ibanOrigen, String ibanDestino, Double cantidad, String tipo) throws ClienteException, CuentaException, IndividualException, SaldoException;
}
