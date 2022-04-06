package es.uma.proyecto;

import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("CUENTA_REFERENCIA")
public class CUENTA_REFERENCIA extends CUENTA {

    @Column(nullable = false)
    private String Nombre_Banco;
    private String Sucursal;
    private String Pais;
    @Column(nullable = false)
    private String Saldo;
    @Temporal(TemporalType.DATE)
    private Date Fecha_Apertura;
    private Boolean Estado;

    @ManyToOne
    @JoinColumn(nullable = false)
    private DIVISA Divisa;

    
    public DIVISA getDivisa() {
        return Divisa;
    }

    public void setDivisa(DIVISA divisa) {
        this.Divisa = divisa;
    }

    public String getSaldo() {
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

    public void setSaldo(String saldo) {
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