package es.uma.proyecto;

import es.uma.proyecto.entidades.Cliente;
import es.uma.proyecto.entidades.Cuenta;
import es.uma.proyecto.entidades.CuentaReferencia;
import es.uma.proyecto.entidades.PooledAccount;
import es.uma.proyecto.entidades.Segregada;
import es.uma.proyecto.exceptions.ClienteException;
import es.uma.proyecto.exceptions.CuentaException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.logging.Logger;

@Stateless
public class Cuentas implements GestionCuentas{

    private static final Logger LOG = Logger.getLogger(Cuentas.class.getCanonicalName());

    @PersistenceContext(name="proyectoEJB")
    private EntityManager em;

    @Override
    public void setCuenta() throws CuentaException {

    }

    @Override
    public Cuenta getCuenta(String iban) throws CuentaException {
        Cuenta ac = em.find(Cuenta.class, iban);
        if(ac == null){
            throw new CuentaException("No existe la cuenta");
        }
        return ac;
    }

    @Override
    public Segregada getCuentaSegregada(String iban) throws CuentaException {
        Segregada ac = em.find(Segregada.class, iban);
        if(ac == null){
            throw new CuentaException("No existe la cuenta");
        }
        return ac;
    }

    @Override
    public PooledAccount getCuentaAgrupada(String iban) throws CuentaException {
        PooledAccount ac = em.find(PooledAccount.class, iban);
        if(ac == null){
            throw new CuentaException("No existe la cuenta");
        }
        return ac;
    }

    @Override
    public CuentaReferencia getCuentaReferencia(String iban) throws CuentaException{
        CuentaReferencia ac = em.find(CuentaReferencia.class, iban);
        if(ac == null){
            throw new CuentaException("No existe la cuenta");
        }
        return ac;
    }
}
