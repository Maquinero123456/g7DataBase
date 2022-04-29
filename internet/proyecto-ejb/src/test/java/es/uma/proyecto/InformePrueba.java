package es.uma.proyecto;

import java.util.List;
import java.util.logging.Logger;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import es.uma.proyecto.entidades.Cliente;
import es.uma.proyecto.exceptions.ClienteException;

public class InformePrueba {
    
    private static final Logger LOG = Logger.getLogger(Informes.class.getCanonicalName());

	private static final String INFORMES_EJB = "java:global/classes/Informes";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "proyectoTest";
	
	private GestionInformes gestionInformes;

    @Before
	public void setup() throws NamingException  {
		gestionInformes = (GestionInformes) SuiteTest.ctx.lookup(INFORMES_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}


    @Test
    public void informeHolanda() throws ClienteException{
    	java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		
        List<Cliente> cliente = gestionInformes.informePaisesBajos();
        System.out.println("/************************************************************************************/");
        if(cliente.isEmpty()){
            System.out.println("Esta vacio");
        }
        else{
        	for(Cliente e : cliente){
        		System.out.println(e);
        	}
        }
        
        System.out.println("/************************************************************************************/");
    }
}
