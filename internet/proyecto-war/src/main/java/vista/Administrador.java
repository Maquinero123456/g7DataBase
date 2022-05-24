package vista;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import es.uma.proyecto.GestionAdministrativos;
import es.uma.proyecto.GestionCuentasUsuarios;
import es.uma.proyecto.entidades.Cliente;
import es.uma.proyecto.entidades.CuentaReferencia;
import es.uma.proyecto.entidades.PersonaAutorizada;
import es.uma.proyecto.entidades.Usuario;
import es.uma.proyecto.exceptions.AutorizacionException;
import es.uma.proyecto.exceptions.ClienteException;
import es.uma.proyecto.exceptions.CuentaException;
import es.uma.proyecto.exceptions.PersonaAutorizadaException;

@Named(value = "admin")
@RequestScoped
public class Administrador {

	@EJB
	private GestionAdministrativos admin;
	@EJB
	private GestionCuentasUsuarios cuentas;
	
	private Cliente cliente;
	private Usuario usuario;
	private CuentaReferencia cuentaRef;
	private PersonaAutorizada perAu;
	private String ident;
	private String iban;
	private String ibanRef;
    
	private String idPer;
	private String idEmp;
	private String tipo;
	
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
	
	public String getIbanRef() {
		return ibanRef;
	}

	public void setIbanRef(String ibanRef) {
		this.ibanRef = ibanRef;
	}
	
	public String darAlta() {	
		try {
			admin.darAltaCliente(ident);
			return "BIEN";
		} catch (ClienteException e) {
			FacesMessage fm = new FacesMessage("No existe el cliente solicitado.");
	        FacesContext.getCurrentInstance().addMessage("administrador:identAlta", fm);
		}
		return "ERROR";
	}
	
	public String darBaja() {	
		try {
			admin.darBajaCliente(ident);
			return "BIEN";
		} catch (ClienteException e) {
			FacesMessage fm = new FacesMessage("No existe el cliente solicitado.");
	        FacesContext.getCurrentInstance().addMessage("administrador:identBaja", fm);
		}
		return "ERROR";
	}
	
	public void modificarClient() {
		try {
			admin.modificarCliente(cliente);
		} catch (ClienteException e) {
			FacesMessage fm = new FacesMessage("No existe el cliente solicitado.");
	        FacesContext.getCurrentInstance().addMessage("administrador:clienteMod", fm);
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
		} catch (ClienteException e) {
			FacesMessage fm = new FacesMessage("El cliente indicado no existe.");
	        FacesContext.getCurrentInstance().addMessage("administrador:ident", fm);
		}
	}
	
	public void aperturaCuentaSegreg() {
		try {
			admin.aperturaCuentaSegregada(iban, ident, cuentaRef);
		} catch (CuentaException e) {
			FacesMessage fm = new FacesMessage("La cuenta no existe o ya está tomada.");
	        FacesContext.getCurrentInstance().addMessage("administrador:iban", fm);
		} catch (ClienteException e) {
			FacesMessage fm = new FacesMessage("El cliente indicado no existe.");
	        FacesContext.getCurrentInstance().addMessage("administrador:ident", fm);
		}
	}
	
	public void modificarAutorizado() {
		try {
			admin.modificarAutorizado(perAu);
		} catch (PersonaAutorizadaException e) {
			FacesMessage fm = new FacesMessage("La persona autorizada indicada no existe.");
	        FacesContext.getCurrentInstance().addMessage("administrador:perAu", fm);
		}
	}
	
	public void addAutorizado() {
		long idE = Long.parseLong(idEmp);
		long idP = Long.parseLong(idPer);
		try {
			admin.addAutorizados(idE, idP, tipo);
		} catch (ClienteException e) {
			FacesMessage fm = new FacesMessage("La empresa indicada no existe.");
	        FacesContext.getCurrentInstance().addMessage("administrador:idEmp", fm);
		} catch (PersonaAutorizadaException e) {
			FacesMessage fm = new FacesMessage("La persona autorizada indicada no existe.");
	        FacesContext.getCurrentInstance().addMessage("administrador:idPer", fm);
		} catch (AutorizacionException e) {
			FacesMessage fm = new FacesMessage("Error al crear la autorizacion.");
	        FacesContext.getCurrentInstance().addMessage("administrador:tipo", fm);
		}
	}

	public void eliminarAutorizado() {
		long idE = Long.parseLong(idEmp);
		long idP = Long.parseLong(idPer);
		try {
			admin.eliminarAutorizado(idE, idP);
		} catch (ClienteException e) {
			FacesMessage fm = new FacesMessage("La empresa indicada no existe.");
	        FacesContext.getCurrentInstance().addMessage("administrador:idEmp", fm);
		} catch (PersonaAutorizadaException e) {
			FacesMessage fm = new FacesMessage("La persona autorizada indicada no existe.");
	        FacesContext.getCurrentInstance().addMessage("administrador:idPer", fm);
		} catch (AutorizacionException e) {
			FacesMessage fm = new FacesMessage("Error al eliminar la autorizacion.");
	        FacesContext.getCurrentInstance().addMessage("admini:tipo", fm);
		}
	}

	public GestionAdministrativos getAdmin() {
		return admin;
	}

	public void setAdmin(GestionAdministrativos admin) {
		this.admin = admin;
	}

	public GestionCuentasUsuarios getCuentas() {
		return cuentas;
	}

	public void setCuentas(GestionCuentasUsuarios cuentas) {
		this.cuentas = cuentas;
	}

	public PersonaAutorizada getPerAu() {
		return perAu;
	}

	public void setPerAu(PersonaAutorizada perAu) {
		this.perAu = perAu;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getIdPer() {
		return idPer;
	}

	public void setIdPer(String idPer) {
		this.idPer = idPer;
	}

	public String getIdEmp() {
		return idEmp;
	}

	public void setIdEmp(String idEmp) {
		this.idEmp = idEmp;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}

	
}
