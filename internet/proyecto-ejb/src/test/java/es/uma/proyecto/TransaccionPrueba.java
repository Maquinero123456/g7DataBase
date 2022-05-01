package es.uma.proyecto;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.util.logging.Logger;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import es.uma.proyecto.entidades.CuentaReferencia;
import es.uma.proyecto.exceptions.ClienteException;
import es.uma.proyecto.exceptions.CuentaException;
import es.uma.proyecto.exceptions.IndividualException;
import es.uma.proyecto.exceptions.SaldoException;

public class TransaccionPrueba {
    @SuppressWarnings("unused")
    private static final Logger LOG = Logger.getLogger(TransaccionPrueba.class.getCanonicalName());

	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "proyectoTest";
    private static final String GENERARTRANSACCION_EJB = "java:global/classes/GenerarTransaccion";
    private static final String CUENTAS_EJB = "java:global/classes/Cuentas";

	private GestionCuentas gestionCuentas;
    private GestionTransaccion generarTransaccion;

    @Before
	public void setup() throws NamingException, ParseException  {
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
        generarTransaccion = (GestionTransaccion) SuiteTest.ctx.lookup(GENERARTRANSACCION_EJB);
        gestionCuentas = (GestionCuentas) SuiteTest.ctx.lookup(CUENTAS_EJB);
    }

    @Requisitos({"RF14"})
    @Test 
    /**
     * Prueba para la correcta transaccion de un Individual 
     * Comprueba si las cuentas de referencia que participan en la transaccion existen
     * Finalmente, las cuentas deben tener el dinero correspondiente
     * Sumandole a la destino y quitandole a la origen 
     * @throws ClienteException, CuentaException, IndividualException, SaldoException
     */
    public void testTransaccionIndividual() throws ClienteException, CuentaException, IndividualException, SaldoException{
        generarTransaccion.transaccionIndividual("Paco", "cuentaOrigen", "cuentaDestino", 100.0, "Quien sabe");
        CuentaReferencia ref = null;
        try{
            ref = gestionCuentas.getCuentaReferencia("cuentaDestino");
        }catch(CuentaException e){
            fail("La cuenta deberia existir");
        }

        CuentaReferencia ref2 = null;
        try{
            ref2 = gestionCuentas.getCuentaReferencia("cuentaOrigen");
        }catch(CuentaException e){
            fail("La cuenta deberia existir");
        }

        assertTrue(200.0 == ref.getSaldo());
        assertTrue(900.0 == ref2.getSaldo());
    }

    @Requisitos({"RF14"})
    @Test 
    /**
     * Prueba para la correcta transaccion de un autorizado 
     * Comprueba si las cuentas de referencia que participan en la transaccion existen
     * Finalmente, las cuentas deben tener el dinero correspondiente
     * Sumandole a la destino y quitandole a la origen 
     * @throws ClienteException, CuentaException, IndividualException, SaldoException
     */
    public void testTransaccionAutorizada() throws ClienteException, CuentaException, IndividualException, SaldoException{
        generarTransaccion.transaccionAutorizado("Pablo", "autorizadoOrigen", "autorizadoDestino", 100.0, "Quien sabe");
        CuentaReferencia ref = null;
        try{
            ref = gestionCuentas.getCuentaReferencia("autorizadoDestino");
        }catch(CuentaException e){
            fail("La cuenta deberia existir");
        }
        
        CuentaReferencia ref2 = null;
        try{
            ref2 = gestionCuentas.getCuentaReferencia("autorizadoOrigen");
        }catch(CuentaException e){
            fail("La cuenta deberia existir");
        }

        assertTrue(200.0 == ref.getSaldo());
        assertTrue(900.0 == ref2.getSaldo());
    }
}
