package es.uma.proyecto;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.logging.Logger;

import javax.naming.NamingException;

import es.uma.proyecto.GestionClientes;
import es.uma.proyecto.entidades.Cliente;
import es.uma.proyecto.exceptions.ClienteException;

public class ClientesPrueba {
    private static final Logger LOG = Logger.getLogger(AdministrativosPrueba.class.getCanonicalName());

    private static final String CLIENTES_EJB = "java:global/classes/Clientes";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "proyectoTest";

    private GestionClientes gestionClientes;

    @Before
	public void setup() throws NamingException  {
		gestionClientes = (GestionClientes) SuiteTest.ctx.lookup(CLIENTES_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}

    @Test 
    public void testGetCliente () {
        java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		Cliente c1 = new Cliente("testGet", "fisica", "Baja", sqlDate, "Avenida 123", "Maracay", "123", "PaisesBajos");
		Cliente res = null;
        
        try{
			gestionClientes.crearCliente(c1);
		}catch(ClienteException e){
			fail("Deberia poder crear el cliente");
		}

        try {
            res = gestionClientes.getCliente("testGet");
        } catch (ClienteException e) {
            fail("El cliente deberia existir");
        }

        assertEquals(res, c1);

    }
}
    