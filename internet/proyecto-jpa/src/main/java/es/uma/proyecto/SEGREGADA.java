package es.uma.proyecto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("POOLED_ACCOUNT")
public class SEGREGADA extends CUENTA_FINTECH {
    
	private Double Comision;

    @OneToOne
    private CUENTA_REFERENCIA cuenta_referencia;

    public SEGREGADA(){

    }
}