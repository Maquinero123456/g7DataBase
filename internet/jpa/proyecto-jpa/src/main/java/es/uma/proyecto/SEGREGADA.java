package es.uma.proyecto;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table()
public class SEGREGADA extends CUENTA_FINTECH {
    public Double Comision;
}