package es.uma.proyecto;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "DEPOSITADAEN")
public class DepositadaEn{
    
    @EmbeddedId
    private CuentaRefPoolAccPK fk;

    @ManyToOne
    @MapsId("cuentaReferencia")
    private CuentaReferencia cuentaReferencia;

    @ManyToOne
    @MapsId("pooledAccount")
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


    public CuentaRefPoolAccPK getFk() {
        return this.fk;
    }

    public CuentaReferencia getCuentaReferencia() {
        return this.cuentaReferencia;
    }

    public void setCuentaReferencia(CuentaReferencia cuentaReferencia) {
        this.cuentaReferencia = cuentaReferencia;
    }

    public PooledAccount getPooledAccount() {
        return this.pooledAccount;
    }

    public void setPooledAccount(PooledAccount pooledAccount) {
        this.pooledAccount = pooledAccount;
    }

    
	@Override
	public String toString() {
		return "DepositadaEn [cuentaReferencia=" + cuentaReferencia + ", pooledAccount=" + pooledAccount
				+ ", saldo=" + saldo + "]";
	}
}