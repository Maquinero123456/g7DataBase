package es.uma.proyecto;

import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("PooledAccount")
public class Segregada extends CuentaFintech {

	private Double comision;

	@OneToOne
	@JoinColumn(nullable = false, unique = true)
    private CuentaReferencia cuentaReferencia;

	public Segregada(){
		super();
	}

	public Segregada(String iban, String swift, Boolean est, Date alta, Date baja, String clasic, Double comision){
		super(iban, swift, est, alta, baja, clasic);
		this.comision = comision;
	}

	public Segregada(String iban, Boolean est, Date alta, Date baja, String clasic, Double comision){
		super(iban, est, alta, baja, clasic);
		this.comision = comision;
	}

    public Segregada(String iban, String swift, Boolean est, Date alta, Date baja, String clasic, Double comision,
			CuentaReferencia cuentaReferencia) {
		super(iban, swift, est, alta, baja, clasic);
		this.comision = comision;
		this.cuentaReferencia = cuentaReferencia;
	}
    
    public Segregada(String iban, String swift, Boolean est, Date alta, Date baja, String clasic) {
    	super(iban, swift, est, alta, baja, clasic);
    }
    
	public Double getComision() {
		return comision;
	}

	public void setComision(Double comision) {
		this.comision = comision;
	}

	public CuentaReferencia getCuentaReferencia() {
		return cuentaReferencia;
	}

	public void setCuentaReferencia(CuentaReferencia cuentaReferencia) {
		this.cuentaReferencia = cuentaReferencia;
	}


	@Override
	public String toString() {
		return "Segregada [comision=" + comision + ", cuentaReferencia=" + cuentaReferencia + "]";
	}

}