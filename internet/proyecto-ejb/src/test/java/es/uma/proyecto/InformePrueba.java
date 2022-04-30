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
        List<String> report1 = gestionInformes.informeCuentasPaisesBajos(true, "15");
        System.out.println(report1.toString());
        
        List<String> report2 = gestionInformes.informeCuentasPaisesBajos(false, "6");
        System.out.println(report2.toString());
        
        List<String> report3 = gestionInformes.informeCuentasPaisesBajos(true, null);
        System.out.println(report3.toString());
    
        List<String> reporte1 = gestionInformes.informeClientePaisesBajos(null, null, "4");
        System.out.println(reporte1.toString());
        
        List<String> reporte2 = gestionInformes.informeClientePaisesBajos("Elsa Capunta", null, null);
        System.out.println(reporte2.toString());
        
        List<String> reporte3 = gestionInformes.informeClientePaisesBajos(null, null, null);
        System.out.println(reporte3.toString());
    }

    /*@Test
    public void informeAlemania() throws ClienteException{
        List<Cuenta> report = gestionInformes.informeAlemania();
        System.out.println("/****************************************************************************");
        for(Cuenta e: report){
            System.out.println(e);
        }
        System.out.println("/****************************************************************************");
    }*/
}
