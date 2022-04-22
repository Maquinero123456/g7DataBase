package es.uma.proyecto;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@DiscriminatorValue("CUENTA_FINTECH")
public class CUENTA_FINTECH extends CUENTA {
    @Column(nullable = false)
    private Boolean estado = false;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaApertura;
    @Temporal(TemporalType.DATE)
    private Date fechaCierre;
    private String clasificacion;

    @JoinColumn(nullable = false)
    private CLIENTE cliente;

    public CUENTA_FINTECH(String iban, String swift, Boolean est, Date alta, Date baja, String clasic) {
    	super(iban, swift);
    	this.estado = est;
    	this.fechaApertura = alta;
    	this.fechaCierre = baja;
    	this.clasificacion = clasic;
    }

    public CUENTA_FINTECH(String iban, Boolean est, Date alta, Date baja, String clasic) {
    	super(iban);
    	this.estado = est;
    	this.fechaApertura = alta;
    	this.fechaCierre = baja;
    	this.clasificacion = clasic;
    }
    
    public CUENTA_FINTECH(String iban, String swift) {
    	super(iban, swift);
    }
    
    public CUENTA_FINTECH() {
    	super();
    }

    

    public Boolean isEstado() {
        return this.estado;
    }
    public void setCliente(CLIENTE cliente) {
        this.cliente = cliente;
    }


    public Date getFechaApertura() {
        return fechaApertura;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public void setFecha_Apertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fCierre) {
        fechaCierre = fCierre;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clas) {
        clasificacion = clas;
    }

	public CLIENTE getCliente() {
		return cliente;
	}

	@Override
	public String toString() {
		return "CUENTA_FINTECH [estado=" + estado + ", fechaApertura=" + fechaApertura + ", fechaCierre="
				+ fechaCierre + ", clasificacion=" + clasificacion + ", cliente=" + cliente + "]";
	}


}