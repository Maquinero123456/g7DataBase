package es.uma.proyecto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.util.logging.Logger;

import javax.naming.NamingException;

import es.uma.proyecto.entidades.CuentaReferencia;
import es.uma.proyecto.exceptions.*;

import org.junit.Before;
import org.junit.Test;

import es.uma.proyecto.entidades.Usuario;
import es.uma.informatica.sii.anotaciones.Requisitos;

import es.uma.proyecto.entidades.Cliente;
import es.uma.proyecto.entidades.Empresa;
import es.uma.proyecto.entidades.PersonaAutorizada;
import es.uma.proyecto.entidades.PooledAccount;

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

    @Requisitos({"RF1"})
    @Test
    /**
     * Test que comprueba el correcto acceso de un usuario administrador a la aplicacion
     * Dado un usuario que debe ser admin se comprueba:
     * 		> Que al crearse al usuario este no exista ya en la aplicacion 
     * 		> Que inicie sesion correctamente dado su usuario y su contraseña
     * @throws UsuarioException y AdministrativoException
     */
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

    @Requisitos({"RF1"})
    @Test
    /**
     * Test que comprueba que un usuario NO administrativo no pueda iniciar sesion como uno
     * Dado un usuario que que no es admin se comprueba:
     * 		> Que al crearse al usuario este no exista ya en la aplicacion 
     * 		> Que al intentar iniciar sesion como un autorizado la aplicacion no se lo permita
     * @throws UsuarioException y AdministrativoException
     */
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

    @Requisitos({"RF1"})
    @Test
    /**
     * Test que comprueba que un usuario administrativo no pueda iniciar sesion si introduce una contraseña incorrecta
     * Se comprueba que:
     * 		> Que al crearse al usuario este no exista ya en la aplicacion 
     * 		> Que al intentar iniciar sesion como un autorizado la aplicacion no se lo permita, pues la contraseña no es la correcta
     * @throws UsuarioException y AdministrativoException
     */
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

    @Requisitos({"RF1"})
    @Test
    /**
     * Se comprueba que:
     * 		> Que al introducir un usuario que no existe, la aplicacion se lo notifique
     * @throws UsuarioException y AdministrativoException
     */
    public void testIniciarSesionAdministrativoNoExisteUsuario(){
		Exception exception = assertThrows(AdministrativoException.class, () -> {
            gestionAdministratitivos.iniciarSesion("Paco", "Anda");
        });
    
        String expectedMessage = "Usuario no encontrado";
        String actualMessage = exception.getMessage();
    
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Requisitos({"RF2"})
    @Test
    /**Test para comprobar el correcto uso de un administrativo al dar de alta un cliente
     * Se comprueba que dado un cliente:
     * 		> Se pueda crear el usuario sin problema
     * 		> Se pueda establecer de alta en el sistema
     * 		> Se puede encontrar el nuevo cliente y su estado debe ser Alta
     * @throws ClienteException
     */
	public void testDarDeAltaCliente () {
		Date utilDate = new Date(System.currentTimeMillis());
		Cliente c1 = new Cliente("testAlta", "fisica", "Baja", utilDate, "Avenida 123", "Maracay", "123", "PaisesBajos");
		
		try{
			gestionClientes.crearCliente(c1);
		}catch(ClienteException e){
			fail("Deberia poder crear el cliente.");
		}
		try{
			gestionAdministratitivos.darAltaCliente("testAlta");
		}catch(ClienteException e){
			fail("No se pudo dar de alta al cliente.");
		}
		try{
			c1 = gestionClientes.getCliente("testAlta");
		}catch(ClienteException e){
			fail("Deberia encontrar el cliente.");
		}

		assertEquals("Alta", c1.getEstado());
	}

    @Requisitos({"RF4"})
    @Test
    /**Test para comprobar el correcto uso de un administrativo al dar de baja un cliente
     * Se comprueba que dado un cliente:
     * 		> Se pueda crear el usuario sin problema
     * 		> Se pueda establecer de baja en el sistema sin problemas
     * 		> Se puede encontrar el nuevo cliente y su estado debe ser Baja
     * @throws ClienteException
     */
	public void testDarDeBajaCliente () {
		Date utilDate = new Date(System.currentTimeMillis());
		Cliente c1 = new Cliente("testBaja", "fisica", "Alta", utilDate, "Avenida 123", "Maracay", "123", "PaisesBajos");
		
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

    @Requisitos({"RF3"})
    @Test
    /**Test para comprobar el correcto uso de un administrativo para modificar a un cliente
     * Se comprueba que dado dos clientes:
     * 		> Se puedan crear sin problema
     * 		> El cliente 1 ahora tenga los parametros del cliente 2, indicando que se ha modificado correctamente
     * @throws ClienteException
     */
	public void testModificarCliente () {
		Date utilDate = new Date(System.currentTimeMillis());
		Cliente c1 = new Cliente("testAlta", "fisica", "Alta", utilDate, "Avenida 123", "Maracay", "123", "PaisesBajos");
		
		try{
			gestionClientes.crearCliente(c1);
		}catch(ClienteException e){
			fail("Deberia poder crear el cliente");
		}

		Cliente c2 = new Cliente("testAlta", "fisica", "Alta", utilDate, "Avenida Falsa", "Guacamayo", "231", "PaisesAltos");

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
	public void testAperturaCuentaAgrupada() throws CuentaException, ClienteException {

		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		Cliente c1 = new Cliente("testApCuentAgrup", "fisica", "Alta", sqlDate, "Avenida 123", "Maracay", "123", "PaisesBajos");
		
		try{
			gestionClientes.crearCliente(c1);
		}catch(ClienteException e){
			fail("Deberia poder crear el cliente");
		}

		PooledAccount prueba =  new PooledAccount("ES45450545054505","swift", true, sqlDate, sqlDate, "Clasic");

		try {
			gestionAdministratitivos.aperturaCuentaAgrupada("ES45450545054505", "testApCuentAgrup");
		}catch (CuentaException e) {
			fail ("No se ha podido crear la cuenta");
		}catch (ClienteException e) {
			fail ("El usuario no existe");
		}
		
		PooledAccount cf = (PooledAccount) gestionCuentas.getCuenta("ES45450545054505");

		assertEquals(prueba, cf);
	}

	@Test
	public void testAperturaCuentaSegregada() throws CuentaException, ClienteException {
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		Cliente c1 = new Cliente("testApCuentSeg", "fisica", "Alta", sqlDate, "Avenida 123", "Maracay", "123", "PaisesBajos");
		
		try{
			gestionClientes.crearCliente(c1);
		}catch(ClienteException e){
			fail("Deberia poder crear el cliente");
		}
		
		CuentaReferencia cuentaRef = gestionCuentas.getCuentaReferencia("8");
		if (cuentaRef == null) {
			fail("No se ha encontrado la cuenta");
		}

		try {
			gestionAdministratitivos.aperturaCuentaSegregada("ES45450545054505", "testApCuentSeg", cuentaRef);
		}catch (CuentaException e) {
			fail ("No se ha podido crear la cuenta");
		}catch (ClienteException e) {
			fail ("El usuario no existe");
		}
		
	}
	
	@Test
	public void testCerrarCuentaAgrupada() {
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		Cliente c1 = new Cliente("testCerrCuentAgrup", "fisica", "Alta", sqlDate, "Avenida 123", "Maracay", "123", "PaisesBajos");
		
		try{
			gestionClientes.crearCliente(c1);
		}catch(ClienteException e){
			fail("Deberia poder crear el cliente");
		}

		try {
			gestionAdministratitivos.aperturaCuentaAgrupada("ES45450545054505", "testCerrCuentAgrup");
		}catch (CuentaException e) {
			fail ("No se ha podido crear la cuenta");
		}catch (ClienteException e) {
			fail ("El usuario no existe");
		}

		try {
			gestionAdministratitivos.cerrarCuenta("ES45450545054505");
		}catch (CuentaException e) {
			fail ("El saldo de la cuenta no es 0");
		}

		Exception exception = assertThrows(AdministrativoException.class, () -> {
            gestionCuentas.getCuenta("ES45450545054505");
        });

        String expectedMessage = "No existe la cuenta";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	public void testCerrarCuentaSegregada() {
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		Cliente c1 = new Cliente("testCerrCuentSeg", "fisica", "Alta", sqlDate, "Avenida 123", "Maracay", "123", "PaisesBajos");
		
		try{
			gestionClientes.crearCliente(c1);
		}catch(ClienteException e){
			fail("Deberia poder crear el cliente");
		}

		CuentaReferencia cuentaRef = null;
		try {
		 	cuentaRef = gestionCuentas.getCuentaReferencia("8");
		}catch (CuentaException e) {
			fail("No se ha encontrado la cuenta  referencia");
		}		
		

		try {
			gestionAdministratitivos.aperturaCuentaSegregada("ES45450545054505", "testCerrCuentSeg",cuentaRef);
		}catch (CuentaException e) {
			fail ("No se ha podido crear la cuenta");
		}catch (ClienteException e) {
			fail ("El usuario no existe");
		}

		try {
			gestionAdministratitivos.cerrarCuenta("ES45450545054505");
		}catch (CuentaException e) {
			fail ("El saldo de la cuenta no es 0");
		}

		Exception exception = assertThrows(AdministrativoException.class, () -> {
            gestionCuentas.getCuenta("ES45450545054505");
        });
    
        String expectedMessage = "No existe la cuenta";
        String actualMessage = exception.getMessage();
    
        assertTrue(actualMessage.contains(expectedMessage));

	}

	@Requisitos({"RF6"})
    @Test
    /**Test para comprobar el correcto uso de un administrativo para añadir
     * personas autorizadas a las cuentas que pertenezcan a un cliente juridico (empresa)
     * Se comprueba que dada una empresa y una PersonaAutorizada:
     * 		> La persona que se busca agregar no este ya entre las personas autorizadas de la empresa
     * 		> Que la persona autorizada creada exista 
     *		> Que al crear la empresa, esta no exista
     *		> Que luego de crear la empresa, esta exista
     *		> Que se pueda añadir correctamente la persona autorizada creada a la empresa
     * @throws PersonaAutorizadaException, EmpresaException, ClienteException, AutorizacionException
     */
	public void testAddAutorizados() {
		Date utilDate = new Date(System.currentTimeMillis());
		Empresa emp = new Empresa("empreTestAddAutot", "fisica", "alta", utilDate, utilDate, "Avenida prueba", "Malaga","29010", "PaisesBajos", "prueba");
		PersonaAutorizada pA = new PersonaAutorizada("perAutTestAddAutot", "Persona", "Autorizado", "Avenida 123", utilDate, "Mara cay", utilDate, utilDate);
		
		try {
			gestionAutorizados.addPersonaAutorizada(pA);
		} catch (PersonaAutorizadaException e) {
			fail ("La persona autorizada ya existe");
		}

		try {
			pA = gestionAutorizados.getPersonaAutorizada("perAutTestAddAutot");
		} catch (PersonaAutorizadaException e) {
			fail ("La persona autorizada deberia existir");
		}

		try {
			gestionClientes.crearEmpresa(emp);
		} catch (EmpresaException e) {
			fail ("La empresa ya existe");
		}

		try {
			emp = gestionClientes.getEmpresa("empreTestAddAutot");
		} catch (EmpresaException e) { 
			fail ("La empresa deberia existir");
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

	@Requisitos({"RF7"})
    @Test
    /**Test para comprobar el correcto uso de un administrativo para modificar los datos de las personas autorizadas
     * Se comprueba que dada una empresa y una PersonaAutorizada:
     * 		> Que la persona autorizada a modificar exista 
     *		> Que al crear la empresa, esta no exista
     *		> Que luego de crear la empresa, esta exista
     *		> Que la persona autorizada ahora tenga los nuevos datos
     * @throws PersonaAutorizadaException, EmpresaException, ClienteException, AutorizacionException
     */
	public void testModificarAutorizado () {
		Date utilDate = new Date(System.currentTimeMillis());
		Empresa emp = new Empresa("empreTestAddAutot", "fisica", "alta", utilDate, utilDate, "Avenida prueba", "Malaga","29010", "PaisesBajos", "prueba");
		PersonaAutorizada pA = new PersonaAutorizada("perAutTestAddAutot", "Persona", "Autorizado", "Avenida 123", utilDate, "Mara cay", utilDate, utilDate);
		
		try {
			pA = gestionAutorizados.getPersonaAutorizada("perAutTestAddAutot");
		} catch (PersonaAutorizadaException e) {
			fail ("La persona autorizada deberia existir");
		}

		try {
			gestionClientes.crearEmpresa(emp);
		} catch (EmpresaException e) {
			fail ("La empresa ya existe");
		}

		try {
			emp = gestionClientes.getEmpresa("empreTestAddAutot");
		} catch (EmpresaException e) { 
			fail ("La empresa deberia existir");
		}

		try {
			PersonaAutorizada mod = new PersonaAutorizada("perAutTestAddAutot", "modPersona", "Autorizado", "Avenida 123", utilDate, "Mara cay", utilDate, utilDate);
			gestionAdministratitivos.modificarAutorizado(mod);
		} catch (PersonaAutorizadaException e)  {
			fail ("Persona no encontrada");
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
		
		PersonaAutorizada comprobar = null;

		try {
			comprobar = gestionAutorizados.getPersonaAutorizada("perAutTestAddAutot");
		} catch  (PersonaAutorizadaException e) {
			fail ("La persona autorizada deberia exisitir");
		}

		assertEquals(pA, comprobar);
    }

	@Requisitos({"RF8"})
    @Test
    /**Test para comprobar el correcto uso de un administrativo para eliminar a un autorizado de la cuenta de la empresa
     * Se comprueba que dada una empresa y una PersonaAutorizada:
     * 		> Que la persona autorizada a eliminar exista 
	 *		> Que la empresa exista
     *		> Que una vez añadida la persona, esta sea eliminada
     * @throws PersonaAutorizadaException, EmpresaException, ClienteException, AutorizacionException
     */
	public void testEliminarAutorizado() throws PersonaAutorizadaException {
		Date utilDate = new Date(System.currentTimeMillis());
		Empresa emp = new Empresa("empreTestAddAutot", "fisica", "alta", utilDate, utilDate, "Avenida prueba", "Malaga","29010", "PaisesBajos", "prueba");
		PersonaAutorizada pA = new PersonaAutorizada("perAutTestAddAutot", "Persona", "Autorizado", "Avenida 123", utilDate, "Mara cay", utilDate, utilDate);
		
		try {
			gestionAutorizados.addPersonaAutorizada(pA);
		} catch (PersonaAutorizadaException e) {
			fail ("La persona autorizada ya existe");
		}

		try {
			pA = gestionAutorizados.getPersonaAutorizada("perAutTestAddAutot");
		} catch (PersonaAutorizadaException e) {
			fail ("La persona autorizada deberia existir");
		}

		try {
			gestionClientes.crearEmpresa(emp);
		} catch (EmpresaException e) {
			fail ("La empresa ya existe");
		}

		try {
			emp = gestionClientes.getEmpresa("empreTestAddAutot");
		} catch (EmpresaException e) {
			fail ("La empresa deberia existir");
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
		
		assertEquals(null, gestionAutorizados.getPersonaAutorizada("perAutTestAddAutot"));
	}

}
   