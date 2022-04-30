package es.uma.proyecto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.logging.Logger;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import es.uma.proyecto.entidades.Cliente;
import es.uma.proyecto.entidades.Cuenta;
import es.uma.proyecto.exceptions.ClienteException;
import es.uma.proyecto.exceptions.CuentaException;

public class InformePrueba {
    
    private static final Logger LOG = Logger.getLogger(Informes.class.getCanonicalName());

	private static final String INFORMES_EJB = "java:global/classes/Informes";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "proyectoTest";
    private static final String CLIENTES_EJB = "java:global/classes/Clientes";
    private static final String CUENTAS_EJB = "java:global/classes/Cuentas";

	private GestionInformes gestionInformes;
    private GestionClientes gestionClientes;
    private GestionCuentas gestionCuentas;

    @Before
	public void setup() throws NamingException  {
        gestionCuentas = (GestionCuentas) SuiteTest.ctx.lookup(CUENTAS_EJB);
		gestionInformes = (GestionInformes) SuiteTest.ctx.lookup(INFORMES_EJB);
		gestionClientes = (GestionClientes) SuiteTest.ctx.lookup(CLIENTES_EJB);
        BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}


    @Test
    public void informeHolanda() throws ClienteException, CuentaException{
        Cliente client = gestionClientes.getCliente("uno");
        if(client == null){
            throw new ClienteException("El cliente deberia existir");
        }
        Cuenta cuenta = gestionCuentas.getCuenta("1");
        if(cuenta == null){
            throw new CuentaException("La cuenta deberia existir");
        }

        List<String> report = gestionInformes.informeCuentasPaisesBajos(true, "1");

        List<String> seSuponeQueDaEsto = null;

        assertEquals(report, seSuponeQueDaEsto);

    }

    @Test
    public void informeAlemania() throws ClienteException{
        List<Cuenta> report = gestionInformes.informeAlemania();
        System.out.println("/****************************************************************************");
        for(Cuenta e: report){
            System.out.println(e);
        }
        System.out.println("/****************************************************************************");
    }
}
