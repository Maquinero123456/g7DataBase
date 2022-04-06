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

    @ManyToOne(cascade = CascadeType.MERGE)
    @Column(nullable = false)
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

    public String getNombreBanco() {
        return Nombre_Banco;
    }

    public void setNombreBanco(String nombreBanco) {
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

}