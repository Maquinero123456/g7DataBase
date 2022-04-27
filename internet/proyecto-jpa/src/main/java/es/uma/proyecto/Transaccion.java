package es.uma.proyecto;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "TRANSACCION")
public class Transaccion {
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

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Cuenta destino;
    
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Cuenta origen;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Divisa receptor;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Divisa emisor;

    public Transaccion() {
    }

    public Transaccion(Date fechaInstruccion, double cantidad, String tipo, Cuenta destino, Cuenta origen, Divisa receptor, Divisa emisor) {
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


    public Cuenta getDestino() {
        return this.destino;
    }

    public void setDestino(Cuenta destino) {
        this.destino = destino;
    }

    public Cuenta getOrigen() {
        return this.origen;
    }

    public void setOrigen(Cuenta origen) {
        this.origen = origen;
    }

    public Divisa getReceptor() {
        return this.receptor;
    }

    public void setReceptor(Divisa receptor) {
        this.receptor = receptor;
    }

    public Divisa getEmisor() {
        return this.emisor;
    }

    public void setEmisor(Divisa emisor) {
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
        if (!(o instanceof Transaccion)) {
            return false;
        }
        Transaccion tran = (Transaccion) o;
        return idUnico.equalsIgnoreCase(tran.idUnico) && Objects.equals(fechaInstruccion, tran.fechaInstruccion) && Objects.equals(cantidad, tran.cantidad) && tipo.equalsIgnoreCase(tran.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUnico, fechaInstruccion, cantidad, tipo);
    }

	@Override
	public String toString() {
		return "Transaccion [idUnico=" + idUnico + ", fechaInstruccion=" + fechaInstruccion + ", cantidad=" + cantidad
				+ ", fechaEjecucion=" + fechaEjecucion + ", tipo=" + tipo + ", comision=" + comision
				+ ", internacional=" + internacional + ", destino=" + destino + ", origen=" + origen + ", receptor="
				+ receptor + ", emisor=" + emisor + "]";
	}
}