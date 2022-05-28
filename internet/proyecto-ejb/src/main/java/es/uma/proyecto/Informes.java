package es.uma.proyecto;

import java.util.Date;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import es.uma.proyecto.entidades.Cliente;
import es.uma.proyecto.entidades.CuentaFintech;
import es.uma.proyecto.entidades.Empresa;
import es.uma.proyecto.entidades.Individual;
import es.uma.proyecto.entidades.PersonaAutorizada;

@Stateless
public class Informes implements GestionInformes{
	@SuppressWarnings("unused")
    private static final Logger LOG = Logger.getLogger(Informes.class.getCanonicalName());

    @PersistenceContext(name="proyectoEJB")
    private EntityManager em;
    
    
	@Override
	public List<String> informeCuentasPaisesBajos(boolean status, String productNumber) {
		JsonbConfig config = new JsonbConfig().withPropertyOrderStrategy(PropertyOrderStrategy.ANY);
		config.withNullValues(true);
		Jsonb builder = JsonbBuilder.create(config);
		List<String> informe = new ArrayList<String>();
		informe.add("PRODUCTOS: \n");
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
	   		if(getFecha(cf.getCliente().getFechaBaja()) > 2019) {
	   			informe.add("accountHolder: "+builder.toJson(cf.getCliente())+"\n"+builder.toJson(cf)+"\n");
	   		}
	   	}
	   	
		return informe;
	}
	
	
	@Override
	public List<String> informeClientePaisesBajos(String ape, String dir, String cp) {
		Jsonb builder = JsonbBuilder.create();
		List<String> informe = new ArrayList<String>();
		informe.add("INDIVIDUALES: ");
		String sentence = "SELECT cl FROM Individual cl WHERE cl.pais = :fpais";
	    
		if(ape != null) {
			sentence = sentence.concat(" AND cl.apellidos LIKE :fape");
		}
		
		if(dir != null) {
			sentence = sentence.concat(" AND cl.direccion LIKE :fdir");
		}
		
		if(cp != null) {
			sentence = sentence.concat(" AND cl.codigoPostal LIKE :fcp");
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
	    	if(getFecha(ind.getFechaBaja()) > 2019) {
	    	   	informe.add("CLIENTE: "+builder.toJson(ind)+"\n");
	    	}
	    }
		
		return informe;
	}
	
	@Override
	public List<String> informeClienteFechaPaisesBajos(String ape, Date alta, Date baja) {
		Jsonb builder = JsonbBuilder.create();
		List<String> informe = new ArrayList<String>();
		
		informe.add("Clientes: ");
		String sentence = "SELECT cl FROM Cliente cl WHERE cl.pais = :fpais";
	    
		if(ape != null) {
			sentence = sentence.concat(" AND (cl.apellidos LIKE :fape OR cl.razonsocial LIKE :fape)");
		}
		
		Query query = em.createQuery(sentence);
		query.setParameter("fpais", "PaisesBajos");
		if(ape != null) {
			query.setParameter("fape", ape);
		}
				
		List<Cliente> listCl = query.getResultList();
	    
	    for(Cliente c: listCl) {
	    	if(c.getFechaAlta().compareTo(alta) > 0 && c.getFechaAlta().compareTo(baja) < 0) {
	    	   	informe.add("CLIENTE: "+builder.toJson(c)+"\n");
	    	}
	    }
		
		return informe;
	}
	
		
	@Override
	public int getFecha(Date date) {
		int ano = 0;
			if(date == null) {
				return 2022;
			}
		ano = Integer.parseInt(date.toString().substring(date.toString().length()-4));
			
		return ano;
	}


	@Override
	public void informeSemanalAlemania() throws IOException {
		List<String[]> informe = new ArrayList<String[]>();
		String sentence = "SELECT cf FROM CuentaFintech cf WHERE cf.cliente.pais = :fpais AND cf.estado = :fstatus AND cf.clasificacion = :fclas";
	    
		Query query = em.createQuery(sentence);
		query.setParameter("fpais", "Alemania");
		query.setParameter("fstatus", true);
		query.setParameter("fclas", "segregada");
		
		
		List<CuentaFintech> listCF = query.getResultList();
		
		for(CuentaFintech cf: listCF) {
	    	Cliente cl = cf.getCliente();
	    	
	    		if((cl.getTipoCliente().equalsIgnoreCase("individual") || cl.getTipoCliente().equalsIgnoreCase("fisica")) && getFecha(cl.getFechaBaja()) > 2017) {
	    			Individual ind = em.find(Individual.class, cl.getID());
	    			
	    			if(ind.getFechaNacimiento() != null) {
	    				informe.add(new String[] {cf.getIBAN(), ind.getApellidos(), ind.getNombre(), ind.getDireccion(), ind.getCiudad(), ind.getCodigoPostal(), ind.getPais(), ind.getIdentificacion(), ind.getFechaNacimiento().toString()});
	    			}
	    			
	    			else {
	    				informe.add(new String[] {cf.getIBAN(), ind.getApellidos(), ind.getNombre(), ind.getDireccion(), ind.getCiudad(), ind.getCodigoPostal(), ind.getPais(), ind.getIdentificacion(), "noexistente"});
	    			}
	    			
	    		}
	    		
		}
		// Crea una CSV printer
		FileWriter fw = new FileWriter("informeSemanalAlemania.csv");
		CSVPrinter printer = new CSVPrinter(fw, CSVFormat.EXCEL);
		// Cabecera 
		printer.printRecord("IBAN", "Apellidos", "Nombre", "Calle", "Ciudad", "Codigo_Postal", "Pais", "Identificacion", "Fecha_de_nacimiento");
		
		// Filas de datos
		for (int i =0; i<informe.size(); i++) {
		    printer.printRecord(informe.get(i));
		}

		// Cierra el printer
		printer.flush();
		printer.close();
		
		
	}
	
	@Override
	public void informeMensualAlemania() throws IOException {
		List<String[]> informe = new ArrayList<String[]>();
		String sentence = "SELECT cf FROM CuentaFintech cf WHERE cf.cliente.pais = :fpais AND cf.clasificacion = :fclas";
	    
		Query query = em.createQuery(sentence);
		query.setParameter("fpais", "Alemania");
		query.setParameter("fclas", "segregada");
		
		
		List<CuentaFintech> listCF = query.getResultList();
		
		for(CuentaFintech cf: listCF) {
	    	Cliente cl = cf.getCliente();
	    	
	    		if((cl.getTipoCliente().equalsIgnoreCase("individual") || cl.getTipoCliente().equalsIgnoreCase("fisica")) && getFecha(cl.getFechaBaja()) > 2017) {
	    			Individual ind = em.find(Individual.class, cl.getID());
	    			
	    			if(ind.getFechaNacimiento() != null) {
	    				informe.add(new String[] {cf.getIBAN(), ind.getApellidos(), ind.getNombre(), ind.getDireccion(), ind.getCiudad(), ind.getCodigoPostal(), ind.getPais(), ind.getIdentificacion(), ind.getFechaNacimiento().toString()});
	    			}
	    			
	    			else {
	    				informe.add(new String[] {cf.getIBAN(), ind.getApellidos(), ind.getNombre(), ind.getDireccion(), ind.getCiudad(), ind.getCodigoPostal(), ind.getPais(), ind.getIdentificacion(), "noexistente"});
	    			}
	    			
	    		}
	    		
		}
		// Crea una CSV printer
		CSVPrinter printer = new CSVPrinter(new FileWriter("informeMensualAlemania.csv"), CSVFormat.DEFAULT);
		// Cabecera 
		printer.printRecord("IBAN", "Apellidos", "Nombre", "Calle", "Ciudad", "Codigo_Postal", "Pais", "Identificacion", "Fecha_de_nacimiento");
		
		// Filas de datos
		for (int i =0; i<informe.size(); i++) {
		    printer.printRecord(informe.get(i));
		}

		// Cierra el printer
		printer.flush();
		printer.close();
		
		
	}
}  