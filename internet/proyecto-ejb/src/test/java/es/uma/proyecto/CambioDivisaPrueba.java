package es.uma.proyecto;

import org.junit.Before;

import javax.naming.NamingException;

import java.text.ParseException;
import java.util.logging.Logger;

public class CambioDivisaPrueba {
    private static final Logger LOG = Logger.getLogger(CambioDivisaPrueba.class.getCanonicalName());

    private static final String ADMINISTRATIVOS_EJB = "java:global/classes/Administrativos";
    private static final String CUENTASUSUARIOS_EJB = "java:global/classes/CuentasUsuarios";
    private static final String CLIENTES_EJB = "java:global/classes/Clientes";
    private static final String UNIDAD_PERSITENCIA_PRUEBAS = "proyectoTest";
    private static final String PERSONA_AUTORIZADA = "java:global/classes/Autorizados";
    private static final String CUENTAS_EJB = "java:global/classes/Cuentas";

    private GestionAdministratitivos gestionAdministratitivos;
    private GestionCuentasUsuarios gestionCuentasUsuarios;
    private GestionClientes gestionClientes;
    private GestionCuentas gestionCuentas;
    private GestionAutorizados gestionAutorizados;

    @Before
    public void setup() throws NamingException, ParseException {
        gestionAdministratitivos = (GestionAdministratitivos) SuiteTest.ctx.lookup(ADMINISTRATIVOS_EJB);
        gestionCuentasUsuarios = (GestionCuentasUsuarios) SuiteTest.ctx.lookup(CUENTASUSUARIOS_EJB);
        gestionClientes = (GestionClientes) SuiteTest.ctx.lookup(CLIENTES_EJB);
        gestionAutorizados  = (GestionAutorizados) SuiteTest.ctx.lookup(PERSONA_AUTORIZADA);
        gestionCuentas = (GestionCuentas) SuiteTest.ctx.lookup(CUENTAS_EJB);
        BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
    }
}
