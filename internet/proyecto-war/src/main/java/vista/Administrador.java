package vista;

import java.net.URI;
import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import es.uma.proyecto.GestionAdministrativos;
import es.uma.proyecto.GestionClientes;
import es.uma.proyecto.GestionCuentasUsuarios;
import es.uma.proyecto.GestionInformes;
import es.uma.proyecto.entidades.Cliente;
import es.uma.proyecto.entidades.CuentaReferencia;
import es.uma.proyecto.entidades.Empresa;
import es.uma.proyecto.entidades.Individual;
import es.uma.proyecto.entidades.PersonaAutorizada;
import es.uma.proyecto.entidades.Usuario;
import es.uma.proyecto.exceptions.AutorizacionException;
import es.uma.proyecto.exceptions.ClienteException;
import es.uma.proyecto.exceptions.CuentaException;
import es.uma.proyecto.exceptions.CuentaRefException;
import es.uma.proyecto.exceptions.EmpresaException;
import es.uma.proyecto.exceptions.PersonaAutorizadaException;

@Named(value = "admin")
@RequestScoped
public class Administrador {

	@EJB
	private GestionAdministrativos admin;
	@EJB
	private GestionClientes clientes;
	@EJB
	private GestionCuentasUsuarios cuentas;
    @EJB
    private GestionInformes informes;
	
	@Inject
	private InfoSesion sesion;

	private String clienteMostrado;
	//Cliente mostrado
	private String idClienteMostrado;
	private String identClienteMostrado;
	private String tipoClienteMostrado;
	private String estadoClienteMostrado;
	private String altaClienteMostrado;
	private String bajaClienteMostrado;
	private String direClienteMostrado;
	private String ciuClienteMostrado;
	private String cpClienteMostrado;
	private String paClienteMostrado;

	//Empresa mostrada
	private String razonClienteMostrado;

	//Individual
	private String nombreClienteMostrado;
	private String apellidoClienteMostrado;
	private String fechaNacClienteMostrado;

	//Cosa a mostrar
	private String cosaAmostrar;

	private Cliente cliente;
	private Usuario usuario;
	private CuentaReferencia cuentaRef;
	private PersonaAutorizada perAu;
	private String ident;
	private String iban;
	private String ibanRef;
	
	// Atributos de Cliente
	private String pais1;
	private String fecha1;
	private String ciudad1;
	private String dir1;
	private String cp1;
	
	// Atributos de Persona Autorizada
	private String fechaInicio;
	private String fechaNac;
	private String fechaFin;
	private String estadoPA;
	private String dir2;
	
	// Atributos autorizacion
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

	public GestionClientes getClientes() {
		return this.clientes;
	}

	public void setClientes(GestionClientes clientes) {
		this.clientes = clientes;
	}

	public GestionInformes getInformes() {
		return this.informes;
	}

	public void setInformes(GestionInformes informes) {
		this.informes = informes;
	}

	public String getClienteMostrado() {
		return this.clienteMostrado;
	}

	public void setClienteMostrado(String clienteMostrado) {
		this.clienteMostrado = clienteMostrado;
	}


	public void mostrarCliente(){
		try{

			Cliente client = clientes.getCliente(ident);
			if(client.getTipoCliente().equals("Individual")){
				cosaAmostrar = "Individual";
				Individual ind = clientes.getIndividual(ident);
				idClienteMostrado = String.valueOf(ind.getId());
				identClienteMostrado = ind.getIdentificacion();
				tipoClienteMostrado = ind.getTipoCliente();
				estadoClienteMostrado = ind.getEstado();
				altaClienteMostrado = ind.getFechaAlta().toString();
				if(ind.getFechaBaja()==null){
					bajaClienteMostrado = "No tiene";
				}else{
					bajaClienteMostrado = ind.getFechaBaja().toString();
				}	
				
				direClienteMostrado = ind.getDireccion();
				ciuClienteMostrado = ind.getCiudad();
				cpClienteMostrado = ind.getCodigoPostal();
				paClienteMostrado = ind.getPais();
				nombreClienteMostrado = ind.getNombre();
				apellidoClienteMostrado = ind.getApellidos();
				if(ind.getFechaNacimiento()==null){
					fechaNacClienteMostrado = "No tiene";
				}else{
					fechaNacClienteMostrado = ind.getFechaNacimiento().toString();
				}
				
				
			}else{
				cosaAmostrar = "Empresa";
				Empresa emp = clientes.getEmpresa(ident);
				idClienteMostrado = String.valueOf(emp.getId());
				identClienteMostrado = emp.getIdentificacion();
				tipoClienteMostrado = emp.getTipoCliente();
				estadoClienteMostrado = emp.getEstado();
				altaClienteMostrado = emp.getFechaAlta().toString();
				if(emp.getFechaBaja()==null){
					bajaClienteMostrado = "No tiene";
				}else{
					bajaClienteMostrado = emp.getFechaBaja().toString();
				}	
				
				direClienteMostrado = emp.getDireccion();
				ciuClienteMostrado = emp.getCiudad();
				cpClienteMostrado = emp.getCodigoPostal();
				paClienteMostrado = emp.getPais();
				razonClienteMostrado = emp.getRazonSocial();
			}
		} catch (ClienteException e){
			FacesMessage fm = new FacesMessage("No existe el cliente solicitado.");
	        FacesContext.getCurrentInstance().addMessage("administrador:identMostrar", fm);
		} catch (EmpresaException e) {
			FacesMessage fm = new FacesMessage("No existe el cliente solicitado.");
	        FacesContext.getCurrentInstance().addMessage("administrador:identMostrar", fm);
		}
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
	        FacesContext.getCurrentInstance().addMessage("administrador:identMod", fm);
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
	        FacesContext.getCurrentInstance().addMessage("admin:ibanAA", fm);
		} catch (ClienteException e) {
			FacesMessage fm = new FacesMessage("El cliente indicado no existe.");
	        FacesContext.getCurrentInstance().addMessage("admin:identAA", fm);
		}
	}
	
	public void aperturaCuentaSegreg() {
		try {
			admin.aperturaCuentaSegregada(iban, ident, cuentaRef);
		} catch (CuentaException e) {
			FacesMessage fm = new FacesMessage("La cuenta no existe o ya está tomada.");
	        FacesContext.getCurrentInstance().addMessage("admin:ibanAS", fm);
		} catch (ClienteException e) {
			FacesMessage fm = new FacesMessage("El cliente indicado no existe.");
	        FacesContext.getCurrentInstance().addMessage("admin:identAS", fm);
		}catch (CuentaRefException e) {
			FacesMessage fm = new FacesMessage("La cuenta referencia no existe.");
	        FacesContext.getCurrentInstance().addMessage("admin:ibanRef", fm);
		}
	}
	
	public void modificarAutorizado() {
		try {
			perAu = admin.getPersonaAutorizada(idPer);
		} catch (PersonaAutorizadaException e2) {
			FacesMessage fm = new FacesMessage("La persona autorizada no existe.");
	        FacesContext.getCurrentInstance().addMessage("admin:identModPer", fm);
		}
		
		if(dir2 != null) {
			perAu.setDireccion(dir2);
		}
		
		if(fechaFin != null) {
			Date fecha = null;
			perAu.setFechaFin(fecha);
		}
		
		if(fechaNac != null) {
			Date fecha = null;
			perAu.setFecha_Nacimiento(fecha);
		}
		
		if(estadoPA != null) {
			perAu.setEstado(estadoPA);
		}
		
		try {
			admin.modificarAutorizado(perAu);
		} catch (PersonaAutorizadaException e) {
			FacesMessage fm = new FacesMessage("La persona autorizada indicada no existe.");
	        FacesContext.getCurrentInstance().addMessage("admin:identModPer", fm);
		}
	}
	
	public void addAutorizado() {
		long idE = Long.parseLong(idEmp);
		long idP = Long.parseLong(idPer);
		try {
			admin.addAutorizados(idE, idP, tipo);
		} catch (ClienteException e) {
			FacesMessage fm = new FacesMessage("La empresa indicada no existe.");
	        FacesContext.getCurrentInstance().addMessage("admin:idEmpAPA", fm);
		} catch (PersonaAutorizadaException e) {
			FacesMessage fm = new FacesMessage("La persona autorizada indicada no existe.");
	        FacesContext.getCurrentInstance().addMessage("admin:idPerAPA", fm);
		} catch (AutorizacionException e) {
			FacesMessage fm = new FacesMessage("Error al crear la autorizacion.");
	        FacesContext.getCurrentInstance().addMessage("admin:tipoAPA", fm);
		}
	}

	public void eliminarAutorizado() {
		long idE = Long.parseLong(idEmp);
		long idP = Long.parseLong(idPer);
		try {
			admin.eliminarAutorizado(idE, idP);
		} catch (ClienteException e) {
			FacesMessage fm = new FacesMessage("La empresa indicada no existe.");
	        FacesContext.getCurrentInstance().addMessage("admin:idEmpE", fm);
		} catch (PersonaAutorizadaException e) {
			FacesMessage fm = new FacesMessage("La persona autorizada indicada no existe.");
	        FacesContext.getCurrentInstance().addMessage("admin:idPerE", fm);
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

	public String getIdClienteMostrado() {
		return this.idClienteMostrado;
	}

	public void setIdClienteMostrado(String idClienteMostrado) {
		this.idClienteMostrado = idClienteMostrado;
	}


	public InfoSesion getSesion() {
		return this.sesion;
	}

	public void setSesion(InfoSesion sesion) {
		this.sesion = sesion;
	}

	public String getCosaAmostrar() {
		return this.cosaAmostrar;
	}

	public void setCosaAmostrar(String cosaAmostrar) {
		this.cosaAmostrar = cosaAmostrar;
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


	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public String getIdentClienteMostrado() {
		return this.identClienteMostrado;
	}

	public void setIdentClienteMostrado(String identClienteMostrado) {
		this.identClienteMostrado = identClienteMostrado;
	}

	public String getTipoClienteMostrado() {
		return this.tipoClienteMostrado;
	}

	public void setTipoClienteMostrado(String tipoClienteMostrado) {
		this.tipoClienteMostrado = tipoClienteMostrado;
	}

	public String getEstadoClienteMostrado() {
		return this.estadoClienteMostrado;
	}

	public void setEstadoClienteMostrado(String estadoClienteMostrado) {
		this.estadoClienteMostrado = estadoClienteMostrado;
	}

	public String getAltaClienteMostrado() {
		return this.altaClienteMostrado;
	}

	public void setAltaClienteMostrado(String altaClienteMostrado) {
		this.altaClienteMostrado = altaClienteMostrado;
	}

	public String getBajaClienteMostrado() {
		return this.bajaClienteMostrado;
	}

	public void setBajaClienteMostrado(String bajaClienteMostrado) {
		this.bajaClienteMostrado = bajaClienteMostrado;
	}

	public String getDireClienteMostrado() {
		return this.direClienteMostrado;
	}

	public void setDireClienteMostrado(String direClienteMostrado) {
		this.direClienteMostrado = direClienteMostrado;
	}

	public String getCiuClienteMostrado() {
		return this.ciuClienteMostrado;
	}

	public void setCiuClienteMostrado(String ciuClienteMostrado) {
		this.ciuClienteMostrado = ciuClienteMostrado;
	}

	public String getCpClienteMostrado() {
		return this.cpClienteMostrado;
	}

	public void setCpClienteMostrado(String cpClienteMostrado) {
		this.cpClienteMostrado = cpClienteMostrado;
	}

	public String getPaClienteMostrado() {
		return this.paClienteMostrado;
	}

	public void setPaClienteMostrado(String paClienteMostrado) {
		this.paClienteMostrado = paClienteMostrado;
	}

	public String getRazonClienteMostrado() {
		return this.razonClienteMostrado;
	}

	public void setRazonClienteMostrado(String razonClienteMostrado) {
		this.razonClienteMostrado = razonClienteMostrado;
	}

	public String getNombreClienteMostrado() {
		return this.nombreClienteMostrado;
	}

	public void setNombreClienteMostrado(String nombreClienteMostrado) {
		this.nombreClienteMostrado = nombreClienteMostrado;
	}

	public String getApellidoClienteMostrado() {
		return this.apellidoClienteMostrado;
	}

	public void setApellidoClienteMostrado(String apellidoClienteMostrado) {
		this.apellidoClienteMostrado = apellidoClienteMostrado;
	}

	public String getFechaNacClienteMostrado() {
		return this.fechaNacClienteMostrado;
	}

	public void setFechaNacClienteMostrado(String fechaNacClienteMostrado) {
		this.fechaNacClienteMostrado = fechaNacClienteMostrado;
	}


	public String getDir2() {
		return dir2;
	}

	public void setDir2(String dir2) {
		this.dir2 = dir2;
	}

	public String getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getEstadoPA() {
		return estadoPA;
	}

	public void setEstadoPA(String estadoPA) {
		this.estadoPA = estadoPA;
	}

	
}
