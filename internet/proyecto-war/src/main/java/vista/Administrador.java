package vista;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import es.uma.proyecto.GestionAdministrativos;
import es.uma.proyecto.GestionCuentasUsuarios;
import es.uma.proyecto.entidades.Cliente;
import es.uma.proyecto.entidades.Usuario;
import es.uma.proyecto.exceptions.ClienteException;

public class Administrador {

	@EJB
	GestionAdministrativos admin;
	@EJB
	GestionCuentasUsuarios cuentas;
	
	private Cliente cliente;
	private Usuario usuario;
	private String ident;
    
    public Administrador() {
    	usuario = new Usuario();
    }
        
	public String darAlta() {	
		try {
			admin.darAltaCliente(ident);
			return "Cliente dado de alta";
		} catch (ClienteException e) {
			FacesMessage fm = new FacesMessage("No existe le cliente solicitado.");
	        FacesContext.getCurrentInstance().addMessage("registro:user", fm);
		}
		
		return "No se pudo dar de alta.";
	}
}
