package es.uma.proyecto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DEPOSITADA_EN")
public class DEPOSITADA_EN {
    
    @Id
    @ManyToOne
    private CUENTA_REFERENCIA cuentaReferencia;

    @Id
    @ManyToOne
    private POOLED_ACCOUNT pooledAccount;
    @Column(nullable = false)
    private Double saldo;


    public DEPOSITADA_EN() {
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double sld) {
        saldo = sld;
    }


    
	@Override
	public String toString() {
		return "DEPOSITADA_EN [cuentaReferencia=" + cuentaReferencia + ", pooledAccount=" + pooledAccount
				+ ", saldo=" + saldo + "]";
	}
}