package es.uma.proyecto;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class TRANSACCION {
    @Id @GeneratedValue
    private String idUnico;
    @Column(nullable = false) @Temporal(TemporalType.DATE)
    private Date fechaInstruccion;
    @Column(nullable = false)
    private double cantidad;
    @Temporal(TemporalType.DATE)
    private Date fechaEjecucion;
    @Column(nullable = false)
    private String tipo;
    private double comision;
    private boolean internacional;

    @JoinColumn(nullable=false)
    private CUENTA destino;
    @JoinColumn(nullable=false)
    private CUENTA origen;

    @JoinColumn(nullable = false)
    private DIVISA receptor;

    @JoinColumn(nullable = false)
    private DIVISA emisor;

    public TRANSACCION() {
    }

    public TRANSACCION(String idUnico, Date fechaInstruccion, double cantidad, String tipo, CUENTA destino, CUENTA origen, DIVISA receptor, DIVISA emisor) {
        this.idUnico = idUnico;
        this.fechaInstruccion = fechaInstruccion;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.destino = destino;
        this.origen = origen;
        this.receptor = receptor;
        this.emisor = emisor;
    }

    public void setFechaInstruccion(Date fechaInstruccion) {
        this.fechaInstruccion = fechaInstruccion;
    }
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean getInternacional() {
        return this.internacional;
    }


    public CUENTA getDestino() {
        return this.destino;
    }

    public void setDestino(CUENTA destino) {
        this.destino = destino;
    }

    public CUENTA getOrigen() {
        return this.origen;
    }

    public void setOrigen(CUENTA origen) {
        this.origen = origen;
    }

    public DIVISA getReceptor() {
        return this.receptor;
    }

    public void setReceptor(DIVISA receptor) {
        this.receptor = receptor;
    }

    public DIVISA getEmisor() {
        return this.emisor;
    }

    public void setEmisor(DIVISA emisor) {
        this.emisor = emisor;
    }

    public String getIDUnico() {
        return idUnico;
    }

    public Date getFechaEjecucion() {
        return fechaEjecucion;
    }

    public void setFechaEjecucion(Date fechaEjecucion) {
        this.fechaEjecucion = fechaEjecucion;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    public boolean isInternacional() {
        return internacional;
    }

    public void setInternacional(boolean internacional) {
        this.internacional = internacional;
    }

    public String getTipo() {
        return tipo;
    }

    public Date getFechaInstruccion() {
        return fechaInstruccion;
    }

    public double getCantidad() {
        return cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TRANSACCION)) {
            return false;
        }
        TRANSACCION tran = (TRANSACCION) o;
        return idUnico.equalsIgnoreCase(tran.idUnico) && Objects.equals(fechaInstruccion, tran.fechaInstruccion) && Objects.equals(cantidad, tran.cantidad) && tipo.equalsIgnoreCase(tran.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUnico, fechaInstruccion, cantidad, tipo);
    }

	@Override
	public String toString() {
		return "TRANSACCION [idUnico=" + idUnico + ", fechaInstruccion=" + fechaInstruccion + ", cantidad=" + cantidad
				+ ", fechaEjecucion=" + fechaEjecucion + ", tipo=" + tipo + ", comision=" + comision
				+ ", internacional=" + internacional + ", destino=" + destino + ", origen=" + origen + ", receptor="
				+ receptor + ", emisor=" + emisor + "]";
	}
}