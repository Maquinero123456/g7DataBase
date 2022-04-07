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

    @JoinColumn(nullable=false)
    private CUENTA Destino;
    @JoinColumn(nullable=false)
    private CUENTA Origen;

    @JoinColumn(nullable = false)
    private DIVISA Receptor;

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

    public void setFechaInstruccion(Date fechaInstruccion) {
        this.fechaInstruccion = fechaInstruccion;
    }
    public void setCantidad(double Cantidad) {
        this.Cantidad = Cantidad;
    }
    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public boolean getInternacional() {
        return this.Internacional;
    }


    public CUENTA getDestino() {
        return this.Destino;
    }

    public void setDestino(CUENTA Destino) {
        this.Destino = Destino;
    }

    public CUENTA getOrigen() {
        return this.Origen;
    }

    public void setOrigen(CUENTA Origen) {
        this.Origen = Origen;
    }

    public DIVISA getReceptor() {
        return this.Receptor;
    }

    public void setReceptor(DIVISA Receptor) {
        this.Receptor = Receptor;
    }

    public DIVISA getEmisor() {
        return this.Emisor;
    }

    public void setEmisor(DIVISA Emisor) {
        this.Emisor = Emisor;
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
        if (o == this)
            return true;
        if (!(o instanceof TRANSACCION)) {
            return false;
        }
        TRANSACCION tran = (TRANSACCION) o;
        return ID_Unico.equalsIgnoreCase(tran.ID_Unico) && Objects.equals(fechaInstruccion, tran.fechaInstruccion) && Objects.equals(Cantidad, tran.Cantidad) && Tipo.equalsIgnoreCase(tran.Tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID_Unico, fechaInstruccion, Cantidad, Tipo);
    }

	@Override
	public String toString() {
		return "TRANSACCION [ID_Unico=" + ID_Unico + ", fechaInstruccion=" + fechaInstruccion + ", Cantidad=" + Cantidad
				+ ", fechaEjecucion=" + fechaEjecucion + ", Tipo=" + Tipo + ", Comision=" + Comision
				+ ", Internacional=" + Internacional + ", Destino=" + Destino + ", Origen=" + Origen + ", Receptor="
				+ Receptor + ", Emisor=" + Emisor + "]";
	}
}