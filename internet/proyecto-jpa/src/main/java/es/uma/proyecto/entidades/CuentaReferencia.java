package es.uma.proyecto.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="CUENTAREFERENCIA")
public class CuentaReferencia extends Cuenta {

    @Column(nullable = false)
    private String nombreBanco;
    private String sucursal;
    private String pais;
    @Column(nullable = false)
    private Double saldo;
    @Temporal(TemporalType.DATE)
    private Date fechaApertura;
    private Boolean estado;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Divisa divisa;

    @OneToMany(mappedBy = "cuentaReferencia")
    private Set<DepositadaEn> depositadaEn = new HashSet<DepositadaEn>();

    @OneToOne(mappedBy = "cuentaReferencia")
    @JoinColumn(unique = true)
    private Segregada segregada;

    public CuentaReferencia(String iban, String nombre, Double saldo){
        super(iban);
        this.nombreBanco = nombre;
        this.saldo = saldo;
    }

    public CuentaReferencia(String iban, String swift, String nombre, Double saldo){
        super(iban, swift);
        this.nombreBanco = nombre;
        this.saldo = saldo;
    }


    public CuentaReferencia() {
        super();
    }


    public Boolean isEstado() {
        return this.estado;
    }


    public Divisa getDivisa() {
        return divisa;
    }

    public void setDivisa(Divisa divisa) {
        this.divisa = divisa;
    }

    public Double getSaldo() {
        return saldo;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nBanco) {
        nombreBanco = nBanco;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String suc) {
        sucursal = suc;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String ps) {
        pais = ps;
    }

    public void setSaldo(Double sld) {
        saldo = sld;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fApertura) {
        fechaApertura = fApertura;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean est) {
        estado = est;
    }

	@Override
	public String toString() {
		return "CuentaReferencia [nombreBanco=" + nombreBanco + ", sucursal=" + sucursal + ", pais=" + pais
				+ ", saldo=" + saldo + ", fechaApertura=" + fechaApertura + ", estado=" + estado + ", divisa="
				+ divisa + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((depositadaEn == null) ? 0 : depositadaEn.hashCode());
		result = prime * result + ((nombreBanco == null) ? 0 : nombreBanco.hashCode());
		result = prime * result + ((sucursal == null) ? 0 : sucursal.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CuentaReferencia other = (CuentaReferencia) obj;
		if (depositadaEn == null) {
			if (other.depositadaEn != null)
				return false;
		} else if (!depositadaEn.equals(other.depositadaEn))
			return false;
		if (nombreBanco == null) {
			if (other.nombreBanco != null)
				return false;
		} else if (!nombreBanco.equals(other.nombreBanco))
			return false;
		if (sucursal == null) {
			if (other.sucursal != null)
				return false;
		} else if (!sucursal.equals(other.sucursal))
			return false;
		return true;
	}

}