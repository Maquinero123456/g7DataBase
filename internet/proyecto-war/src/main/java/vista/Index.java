package vista;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.proyecto.GestionAutorizados;
import es.uma.proyecto.GestionCambioDivisa;
import es.uma.proyecto.GestionClientes;
import es.uma.proyecto.GestionCuentas;
import es.uma.proyecto.GestionTransaccion;
import es.uma.proyecto.entidades.CuentaFintech;
import es.uma.proyecto.entidades.CuentaReferencia;
import es.uma.proyecto.entidades.DepositadaEn;
import es.uma.proyecto.entidades.Divisa;
import es.uma.proyecto.entidades.PooledAccount;
import es.uma.proyecto.entidades.Segregada;
import es.uma.proyecto.entidades.Usuario;
import es.uma.proyecto.exceptions.ClienteException;
import es.uma.proyecto.exceptions.CuentaException;
import es.uma.proyecto.exceptions.CuentaReferenciaException;
import es.uma.proyecto.exceptions.DivisaException;
import es.uma.proyecto.exceptions.IndividualException;
import es.uma.proyecto.exceptions.PersonaAutorizadaException;
import es.uma.proyecto.exceptions.PooledAccountException;
import es.uma.proyecto.exceptions.SaldoException;

@Named(value = "index")
@RequestScoped
public class Index {
    
    @EJB
    private GestionCambioDivisa cambioDivisa;
    @EJB
    private GestionCuentas cuentas;
    @EJB
    private GestionClientes clientes;
    @EJB
    private GestionTransaccion trans;
	@EJB
	private GestionAutorizados autori;

	@Inject
	private InfoSesion sesion;

    private Usuario usuario;
    
    private String ibanOrigen;
    private String ibanDestino;
    private String ori;
    private String nue;
    private String cantidad;
    private String ident;
    private String tipo;

    public Index() {
    }
    
    public int entrada() {
    	int aux = 0;
    	
    	if(sesion.getUsuario() == null){
    		return aux;
    	}
    	
    	if(!sesion.getUsuario().getEsAdministrativo() && sesion.getUsuario().getCliente().getTipoCliente().equalsIgnoreCase("individual")) {
    		aux = 1;
    	}
    	
    	if(!sesion.getUsuario().getEsAdministrativo() && sesion.getUsuario().getCliente().getTipoCliente().equalsIgnoreCase("empresa")) {
    		aux = 2;
    	}
    	
    	return aux;
    }
    
	public List<vistaCuentas> retrieveCuentas(){
		List<vistaCuentas> cuentasBancarias = new ArrayList<>();
		List<CuentaFintech> aux = new ArrayList<>();
		if(sesion.getUsuario().getPersonaAutorizada()!=null){
			try {
				aux.addAll(autori.getCuentasPersonaAutorizada(sesion.getUsuario().getPersonaAutorizada().getIdentificacion()));
			} catch (PersonaAutorizadaException | CuentaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if(sesion.getUsuario().getCliente()!=null){
			aux.addAll(sesion.getUsuario().getCliente().getCuentas());
		}

		for(CuentaFintech e : aux){
			if(e.getClasificacion().equals("Pooled")){
				vistaCuentas aux2 = new vistaCuentas();
				PooledAccount pool = null;
				try{
					pool = cuentas.getCuentaAgrupada(e.getIBAN());
				} catch (CuentaException e1){

				}

				for(DepositadaEn a : pool.getDepositadaEn()){
					CuentaReferencia ref = a.getCuentaReferencia();
					aux2.setIban(pool.getIBAN());
					aux2.setSwift(pool.getSWIFT());
					if(pool.getEstado()){
						aux2.setEstado("Active");
					}else{
						aux2.setEstado("Not active");
					}
					aux2.setFechaApertura(pool.getFechaApertura().toString());
					aux2.setFechaCierre(pool.getFechaCierre().toString());
					aux2.setClasificacion(pool.getClasficicacion());
					aux2.setIbanRef(ref.getIBAN());
					aux2.setNombreBanco(ref.getNombreBanco());
					aux2.setSucursal(ref.getSucursal());
					aux2.setPais(ref.getPais());
					aux2.setSaldo(ref.getSaldo());
					aux2.setFechaAperturaRef(ref.getFechaApertura().toString());
					if(ref.getEstado()){
						aux2.setEstadoRef("Active");
					}else{
						aux2.setEstadoRef("Not active");
					}
					aux2.setDivisa(ref.getDivisa().getNombre());
					cuentasBancarias.add(aux2);

				}
			}else{
				vistaCuentas aux2 = new vistaCuentas();
				Segregada seg = null;
				try {
					seg = cuentas.getCuentaSegregada(e.getIBAN());
				} catch (CuentaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				aux2.setIban(seg.getIBAN());
				aux2.setSwift(seg.getSWIFT());
				if(seg.getEstado()){
					aux2.setEstado("Active");
				}else{
					aux2.setEstado("Not active");
				}
				aux2.setFechaApertura(seg.getFechaApertura().toString());
				aux2.setFechaCierre(seg.getFechaCierre().toString());
				aux2.setClasificacion(seg.getClasficicacion());
				aux2.setComision(seg.getComision());
				CuentaReferencia ref = seg.getCuentaReferencia();
				aux2.setIbanRef(ref.getIBAN());
				aux2.setNombreBanco(ref.getNombreBanco());
				aux2.setSucursal(ref.getSucursal());
				aux2.setPais(ref.getPais());
				aux2.setSaldo(ref.getSaldo());
				aux2.setFechaAperturaRef(ref.getFechaApertura().toString());
				if(ref.getEstado()){
					aux2.setEstadoRef("Active");
				}else{
					aux2.setEstadoRef("Not active");
				}
				aux2.setDivisa(ref.getDivisa().getNombre());
				cuentasBancarias.add(aux2);
			}
		}


		return cuentasBancarias;
	}


    public void transaccionIndividual() {
    	double cant = Double.parseDouble(cantidad);
    	
    	try {
			trans.transaccionIndividual(usuario.getCliente().getIdentificacion(), ibanOrigen, ibanDestino, cant, tipo);
		} catch (ClienteException e) {
			FacesMessage fm = new FacesMessage("El cliente indicado no existe o no es individual.");
            FacesContext.getCurrentInstance().addMessage(null, fm);
		} catch (CuentaException e) {
			FacesMessage fm = new FacesMessage("Error en la cuenta: "+e);
            FacesContext.getCurrentInstance().addMessage("index:ibanOriI", fm);
		} catch (IndividualException e) {
			FacesMessage fm = new FacesMessage("Error en la cuenta individual: "+e);
            FacesContext.getCurrentInstance().addMessage("index:ibanOriI", fm);
		} catch (SaldoException e) {
			FacesMessage fm = new FacesMessage("Error en el saldo: "+e);
            FacesContext.getCurrentInstance().addMessage("index:cantI", fm);
		}
    }
    
    public void transaccionAutorizado() {
    	double cant = Double.parseDouble(cantidad);
    	
    	try {
			trans.transaccionAutorizado(usuario.getCliente().getIdentificacion(), ibanOrigen, ibanDestino, cant, tipo);
		} catch (ClienteException e) {
			FacesMessage fm = new FacesMessage("El cliente indicado no existe o no es individual.");
            FacesContext.getCurrentInstance().addMessage(null, fm);
		} catch (CuentaException e) {
			FacesMessage fm = new FacesMessage("Error en la cuenta: "+e);
            FacesContext.getCurrentInstance().addMessage("index:ibanOriPA", fm);
		} catch (IndividualException e) {
			FacesMessage fm = new FacesMessage("Error en la cuenta individual: "+e);
            FacesContext.getCurrentInstance().addMessage("index:ibanOriPA", fm);
		} catch (SaldoException e) {
			FacesMessage fm = new FacesMessage("Error en el saldo: "+e);
            FacesContext.getCurrentInstance().addMessage("index:cantPA", fm);
		}
    }
    
    public void cambiarDivisas(){
        PooledAccount pool=null;
        Divisa original=null;
        Divisa nueva=null;
        
        try{
            pool = cuentas.getCuentaAgrupada(ibanOrigen);
            original = cambioDivisa.getDivisa(ori);
            nueva = cambioDivisa.getDivisa(nue);
        } catch (CuentaException e){
        	FacesMessage fm = new FacesMessage("No existe la cuenta indicada.");
	        FacesContext.getCurrentInstance().addMessage("index:ibanCDD", fm);
        } catch (DivisaException e) {
        	FacesMessage fm = new FacesMessage("Error en la divisa: "+ e);
	        FacesContext.getCurrentInstance().addMessage("index:oriCDD", fm);
        }
        
        try{
        	double cant = Double.parseDouble(cantidad);
            cambioDivisa.cambioDivisas(pool, original, nueva, cant);
        } catch (PooledAccountException e){
			FacesMessage fm = new FacesMessage("Error con la cuenta agrupada indicada.");
	        FacesContext.getCurrentInstance().addMessage("index:ibanCDD", fm);
        } catch (DivisaException e) {
			FacesMessage fm = new FacesMessage("Error con la divisa: "+e);
	        FacesContext.getCurrentInstance().addMessage("index:nueCDD", fm);
        } catch (SaldoException e)  {
        	FacesMessage fm = new FacesMessage("Error durante la transaccion del saldo: "+e);
	        FacesContext.getCurrentInstance().addMessage("index:cantiCDD", fm);
        } catch (CuentaReferenciaException e) {
        	FacesMessage fm = new FacesMessage("Error con la cuenta referencia: "+e);
	        FacesContext.getCurrentInstance().addMessage("index:ibanCDD", fm);
        } catch (NullPointerException e){
        	FacesMessage fm = new FacesMessage("Error: "+e);
	        FacesContext.getCurrentInstance().addMessage(null, fm);
        }
        
    }

	public GestionCambioDivisa getCambioDivisa() {
		return cambioDivisa;
	}

	public void setCambioDivisa(GestionCambioDivisa cambioDivisa) {
		this.cambioDivisa = cambioDivisa;
	}

	public GestionCuentas getCuentas() {
		return cuentas;
	}

	public void setCuentas(GestionCuentas cuentas) {
		this.cuentas = cuentas;
	}

	public GestionClientes getClientes() {
		return clientes;
	}

	public void setClientes(GestionClientes clientes) {
		this.clientes = clientes;
	}

	public GestionTransaccion getTrans() {
		return trans;
	}

	public void setTrans(GestionTransaccion trans) {
		this.trans = trans;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getIbanOrigen() {
		return ibanOrigen;
	}

	public void setIbanOrigen(String iban) {
		this.ibanOrigen = iban;
	}
	
	public String getIbanDestino() {
		return ibanDestino;
	}

	public void setIbanDestino(String iban) {
		this.ibanDestino = iban;
	}


	public String getOri() {
		return ori;
	}

	public void setOri(String ori) {
		this.ori = ori;
	}

	public String getNue() {
		return nue;
	}

	public void setNue(String nue) {
		this.nue = nue;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public InfoSesion getSesion() {
		return sesion;
	}

	public void setSesion(InfoSesion sesion) {
		this.sesion = sesion;
	}    

}