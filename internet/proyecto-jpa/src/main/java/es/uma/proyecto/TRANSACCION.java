package es.uma.proyecto;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class TRANSACCION {
    @Id @GeneratedValue
    private String ID_Unico;
    @Column(nullable = false) @Temporal(TemporalType.DATE)
    private Date fechaInstruccion;
    @Column(nullable = false)
    private double Cantidad;
    @Temporal(TemporalType.DATE)
    private Date fechaEjecucion;
    @Column(nullable = false)
    private String Tipo;
    private double Comision;
    private boolean Internacional;

    @ManyToOne
    private CUENTA cuenta;

    public CUENTA getCuenta() {
        return cuenta;
    }

    public void setCuenta(CUENTA cuenta) {
        this.cuenta = cuenta;
    }

    @ManyToOne
    private DIVISA divisa;

    public DIVISA getDivisa() {
        return divisa;
    }

    public void setDivisa(DIVISA divisa) {
        this.divisa = divisa;
    }

    public TRANSACCION() {
    }

    public TRANSACCION(String ID_Unico, Date fechaInstruccion, double cantidad, String tipo) {
        this.ID_Unico = ID_Unico;
        this.fechaInstruccion = fechaInstruccion;
        Cantidad = cantidad;
        Tipo = tipo;
    }

    public String getID_Unico() {
        return ID_Unico;
    }

    public Date getFechaEjecucion() {
        return fechaEjecucion;
    }

    public void setFechaEjecucion(Date fechaEjecucion) {
        this.fechaEjecucion = fechaEjecucion;
    }

    public double getComision() {
        return Comision;
    }

    public void setComision(double comision) {
        Comision = comision;
    }

    public boolean isInternacional() {
        return Internacional;
    }

    public void setInternacional(boolean internacional) {
        Internacional = internacional;
    }

    public String getTipo() {
        return Tipo;
    }

    public Date getFechaInstruccion() {
        return fechaInstruccion;
    }

    public double getCantidad() {
        return Cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TRANSACCION that = (TRANSACCION) o;

        return ID_Unico.equals(that.ID_Unico);
    }

    @Override
    public int hashCode() {
        return ID_Unico.hashCode();
    }
}