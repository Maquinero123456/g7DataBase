package es.uma.proyecto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("POOLED_ACCOUNT")
public class SEGREGADA extends CUENTA_FINTECH {

	@OneToOne
	private Double comision;

	@OneToOne
    private CUENTA_REFERENCIA cuentaReferencia;

	public SEGREGADA(){
		super();
	}

	public SEGREGADA(String iban, String swift, Boolean est, Date alta, Date baja, String clasic, Double comision){
		super(iban, swift, est, alta, baja, clasic);
		this.comision = comision;
	}

	public SEGREGADA(String iban, Boolean est, Date alta, Date baja, String clasic, Double comision){
		super(iban, est, alta, baja, clasic);
		this.comision = comision;
	}

    public SEGREGADA(String iban, String swift, Boolean est, Date alta, Date baja, String clasic, Double comision,
			CUENTA_REFERENCIA cuentaReferencia) {
		super(iban, swift, est, alta, baja, clasic);
		this.comision = comision;
		this.cuentaReferencia = cuentaReferencia;
	}
    
    public SEGREGADA(String iban, String swift, Boolean est, Date alta, Date baja, String clasic) {
    	super(iban, swift, est, alta, baja, clasic);
    }
    
	public Double getComision() {
		return comision;
	}

	public void setComision(Double comision) {
		this.comision = comision;
	}

	public CUENTA_REFERENCIA getCuentaReferencia() {
		return cuentaReferencia;
	}

	public void setCuentaReferencia(CUENTA_REFERENCIA cuentaReferencia) {
		this.cuentaReferencia = cuentaReferencia;
	}


	@Override
	public String toString() {
		return "SEGREGADA [comision=" + comision + ", cuentaReferencia=" + cuentaReferencia + "]";
	}

}