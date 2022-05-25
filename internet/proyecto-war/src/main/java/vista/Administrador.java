package vista;

import java.util.Date;

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
	
	private String pais1;
	private String fecha1;
	private String ciudad1;
	private String dir1;
	private String cp1;
	
	private String pais2;
	private String fecha2;
	private String ciudad2;
	private String dir2;
	private String cp2;
    
	private String idPer;
	private String idEmp;
	private String tipo;
	
	public Administrador() {
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
			cliente = admin.getCliente(ident);
		} catch (ClienteException e1) {
		}
			
		if(ciudad1 != null) {
			cliente.setCiudad(ciudad1);
		}
		
		if(cp1 != null) {
			cliente.setCodigoPostal(cp1);
		}
		
		if(dir1 != null) {
			cliente.setDireccion(dir1);
		}
		
		if(pais1 != null){
			cliente.setPais(pais1);
		}
		if(fecha1 != null) {
			Date fecha = null;
			cliente.setFechaBaja(fecha);
		}
		
		/*if() {
			cliente.setCuentas(cliente.getCuentas());
		}*/
		
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
	        FacesContext.getCurrentInstance().addMessage("administrador:ibanCer", fm);
		}
	}
	
	public void aperturaCuentaAgrup() {
		try {
			admin.aperturaCuentaAgrupada(iban, ident);
		} catch (CuentaException e) {
			FacesMessage fm = new FacesMessage("La cuenta no existe o ya está tomada.");
	        FacesContext.getCurrentInstance().addMessage("administrador:ibanAA", fm);
		} catch (ClienteException e) {
			FacesMessage fm = new FacesMessage("El cliente indicado no existe.");
	        FacesContext.getCurrentInstance().addMessage("administrador:identAA", fm);
		}
	}
	
	public void aperturaCuentaSegreg() {
		try {
			admin.aperturaCuentaSegregada(iban, ident, cuentaRef);
		} catch (CuentaException e) {
			FacesMessage fm = new FacesMessage("La cuenta no existe o ya está tomada.");
	        FacesContext.getCurrentInstance().addMessage("administrador:ibanAS", fm);
		} catch (ClienteException e) {
			FacesMessage fm = new FacesMessage("El cliente indicado no existe.");
	        FacesContext.getCurrentInstance().addMessage("administrador:identAS", fm);
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
	        FacesContext.getCurrentInstance().addMessage("administrador:idEmpAPA", fm);
		} catch (PersonaAutorizadaException e) {
			FacesMessage fm = new FacesMessage("La persona autorizada indicada no existe.");
	        FacesContext.getCurrentInstance().addMessage("administrador:idPerAPA", fm);
		} catch (AutorizacionException e) {
			FacesMessage fm = new FacesMessage("Error al crear la autorizacion.");
	        FacesContext.getCurrentInstance().addMessage("administrador:tipoAPA", fm);
		}
	}

	public void eliminarAutorizado() {
		long idE = Long.parseLong(idEmp);
		long idP = Long.parseLong(idPer);
		try {
			admin.eliminarAutorizado(idE, idP);
		} catch (ClienteException e) {
			FacesMessage fm = new FacesMessage("La empresa indicada no existe.");
	        FacesContext.getCurrentInstance().addMessage("administrador:idEmpE", fm);
		} catch (PersonaAutorizadaException e) {
			FacesMessage fm = new FacesMessage("La persona autorizada indicada no existe.");
	        FacesContext.getCurrentInstance().addMessage("administrador:idPerE", fm);
		} catch (AutorizacionException e) {
			FacesMessage fm = new FacesMessage("Error al eliminar la autorizacion.");
	        FacesContext.getCurrentInstance().addMessage("admini:tipo", fm);
	        FacesContext.getCurrentInstance().addMessage("administrador:tipoE", fm);
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

	public String getPais1() {
		return pais1;
	}

	public void setPais1(String pais1) {
		this.pais1 = pais1;
	}

	public String getFecha1() {
		return fecha1;
	}

	public void setFecha1(String fecha1) {
		this.fecha1 = fecha1;
	}

	public String getCiudad1() {
		return ciudad1;
	}

	public void setCiudad1(String ciudad1) {
		this.ciudad1 = ciudad1;
	}

	public String getDir1() {
		return dir1;
	}

	public void setDir1(String dir1) {
		this.dir1 = dir1;
	}

	public String getCp1() {
		return cp1;
	}

	public void setCp1(String cp1) {
		this.cp1 = cp1;
	}

	public String getPais2() {
		return pais2;
	}

	public void setPais2(String pais2) {
		this.pais2 = pais2;
	}

	public String getFecha2() {
		return fecha2;
	}

	public void setFecha2(String fecha2) {
		this.fecha2 = fecha2;
	}

	public String getCiudad2() {
		return ciudad2;
	}

	public void setCiudad2(String ciudad2) {
		this.ciudad2 = ciudad2;
	}

	public String getDir2() {
		return dir2;
	}

	public void setDir2(String dir2) {
		this.dir2 = dir2;
	}

	public String getCp2() {
		return cp2;
	}

	public void setCp2(String cp2) {
		this.cp2 = cp2;
	}
	
}
