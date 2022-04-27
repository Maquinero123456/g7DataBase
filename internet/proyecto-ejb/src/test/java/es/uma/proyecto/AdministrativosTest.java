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

public class AdministrativosTest {
    private static final Logger LOG = Logger.getLogger(AdministrativosTest.class.getCanonicalName());

	private static final String ADMINISTRATIVOS_EJB = "java:global/classes/Administrativos";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "AdministrativosTest";
	
	private GestionAdministratitivos gestionAdministratitivos;

    @Before
	public void setup() throws NamingException  {
		gestionAdministratitivos = (GestionAdministratitivos) SuiteTest.ctx.lookup(ADMINISTRATIVOS_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}

    @Test
    public void testIniciarSesionAdministrativo(){
        
    }
}
