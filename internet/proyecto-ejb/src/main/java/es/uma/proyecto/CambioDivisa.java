package es.uma.proyecto;

import java.util.Date;
import java.util.Set;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uma.proyecto.entidades.CuentaReferencia;
import es.uma.proyecto.entidades.DepositadaEn;
import es.uma.proyecto.entidades.Divisa;
import es.uma.proyecto.entidades.PooledAccount;
import es.uma.proyecto.entidades.Transaccion;
import es.uma.proyecto.exceptions.CuentaReferenciaException;
import es.uma.proyecto.exceptions.DivisaException;
import es.uma.proyecto.exceptions.PooledAccountException;
import es.uma.proyecto.exceptions.SaldoException;

@Stateless
public class CambioDivisa implements GestionCambioDivisa{
    @SuppressWarnings("unused")
    private static final Logger LOG = Logger.getLogger(CambioDivisa.class.getCanonicalName());

    @PersistenceContext(name="proyectoEJB")
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
            if(a.getDivisa().equals(original)){
                depositadaOriginal = e;
                referenciaOriginal = a;
            }else if(a.getDivisa().equals(divisa)){
                depositadaCambio = e;
                referenciaCambio = a;
            }
        }
        //Si alguna de las dos no existe lanzo la excepcion
        if(referenciaOriginal== null || referenciaCambio == null){
            throw new CuentaReferenciaException("No existe alguna de las cuentas referencia");
        }
        //Si el saldo en la cuenta original no es suficiente, lanzo la excepcion
        if(cantidad>depositadaOriginal.getSaldo()){
            throw new SaldoException("No hay saldo suficiente");
        }
        //Actualizo el saldo en depositadaEn y CuentaReferencia que es igual a la divisa original
        depositadaOriginal.setSaldo(depositadaOriginal.getSaldo()-cantidad);
        referenciaOriginal.setSaldo(referenciaOriginal.getSaldo()-cantidad);
        em.merge(depositadaOriginal);
        em.merge(referenciaOriginal);
        //Actualiza el saldo en las cuentas con la divisa a la que quiero cambiar
        depositadaCambio.setSaldo(depositadaCambio.getSaldo()+cantidad*original.getCambioEuro()/divisa.getCambioEuro());
        referenciaCambio.setSaldo(referenciaCambio.getSaldo()+cantidad*original.getCambioEuro()/divisa.getCambioEuro());
        em.merge(depositadaCambio);
        em.merge(referenciaCambio);
        //Consigo la fecha de hoy
        Date utilDate = new Date(System.currentTimeMillis());
        //Creo una nueva transaccion y la meto en la base de datos
        Transaccion transaccion = new Transaccion(utilDate, cantidad, "Cambio divisa", pooled, pooled, original, divisa);
        em.persist(transaccion);
    }

    public Divisa getDivisa(String abrev) throws DivisaException {
        Divisa div = em.find(Divisa.class, abrev);
        if(div == null){
            throw new DivisaException("No existe la divisa");
        }
        return div;
    }
    
}
