package rest;

import java.net.URI;
import java.util.ArrayList;
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

import es.uma.proyecto.Clientes;
import es.uma.proyecto.GestionAutorizados;
import es.uma.proyecto.GestionClientes;
import es.uma.proyecto.GestionCuentas;
import es.uma.proyecto.entidades.Cliente;
import es.uma.proyecto.entidades.CuentaFintech;
import es.uma.proyecto.entidades.Empresa;
import es.uma.proyecto.entidades.Individual;
import es.uma.proyecto.entidades.PersonaAutorizada;
import es.uma.proyecto.exceptions.ClienteException;
import es.uma.proyecto.exceptions.CuentaException;
import es.uma.proyecto.exceptions.EmpresaException;
import es.uma.proyecto.exceptions.IndividualException;
import es.uma.proyecto.exceptions.PersonaAutorizadaException;

import javax.ws.rs.core.UriInfo;
@Path("/api")
public class informeREST {

	@EJB
	private GestionCuentas cuentas;
	@EJB
	private GestionAutorizados autorizados;
	@EJB
	private GestionClientes clientes;


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
    @Consumes (MediaType.APPLICATION_JSON)
    public Response clients(String name, String lastName, String alta, String baja){
		List<Individual> individuos = null;
		try {
			individuos = clientes.getIndividualNombre(name, lastName);
		} catch (IndividualException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<PersonaAutorizada> personas = null;
		try {
			personas = autorizados.getPersonaAutorizadaNombre(name, lastName);
		} catch (PersonaAutorizadaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<String> json = new ArrayList<>();
		json.add("{");
		for(Individual ind : individuos){
			json.add("'Individual':[ {");
			for(CuentaFintech cf : ind.getCuentas()){
				String activa = null;
				if(cf.getEstado()){
					activa = "activa";
				}else{
					activa = "baja";
				}
				json.add("{'productNumber' : '"+cf.getIBAN()+"', 'status': '"+activa+"', 'relationship': 'propietaria'}");
			}
			json.add("], ");
			String activa = "true";
			if(ind.getEstado().equals("Baja")){
				activa = "false";
			}
			json.add("'activeCustomer':"+activa+", ");
			json.add("'indentificacionNumber':'"+ind.getIdentificacion()+"', ");
			json.add("'dateOfBirth':'"+ind.getFechaNacimiento()+"', ");
			json.add("'name': {'firstname':'"+ind.getNombre()+"', 'lastname':'"+ind.getApellidos()+"'}, ");
			json.add("'address': {'streetNumber':'"+ind.getDireccion()+"', 'postalCode':'"+ind.getCodigoPostal()+"', 'city':'"+ind.getCiudad()+"', 'country':'"+ind.getPais()+"'}");
			json.add("} ]");
			json.add(", ");
		}

		for(PersonaAutorizada pers : personas){
			json.add("'Persona Autorizada':[");
			try{
				for(CuentaFintech cf : autorizados.getCuentasPersonaAutorizada(pers.getIdentificacion())){
					String activa = null;
					if(cf.getEstado()){
						activa = "activa";
					}else{
						activa = "baja";
					}
					json.add("{'productNumber' : '"+cf.getIBAN()+"', 'status': '"+activa+"', 'relationship': 'autorizada'}");
				}
				json.add("], ");
				String activa = "true";
				if(pers.getEstado().equals("Baja")){
					activa = "false";
				}
				json.add("'activeCustomer':"+activa+", ");
				json.add("'indentificacionNumber':'"+pers.getIdentificacion()+"', ");
				json.add("'dateOfBirth':'null', ");
				json.add("'name': {'firstname':'"+pers.getNombre()+"', 'lastname':'"+pers.getApellidos()+"'}, ");
				json.add("'address': {'streetNumber':'"+pers.getDireccion()+"', 'postalCode':'null', 'city':'null', 'country':'null'}");
				json.add("} ]");
				json.add(", ");
			} catch (PersonaAutorizadaException e){

			} catch (CuentaException e){

			}
			
		}

		if(json.get(json.size()-1).equals(", ")){
			json.remove(json.size()-1);
		}
		json.add("}");
        return Response.ok(json.toString()).build();
    }

	@Path("/products")
    @POST
    @Consumes (MediaType.APPLICATION_JSON)
	public Response products(Boolean status, String iban){
		List<Cliente> clients=null;
		try {
			clients = clientes.getClientesIBAN(iban, status);
		} catch (ClienteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<String> json = new ArrayList<>();
		json.add("{ 'products': [{");
		for(Cliente cl : clients){
			json.add(" 'accountHolder': {");
			if(cl.getEstado().equals("Alta")){
				json.add("'activeCustomer': true, ");
			}else{
				json.add("'activeCustomer': false");
			}
			json.add("'accounttype':'"+cl.getTipoCliente()+"', ");
			if(cl.getTipoCliente().equals("Fisica")){
				Individual aux = null;
				try {
					aux = clientes.getIndividual(cl.getIdentificacion());
				} catch (ClienteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				json.add("'name': {'firstname':'"+aux.getNombre()+"', 'lastname':'"+aux.getApellidos()+"'}, ");
			}else{
				Empresa aux = null;
				try {
					aux = clientes.getEmpresa(cl.getIdentificacion());
				} catch (EmpresaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				json.add("'razonSocial':'"+aux.getRazonSocial()+"");
			}
			json.add("'address': {'streetNumber':'"+cl.getDireccion()+"', 'postalCode':'"+cl.getCodigoPostal()+"', 'city':'"+cl.getCiudad()+"', 'country':'"+cl.getPais()+"'}");
			json.add("}, 'productNumber':'"+iban+"', ");
			if(cl.getEstado().equals("Alta")){
				json.add("'status':'activa'");
			}else{
				json.add("'status':'baja'");
			}
			json.add("'startDate':'"+cl.getFechaAlta()+"', ");
			json.add("'endDate':'"+cl.getFechaBaja()+"'},");
		}
		if(json.get(json.size()-1).equals(", ")){
			json.remove(json.size()-1);
		}
		json.add("}");
		return Response.ok(json.toString()).build();
	}

}
