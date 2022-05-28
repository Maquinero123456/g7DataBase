package rest;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
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


	//1
	@Path("/healcheck")
    @GET
	@Produces ("text/plain")
    public String healthCheck(){
        return "OK";
    }
    
	//2
    @Path("/clients")
    @POST
    @Consumes ({MediaType.APPLICATION_JSON, "text/plain"})
    public Response clients(ClientsJson json) throws ParseException{
    	Date prim = new SimpleDateFormat("yyyy-MM-dd").parse(json.getSearchParameters().getStartPeriod());
    	Date fin = new SimpleDateFormat("yyyy-MM-dd").parse(json.getSearchParameters().getEndPeriod());
    	List<String> lol = informes.informeClienteFechaPaisesBajos(json.getSearchParameters().getName().getLastName(), prim, fin);
        return Response.ok(lol.toString()).build();
    }

    //3
	@Path("/products")
    @POST
    @Consumes (MediaType.APPLICATION_JSON)
	public Response products(ProductsJson json) throws ParseException{
		Boolean status = false;
		String act = json.getSearchParameters().getStatus();
		if(act.equalsIgnoreCase("active") || act.equalsIgnoreCase("alta") || act.equalsIgnoreCase("activa")) {
			status = true;
		}
		
		else if(act.equals(null)){
			status = null;
		}
		List<String> lol = informes.informeCuentasPaisesBajos(status, json.getSearchParameters().getProductNumber());
		return Response.ok(lol.toString()).build();
	}

}
