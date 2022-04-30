package es.uma.proyecto.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name="CUENTAFINTECH")
public class CuentaFintech extends Cuenta {
    @Column(nullable = false)
    private Boolean estado = false;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaApertura;
    @Temporal(TemporalType.DATE)
    private Date fechaCierre;
    private String clasificacion;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Cliente cliente;

    public CuentaFintech(String iban, String swift, Boolean est, Date alta, Date baja, String clasic) {
    	super(iban, swift);
    	this.estado = est;
    	this.fechaApertura = alta;
    	this.fechaCierre = baja;
    	this.clasificacion = clasic;
    }

    public CuentaFintech(String iban, Boolean est, Date alta, Date baja, String clasic) {
    	super(iban);
    	this.estado = est;
    	this.fechaApertura = alta;
    	this.fechaCierre = baja;
    	this.clasificacion = clasic;
    }
    
    public CuentaFintech(String iban, String swift) {
    	super(iban, swift);
    }
    
    public CuentaFintech() {
    	super();
    }

    

    public Boolean isEstado() {
        return this.estado;
    }
    public void setCliente(Cliente cliente) {
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

    public void setFechaApertura(Date fechaApertura) {
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

	public Cliente getCliente() {
		return cliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		return result;
	}


	@Override
	public String toString() {
		return "CuentaFintech [estado=" + estado + ", fechaApertura=" + fechaApertura + ", fechaCierre="
				+ fechaCierre + ", clasificacion=" + clasificacion + ", cliente=" + cliente + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CuentaFintech other = (CuentaFintech) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		return true;
	}

}