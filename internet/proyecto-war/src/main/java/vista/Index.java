package vista;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import es.uma.proyecto.GestionCambioDivisa;
import es.uma.proyecto.GestionClientes;
import es.uma.proyecto.GestionCuentas;
import es.uma.proyecto.entidades.Divisa;
import es.uma.proyecto.entidades.PooledAccount;
import es.uma.proyecto.exceptions.CuentaException;
import es.uma.proyecto.exceptions.CuentaReferenciaException;
import es.uma.proyecto.exceptions.DivisaException;
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
    
    private String iban;
    private String ori;
    private String nue;
    private String cantidad;

    public void cambiarDivisas(){
        PooledAccount pool=null;
        Divisa original=null;
        Divisa nueva=null;
        
        try{
            pool = cuentas.getCuentaAgrupada(iban);
            original = cambioDivisa.getDivisa(ori);
            nueva = cambioDivisa.getDivisa(nue);
        } catch (CuentaException e){
        	FacesMessage fm = new FacesMessage("No existe la cuenta indicada.");
	        FacesContext.getCurrentInstance().addMessage("index:iban", fm);
        } catch (DivisaException e) {
        	FacesMessage fm = new FacesMessage("Error en la divisa: "+ e);
	        FacesContext.getCurrentInstance().addMessage(null, fm);
        }
        
        try{
        	Double cant = Double.parseDouble(cantidad);
            cambioDivisa.cambioDivisas(pool, original, nueva, cant);
        } catch (PooledAccountException e){
			FacesMessage fm = new FacesMessage("Error con la cuenta agrupada indicada.");
	        FacesContext.getCurrentInstance().addMessage("index:"+pool.getIBAN(), fm);
        } catch (DivisaException e) {
			FacesMessage fm = new FacesMessage("Error con la divisa: "+e);
	        FacesContext.getCurrentInstance().addMessage(null, fm);
        } catch (SaldoException e)  {
        	FacesMessage fm = new FacesMessage("Error durante la transaccion del saldo: "+e);
	        FacesContext.getCurrentInstance().addMessage("index:cantidad", fm);
        } catch (CuentaReferenciaException e) {
        	FacesMessage fm = new FacesMessage("Error con la cuenta referencia: "+e);
	        FacesContext.getCurrentInstance().addMessage(null, fm);
        } catch (NullPointerException e){
        	FacesMessage fm = new FacesMessage("Error: "+e);
	        FacesContext.getCurrentInstance().addMessage(null, fm);
        }
    }

}