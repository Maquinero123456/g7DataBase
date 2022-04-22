package es.uma.proyecto;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(PACCOUNT_CUENTAREF_FK.class)
@Table(name = "DEPOSITADA_EN")
public class DEPOSITADA_EN {
    
    @Id
    private CUENTA_REFERENCIA cuentaReferencia;

    @Id
    private POOLED_ACCOUNT pooledAccount;
    @Column(nullable = false)
    private Double saldo;


    public DEPOSITADA_EN() {
    }

    public DEPOSITADA_EN(CUENTA_REFERENCIA cr, POOLED_ACCOUNT pa, Double saldo){
        this.cuentaReferencia = cr;
        this.pooledAccount = pa;
        this.saldo = saldo;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double sld) {
        saldo = sld;
    }


    public CUENTA_REFERENCIA getCuentaReferencia() {
		return cuentaReferencia;
	}

	public POOLED_ACCOUNT getPooledAccount() {
		return pooledAccount;
	}

	@Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof DEPOSITADA_EN)) {
            return false;
        }
        DEPOSITADA_EN dep = (DEPOSITADA_EN) o;
        return Objects.equals(this.pooledAccount, dep.getPooledAccount()) && Objects.equals(this.cuentaReferencia, dep.getCuentaReferencia());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCuentaReferencia(), getPooledAccount());
    }
    
	@Override
	public String toString() {
		return "DEPOSITADA_EN [cuentaReferencia=" + cuentaReferencia + ", pooledAccount=" + pooledAccount
				+ ", saldo=" + saldo + "]";
	}
}