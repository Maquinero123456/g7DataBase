package es.uma.proyecto;

import es.uma.proyecto.entidades.Cliente;
import es.uma.proyecto.entidades.CuentaReferencia;
import es.uma.proyecto.entidades.DepositadaEn;
import es.uma.proyecto.entidades.Divisa;
import es.uma.proyecto.entidades.PooledAccount;
import es.uma.proyecto.exceptions.*;
import org.eclipse.persistence.jpa.jpql.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.NamingException;
import java.sql.Date;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import static org.eclipse.persistence.jpa.jpql.Assert.*;
import static org.junit.Assert.assertEquals;

public class CambioDivisaPrueba {
    private static final Logger LOG = Logger.getLogger(CambioDivisaPrueba.class.getCanonicalName());

    private static final String ADMINISTRATIVOS_EJB = "java:global/classes/Administrativos";
    private static final String CUENTASUSUARIOS_EJB = "java:global/classes/CuentasUsuarios";
    private static final String CLIENTES_EJB = "java:global/classes/Clientes";
    private static final String UNIDAD_PERSITENCIA_PRUEBAS = "proyectoTest";
    private static final String PERSONA_AUTORIZADA = "java:global/classes/Autorizados";
    private static final String CUENTAS_EJB = "java:global/classes/Cuentas";
    private static final String CAMBIODIVISA_EJB = "java:global/classes/CambioDivisa";

    private GestionAdministratitivos gestionAdministratitivos;
    private GestionCuentasUsuarios gestionCuentasUsuarios;
    private GestionClientes gestionClientes;
    private GestionCuentas gestionCuentas;
    private GestionAutorizados gestionAutorizados;
    private GestionCambioDivisa gestionCambioDivisa;

    @Before
    public void setup() throws NamingException {
        gestionAdministratitivos = (GestionAdministratitivos) SuiteTest.ctx.lookup(ADMINISTRATIVOS_EJB);
        gestionCuentasUsuarios = (GestionCuentasUsuarios) SuiteTest.ctx.lookup(CUENTASUSUARIOS_EJB);
        gestionClientes = (GestionClientes) SuiteTest.ctx.lookup(CLIENTES_EJB);
        gestionAutorizados  = (GestionAutorizados) SuiteTest.ctx.lookup(PERSONA_AUTORIZADA);
        gestionCuentas = (GestionCuentas) SuiteTest.ctx.lookup(CUENTAS_EJB);
        gestionCambioDivisa = (GestionCambioDivisa) SuiteTest.ctx.lookup(CAMBIODIVISA_EJB);
        BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
    }

    @Test
    public void testCambioDivisa()  {
        PooledAccount p1 = null;
        try {
            p1 = gestionCuentas.getCuentaAgrupada("ES44");
        }catch (CuentaException e){
            fail("Deberia encontrar la cuenta");
        }

        Divisa euro1 = null;
        try{
            euro1 = gestionCambioDivisa.getDivisa("eu1");
        }catch (DivisaException e){
            fail("No se encontro la divisa");
        }

        Divisa dolar1 = null;
        try{
            dolar1 = gestionCambioDivisa.getDivisa("us1");
        }catch (DivisaException e){
            fail("No se encontro la divisa");
        }

        try{
            gestionCambioDivisa.cambioDivisas(p1, euro1, dolar1, 100.00);
        }catch (PooledAccountException e){
            fail("No se encontro la cuenta pooled");
        }catch (CuentaReferenciaException e ){
            fail("No se encontro la cuenta referencia");
        }catch (DivisaException e){
            fail("No se encontro la divisa");
        }catch (SaldoException e){
            fail("No hay saldo ");
        }

        CuentaReferencia ra1 = null;
        try {
            ra1 = gestionCuentas.getCuentaReferencia("c4");
        }catch (CuentaException e){
            fail("No se encontro la cuenta");
        }

        Double saldo1 = 100.00 + 100.00 /dolar1.getCambioEuro();
        Double saldo2 = ra1.getSaldo();

       assertEquals(saldo1, saldo2);
    }
}
