package es.uma.proyecto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.naming.NamingException;

import es.uma.proyecto.entidades.CuentaFintech;
import es.uma.proyecto.exceptions.*;

import org.glassfish.appclient.client.CLIBootstrap;
import org.junit.Before;
import org.junit.Test;

import es.uma.proyecto.GestionAdministratitivos;
import es.uma.proyecto.entidades.Usuario;
import es.uma.proyecto.Administrativos;

import es.uma.proyecto.entidades.Cliente;
import es.uma.proyecto.entidades.Empresa;
import es.uma.proyecto.entidades.PersonaAutorizada;
import es.uma.proyecto.entidades.Usuario;
import es.uma.proyecto.exceptions.AdministrativoException;

public class AdministrativosPrueba {
    private static final Logger LOG = Logger.getLogger(Administrativos.class.getCanonicalName());

	private static final String ADMINISTRATIVOS_EJB = "java:global/classes/Administrativos";
	private static final String CUENTASUSUARIOS_EJB = "java:global/classes/CuentasUsuarios";
	private static final String CLIENTES_EJB = "java:global/classes/Clientes";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "proyectoTest";
	private static final String PERSONA_AUTORIZADA = "java:global/classes/Autorizados";

	private GestionAdministratitivos gestionAdministratitivos;
	private GestionCuentasUsuarios gestionCuentasUsuarios;
	private GestionClientes gestionClientes;
	private GestionCuentas gestionCuentas;
	private GestionAutorizados gestionAutorizados;

    @Before
	public void setup() throws NamingException  {
		gestionAdministratitivos = (GestionAdministratitivos) SuiteTest.ctx.lookup(ADMINISTRATIVOS_EJB);
		gestionCuentasUsuarios = (GestionCuentasUsuarios) SuiteTest.ctx.lookup(CUENTASUSUARIOS_EJB);
		gestionClientes = (GestionClientes) SuiteTest.ctx.lookup(CLIENTES_EJB);
		gestionAutorizados  = (GestionAutorizados) SuiteTest.ctx.lookup(PERSONA_AUTORIZADA);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}

    @Test
    public void testIniciarSesionAdministrativo(){
        Usuario admin = new Usuario("Pepito", "Juanito", true);
		
		try{
			gestionCuentasUsuarios.CrearUsuario(admin);
		}catch(UsuarioException e){
			fail("Usuario no deberia existir");
		}

		try {
			gestionAdministratitivos.iniciarSesion("Pepito","Juanito");
		}catch (AdministrativoException e) {
			fail("Se deberia haber iniciado sesion");
		}
    }

	@Test
    public void testIniciarSesionAdministrativoNoAdministrativo(){
        Usuario user = new Usuario("Paco", "Paco", false);

		try{
			gestionCuentasUsuarios.CrearUsuario(user);
		}catch(UsuarioException e){
			fail("Usuario no deberia existir");
		}


		Exception exception = assertThrows(AdministrativoException.class, () -> {
            gestionAdministratitivos.iniciarSesion("Paco", "Paco");
        });
    
        String expectedMessage = "El usuario NO es administrativo";
        String actualMessage = exception.getMessage();
    
        assertTrue(actualMessage.contains(expectedMessage));
    }

	@Test
    public void testIniciarSesionAdministrativoPasswordIncorrecta(){
        Usuario user = new Usuario("Paco", "Paco", true);

		try{
			gestionCuentasUsuarios.CrearUsuario(user);
		}catch(UsuarioException e){
			fail("Usuario no deberia existir");
		}


		Exception exception = assertThrows(AdministrativoException.class, () -> {
            gestionAdministratitivos.iniciarSesion("Paco", "Anda");
        });
    
        String expectedMessage = "Password incorrecta";
        String actualMessage = exception.getMessage();
    
        assertTrue(actualMessage.contains(expectedMessage));
    }

	@Test
    public void testIniciarSesionAdministrativoNoExisteUsuario(){
		Exception exception = assertThrows(AdministrativoException.class, () -> {
            gestionAdministratitivos.iniciarSesion("Paco", "Anda");
        });
    
        String expectedMessage = "Usuario no encontrado";
        String actualMessage = exception.getMessage();
    
        assertTrue(actualMessage.contains(expectedMessage));
    }

	@Test
	public void testDarDeAltaCliente () {
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		Cliente c1 = new Cliente("testAlta", "fisica", "Baja", sqlDate, "Avenida 123", "Maracay", "123", "PaisesBajos");
		
		try{
			gestionClientes.crearCliente(c1);
		}catch(ClienteException e){
			fail("Deberia poder crear el cliente");
		}
		try{
			gestionAdministratitivos.darAltaCliente("testAlta");
		}catch(ClienteException e){
			fail("Deberia encontrar al cliente al darlo de alta");
		}
		try{
			c1 = gestionClientes.getCliente("testAlta");
		}catch(ClienteException e){
			fail("Deberia encontrar el cliente");
		}

		assertEquals("Alta", c1.getEstado());
	}

	@Test
	public void testDarDeBajaCliente () {
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		Cliente c1 = new Cliente("testBaja", "fisica", "Alta", sqlDate, "Avenida 123", "Maracay", "123", "PaisesBajos");
		
		try{
			gestionClientes.crearCliente(c1);
		}catch(ClienteException e){
			fail("Deberia poder crear el cliente");
		}
		try{
			gestionAdministratitivos.darBajaCliente("testBaja");
		}catch(ClienteException e){
			fail("Deberia encontrar al cliente al darlo de baja");
		}
		try{
			c1 = gestionClientes.getCliente("testBaja");
		}catch(ClienteException e){
			fail("Deberia encontrar el cliente");
		}

		assertEquals("Baja", c1.getEstado());
	}

	@Test
	public void testModificarCliente () {
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		Cliente c1 = new Cliente("testAlta", "fisica", "Alta", sqlDate, "Avenida 123", "Maracay", "123", "PaisesBajos");
		
		try{
			gestionClientes.crearCliente(c1);
		}catch(ClienteException e){
			fail("Deberia poder crear el cliente");
		}

		Cliente c2 = new Cliente("testAlta", "fisica", "Alta", sqlDate, "Avenida Falsa", "Guacamayo", "231", "PaisesAltos");

		try{
			gestionAdministratitivos.modificarCliente(c2);
		}catch(ClienteException e){
			fail("Deberia encontrar cliente");
		}
		
		try {
			c1 = gestionClientes.getCliente("testAlta");
		} catch (ClienteException e) {
			fail("Deberia encontrar el cliente");
		}

		assertTrue("Deberia haber cambiado direccion, ciudad, codigo postal y pais", 
		c1.getDireccion().equals(c2.getDireccion()) && c1.getCiudad().equals(c2.getCiudad()) && c1.getCodigoPostal().equals(c2.getCodigoPostal()) && c1.getPais().equals(c2.getPais()) );
	
	}

	@Test
	public void testAperturaCuenta() throws CuentaException, AdministrativoException {
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		CuentaFintech prueba = new CuentaFintech("ES45450545054505", null, true, sqlDate, null, "segregada");
		gestionAdministratitivos.aperturaCuenta("ES45450545054505", "segregada");
		CuentaFintech cf = (CuentaFintech) gestionCuentas.getCuenta("ES45450545054505");
		assertEquals(prueba, cf);
	}

	@Test
	public void testAddAutorizados() {
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		Empresa emp = new Empresa("empreTestAddAutot", "fisica", "alta", sqlDate, sqlDate, "Avenida prueba", "Malaga","29010", "PaisesBajos", "prueba");
		PersonaAutorizada pA = new PersonaAutorizada("perAutTestAddAutot", "Persona", "Autorizado", "Avenida 123", sqlDate, "Mara cay", sqlDate, sqlDate);
		
		try {
			gestionAutorizados.addPersonaAutorizada(pA);
		} catch (PersonaAutorizadaException e) {
			fail ("La persona autorizada ya exisiste");
		}

		try {
			pA = gestionAutorizados.getPersonaAutorizada("perAutTestAddAutot");
		} catch (PersonaAutorizadaException e) {
			fail ("La persona autorizada deberia exisitir");
		}

		try {
			gestionClientes.crearEmpresa(emp);
		} catch (EmpresaException e) {
			fail ("La empresa ya exisiste");
		}

		try {
			emp = gestionClientes.getEmpresa("empreTestAddAutot");
		} catch (EmpresaException e) { 
			fail ("La empresa deberia exisitir");
		}

		try {
			gestionAdministratitivos.addAutorizados(emp.getID(), pA.getID(), "tipo");
		} catch (ClienteException e) {
			fail ("La empresa deberia existir");
		} catch (AutorizacionException e) {
			fail ("La persona no tiene autorización de la empresa");
		} catch (PersonaAutorizadaException e)  {
			fail ("Persona no encontrada");
		}
		
	}

	@Test
	public void testModificarAutorizado () {
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		Empresa emp = new Empresa("empreTestAddAutot", "fisica", "alta", sqlDate, sqlDate, "Avenida prueba", "Malaga","29010", "PaisesBajos", "prueba");
		PersonaAutorizada pA = new PersonaAutorizada("perAutTestAddAutot", "Persona", "Autorizado", "Avenida 123", sqlDate, "Mara cay", sqlDate, sqlDate);
		
		try {
			gestionAutorizados.addPersonaAutorizada(pA);
		} catch (PersonaAutorizadaException e) {
			fail ("La persona autorizada ya exisiste");
		}

		try {
			pA = gestionAutorizados.getPersonaAutorizada("perAutTestAddAutot");
		} catch (PersonaAutorizadaException e) {
			fail ("La persona autorizada deberia exisitir");
		}

		try {
			gestionClientes.crearEmpresa(emp);
		} catch (EmpresaException e) {
			fail ("La empresa ya exisiste");
		}

		try {
			emp = gestionClientes.getEmpresa("empreTestAddAutot");
		} catch (EmpresaException e) { 
			fail ("La empresa deberia exisitir");
		}

		try {
			gestionAdministratitivos.addAutorizados(emp.getID(), pA.getID(), "tipo");
		} catch (ClienteException e) {
			fail ("La empresa deberia existir");
		} catch (AutorizacionException e) {
			fail ("La persona no tiene autorización de la empresa");
		} catch (PersonaAutorizadaException e)  {
			fail ("Persona no encontrada");
		}

		try {
			PersonaAutorizada mod = new PersonaAutorizada("perAutTestAddAutot", "modPersona", "Autorizado", "Avenida 123", sqlDate, "Mara cay", sqlDate, sqlDate);
			gestionAdministratitivos.modificarAutorizado(mod);
		} catch (PersonaAutorizadaException e)  {
			fail ("Persona no encontrada");
		}

		PersonaAutorizada comprobar = null;

		try {
			comprobar = gestionAutorizados.getPersonaAutorizada("perAutTestAddAutot");
		} catch  (PersonaAutorizadaException e) {
			fail ("La persona autorizada deberia exisitir");
		}

		assertEquals("modPersona", comprobar.getID());
    }

	@Test
	public void testEliminarAutorizado() {
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		Empresa emp = new Empresa("empreTestAddAutot", "fisica", "alta", sqlDate, sqlDate, "Avenida prueba", "Malaga","29010", "PaisesBajos", "prueba");
		PersonaAutorizada pA = new PersonaAutorizada("perAutTestAddAutot", "Persona", "Autorizado", "Avenida 123", sqlDate, "Mara cay", sqlDate, sqlDate);
		
		try {
			gestionAutorizados.addPersonaAutorizada(pA);
		} catch (PersonaAutorizadaException e) {
			fail ("La persona autorizada ya exisiste");
		}

		try {
			pA = gestionAutorizados.getPersonaAutorizada("perAutTestAddAutot");
		} catch (PersonaAutorizadaException e) {
			fail ("La persona autorizada deberia exisitir");
		}

		try {
			gestionClientes.crearEmpresa(emp);
		} catch (EmpresaException e) {
			fail ("La empresa ya exisiste");
		}

		try {
			emp = gestionClientes.getEmpresa("empreTestAddAutot");
		} catch (EmpresaException e) {
			fail ("La empresa deberia exisitir");
		}

		try {
			gestionAdministratitivos.addAutorizados(emp.getID(), pA.getID(), "tipo");
		} catch (ClienteException e) {
			fail ("La empresa deberia existir");
		} catch (AutorizacionException e) {
			fail ("La persona no tiene autorización de la empresa");
		} catch (PersonaAutorizadaException e)  {
			fail ("Persona no encontrada");
		}
		
		try {
			gestionAdministratitivos.eliminarAutorizado(emp.getID(), pA.getID());
		} catch (ClienteException e) {
			fail ("La empresa deberia existir");
		} catch (AutorizacionException e) {
			fail ("La persona no tiene autorización de la empresa");
		} catch (PersonaAutorizadaException e)  {
			fail ("Persona no encontrada");
		}

	}

	@Test
	public void testCerrarCuenta() {

	}

}
   