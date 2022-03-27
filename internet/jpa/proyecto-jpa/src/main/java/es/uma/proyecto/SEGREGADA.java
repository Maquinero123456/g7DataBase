package es.uma.proyecto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("POOLED_ACCOUNT")
public class SEGREGADA extends CUENTA_FINTECH {
    private Double Comision;

    public SEGREGADA(){

    }
}