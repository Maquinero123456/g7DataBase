package es.uma.proyecto;

import javax.persistence.*;
import java.util.Date;

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
    @JoinColumn(nullable=false)
    private CUENTA Destino;
    @ManyToOne
    @JoinColumn(nullable=false)
    private CUENTA Origen;

    @ManyToOne
    @JoinColumn(nullable = false)
    private DIVISA Receptor;

    @ManyToOne
    @JoinColumn(nullable = false)
    private DIVISA Emisor;

    public TRANSACCION() {
    }

    public TRANSACCION(String ID_Unico, Date fechaInstruccion, double cantidad, String tipo, CUENTA destino, CUENTA origen, DIVISA receptor, DIVISA emisor) {
        this.ID_Unico = ID_Unico;
        this.fechaInstruccion = fechaInstruccion;
        this.Cantidad = cantidad;
        this.Tipo = tipo;
        this.Destino = destino;
        this.Origen = origen;
        this.Receptor = receptor;
        this.Emisor = emisor;
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

	@Override
	public String toString() {
		return "TRANSACCION [ID_Unico=" + ID_Unico + ", fechaInstruccion=" + fechaInstruccion + ", Cantidad=" + Cantidad
				+ ", fechaEjecucion=" + fechaEjecucion + ", Tipo=" + Tipo + ", Comision=" + Comision
				+ ", Internacional=" + Internacional + ", Destino=" + Destino + ", Origen=" + Origen + ", Receptor="
				+ Receptor + ", Emisor=" + Emisor + "]";
	}
}