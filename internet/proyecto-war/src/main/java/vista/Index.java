package vista;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.proyecto.GestionCambioDivisa;
import es.uma.proyecto.GestionClientes;
import es.uma.proyecto.GestionCuentas;
import es.uma.proyecto.GestionTransaccion;
import es.uma.proyecto.entidades.Divisa;
import es.uma.proyecto.entidades.PooledAccount;
import es.uma.proyecto.entidades.Usuario;
import es.uma.proyecto.exceptions.ClienteException;
import es.uma.proyecto.exceptions.CuentaException;
import es.uma.proyecto.exceptions.CuentaReferenciaException;
import es.uma.proyecto.exceptions.DivisaException;
import es.uma.proyecto.exceptions.IndividualException;
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

}