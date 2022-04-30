package es.uma.proyecto;

import javax.ejb.Local;

import es.uma.proyecto.entidades.Cuenta;
import es.uma.proyecto.exceptions.CuentaException;

@Local
public interface GestionCuentas {
    public void setCuenta()throws CuentaException;
    public Cuenta getCuenta(String iban) throws CuentaException;
}
