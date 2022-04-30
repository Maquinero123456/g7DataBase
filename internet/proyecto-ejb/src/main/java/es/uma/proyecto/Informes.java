package es.uma.proyecto;

import java.sql.Date;
import java.util.ArrayList;
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
		List<String> informe = new ArrayList<String>();
		informe.add("'PRODUCTS':[");
		String sentence = "SELECT cu FROM CuentaFintech cu WHERE cu.cliente.pais = :fpais AND cu.estado = :fstatus";
	    
		if(productNumber != null) {
			sentence = sentence.concat(" AND cu.iban = :fiban");
		}
		
		Query query = em.createQuery(sentence);
		query.setParameter("fpais", "PaisesBajos");
		query.setParameter("fstatus", status);
		if(productNumber != null) {
			query.setParameter("fiban", productNumber);
		}
		
		List<CuentaFintech> listCl = query.getResultList();
		
	    for(CuentaFintech cf: listCl) {
	    	Cliente cl = cf.getCliente();
	    	
	    		if((cl.getTipoCliente().equalsIgnoreCase("individual") || cl.getTipoCliente().equalsIgnoreCase("fisica"))) {
	    			Individual ind = em.find(Individual.class, cl.getID());
	    			informe.add("\n	'ACCOUNTHOLDER':[ activeCustomer: "+cl.getEstado()+", accountType: " +cl.getTipoCliente() + "]"
	    	    	+"\n	'NAME':[ First Name: " +ind.getNombre()+", Last Name: "+ind.getApellidos() + "] " 
	    	    	+"\n 	'ADRESSES':[ city: "+ cl.getCiudad() + ", street: "+ cl.getDireccion() +", postalCode: "+cl.getCodigoPostal() + ", country: " + cl.getPais() + "]"
	    	    	+"\n 	'CUENTA':[ productType: "+ cf.getClasificacion() + ", productNumber: "+ cf.getIBAN()+ ", status: " +status+ ", startDate: "+ cl.getFechaAlta() + ", endDate: "+ cl.getFechaBaja()+"\n");
	    	    
	    		}
	    		
	    		else if(true){
	    			Empresa emp = em.find(Empresa.class, cl.getID());
	    			informe.add("\n	'ACCOUNTHOLDER':[ activeCustomer: "+cl.getEstado()+", accountType: " +cl.getTipoCliente() + "]"
	    	    	+"\n	'NAME':[ business name: "+ emp.getRazonSocial() +" ] "  
	    	    	+"\n 	'ADRESSES':[ city: "+ cl.getCiudad() + ", street: "+ cl.getDireccion() +", postalCode: "+cl.getCodigoPostal() + ", country: " + cl.getPais() + "]"
	    	    	+"\n 	'CUENTA':[ productType: "+ cf.getClasificacion() + ", productNumber: "+ cf.getIBAN()+ ", status: " +status+ ", startDate: "+ cl.getFechaAlta() + ", endDate: "+ cl.getFechaBaja()+"\n");
	    	    
	    		}
	    		
	    		
	    	}
		
		return informe;
	}
	
	
	@Override
	public List<String> informeClientePaisesBajos(String ape, String dir, String cp) {
		List<String> informeC = new ArrayList<String>();
		informeC.add("'PERSONAS':[");
		String sentence = "SELECT cl FROM Individual cl WHERE cl.pais = :fpais";
	    
		if(ape != null) {
			sentence = sentence.concat(" AND cl.apellidos = :fape");
		}
		
		if(dir != null) {
			sentence = sentence.concat(" AND cl.direccion = :fdir");
		}
		
		if(cp != null) {
			sentence = sentence.concat(" AND cl.codigoPostal = :fcp");
		}
		
		Query query = em.createQuery(sentence);
		query.setParameter("fpais", "PaisesBajos");
		if(ape != null) {
			query.setParameter("fape", ape);
		}
		
		if(dir != null) {
			query.setParameter("fdir", dir);
		}
		
		if(cp != null) {
			query.setParameter("fcp", cp);
		}
		
		List<Individual> listCl = query.getResultList();
	    
	    for(Individual ind: listCl) {
	    	//if() {
	    		informeC.add("\n	'NAME':[ First Name: " +ind.getNombre()+", Last Name: "+ind.getApellidos() + "] " 
	    				+"\n 	'ADRESSES':[ city: "+ ind.getCiudad() + ", street: "+ ind.getDireccion() +", postalCode: "+ind.getCodigoPostal() + ", country: " + ind.getPais() + "]");
	    	    
	    		for(CuentaFintech cf: ind.getCuentas()) {
	    			informeC.add("\n 	'CUENTA':[ productType: "+ cf.getClasificacion() + ", productNumber: "+ cf.getIBAN()+ ", status: " + cf.getEstado()+ ", startDate: "+ ind.getFechaAlta() + ", endDate: "+ ind.getFechaBaja()+"\n");
	    		}
	    	//}
	    }
		
		return informeC;
	}
	
		
	@Override
	public List<Cuenta> informeAlemania() {
		Query query = em.createQuery("Select cu from Cuenta cu, Cliente cl where cl.pais LIKE :fpais");
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
