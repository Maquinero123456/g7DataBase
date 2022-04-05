package es.uma.proyecto;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.print.DocFlavor.STRING;

public class PERSONA_CLIENTE_FK{
    @OneToMany
    @JoinColumn(name = "PERSONA_FK", nullable = false)
    private String persona;

    @OneToMany
    @JoinColumn(name = "CLIENTE_FK", nullable = false)
    private String cliente;

}