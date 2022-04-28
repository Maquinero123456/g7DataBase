package es.uma.proyecto;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.uma.proyecto.entidades.Cliente;
import es.uma.proyecto.entidades.CuentaFintech;

@Stateless
public class Informes implements GestionInformes{

    private static final Logger LOG = Logger.getLogger(Informes.class.getCanonicalName());

    @PersistenceContext(name="Cache")
    private EntityManager em;
    
    
	@Override
	public void informePaisesBajos() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JPA");
		em = emfactory.createEntityManager();
	      
	    Query query = em.createQuery("select * from CuentaFintech c");
	    List<CuentaFintech> list = query.getResultList();

	    for(CuentaFintech c:list){
	    	System.out.println("Cuenta :"+ c.toString());
	    }
	      
	    //Aggregate function
	    Query queryCl = em.createQuery("select * from Cliente c");
	    List<Cliente> listCl = queryCl.getResultList();
	    for(Cliente c:listCl){
	    	System.out.println("Cliente :"+ c.toString());
	    }	
	}

	
	@Override
	public void informeAlemania() {
		// TODO Auto-generated method stub
		
	}
 
    
}
