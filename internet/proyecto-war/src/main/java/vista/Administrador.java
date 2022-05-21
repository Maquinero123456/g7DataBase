package vista;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import es.uma.proyecto.GestionAdministrativos;
import es.uma.proyecto.GestionCuentasUsuarios;
import es.uma.proyecto.entidades.Cliente;
import es.uma.proyecto.entidades.CuentaReferencia;
import es.uma.proyecto.entidades.Usuario;
import es.uma.proyecto.exceptions.ClienteException;
import es.uma.proyecto.exceptions.CuentaException;

public class Administrador {

	@EJB
	private GestionAdministrativos admin;
	@EJB
	private GestionCuentasUsuarios cuentas;
	
	private Cliente cliente;
	private Usuario usuario;
	private CuentaReferencia cuentaRef;
	private String ident;
	private String iban;
    
	public Administrador() {
    	usuario = new Usuario();
    }
	
	public String getIdent() {
        return ident;
    }

    public void setident(String id) {
        this.ident = id;
    }
    
	public Cliente getCliente() {
		return cliente;
	}
	
	public void getCliente(Cliente clie) {
		cliente = clie;
	}
	
	public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public CuentaReferencia getCuentaRef() {
		return cuentaRef;
	}

	public void setCuentaRef(CuentaReferencia cuentaRef) {
		this.cuentaRef = cuentaRef;
	}
	
	public void darAlta() {	
		try {
			admin.darAltaCliente(ident);
		} catch (ClienteException e) {
			FacesMessage fm = new FacesMessage("No existe el cliente solicitado.");
	        FacesContext.getCurrentInstance().addMessage("administrador:ident", fm);
		}

	}
	
	public void darBaja() {	
		try {
			admin.darBajaCliente(ident);
		} catch (ClienteException e) {
			FacesMessage fm = new FacesMessage("No existe el cliente solicitado.");
	        FacesContext.getCurrentInstance().addMessage("administrador:ident", fm);
		}
	}
	
	public void modificarClient() {
		try {
			admin.modificarCliente(cliente);
		} catch (ClienteException e) {
			FacesMessage fm = new FacesMessage("No existe el cliente solicitado.");
	        FacesContext.getCurrentInstance().addMessage("administrador:cliente", fm);
		}
	}
	
	public void cerrarCuenta() {
		try {
			admin.cerrarCuenta(iban);
		} catch (CuentaException e) {
			FacesMessage fm = new FacesMessage("No la cuenta o el saldo no es 0.");
	        FacesContext.getCurrentInstance().addMessage("administrador:iban", fm);
		}
	}
	
	public void aperturaCuentaAgrup() {
		try {
			admin.aperturaCuentaAgrupada(iban, ident);
		} catch (CuentaException e) {
			FacesMessage fm = new FacesMessage("La cuenta no existe o ya está tomada.");
	        FacesContext.getCurrentInstance().addMessage("administrador:iban", fm);
			e.printStackTrace();
		} catch (ClienteException e) {
			FacesMessage fm = new FacesMessage("El cliente indicado no existe.");
	        FacesContext.getCurrentInstance().addMessage("administrador:ident", fm);
			e.printStackTrace();
		}
	}
	
	public void aperturaCuentaSegreg() {
		try {
			admin.aperturaCuentaSegregada(iban, ident, cuentaRef);
		} catch (CuentaException e) {
			FacesMessage fm = new FacesMessage("La cuenta no existe o ya está tomada.");
	        FacesContext.getCurrentInstance().addMessage("administrador:iban", fm);
			e.printStackTrace();
		} catch (ClienteException e) {
			FacesMessage fm = new FacesMessage("El cliente indicado no existe.");
	        FacesContext.getCurrentInstance().addMessage("administrador:ident", fm);
			e.printStackTrace();
		}
	}

	
}
