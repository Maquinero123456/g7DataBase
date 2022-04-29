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

import es.uma.proyecto.entidades.Usuario;
import es.uma.proyecto.exceptions.UsuarioException;

public class CuentasUsuariosPrueba {
    
    private static final Logger LOG = Logger.getLogger(CuentasUsuarios.class.getCanonicalName());

	private static final String CUENTASUSUARIOS_EJB = "java:global/classes/CuentasUsuarios";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "proyectoTest";
	
	private GestionCuentasUsuarios gestionCuentasUsuarios;

    @Before
	public void setup() throws NamingException  {
		gestionCuentasUsuarios = (GestionCuentasUsuarios) SuiteTest.ctx.lookup(CUENTASUSUARIOS_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}

    @Test
    public void testCrearUsuario(){
        Usuario user = new Usuario("Pepito", "Juanito", true);
        try{
            gestionCuentasUsuarios.CrearUsuario(user);
        }catch (UsuarioException e){
            fail("El usuario no deberia existir");
        }

        try{
            Usuario user2 = gestionCuentasUsuarios.getUsuario("Pepito");
            assertEquals(user2, user);
        }catch (UsuarioException e){
            fail("El usuario deberia existir");
        }
    }

    @Test
    public void testUsuarioRepetido(){
        Usuario user = new Usuario("Pepito", "Juanito", true);
        try{
            gestionCuentasUsuarios.CrearUsuario(user);
        }catch (UsuarioException e){
            fail("El usuario no deberia existir");
        }
        
        Exception exception = assertThrows(UsuarioException.class, () -> {
            gestionCuentasUsuarios.CrearUsuario(user);
        });
    
        String expectedMessage = "El usuario ya existe.";
        String actualMessage = exception.getMessage();
    
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testIniciarSesion(){
        String nombre = "Juanito";
        String password = "Pepito";
        Usuario user = new Usuario(nombre, password, false);
        Usuario usuario = null;

        try{
            gestionCuentasUsuarios.CrearUsuario(user);
        }catch (UsuarioException e){
            fail("El usuario no deberia existir");
        }

        try{
            usuario = gestionCuentasUsuarios.iniciarSesion(nombre, password);
        }catch(UsuarioException e){
            fail("No se deberia lanzar ninguna excepcion");
        }

        assertEquals(usuario, user);
    }

    @Test
    public void testIniciarSesionAdministrativo(){
        String nombre = "Admin";
        String password = "Admin";
        Usuario user = new Usuario(nombre, password, true);
        Usuario usuario = null;

        try{
            gestionCuentasUsuarios.CrearUsuario(user);
        }catch (UsuarioException e){
            fail("El usuario no deberia existir");
        }

        Exception exception = assertThrows(UsuarioException.class, () -> {
            gestionCuentasUsuarios.iniciarSesion(nombre, password);
        });
    
        String expectedMessage = "No puedes iniciar sesion como administrativo aqui";
        String actualMessage = exception.getMessage();
    
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testIniciarSesionPasswordIncorrecta(){
        Usuario user = new Usuario("Tremendo", "Metodo", true);
        try {
            gestionCuentasUsuarios.CrearUsuario(user);
        } catch (UsuarioException e) {
            fail("Usuario no deberia existir");
        }
        Exception exception = assertThrows(UsuarioException.class, () -> {
            gestionCuentasUsuarios.iniciarSesion("Tremendo", "Anda");
        });
    
        String expectedMessage = "Password incorrecta";
        String actualMessage = exception.getMessage();
    
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
