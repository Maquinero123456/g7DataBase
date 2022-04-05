package es.uma.proyecto;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("POOLED_ACCOUNT")
public class SEGREGADA extends CUENTA_FINTECH {
    @SuppressWarnings("unused")
	private Double Comision;

    @OneToOne
    @Column(nullable = false)
    private CUENTA_REFERENCIA cuenta_referencia;

    public SEGREGADA(){

    }
}