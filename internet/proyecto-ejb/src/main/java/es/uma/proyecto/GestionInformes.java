package es.uma.proyecto;

import java.sql.Date;
import java.util.List;

import javax.ejb.Local;

import es.uma.proyecto.entidades.Cliente;
import es.uma.proyecto.entidades.Cuenta;
import es.uma.proyecto.entidades.CuentaFintech;

@Local
public interface GestionInformes {

	/* REQUISITO 11
	 * La aplicación implementará una API REST con tres endpoints que proporcionen 
	 * la información de las cuentas y los clientes. 
	 * En el caso de que algún campo no esté disponible en el modelo de datos se devuelve "non-existent". 
	 * Hay que reportar las cuentas segregadas asociadas a los clientes 
	 * y en el caso de las cuentas de referencia asociadas a las pooled
	 * hay que devolverlas todas indicando que su propietario es la FINTECH 
	 * (no el cliente, puesto que esa cuenta no es suya).
	 */
	public List<String> informeCuentasPaisesBajos(boolean status, String productNumber);
	public List<String> informeClientePaisesBajos(Date alta, Date baja, String name, String apellidos, String direccion, String cp);
	
	
	
	/* REQUISITO 12
	 * La aplicación será capaz de generar un fichero CSV con la información que exige Alemania 
	 * (ver esta información en la presentación de eBury). Deberá haber un botón de descarga para que una persona 
	 * administrativa de la empresa pueda descargarlo y posteriormente subirlo al SFTP (ajeno a la aplicación). 
	 * Hay dos tipos de informes: el inicial y el periódico (con menos información). 
	 * El usuario administrativo deberá poder escoger entre estos dos informes.
	 * Hay que reportar las cuentas segregadas asociadas a los clientes y en el caso de las cuentas de 
	 * referencia asociadas a las pooled, hay que devolverlas todas indicando que su propietario es la FINTECH 
	 * (no el cliente, puesto que esa cuenta no es suya).
	 */
	
	public List<Cuenta> informeAlemania();
	

	public List<Cliente> getClientesPais(String pais);
	
	
	public List<CuentaFintech> getCuentasPais(String pais);

}
