package es.uma.proyecto;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Logger;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import es.uma.informatica.sii.anotaciones.Requisitos;

public class InformePrueba {
    @SuppressWarnings("unused")
    private static final Logger LOG = Logger.getLogger(InformePrueba.class.getCanonicalName());

	private static final String INFORMES_EJB = "java:global/classes/Informes";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "proyectoTest";

	private GestionInformes gestionInformes;

    @Before
	public void setup() throws NamingException, ParseException  {
		gestionInformes = (GestionInformes) SuiteTest.ctx.lookup(INFORMES_EJB);
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
    public void informeHolanda() throws IOException, ParseException {
    	/*// Cuentas de Holanda activas y con iban 15
    	// Debe devolver la cuenta de "Perez Castillo", pues es el dueño de la cuenta con iban 15
        List<String> report1 = gestionInformes.informeCuentasPaisesBajos(true, "15");
        System.out.println(report1.toString());
        assertTrue(report1.toString().contains("Perez Castillo"));
        assertFalse(report1.toString().contains("Navarro Jimena"));
        
        // Cuentas de Holanda inactiva con iban 6
        // Debe devolver solo a "Vazquez Vera"
        List<String> report2 = gestionInformes.informeCuentasPaisesBajos(false, "6");
        System.out.println(report2.toString());
        assertTrue(report2.toString().contains("Vazquez Vera"));
        assertFalse(report2.toString().contains("Perez Castillo"));
        
        
        // Clientes de Holanda con codigo postal 4
        // Debe devolver solo a Vazquez Vera, pues Navarro Jimena es de Alemania
        List<String> reporte1 = gestionInformes.informeClientePaisesBajos(null, null, "4");
        System.out.println(reporte1.toString());
        assertTrue(reporte1.toString().contains("Vazquez Vera"));
        assertFalse(reporte1.toString().contains("Navarro Jimena"));
        
        // Clientes de holanda con nombre Elsa Capunta
        // No debe devolver nada, pues Elsa Capunta es de Francia
        List<String> reporte2 = gestionInformes.informeClientePaisesBajos("Elsa Capunta", null, null);
        System.out.println(reporte2.toString());
        assertFalse(reporte2.toString().contains("Elsa Capunta"));
        
        // Todos los clientes fisicos (NO JURIDICOS) de Holanda en la BaseDeDatos 
        List<String> reporte3 = gestionInformes.informeClientePaisesBajos(null, null, null);
        System.out.println(reporte3.toString());
        assertTrue(reporte3.toString().contains("Perez Castillo"));
        assertFalse(reporte3.toString().contains("Activision Blizzard"));
        assertTrue(reporte3.toString().contains("Vazquez Vera"));
        
        // Cuentas de Alemania   
        // Debe devolver solo a Navarro Jimena, pues es el unico aleman con una cuenta activa
        // en los ultimos cinco años en la base de datos
        gestionInformes.informeSemanalAlemania();
        gestionInformes.informeMensualAlemania();*/
        // System.out.println(reporteAlemania.iterator());
        }

}
