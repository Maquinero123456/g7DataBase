package es.uma.proyecto;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue("CuentaReferencia")
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
    private Divisa divisa;

    @OneToOne(mappedBy = "cuentaReferencia")
    private Segredada segredada;

    @OneToMany(mappedBy = "cuentaReferencia")
    private List<DepositadaEn> depositadaEn;

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

}