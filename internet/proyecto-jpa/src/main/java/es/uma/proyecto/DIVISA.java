package es.uma.proyecto;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "DIVISA")
public class DIVISA {
	
    @Id
    @Column(nullable = false, unique = true, length = 3)
    private String Abreviatura;
    @Column(nullable = false)
    private String Nombre;
    private String Simbolo;
    @Column(nullable = false)
    private Double CambioEuro;

    @OneToMany (mappedBy = "divisa")
    private List<TRANSACCION> transacciones;

    public List<TRANSACCION> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<TRANSACCION> transacciones) {
        this.transacciones = transacciones;
    }
    
    public DIVISA() {
    	
    }

    public void setAbreviatura(String Abreviatura) {
        this.Abreviatura = Abreviatura;
    }

    public String getAbreviatura() {
        return Abreviatura;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
    
    public String getNombre() {
        return Nombre;
    }

    public String getSimbolo() {
        return Simbolo;
    }

    public void setSimbolo(String simbolo) {
        Simbolo = simbolo;
    }

    public void setCambioEuro(Double cambioEuro) {
        CambioEuro = cambioEuro;
    }
    
    public Double getCambioEuro() {
        return CambioEuro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DIVISA)) return false;
        DIVISA divisa = (DIVISA) o;
        return Objects.equals(getAbreviatura(), divisa.getAbreviatura()) && divisa.Nombre.equalsIgnoreCase(this.Nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAbreviatura());
    }

}