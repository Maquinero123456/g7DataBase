package es.uma.proyecto;


import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@DiscriminatorValue("CUENTA_REFERENCIA")
public class CUENTA_REFERENCIA extends CUENTA {

    @Column(nullable = false)
    private String Nombre_Banco;
    private String Sucursal;
    private String Pais;
    @Column(nullable = false)
    private Double Saldo;
    @Temporal(TemporalType.DATE)
    private Date Fecha_Apertura;
    private Boolean Estado;

    @JoinColumn(nullable = false)
    private DIVISA Divisa;

    public CUENTA_REFERENCIA(String IBAN, String nombre, Double saldo){
        super(IBAN);
        this.Nombre_Banco = nombre;
        this.Saldo = saldo;
    }

    public CUENTA_REFERENCIA(String IBAN, String swift, String nombre, Double saldo){
        super(IBAN, swift);
        this.Nombre_Banco = nombre;
        this.Saldo = saldo;
    }


    public CUENTA_REFERENCIA() {
        super();
    }


    public Boolean isEstado() {
        return this.Estado;
    }


    public DIVISA getDivisa() {
        return Divisa;
    }

    public void setDivisa(DIVISA divisa) {
        this.Divisa = divisa;
    }

    public Double getSaldo() {
        return Saldo;
    }

    public String getNombre_Banco() {
        return Nombre_Banco;
    }

    public void setNombre_Banco(String nombreBanco) {
        Nombre_Banco = nombreBanco;
    }

    public String getSucursal() {
        return Sucursal;
    }

    public void setSucursal(String sucursal) {
        Sucursal = sucursal;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String pais) {
        Pais = pais;
    }

    public void setSaldo(Double saldo) {
        Saldo = saldo;
    }

    public Date getFecha_Apertura() {
        return Fecha_Apertura;
    }

    public void setFecha_Apertura(Date fecha_Apertura) {
        Fecha_Apertura = fecha_Apertura;
    }

    public Boolean getEstado() {
        return Estado;
    }

    public void setEstado(Boolean estado) {
        Estado = estado;
    }

	@Override
	public String toString() {
		return "CUENTA_REFERENCIA [Nombre_Banco=" + Nombre_Banco + ", Sucursal=" + Sucursal + ", Pais=" + Pais
				+ ", Saldo=" + Saldo + ", Fecha_Apertura=" + Fecha_Apertura + ", Estado=" + Estado + ", Divisa="
				+ Divisa + "]";
	}

}