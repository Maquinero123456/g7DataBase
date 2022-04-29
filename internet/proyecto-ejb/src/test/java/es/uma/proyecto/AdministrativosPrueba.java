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

import org.junit.Before;
import org.junit.Test;

import es.uma.proyecto.GestionAdministratitivos;
import es.uma.proyecto.entidades.Cliente;
import es.uma.proyecto.entidades.Usuario;
import es.uma.proyecto.exceptions.AdministrativoException;
import es.uma.proyecto.exceptions.ClienteException;
import es.uma.proyecto.exceptions.UsuarioException;

public class AdministrativosPrueba {
    private static final Logger LOG = Logger.getLogger(Administrativos.class.getCanonicalName());

	private static final String ADMINISTRATIVOS_EJB = "java:global/classes/Administrativos";
	private static final String CUENTASUSUARIOS_EJB = "java:global/classes/CuentasUsuarios";
	private static final String CLIENTES_EJB = "java:global/classes/Clientes";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "proyectoTest";

	private GestionAdministratitivos gestionAdministratitivos;
	private GestionCuentasUsuarios gestionCuentasUsuarios;
	private GestionClientes gestionClientes;

    @Before
	public void setup() throws NamingException  {
		gestionAdministratitivos = (GestionAdministratitivos) SuiteTest.ctx.lookup(ADMINISTRATIVOS_EJB);
		gestionCuentasUsuarios = (GestionCuentasUsuarios) SuiteTest.ctx.lookup(CUENTASUSUARIOS_EJB);
		gestionClientes = (GestionClientes) SuiteTest.ctx.lookup(CLIENTES_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}

    @Test
    public void testIniciarSesionAdministrativo(){
        Usuario user = new Usuario("Paco", "Paco", true);

		try{
			gestionCuentasUsuarios.CrearUsuario(user);
		}catch(UsuarioException e){
			fail("Usuario no deberia existir");
		}

		Usuario admin = null;
		try{
			admin = gestionAdministratitivos.iniciarSesion(user.getNombre(), user.getPassword());
		}catch(AdministrativoException e){
			fail("No deberia saltar excepcion");
		}

		assertEquals(admin, user);
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
    public void testIniciarSesionAdministrativoUsuarioNoExiste(){
		Exception exception = assertThrows(AdministrativoException.class, () -> {
            gestionAdministratitivos.iniciarSesion("Paco", "Paco");
        });
    
        String expectedMessage = "Usuario no encontrado";
        String actualMessage = exception.getMessage();
    
        assertTrue(actualMessage.contains(expectedMessage));
    }

	@Test
	public void testDarAltaCliente(){
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
}