package es.uma.proyecto;

import es.uma.proyecto.exceptions.SaldoException;

import javax.ejb.Local;

import es.uma.proyecto.exceptions.CuentaReferenciaException;
import es.uma.proyecto.exceptions.DivisaException;
import es.uma.proyecto.exceptions.PooledAccountException;

@Local
public interface GestionCambioDivisa {
    /**
     * La aplicación permitirá a un cliente/autorizado realizar un cambio de divisas en una cuenta agrupada (pooled).
     * El cambio de divisas se considerará una transacción especial donde el origen y destino es la misma cuenta. 
     * Para poder realizar un cambio de divisas será necesario que la cuenta tenga saldos en las divisas de origen y destino.
     * Los saldos de las cuentas asociadas con la cuenta agrupada deberá actualizarse también. No será posible realizar un cambio de divisas en cuentas segregadas.
     * @throws CuentaReferenciaException
     */
    public void cambioDivisas(PooledAccount pooled, Divisa original, Divisa nueva, Double cantidadDeseada) throws PooledAccountException, DivisaException, SaldoException, CuentaReferenciaException, CuentaReferenciaException;
}
