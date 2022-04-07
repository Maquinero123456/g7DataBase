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
	public String toString() {
		return "SEGREGADA [Comision=" + Comision + ", cuenta_referencia=" + cuenta_referencia + "]";
	}

}