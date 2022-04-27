package es.uma.proyecto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uma.proyecto.exceptions.CuentaReferenciaException;
import es.uma.proyecto.exceptions.DivisaException;
import es.uma.proyecto.exceptions.PooledAccountException;
import es.uma.proyecto.exceptions.SaldoException;

public class CambioDivisa implements GestionCambioDivisa{

    private static final Logger LOG = Logger.getLogger(CambioDivisa.class.getCanonicalName());

    @PersistenceContext(name="Cache")
    private EntityManager em;

    @Override
    public void cambioDivisas(PooledAccount pooled, Divisa original, Divisa divisa, Double cantidad)
            throws PooledAccountException, DivisaException, SaldoException, CuentaReferenciaException{
        //Consigo los depositadaEn relacionadas con la cuenta
        Set<DepositadaEn> depositada = pooled.getDepositadaEn();
        DepositadaEn depositadaOriginal = null, depositadaCambio = null;
        CuentaReferencia referenciaOriginal = null, referenciaCambio = null;

        //Busco las cuentas referencia que tengan la divisa original y la divisa a la que quiero cambiar
        for(DepositadaEn e: depositada){
            CuentaReferencia a = em.find(CuentaReferencia.class, e.getFk().getCuentaReferencia());
            if(a == null){
                throw new CuentaReferenciaException("Cuenta no existe");
            }
            if(a.getDivisa()==original){
                depositadaOriginal = e;
                referenciaOriginal = a;
            }else if(a.getDivisa()==divisa){
                depositadaCambio = e;
                referenciaCambio = a;
            }
        }
        //Si alguna de las dos no existe lanzo la excepcion
        if(referenciaOriginal== null || referenciaCambio == null){
            throw new CuentaReferenciaException("No existe alguna de las cuentas referencia");
        }
        //Si el saldo en la cuenta original no es suficiente, lanzo la excepcion
        if(cantidad*divisa.getCambioEuro()>depositadaOriginal.getSaldo()*original.getCambioEuro()){
            throw new SaldoException("No hay saldo suficiente");
        }
        //Actualizo el saldo en depositadaEn y CuentaReferencia que es igual a la divisa original
        Double saldo = cantidad*divisa.getCambioEuro()/original.getCambioEuro()-depositadaOriginal.getSaldo();
        depositadaOriginal.setSaldo(saldo);
        referenciaOriginal.setSaldo(saldo);
        //Actualiza el saldo en las cuentas con la divisa a la que quiero cambiar
        depositadaCambio.setSaldo(depositadaCambio.getSaldo()+cantidad);
        referenciaCambio.setSaldo(referenciaCambio.getSaldo()+cantidad);
        //Consigo la fecha de hoy
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        //Creo una nueva transaccion y la meto en la base de datos
        Transaccion transaccion = new Transaccion(date, cantidad, "Cambio divisa", pooled, pooled, original, divisa);
        em.persist(transaccion);
    }
    
}
