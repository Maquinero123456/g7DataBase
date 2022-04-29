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
	    List<Cliente> listCl = getClientesPais("PaisesBajos");
		return listCl;
	}
	
		
	@Override
	public void informeAlemania() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
    public List<Cliente> getClientesPais(String pais) {
		Query query = em.createQuery("SELECT cl from Cliente cl WHERE cl.pais = :fpais");
		query.setParameter("fpais", pais);
    	return query.getResultList();
    }
    
	@Override
    public List<Cliente> getCuentasPais(String pais) {
    	Query query = em.createQuery("SELECT cu from CuentaFintech cu, Cliente cl WHERE cl.pais = :fpais");
		query.setParameter("fpais", pais);
    	return query.getResultList();
    }
}
