package es.uma.proyecto;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CuentaRefPoolAccPK implements Serializable{
    @Column(name = "cuentaReferencia_pk")
    private String cuentaReferencia;
    @Column(name = "pooledAccount_pk")
    private String pooledAccount;


    public CuentaRefPoolAccPK() {
    }


    public String getCuentaReferencia() {
        return this.cuentaReferencia;
    }

    public void setCuentaReferencia(String cuentaReferencia) {
        this.cuentaReferencia = cuentaReferencia;
    }

    public String getPooledAccount() {
        return this.pooledAccount;
    }

    public void setPooledAccount(String pooledAccount) {
        this.pooledAccount = pooledAccount;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CuentaRefPoolAccPK)) {
            return false;
        }
        CuentaRefPoolAccPK cuentaRefPoolAccPK = (CuentaRefPoolAccPK) o;
        return Objects.equals(cuentaReferencia, cuentaRefPoolAccPK.cuentaReferencia) && Objects.equals(pooledAccount, cuentaRefPoolAccPK.pooledAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cuentaReferencia, pooledAccount);
    }


    @Override
    public String toString() {
        return "{" +
            " cuentaReferencia='" + getCuentaReferencia() + "'" +
            ", pooledAccount='" + getPooledAccount() + "'" +
            "}";
    }

}