package es.uma.proyecto;

import static org.junit.Assert.assertEquals;
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
import es.uma.proyecto.entidades.Usuario;
import es.uma.proyecto.exceptions.AdministrativoException;

public class AdministrativosPrueba {
    private static final Logger LOG = Logger.getLogger(Administrativos.class.getCanonicalName());

	private static final String ADMINISTRATIVOS_EJB = "java:global/classes/Administrativos";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "AdministrativosPrueba";
	
	private GestionAdministratitivos gestionAdministratitivos;

    @Before
	public void setup() throws NamingException  {
		gestionAdministratitivos = (GestionAdministratitivos) SuiteTest.ctx.lookup(ADMINISTRATIVOS_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}

    @Test
    public void testIniciarSesionAdministrativo(){
        Usuario admin = new Usuario("Pepito", "Juanito", true);
		
		try {
			gestionAdministratitivos.iniciarSesion("Pepito","Juanito");
		}catch (AdministrativoException e) {
			throw new RuntimeException(e);
		}

		try {
			gestionAdministratitivos.iniciarSesion("Juanito", "Juanito");
		}catch (AdministrativoException e) {
			fail("El usuario no esta regitrado")
		}

		try {
			gestionAdministratitivos.iniciarSesion("Pepito", "Pepito");
		} catch (AdministrativoException e) {
			fail("La contraseña es incorrecta");
		}

		try {
			Usuario user = new Usuario("Juanito", "Pepito", false);
			gestionAdministratitivos.iniciarSesion("Juanito", "Pepito");
		} catch (AdministrativoException e) {
			fail("El usuario no es administrativo");
		}
    }

	@Test
	public void testDarDeAltaCliente () {

	}

	@Test
	public void testDarDeBajaCliente () {

	}

	@Test
	public void testModificarCliente () {

	}

	@Test
	public void testAperturaCuenta() {

	}

	@Test
	public void testAddAutorizados() {

	}

	@Test
	public void testModificarAutorizado () {

	}

	@Test
	public void testEliminarAutorizado() {

	}

	@Test
	public void testCerrarCuenta() {

	}

}
