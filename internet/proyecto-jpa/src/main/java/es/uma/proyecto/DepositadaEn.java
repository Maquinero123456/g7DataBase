package es.uma.proyecto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DepositadaEn")
public class DepositadaEn {
    
    @Id
    @ManyToOne
    private CuentaReferencia cuentaReferencia;

    @Id
    @ManyToOne
    private PooledAccount pooledAccount;
    @Column(nullable = false)
    private Double saldo;


    public DepositadaEn() {
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double sld) {
        saldo = sld;
    }


    
	@Override
	public String toString() {
		return "DepositadaEn [cuentaReferencia=" + cuentaReferencia + ", pooledAccount=" + pooledAccount
				+ ", saldo=" + saldo + "]";
	}
}