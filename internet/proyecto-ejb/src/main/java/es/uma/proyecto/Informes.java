package es.uma.proyecto;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.uma.proyecto.entidades.Cliente;
import es.uma.proyecto.entidades.CuentaFintech;
import es.uma.proyecto.entidades.Individual;
import es.uma.proyecto.exceptions.ClienteException;

@Stateless
public class Informes implements GestionInformes{

    private static final Logger LOG = Logger.getLogger(Informes.class.getCanonicalName());

    @PersistenceContext(name="proyectoEJB")
    private EntityManager em;
    
    
	@Override
	public List<String> informeCuentasPaisesBajos(boolean status, String productNumber) {
		List<String> informe = null;
		informe.add("{'PRODUCTS':[");
	    Query query = em.createQuery("SELECT cu from CuentaFintech cu, Cliente cl WHERE cl.pais = :fpais AND c.estado = :fstatus AND c.iban = :fiban");
		query.setParameter("fpais", "PaisesBajos");
		query.setParameter("fstatus", status);
		query.setParameter("fiban", productNumber);
		
		List<CuentaFintech> listCl = query.getResultList();
	    
		
	    for(CuentaFintech cf: listCl) {
	    	Cliente cl = cf.getCliente();
	    	Individual ind = em.find(Individual.class, cl.getID());
	    	informe.add("ACCOUNTHOLDER:[ activeCustomer: "+cl.getEstado()+" accountType: " +cl.getTipoCliente() + "]"
	    			+"\n'name':[ First Name: " +ind.getNombre()+", Last Name: "+ind.getApellidos() + "] " 
	    			+"\n 'adresses':[ city: "+ cl.getCiudad() + ", street: "+ cl.getDireccion() +", postalCode: "+cl.getCodigoPostal() + ", country: " + cl.getPais() + "]"
	    			+"\n CUENTA:[ productType: "+ cf.getClasificacion() + " productNumber: "+ cf.getIBAN()+ " status: " +status+ " startDate: "+ cl.getFechaAlta() + " endDate: "+ cl.getFechaBaja());
	    }
		
		return informe;
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
    public List<CuentaFintech> getCuentasPais(String pais) {
    	Query query = em.createQuery("SELECT cu from CuentaFintech cu, Cliente cl WHERE cl.pais = :fpais");
		query.setParameter("fpais", pais);
    	return query.getResultList();
    }
}
