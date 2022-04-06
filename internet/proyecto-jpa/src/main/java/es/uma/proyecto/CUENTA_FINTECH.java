package es.uma.proyecto;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@DiscriminatorValue("CUENTA_FINTECH")
public class CUENTA_FINTECH extends CUENTA {
    @Column(nullable = false)
    private Boolean Estado = false;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date Fecha_Apertura;
    @Temporal(TemporalType.DATE)
    private Date Fecha_Cierre;
    private String Clasificacion;

    @ManyToOne
    @JoinColumn(nullable = false)
    private CLIENTE cliente;

    public CUENTA_FINTECH(String iban, String swift, Boolean est, Date alta, Date baja, String clasic) {
    	super(iban, swift);
    	this.Estado = est;
    	this.Fecha_Apertura = alta;
    	this.Fecha_Cierre = baja;
    	this.Clasificacion = clasic;
    }
    
    public CUENTA_FINTECH(String iban, String swift) {
    	super(iban, swift);
    }
    
    public CUENTA_FINTECH() {
    	
    }

    public Date getFecha_Apertura() {
        return Fecha_Apertura;
    }

    public Boolean getEstado() {
        return Estado;
    }

    public void setEstado(Boolean estado) {
        Estado = estado;
    }

    public void setFecha_Apertura(Date fecha_Apertura) {
        Fecha_Apertura = fecha_Apertura;
    }

    public Date getFecha_Cierre() {
        return Fecha_Cierre;
    }

    public void setFecha_Cierre(Date fecha_Cierre) {
        Fecha_Cierre = fecha_Cierre;
    }

    public String getClasificacion() {
        return Clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        Clasificacion = clasificacion;
    }

	public CLIENTE getCliente() {
		return cliente;
	}

	@Override
	public String toString() {
		return "CUENTA_FINTECH [Estado=" + Estado + ", Fecha_Apertura=" + Fecha_Apertura + ", Fecha_Cierre="
				+ Fecha_Cierre + ", Clasificacion=" + Clasificacion + ", cliente=" + cliente + "]";
	}


}