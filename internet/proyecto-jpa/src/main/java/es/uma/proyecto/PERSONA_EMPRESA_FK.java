package es.uma.proyecto;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class PERSONA_EMPRESA_FK{
    @ManyToOne
    @JoinColumn(name = "PERSONA_FK", nullable = false)
    private String persona;

    @ManyToOne
    @JoinColumn(name = "EMPRESA_FK", nullable = false)
    private String empresa;

}