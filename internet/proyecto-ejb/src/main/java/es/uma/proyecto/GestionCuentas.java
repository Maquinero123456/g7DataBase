package es.uma.proyecto;

import es.uma.proyecto.entidades.Cuenta;
import es.uma.proyecto.exceptions.CuentaException;

public interface GestionCuentas {
    public void setCuenta()throws CuentaException;
    public Cuenta getCuenta(String iban) throws CuentaException;
}
