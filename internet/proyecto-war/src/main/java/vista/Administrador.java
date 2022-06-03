package vista;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import es.uma.proyecto.GestionAutorizados;
import es.uma.proyecto.GestionCambioDivisa;
import es.uma.proyecto.GestionClientes;
import es.uma.proyecto.GestionCuentas;
import es.uma.proyecto.GestionCuentasUsuarios;
import es.uma.proyecto.GestionInformes;
import es.uma.proyecto.entidades.Autorizacion;
import es.uma.proyecto.entidades.Cliente;
import es.uma.proyecto.entidades.CuentaFintech;
import es.uma.proyecto.entidades.CuentaReferencia;
import es.uma.proyecto.entidades.DepositadaEn;
import es.uma.proyecto.entidades.Empresa;
import es.uma.proyecto.entidades.EmpresaPersAutoPK;
import es.uma.proyecto.entidades.Individual;
import es.uma.proyecto.entidades.PersonaAutorizada;
import es.uma.proyecto.entidades.PooledAccount;
import es.uma.proyecto.entidades.Segregada;
import es.uma.proyecto.entidades.Usuario;
import es.uma.proyecto.exceptions.AutorizacionException;
import es.uma.proyecto.exceptions.ClienteException;
import es.uma.proyecto.exceptions.CuentaException;
import es.uma.proyecto.exceptions.CuentaRefException;
import es.uma.proyecto.exceptions.DivisaException;
import es.uma.proyecto.exceptions.EmpresaException;
import es.uma.proyecto.exceptions.IndividualException;
import es.uma.proyecto.exceptions.PersonaAutorizadaException;
import es.uma.proyecto.exceptions.UsuarioException;

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
	@EJB
	private GestionCuentas bCuentas;
	@EJB
	private GestionAutorizados autorizados;
	@EJB
	private GestionCambioDivisa cDivisa;


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

	//Cuenta Fintech
	private String ibanCuentaMostrada;
	private String swiftCuentaMostrada;
	private String estadoCuentaMostrado;
	private String aperturaCuentaMostrado;
	private String cierreCuentaMostrado;
	private String clasificacionCuentaMostrado;

	//Pooled
	private String cuentasRefCuentaMostrada;

	//Segregada
	private String comisionCuentaMostrada;
	private String cuentaIbanCuentaMostrada;
	private String divisaRefCuentaMostrada;

	//Referencia
	private String nombreBancoCuentaMostrada;
	private String sucursalCuentaMostrada;
	private String paisCuentaMostrada;
	private String saldoCuentaMostrada;
	private String fechaRefCuentaMostrada;
	private String estadoRefCuentaMostrada;
	private String divisaCuentaMostrada;

	//Persona Autorizada
	private String idPersonaMostrar;
	private String identiPersonaMostrar;
	private String nombrePersonaMostrar;
	private String apellPersonaMostrar;
	private String direccPersonaMostrar;
	private String fechaNacPersonaMostrar;
	private String estadoPersonaMostrar;
	private String fechaIniPersonaMostrar;
	private String fechaFinPersonaMostrar;
	private String empresasPersonaMostrar;


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

	// Atributos crearCliente
	private String nombre;
	private String apellidos;
	private String fechaNacimiento;
	private String fechaBaja;
	private String pais2;
	private String fecha2;
	private String ciudad2;
	private String dir3;
	private String cp2;
	
	private String abrevDivisa;

	private String usuarioCrearCliente;

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
	
	public void mostrarCuenta(){
		try{
			CuentaFintech cuent = bCuentas.getCuentaFintech(iban);
			if(cuent.getClasificacion().equalsIgnoreCase("Segregada")){
				cosaAmostrar = "Segregada";
				Segregada seg = bCuentas.getCuentaSegregada(iban);
				ibanCuentaMostrada = seg.getIBAN();
				if(seg.getSWIFT()==null){
					swiftCuentaMostrada = "No tiene";
				}else{
					swiftCuentaMostrada = seg.getSWIFT();
				}
				if(seg.getEstado()){
					estadoCuentaMostrado = "Active";
				}else{
					estadoCuentaMostrado = "Not Active";
				}
				aperturaCuentaMostrado = seg.getFechaApertura().toString();
				if(seg.getFechaCierre()==null){
					cierreCuentaMostrado = "No tiene";
				}else{
					cierreCuentaMostrado = seg.getFechaCierre().toString();
				}
				if(seg.getComision()==null){
					comisionCuentaMostrada = "0.0";
				}else{
					comisionCuentaMostrada = String.valueOf(seg.getComision());
				}
				clasificacionCuentaMostrado = seg.getClasficicacion();
				cuentaIbanCuentaMostrada = seg.getCuentaReferencia().getIBAN();
				divisaRefCuentaMostrada = seg.getCuentaReferencia().getDivisa().getNombre();
			}else{
				cosaAmostrar = "Pooled";
				PooledAccount seg = bCuentas.getCuentaAgrupada(iban);
				ibanCuentaMostrada = seg.getIBAN();
				if(seg.getSWIFT()==null){
					swiftCuentaMostrada = "No tiene";
				}else{
					swiftCuentaMostrada = seg.getSWIFT();
				}
				if(seg.getEstado()){
					estadoCuentaMostrado = "Active";
				}else{
					estadoCuentaMostrado = "Not Active";
				}
				aperturaCuentaMostrado = seg.getFechaApertura().toString();
				if(seg.getFechaCierre()==null){
					cierreCuentaMostrado = "No tiene";
				}else{
					cierreCuentaMostrado = seg.getFechaCierre().toString();
				}
				if(!seg.getDepositadaEn().isEmpty()){
					StringBuilder sb = new StringBuilder();
					sb.append("{");
					for(DepositadaEn e : seg.getDepositadaEn()){
						sb.append(e.getCuentaReferencia().getIBAN());
						sb.append("("+e.getCuentaReferencia().getDivisa().getNombre()+")");
						sb.append(", ");
					}
					sb.delete(sb.length()-2, sb.length());
					sb.append("}");
					
				}else{
					cuentasRefCuentaMostrada ="{}";
				}
				clasificacionCuentaMostrado = seg.getClasficicacion();
				
			}
		} catch (CuentaException e){
			FacesMessage fm = new FacesMessage("La cuenta solicitada no existe o es CuentaReferencia.");
	        FacesContext.getCurrentInstance().addMessage("administrador:ibanMostrar", fm);
		} catch (NullPointerException e){
			FacesMessage fm = new FacesMessage("La cuenta solicitada no existe o es CuentaReferencia.");
	        FacesContext.getCurrentInstance().addMessage("administrador:ibanMostrar", fm);
		}
	}

	public void mostrarCuentaRef(){
		try{
			cosaAmostrar = "Referencia";
			CuentaReferencia seg = bCuentas.getCuentaReferencia(iban);
			ibanCuentaMostrada = seg.getIBAN();
			if(seg.getSWIFT()==null){
				swiftCuentaMostrada = "No tiene";
			}else{
				swiftCuentaMostrada = seg.getSWIFT();
			}
			nombreBancoCuentaMostrada = seg.getNombreBanco();
			if(seg.getSucursal()==null){
				sucursalCuentaMostrada = "No tiene";
			}else{
				sucursalCuentaMostrada = seg.getSucursal();
			}
			if(seg.getPais()==null){
				paisCuentaMostrada = "No tiene";
			}else{
				paisCuentaMostrada = seg.getPais();
			}
			saldoCuentaMostrada = String.valueOf(seg.getSaldo());
			if(seg.getFechaApertura()==null){
				fechaRefCuentaMostrada = "No tiene";
			}else{
				fechaRefCuentaMostrada = seg.getFechaApertura().toString();
			}
			
			if(seg.getEstado()!=null && seg.getEstado()){
				estadoRefCuentaMostrada = "Active";
			}else{
				estadoRefCuentaMostrada = "Not active";
			}
			divisaCuentaMostrada = seg.getDivisa().getNombre();
		} catch (CuentaException e){
			FacesMessage fm = new FacesMessage("La cuenta solicitada no existe o es CuentaReferencia.");
	        FacesContext.getCurrentInstance().addMessage("administrador:ibanMostrar", fm);
		} catch (NullPointerException e){
			FacesMessage fm = new FacesMessage("La cuenta solicitada no existe o es CuentaReferencia.");
	        FacesContext.getCurrentInstance().addMessage("administrador:ibanMostrar", fm);
		}
	}

	public void mostrarPersonaAutorizada(){
		try{
			PersonaAutorizada pers = autorizados.getPersonaAutorizada(ident);
			cosaAmostrar = "Persona";
			idPersonaMostrar = String.valueOf(pers.getID());
			identiPersonaMostrar = pers.getIdentificacion();
			nombrePersonaMostrar = pers.getNombre();
			apellPersonaMostrar = pers.getApellidos();
			direccPersonaMostrar = pers.getDireccion();
			if(pers.getFechaNacimiento()==null){
				fechaNacPersonaMostrar = "No tiene";
			}else{
				fechaNacPersonaMostrar = pers.getFechaNacimiento().toString();
			}

			if(pers.getEstado()!=null && pers.getEstado().equalsIgnoreCase("Alta")){
				estadoPersonaMostrar = "Active";
			}else{
				estadoPersonaMostrar = "Not Active";
			}

			if(pers.getFechaInicio()==null){
				fechaIniPersonaMostrar = "No tiene";
			}else{
				fechaIniPersonaMostrar = pers.getFechaInicio().toString();
			}

			if(pers.getFechaFin()==null){
				fechaFinPersonaMostrar = "No tiene";
			}else{
				fechaFinPersonaMostrar = pers.getFechaFin().toString();
			}
			StringBuilder sb = new StringBuilder();
			sb.append("{");
			for(Autorizacion e : pers.getAutorizacion()){
				sb.append(e.getEmpresa().getRazonSocial());
				sb.append("("+e.getEmpresa().getIdentificacion()+")");
				sb.append(", ");
			}
			sb.delete(sb.length()-2, sb.length());
			sb.append("}");
			empresasPersonaMostrar = sb.toString();
		}catch (PersonaAutorizadaException e){
			FacesMessage fm = new FacesMessage("La persona autorizada no existe");
	        FacesContext.getCurrentInstance().addMessage("administrador:persMostrar", fm);
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
			System.out.println(cliente.toString());
		} catch (ClienteException e1) {
		}
			
		if(ciudad1 != null && !ciudad1.equals("")) {
			cliente.setCiudad(ciudad1);
		}
		
		if(cp1 != null && !cp1.equals("")) {
			cliente.setCodigoPostal(cp1);
		}
		
		if(dir1 != null && !dir1.equals("")) {
			cliente.setDireccion(dir1);
		}
		
		if(pais1 != null && !pais1.equals("")){
			cliente.setPais(pais1);
		}
	
		if(fecha1 != null && !fecha1.equals("")) {
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
			admin.aperturaCuentaSegregada(iban, ident, bCuentas.getCuentaReferencia(ibanRef));
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
		try {
			admin.addAutorizados(clientes.getEmpresa(idEmp).getId(), autorizados.getPersonaAutorizada(idPer).getID(), tipo);
		} catch (ClienteException e) {
			FacesMessage fm = new FacesMessage("La empresa indicada no existe.");
	        FacesContext.getCurrentInstance().addMessage("admin:idEmpAPA", fm);
		} catch (PersonaAutorizadaException e) {
			FacesMessage fm = new FacesMessage("La persona autorizada indicada no existe.");
	        FacesContext.getCurrentInstance().addMessage("admin:idPerAPA", fm);
		} catch (AutorizacionException e) {
			FacesMessage fm = new FacesMessage("Error al crear la autorizacion.");
	        FacesContext.getCurrentInstance().addMessage("admin:tipoAPA", fm);
		} catch (EmpresaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void eliminarAutorizado() {
		try {
			admin.eliminarAutorizado(clientes.getEmpresa(idEmp).getId(), autorizados.getPersonaAutorizada(idPer).getID());
		} catch (ClienteException e) {
			FacesMessage fm = new FacesMessage("La empresa indicada no existe.");
	        FacesContext.getCurrentInstance().addMessage("admin:idEmpE", fm);
		} catch (PersonaAutorizadaException e) {
			FacesMessage fm = new FacesMessage("La persona autorizada indicada no existe.");
	        FacesContext.getCurrentInstance().addMessage("admin:idPerE", fm);
		} catch (AutorizacionException e) {
			FacesMessage fm = new FacesMessage("Error al eliminar la autorizacion.");
	        FacesContext.getCurrentInstance().addMessage("admini:tipo", fm);
		} catch (EmpresaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void crearCuentaReferencia(){
		CuentaReferencia ref = new CuentaReferencia();
		ref.setIBAN(ibanCuentaMostrada);
		if(!swiftCuentaMostrada.equals("")){
			ref.setSWIFT(swiftCuentaMostrada);
		}else{
			ref.setSWIFT(null);
		}
		
		ref.setNombreBanco(nombreBancoCuentaMostrada);
		if(!sucursalCuentaMostrada.equals("")){
			ref.setSucursal(sucursalCuentaMostrada);
		}else{
			ref.setSucursal(null);
		}
		if(!paisCuentaMostrada.equals("")){
			ref.setPais(paisCuentaMostrada);
		}else{
			ref.setPais(null);
		}
		
		ref.setSaldo(Double.parseDouble(saldoCuentaMostrada));
		if(!fechaRefCuentaMostrada.equals("")){
			try {
				ref.setFechaApertura(new SimpleDateFormat("yyyy-MM-dd").parse(fechaRefCuentaMostrada));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			ref.setFechaApertura(null);
		}
		
		if(estadoRefCuentaMostrada.equalsIgnoreCase("active")){
			ref.setEstado(true);
		}else{
			ref.setEstado(false);
		}

		try {
			ref.setDivisa(cDivisa.getDivisa(abrevDivisa));
		} catch (DivisaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			bCuentas.crearCuentaRef(ref);
		} catch (CuentaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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


	public GestionCuentas getBCuentas() {
		return this.bCuentas;
	}

	public void setBCuentas(GestionCuentas bCuentas) {
		this.bCuentas = bCuentas;
	}

	public String getIbanCuentaMostrada() {
		return this.ibanCuentaMostrada;
	}

	public void setIbanCuentaMostrada(String ibanCuentaMostrada) {
		this.ibanCuentaMostrada = ibanCuentaMostrada;
	}

	public String getSwiftCuentaMostrada() {
		return this.swiftCuentaMostrada;
	}

	public void setSwiftCuentaMostrada(String swiftCuentaMostrada) {
		this.swiftCuentaMostrada = swiftCuentaMostrada;
	}

	public String getEstadoCuentaMostrado() {
		return this.estadoCuentaMostrado;
	}

	public void setEstadoCuentaMostrado(String estadoCuentaMostrado) {
		this.estadoCuentaMostrado = estadoCuentaMostrado;
	}

	public String getAperturaCuentaMostrado() {
		return this.aperturaCuentaMostrado;
	}

	public void setAperturaCuentaMostrado(String aperturaCuentaMostrado) {
		this.aperturaCuentaMostrado = aperturaCuentaMostrado;
	}

	public String getCierreCuentaMostrado() {
		return this.cierreCuentaMostrado;
	}

	public void setCierreCuentaMostrado(String cierreCuentaMostrado) {
		this.cierreCuentaMostrado = cierreCuentaMostrado;
	}

	public String getClasificacionCuentaMostrado() {
		return this.clasificacionCuentaMostrado;
	}

	public void setClasificacionCuentaMostrado(String clasificacionCuentaMostrado) {
		this.clasificacionCuentaMostrado = clasificacionCuentaMostrado;
	}

	public String getCuentasRefCuentaMostrada() {
		return this.cuentasRefCuentaMostrada;
	}

	public void setCuentasRefCuentaMostrada(String cuentasRefCuentaMostrada) {
		this.cuentasRefCuentaMostrada = cuentasRefCuentaMostrada;
	}

	public String getComisionCuentaMostrada() {
		return this.comisionCuentaMostrada;
	}

	public void setComisionCuentaMostrada(String comisionCuentaMostrada) {
		this.comisionCuentaMostrada = comisionCuentaMostrada;
	}

	public String getCuentaIbanCuentaMostrada() {
		return this.cuentaIbanCuentaMostrada;
	}

	public void setCuentaIbanCuentaMostrada(String cuentaIbanCuentaMostrada) {
		this.cuentaIbanCuentaMostrada = cuentaIbanCuentaMostrada;
	}

	public String getNombreBancoCuentaMostrada() {
		return this.nombreBancoCuentaMostrada;
	}

	public void setNombreBancoCuentaMostrada(String nombreBancoCuentaMostrada) {
		this.nombreBancoCuentaMostrada = nombreBancoCuentaMostrada;
	}

	public String getSucursalCuentaMostrada() {
		return this.sucursalCuentaMostrada;
	}

	public void setSucursalCuentaMostrada(String sucursalCuentaMostrada) {
		this.sucursalCuentaMostrada = sucursalCuentaMostrada;
	}

	public String getPaisCuentaMostrada() {
		return this.paisCuentaMostrada;
	}

	public void setPaisCuentaMostrada(String paisCuentaMostrada) {
		this.paisCuentaMostrada = paisCuentaMostrada;
	}

	public String getSaldoCuentaMostrada() {
		return this.saldoCuentaMostrada;
	}

	public void setSaldoCuentaMostrada(String saldoCuentaMostrada) {
		this.saldoCuentaMostrada = saldoCuentaMostrada;
	}

	public String getFechaRefCuentaMostrada() {
		return this.fechaRefCuentaMostrada;
	}

	public void setFechaRefCuentaMostrada(String fechaRefCuentaMostrada) {
		this.fechaRefCuentaMostrada = fechaRefCuentaMostrada;
	}

	public String getEstadoRefCuentaMostrada() {
		return this.estadoRefCuentaMostrada;
	}

	public void setEstadoRefCuentaMostrada(String estadoRefCuentaMostrada) {
		this.estadoRefCuentaMostrada = estadoRefCuentaMostrada;
	}

	public String getDivisaCuentaMostrada() {
		return this.divisaCuentaMostrada;
	}

	public void setDivisaCuentaMostrada(String divisaCuentaMostrada) {
		this.divisaCuentaMostrada = divisaCuentaMostrada;
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

	public String getDivisaRefCuentaMostrada() {
		return this.divisaRefCuentaMostrada;
	}

	public void setDivisaRefCuentaMostrada(String divisaRefCuentaMostrada) {
		this.divisaRefCuentaMostrada = divisaRefCuentaMostrada;
	}


	public GestionAutorizados getAutorizados() {
		return this.autorizados;
	}

	public void setAutorizados(GestionAutorizados autorizados) {
		this.autorizados = autorizados;
	}

	public String getIdPersonaMostrar() {
		return this.idPersonaMostrar;
	}

	public void setIdPersonaMostrar(String idPersonaMostrar) {
		this.idPersonaMostrar = idPersonaMostrar;
	}

	public String getIdentiPersonaMostrar() {
		return this.identiPersonaMostrar;
	}

	public void setIdentiPersonaMostrar(String identiPersonaMostrar) {
		this.identiPersonaMostrar = identiPersonaMostrar;
	}

	public String getNombrePersonaMostrar() {
		return this.nombrePersonaMostrar;
	}

	public void setNombrePersonaMostrar(String nombrePersonaMostrar) {
		this.nombrePersonaMostrar = nombrePersonaMostrar;
	}

	public String getApellPersonaMostrar() {
		return this.apellPersonaMostrar;
	}

	public void setApellPersonaMostrar(String apellPersonaMostrar) {
		this.apellPersonaMostrar = apellPersonaMostrar;
	}

	public String getDireccPersonaMostrar() {
		return this.direccPersonaMostrar;
	}

	public void setDireccPersonaMostrar(String direccPersonaMostrar) {
		this.direccPersonaMostrar = direccPersonaMostrar;
	}

	public String getFechaNacPersonaMostrar() {
		return this.fechaNacPersonaMostrar;
	}

	public void setFechaNacPersonaMostrar(String fechaNacPersonaMostrar) {
		this.fechaNacPersonaMostrar = fechaNacPersonaMostrar;
	}

	public String getEstadoPersonaMostrar() {
		return this.estadoPersonaMostrar;
	}

	public void setEstadoPersonaMostrar(String estadoPersonaMostrar) {
		this.estadoPersonaMostrar = estadoPersonaMostrar;
	}

	public String getFechaIniPersonaMostrar() {
		return this.fechaIniPersonaMostrar;
	}

	public void setFechaIniPersonaMostrar(String fechaIniPersonaMostrar) {
		this.fechaIniPersonaMostrar = fechaIniPersonaMostrar;
	}

	public String getFechaFinPersonaMostrar() {
		return this.fechaFinPersonaMostrar;
	}

	public void setFechaFinPersonaMostrar(String fechaFinPersonaMostrar) {
		this.fechaFinPersonaMostrar = fechaFinPersonaMostrar;
	}

	public String getEmpresasPersonaMostrar() {
		return this.empresasPersonaMostrar;
	}

	public void setEmpresasPersonaMostrar(String empresasPersonaMostrar) {
		this.empresasPersonaMostrar = empresasPersonaMostrar;
	}


	public GestionCambioDivisa getCDivisa() {
		return this.cDivisa;
	}

	public void setCDivisa(GestionCambioDivisa cDivisa) {
		this.cDivisa = cDivisa;
	}

	public String getAbrevDivisa() {
		return this.abrevDivisa;
	}

	public void setAbrevDivisa(String abrevDivisa) {
		this.abrevDivisa = abrevDivisa;
	}

	public void crearClienteIndividual(){ //INCOMPLETO
		Individual ind = new Individual();
		ind.setIdentificacion(ident);
		Usuario aux = null;
		try {
			aux = cuentas.getUsuario(usuarioCrearCliente);
		} catch (UsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ind.setUsuario(aux);
		ind.setNombre(nombre);
		ind.setApellidos(apellidos);
		if(!fechaNacimiento.equals("")){
			try {
				ind.setFechaNacimiento(new SimpleDateFormat("yyyy-MM-dd").parse(fechaNacimiento));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			ind.setFechaNacimiento(null);
		}
		
		ind.setDireccion(dir3);
		ind.setCiudad(ciudad2);
		ind.setCodigoPostal(cp2);
		ind.setPais(pais2);
		if(!fechaBaja.equals("")){
			try {
				ind.setFechaBaja(new SimpleDateFormat("yyyy-MM-dd").parse(fechaBaja));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}else{
			ind.setFechaBaja(null);
		}
		ind.setEstado("Active");
		try {
			ind.setFechaAlta(new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-28"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(ind.toString());
		ind.setTipoCliente("Individual");
		try {
			clientes.crearIndividual(ind);
		} catch (IndividualException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void crearClienteEmpresa(){ //INCOMPLETO
		Empresa ind = new Empresa();
		ind.setIdentificacion(ident);
		ind.setRazonSocial(nombre);
		ind.setDireccion(dir3);
		ind.setCiudad(ciudad2);
		ind.setCodigoPostal(cp2);
		ind.setPais(pais2);
		if(!fechaBaja.equals("")){
			try {
				ind.setFechaBaja(new SimpleDateFormat("yyyy-MM-dd").parse(fechaBaja));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}else{
			ind.setFechaBaja(null);
		}
		ind.setEstado("Active");
		try {
			ind.setFechaAlta(new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-28"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(ind.toString());
		ind.setTipoCliente("Empresa");
		try {
			clientes.crearEmpresa(ind);
		} catch (EmpresaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getFechaBaja() {
		return this.fechaBaja;
	}

	public void setFechaBaja(String fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public String getUsuarioCrearCliente() {
		return this.usuarioCrearCliente;
	}

	public void setUsuarioCrearCliente(String usuarioCrearCliente) {
		this.usuarioCrearCliente = usuarioCrearCliente;
	}


	public String getPais2() {
		return this.pais2;
	}

	public void setPais2(String pais2) {
		this.pais2 = pais2;
	}

	public String getFecha2() {
		return this.fecha2;
	}

	public void setFecha2(String fecha2) {
		this.fecha2 = fecha2;
	}

	public String getCiudad2() {
		return this.ciudad2;
	}

	public void setCiudad2(String ciudad2) {
		this.ciudad2 = ciudad2;
	}

	public String getDir3() {
		return this.dir3;
	}

	public void setDir3(String dir3) {
		this.dir3 = dir3;
	}

	public String getCp2() {
		return this.cp2;
	}

	public void setCp2(String cp2) {
		this.cp2 = cp2;
	}

	public void crearAutorizado(){
		PersonaAutorizada pers = new PersonaAutorizada();
		pers.setIdentificacion(idPer);
		pers.setNombre(nombre);
		pers.setApellidos(apellidos);
		pers.setDireccion(dir2);
		if(!fechaNac.equals("")){
			try {
				pers.setFechaNacimiento(new SimpleDateFormat("yyyy-MM-dd").parse(fechaNac));
			} catch (ParseException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
		}else{
			pers.setFechaNacimiento(null);
		}
		if(!estadoPA.equals("")){
			pers.setEstado(estadoPA);
		}else{
			pers.setEstado(null);
		}
		
		if(!fechaFin.equals("")){
			try {
				pers.setFechaFin(new SimpleDateFormat("yyyy-MM-dd").parse(fechaFin));
			} catch (ParseException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
		}else{
			pers.setFechaFin(null);
		}

		try {
			pers.setFechaInicio(new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-28"));
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		try {
			autorizados.addPersonaAutorizada(pers);
		} catch (PersonaAutorizadaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			pers = autorizados.getPersonaAutorizada(idPer);
		} catch (PersonaAutorizadaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Empresa emp = null;
		try {
			emp = clientes.getEmpresa(idEmp);
		} catch (EmpresaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			admin.addAutorizados(emp.getId(), pers.getID(), tipo);
		} catch (ClienteException | PersonaAutorizadaException | AutorizacionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
