package es.uma.proyecto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("POOLED_ACCOUNT")
public class POOLED_ACCOUNT extends CUENTA_FINTECH {
    public POOLED_ACCOUNT(){

    }
}