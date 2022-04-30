package es.uma.proyecto;

import java.sql.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.uma.proyecto.entidades.Cliente;
import es.uma.proyecto.entidades.Cuenta;
import es.uma.proyecto.entidades.CuentaFintech;
import es.uma.proyecto.entidades.Empresa;
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
	    	
	    		if(cl.getTipoCliente().equalsIgnoreCase("individual") || cl.getTipoCliente().equalsIgnoreCase("fisico")) {
	    			Individual ind = em.find(Individual.class, cl.getID());
	    			informe.add("ACCOUNTHOLDER:[ activeCustomer: "+cl.getEstado()+" accountType: " +cl.getTipoCliente() + "]"
	    	    	+"\n'name':[ First Name: " +ind.getNombre()+", Last Name: "+ind.getApellidos() + "] " 
	    	    	+"\n 'adresses':[ city: "+ cl.getCiudad() + ", street: "+ cl.getDireccion() +", postalCode: "+cl.getCodigoPostal() + ", country: " + cl.getPais() + "]"
	    	    	+"\n CUENTA:[ productType: "+ cf.getClasificacion() + " productNumber: "+ cf.getIBAN()+ " status: " +status+ " startDate: "+ cl.getFechaAlta() + " endDate: "+ cl.getFechaBaja());
	    	    
	    		}
	    		
	    		else{
	    			Empresa emp = em.find(Empresa.class, cl.getID());
	    			informe.add("ACCOUNTHOLDER:[ activeCustomer: "+cl.getEstado()+" accountType: " +cl.getTipoCliente() + "]"
	    	    	+"\n'name':[ business name: "+ emp.getRazonSocial() +" ] "  
	    	    	+"\n 'adresses':[ city: "+ cl.getCiudad() + ", street: "+ cl.getDireccion() +", postalCode: "+cl.getCodigoPostal() + ", country: " + cl.getPais() + "]"
	    	    	+"\n CUENTA:[ productType: "+ cf.getClasificacion() + " productNumber: "+ cf.getIBAN()+ " status: " +status+ " startDate: "+ cl.getFechaAlta() + " endDate: "+ cl.getFechaBaja());
	    	    
	    		}
	    		
	    		
	    	}
		
		return informe;
	}
	
	
	@Override
	public List<String> informeClientePaisesBajos(Date alta, Date baja, String nom, String ape, String dir, String cp) {
		List<String> informe = null;
		informe.add("{'PERSONAS':[");
		String sentencia = "SELECT cl Cliente cl WHERE cl.pais = :fpais";
		
		if(alta != null) {
			sentencia.concat(" AND cl.fechaAlta = :falta");
		}
		
		if(baja != null) {
			sentencia.concat(" AND cl.fechaBaja = :fbaja");
		}
		
		if(nom != null) {
			sentencia.concat(" AND cl.fechaAlta = :falta");
		}
		
		if(ape != null) {
			sentencia.concat(" AND cl.fechaAlta = :falta");
		}
		
		if(dir != null) {
			sentencia.concat(" AND cl.fechaAlta = :falta");
		}
		
		if(cp != null) {
			sentencia.concat(" AND cl.fechaAlta = :falta");
		}
		
	    Query query = em.createQuery(sentencia);
		query.setParameter("fpais", "PaisesBajos");
		query.setParameter("fpais", "PaisesBajos");
		query.setParameter("fpais", "PaisesBajos");
		query.setParameter("fpais", "PaisesBajos");
		query.setParameter("fpais", "PaisesBajos");
		query.setParameter("fpais", "PaisesBajos");
		query.setParameter("fpais", "PaisesBajos");
		
		
		List<CuentaFintech> listCl = query.getResultList();
	    
	    for(CuentaFintech cf: listCl) {
	    	Cliente cl = cf.getCliente();
	    		
	    	}
		
		return informe;
	}
	
		
	@Override
	public List<Cuenta> informeAlemania() {
		Query query = em.createQuery("Select c from Cuenta c, Cliente c where c.pais LIKE :fpais");
		query.setParameter("fpais", "Alemania");
		List<Cuenta> clientes = query.getResultList();
		return clientes;
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
