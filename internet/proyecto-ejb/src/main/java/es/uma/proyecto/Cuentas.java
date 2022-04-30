package es.uma.proyecto;

import es.uma.proyecto.entidades.Cliente;
import es.uma.proyecto.entidades.Cuenta;
import es.uma.proyecto.exceptions.ClienteException;
import es.uma.proyecto.exceptions.CuentaException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.logging.Logger;

public class Cuentas implements GestionCuentas{

    private static final Logger LOG = Logger.getLogger(Clientes.class.getCanonicalName());

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
}
