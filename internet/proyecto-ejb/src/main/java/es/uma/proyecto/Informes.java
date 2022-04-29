package es.uma.proyecto;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.uma.proyecto.entidades.Cliente;
import es.uma.proyecto.entidades.CuentaFintech;
import es.uma.proyecto.exceptions.ClienteException;

@Stateless
public class Informes implements GestionInformes{

    private static final Logger LOG = Logger.getLogger(Informes.class.getCanonicalName());

    @PersistenceContext(name="proyectoEJB")
    private EntityManager em;
    
    
	@Override
	public List<Cliente> informePaisesBajos() {
		Query query = em.createQuery("SELECT c FROM CuentaFintech c");
	    
	    List<CuentaFintech> list = query.getResultList();

	    for(CuentaFintech c:list){
	    	System.out.println("Cuenta :"+ c.toString());
	    }
	      
	    //Aggregate function
	    Query queryCl = em.createQuery("SELECT c from Cliente c");
	    List<Cliente> listCl = queryCl.getResultList();
	    /*for(Cliente c:listCl){
	    	System.out.println("Cliente :"+ c.toString());
	    }	*/
		return listCl;
	}
		
	@Override
	public void informeAlemania() {
		// TODO Auto-generated method stub
		
	}
 
    
}
