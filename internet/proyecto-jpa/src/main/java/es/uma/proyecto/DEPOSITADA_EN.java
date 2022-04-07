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
    private CUENTA_REFERENCIA cuenta_referencia;

    @Id
    private POOLED_ACCOUNT pooled_account;
    @Column(nullable = false)
    private Double Saldo;


    public DEPOSITADA_EN() {
    }

    public DEPOSITADA_EN(CUENTA_REFERENCIA cr, POOLED_ACCOUNT pa, Double saldo){
        this.cuenta_referencia = cr;
        this.pooled_account = pa;
        this.Saldo = saldo;
    }

    public Double getSaldo() {
        return Saldo;
    }

    public void setSaldo(Double saldo) {
        Saldo = saldo;
    }


    public CUENTA_REFERENCIA getCuenta_referencia() {
		return cuenta_referencia;
	}

	public POOLED_ACCOUNT getPooled_account() {
		return pooled_account;
	}

	@Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof DEPOSITADA_EN)) {
            return false;
        }
        DEPOSITADA_EN dep = (DEPOSITADA_EN) o;
        return Objects.equals(this.pooled_account, dep.getPooled_account()) && Objects.equals(this.cuenta_referencia, dep.getCuenta_referencia());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCuenta_referencia(), getPooled_account());
    }
    
	@Override
	public String toString() {
		return "DEPOSITADA_EN [cuenta_referencia=" + cuenta_referencia + ", pooled_account=" + pooled_account
				+ ", Saldo=" + Saldo + "]";
	}
}