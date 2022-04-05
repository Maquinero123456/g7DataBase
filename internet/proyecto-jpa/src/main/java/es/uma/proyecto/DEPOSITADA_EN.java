package es.uma.proyecto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(PACCOUNT_CUENTAREF_FK.class)
@Table(name = "DEPOSITADA_EN")
public class DEPOSITADA_EN {
    
    @Id
    private CUENTA_REFERENCIA cuenta_referencia;

    @Id
    private POOLED_ACCOUNT pooled_account;

    private Double Saldo;


    public Double getSaldo() {
        return Saldo;
    }

    public void setSaldo(Double saldo) {
        Saldo = saldo;
    }
}