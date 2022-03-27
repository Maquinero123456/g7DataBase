package es.uma.proyecto;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.sql.Date;
import java.util.Objects;

@Entity
@DiscriminatorValue("POOLED_ACCOUNT")
public class CUENTA_REFERENCIA extends CUENTA {

    @Column(nullable = false)
    private String NombreBanco;
    private String Sucursal;
    private String Pais;
    @Column(nullable = false)
    private String Saldo;
    private Date Fecha_Apertura;
    private Boolean Estado;

    public String getSaldo() {
        return Saldo;
    }

    public String getNombreBanco() {
        return NombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        NombreBanco = nombreBanco;
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