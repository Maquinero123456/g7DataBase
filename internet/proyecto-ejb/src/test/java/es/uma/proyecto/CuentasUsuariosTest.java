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

import es.uma.proyecto.exceptions.UsuarioException;

public class CuentasUsuariosTest {
    
    private static final Logger LOG = Logger.getLogger(CuentasUsuariosTest.class.getCanonicalName());

	private static final String CUENTASUSUARIOS_EJB = "java:global/classes/es/uma/proyectos/CuentasUsuarios";
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
            throw new RuntimeException(e);
        }

        try{
            Usuario user2 = gestionCuentasUsuarios.getUsuario("Pepito");
            assertEquals(user2, user);
        }catch (UsuarioException e){
            fail("El usuario deberia existir");
        }
    }
}
