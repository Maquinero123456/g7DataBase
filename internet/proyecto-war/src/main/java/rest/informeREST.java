package rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


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
    	
    	List<String> lol = informes.informeClienteFechaPaisesBajos(json.getSearchParameters().getName().getLastName(), prim, fin);
        return Response.ok(lol.toString()).build();
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

		List<String> lol = informes.informeCuentasPaisesBajos(status, json.getSearchParameters().getProductNumber());
		return Response.ok(lol.toString()).build();
	}

}