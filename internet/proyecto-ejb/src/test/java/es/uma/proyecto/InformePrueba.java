package es.uma.proyecto;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.logging.Logger;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import es.uma.proyecto.entidades.Cliente;
import es.uma.proyecto.entidades.Cuenta;
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
        /*List<Cliente> cliente = gestionInformes.informeCuentasPaisesBajos();
        for(Cliente e : cliente){
            if(!e.getPais().equals("PaisesBajos")){
                fail("Solo deberia devolver clientes de holanda");
            }
        }*/
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
