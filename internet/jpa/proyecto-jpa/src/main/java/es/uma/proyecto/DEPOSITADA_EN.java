package es.uma.proyecto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DEPOSITADA_EN")
public class DEPOSITADA_EN {
    @Id
    private Double Saldo;


    public Double getSaldo() {
        return Saldo;
    }

    public void setSaldo(Double saldo) {
        Saldo = saldo;
    }
}