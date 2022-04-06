package es.uma.proyecto;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("POOLED_ACCOUNT")
public class SEGREGADA extends CUENTA_FINTECH {

	private Double Comision;

	public SEGREGADA(){
		super();
	}

	public SEGREGADA(String iban, String swift, Boolean est, Date alta, Date baja, String clasic, Double comision){
		super(iban, swift, est, alta, baja, clasic);
		this.Comision= comision;
	}

	public SEGREGADA(String iban, Boolean est, Date alta, Date baja, String clasic, Double comision){
		super(iban, est, alta, baja, clasic);
		this.Comision = comision;
	}

    @OneToOne
    @JoinColumn(nullable = false, unique = true)
    private CUENTA_REFERENCIA cuenta_referencia;

    public SEGREGADA(String iban, String swift, Boolean est, Date alta, Date baja, String clasic, Double comision,
			CUENTA_REFERENCIA cuenta_referencia) {
		super(iban, swift, est, alta, baja, clasic);
		Comision = comision;
		this.cuenta_referencia = cuenta_referencia;
	}
    
    public SEGREGADA(String iban, String swift, Boolean est, Date alta, Date baja, String clasic) {
    	super(iban, swift, est, alta, baja, clasic);
    }
    
	public Double getComision() {
		return Comision;
	}

	public void setComision(Double comision) {
		Comision = comision;
	}

	public CUENTA_REFERENCIA getCuenta_referencia() {
		return cuenta_referencia;
	}

	public void setCuenta_referencia(CUENTA_REFERENCIA cuenta_referencia) {
		this.cuenta_referencia = cuenta_referencia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((Comision == null) ? 0 : Comision.hashCode());
		result = prime * result + ((cuenta_referencia == null) ? 0 : cuenta_referencia.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SEGREGADA other = (SEGREGADA) obj;
		if (Comision == null) {
			if (other.Comision != null)
				return false;
		} else if (!Comision.equals(other.Comision))
			return false;
		if (cuenta_referencia == null) {
			if (other.cuenta_referencia != null)
				return false;
		} else if (!cuenta_referencia.equals(other.cuenta_referencia))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SEGREGADA [Comision=" + Comision + ", cuenta_referencia=" + cuenta_referencia + "]";
	}

}