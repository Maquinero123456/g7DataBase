package es.uma.proyecto;

import javax.ejb.Local;

import es.uma.proyecto.entidades.Cuenta;
import es.uma.proyecto.entidades.CuentaReferencia;
import es.uma.proyecto.entidades.PooledAccount;
import es.uma.proyecto.entidades.Segregada;
import es.uma.proyecto.exceptions.CuentaException;

@Local
public interface GestionCuentas {
    public void setCuenta()throws CuentaException;
    public Cuenta getCuenta(String iban) throws CuentaException;
    public Segregada getCuentaSegregada(String iban) throws CuentaException;
    public PooledAccount getCuentaAgrupada(String iban) throws CuentaException;
    public CuentaReferencia getCuentaReferencia(String iban) throws CuentaException;
    public void crearCuenta(String iban)throws CuentaException;
}
