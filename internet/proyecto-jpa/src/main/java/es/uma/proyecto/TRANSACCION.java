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


    public boolean equals(TRANSACCION t) {
        if (this == t) return true;
        if (t == null || getClass() != t.getClass()) return false;
        TRANSACCION that = (TRANSACCION) t;
        return ID_Unico.equals(that.ID_Unico);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID_Unico);
    }
}