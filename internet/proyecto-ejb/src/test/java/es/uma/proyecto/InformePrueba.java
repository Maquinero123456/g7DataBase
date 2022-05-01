package es.uma.proyecto;

import java.util.List;
import java.util.logging.Logger;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import es.uma.informatica.sii.anotaciones.Requisitos;
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

    @Requisitos({"RF11", "RF12"})
    @Test 
    /*
     * Muestra por pantalla todas las cuentas, clientes de Holanda y Alemania, segun se pida
     * en cada legislacion y los parametros necesarios para cada busqueda
     * Estas cuentas deben tener minimo 3 años de actividad en el caso de Holanda
     * y 5 años en caso de el caso de Alemania
     */
    public void informeHolanda() throws ClienteException, CuentaException{
    	// Cuentas de Holanda activas y con iban 15
        List<String> report1 = gestionInformes.informeCuentasPaisesBajos(true, "15");
        System.out.println(report1.toString());
        
        // Cuentas de Holanda inactiva con iban 6
        List<String> report2 = gestionInformes.informeCuentasPaisesBajos(false, "6");
        System.out.println(report2.toString());
        
        // Cuentas de Holanda activas
        List<String> report3 = gestionInformes.informeCuentasPaisesBajos(true, null);
        System.out.println(report3.toString());
    
        // Clientes de Holanda con codigo postal 4
        List<String> reporte1 = gestionInformes.informeClientePaisesBajos(null, null, "4");
        System.out.println(reporte1.toString());
        
        // Clientes de holanda con nombre Elsa Capunta
        List<String> reporte2 = gestionInformes.informeClientePaisesBajos("Elsa Capunta", null, null);
        System.out.println(reporte2.toString());
        
        // Cuentas de Holanda activas y con iban 15
        List<String> reporte3 = gestionInformes.informeClientePaisesBajos(null, null, null);
        System.out.println(reporte3.toString());
        
        // Cuentas de Holanda activas y con iban 15        
        List<String> reporteAlemania = gestionInformes.informeAlemania();
        System.out.println(reporteAlemania.toString());
    }

}
