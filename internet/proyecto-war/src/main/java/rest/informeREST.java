package rest;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;


import es.uma.proyecto.GestionAutorizados;
import es.uma.proyecto.GestionClientes;
import es.uma.proyecto.GestionCuentas;
import es.uma.proyecto.GestionInformes;
import es.uma.proyecto.entidades.Cliente;
import es.uma.proyecto.entidades.CuentaFintech;
import es.uma.proyecto.entidades.Empresa;
import es.uma.proyecto.entidades.Individual;

@Path("/api")
public class informeREST {

	@EJB
	private GestionCuentas cuentas;
	@EJB
	private GestionAutorizados autorizados;
	@EJB
	private GestionClientes clientes;
	@EJB
	private GestionInformes informes;
	
	private EntityManager em;
	

	private Address direc;
	private Name name;
	private Products product;
	private Individuo indi;
	
	private ResponseProducts rp;


	//1
	@Path("/healthcheck")
    @GET
	@Produces ("text/plain")
    public String healthCheck(){
        return "OK";
    }
    
	//2
    @Path("/clients")	
    @POST
    @Consumes (MediaType.APPLICATION_JSON)
    public Response clients(ClientsJson json) throws ParseException{
    	Date prim = new SimpleDateFormat("yyyy-MM-dd").parse(json.getSearchParameters().getStartPeriod());
    	Date fin = new SimpleDateFormat("yyyy-MM-dd").parse("2022-06-15");
    	if(json.getSearchParameters().getEndPeriod() != null) {
    		fin = new SimpleDateFormat("yyyy-MM-dd").parse(json.getSearchParameters().getEndPeriod());
    	}
    	
    	List<Cliente> listCl = informes.informeClienteFechaPaisesBajos(json.getSearchParameters().getName().getLastName(), prim, fin);
        
    	return Response.ok(listCl.toString()).build();
        
        
    }

    //3
	@Path("/products")
    @POST
    @Consumes (MediaType.APPLICATION_JSON)
	public Response products(ProductsJson json) throws ParseException{
		Boolean status = null;
		String act = json.getSearchParameters().getStatus();
		if(act!=null && (act.equalsIgnoreCase("active") || act.equalsIgnoreCase("alta") || act.equalsIgnoreCase("activa"))) {
			status = true;
		}else if(act!=null){
			status = false;
		}
		
		Date limite = new SimpleDateFormat("yyyy-MM-dd").parse("2019-05-28");
		
		List<CuentaFintech> listCf = informes.informeCuentasPaisesBajos(status, json.getSearchParameters().getProductNumber());
		
		for(CuentaFintech cf: listCf) {
			if(cf.getFechaCierre() == null) {
				cf.setFechaCierre(limite);
			}
	   		if(cf.getFechaCierre().compareTo(limite) >= 0) {
	   			Cliente c = cf.getCliente();
	   			direc.setCity(c.getCiudad());
	   			direc.setCountry(c.getPais());
	   			direc.setPostalCode(c.getCodigoPostal());
	   			direc.setStreetNumber(c.getDireccion());
	   			
	   			if(cf.getClasificacion().equalsIgnoreCase("fisica")) {
	   				Individual ind = em.find(Individual.class, c.getId());
	   				name.setFirstName(ind.getNombre());
	   				name.setLastName(ind.getApellidos());
					indi.setDateOfBirth(ind.getFechaNacimiento().toString());
	   			}
	   			product.setProductNumber(cf.getIBAN());
		   		if(cf.getEstado()) {
		   			product.setStatus("activa");
		   		}
		   		else {
		   			product.setStatus("inactiva");
		   		}
		   		
		   		product.setRelationship(cf.getClasificacion());
		   		
		   		indi.setAddress(direc);
				indi.setName(name);
				indi.setProducts(product);
	   		}
	   		
	   		
	   	}
		
		rp.setIndividual(indi);
		
		return Response.ok(rp.toString()).build();
	}

}
