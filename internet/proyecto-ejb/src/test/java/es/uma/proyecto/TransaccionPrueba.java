package es.uma.proyecto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.logging.Logger;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import es.uma.proyecto.entidades.CuentaReferencia;
import es.uma.proyecto.exceptions.ClienteException;
import es.uma.proyecto.exceptions.CuentaException;
import es.uma.proyecto.exceptions.IndividualException;
import es.uma.proyecto.exceptions.SaldoException;

public class TransaccionPrueba {
    private static final Logger LOG = Logger.getLogger(GenerarTransaccion.class.getCanonicalName());

	private static final String ADMINISTRATIVOS_EJB = "java:global/classes/Administrativos";
	private static final String CUENTASUSUARIOS_EJB = "java:global/classes/CuentasUsuarios";
	private static final String CLIENTES_EJB = "java:global/classes/Clientes";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "proyectoTest";
    private static final String GENERARTRANSACCION_EJB = "java:global/classes/GenerarTransaccion";
	private static final String PERSONA_AUTORIZADA = "java:global/classes/Autorizados";
    private static final String CUENTAS_EJB = "java:global/classes/Cuentas";

	private GestionAdministratitivos gestionAdministratitivos;
	private GestionCuentasUsuarios gestionCuentasUsuarios;
	private GestionClientes gestionClientes;
	private GestionCuentas gestionCuentas;
	private GestionAutorizados gestionAutorizados;
    private GestionTransaccion generarTransaccion;

    @Before
	public void setup() throws NamingException  {
		gestionAdministratitivos = (GestionAdministratitivos) SuiteTest.ctx.lookup(ADMINISTRATIVOS_EJB);
		gestionCuentasUsuarios = (GestionCuentasUsuarios) SuiteTest.ctx.lookup(CUENTASUSUARIOS_EJB);
		gestionClientes = (GestionClientes) SuiteTest.ctx.lookup(CLIENTES_EJB);
		gestionAutorizados  = (GestionAutorizados) SuiteTest.ctx.lookup(PERSONA_AUTORIZADA);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
        generarTransaccion = (GestionTransaccion) SuiteTest.ctx.lookup(GENERARTRANSACCION_EJB);
        gestionCuentas = (GestionCuentas) SuiteTest.ctx.lookup(CUENTAS_EJB);
    }

    @Test
    public void testTransaccionIndividual() throws ClienteException, CuentaException, IndividualException, SaldoException{
        generarTransaccion.transaccionIndividual("Paco", "cuentaOrigen", "cuentaDestino", 100.0, "Quien sabe");
        CuentaReferencia ref = null;
        try{
            ref = gestionCuentas.getCuentaReferencia("cuentaDestino");
        }catch(CuentaException e){
            fail("La cuenta deberia existir");
        }

        assertTrue(200.0 == ref.getSaldo());
    }

    @Test
    public void testTransaccionAutorizada() throws ClienteException, CuentaException, IndividualException, SaldoException{
        generarTransaccion.transaccionIndividual("Pablo", "autorizadoOrigen", "autorizadoDestino", 100.0, "Quien sabe");
        CuentaReferencia ref = null;
        try{
            ref = gestionCuentas.getCuentaReferencia("autorizadoDestino");
        }catch(CuentaException e){
            fail("La cuenta deberia existir");
        }

        assertTrue(200.0 == ref.getSaldo());
    }
}
