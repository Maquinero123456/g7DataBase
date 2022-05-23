package vista;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import es.uma.proyecto.GestionCambioDivisa;
import es.uma.proyecto.GestionCuentas;
import es.uma.proyecto.entidades.Divisa;
import es.uma.proyecto.entidades.PooledAccount;
import es.uma.proyecto.exceptions.CuentaException;
import es.uma.proyecto.exceptions.CuentaReferenciaException;
import es.uma.proyecto.exceptions.DivisaException;
import es.uma.proyecto.exceptions.PooledAccountException;
import es.uma.proyecto.exceptions.SaldoException;



@Named(value = "cambioDivisa")
@RequestScoped
public class CambioDivisa {
    
    @EJB
    private GestionCambioDivisa cambioDivisa;
    @EJB
    private GestionCuentas cuentas;


    public void cambiarDivisas(String iban, String ori, String nue, Double cantidad){
        PooledAccount pool=null;
        Divisa original=null;
        Divisa nueva=null;
        
        try{
            pool = cuentas.getCuentaAgrupada(iban);
            original = cambioDivisa.getDivisa(ori);
            nueva = cambioDivisa.getDivisa(nue);
        } catch (CuentaException e){

        } catch (DivisaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        try{
            cambioDivisa.cambioDivisas(pool, original, nueva, cantidad);
        } catch (PooledAccountException e){

        } catch (DivisaException e) {

        } catch (SaldoException e)  {

        } catch (CuentaReferenciaException e) {

        }catch (NullPointerException e){

        }



    }

}
