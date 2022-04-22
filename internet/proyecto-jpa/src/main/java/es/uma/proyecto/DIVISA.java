package es.uma.proyecto;

import javax.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "DIVISA")
public class DIVISA {
	@Id
    @Column(nullable = false, length = 3)
    private String abreviatura;
    @Column(nullable = false)
    private String nombre;
    private String simbolo;
    @Column(nullable = false)
    private Double cambioEuro;


    @OneToMany(mappedBy = "divisa")
    private List<CUENTA_REFERENCIA> divisas;

    @OneToMany(mappedBy = "receptor")
    private List<TRANSACCION> receptor;


    @OneToMany(mappedBy = "emisor")
    private List<TRANSACCION> emisor;

    public DIVISA(String abreviatura, String nombre, String simbolo, Double cambioEuro) {
		this.abreviatura = abreviatura;
		this.nombre = nombre;
		this.simbolo = simbolo;
		this.cambioEuro = cambioEuro;
	}

    public DIVISA(String abreviatura, String nombre, Double cambioEuro) {
    	this.abreviatura = abreviatura;
    	this.nombre = nombre;
    	this.cambioEuro = cambioEuro;
	}
    
    public DIVISA() {
    	
    }


    public List<CUENTA_REFERENCIA> getDivisas() {
        return this.divisas;
    }

    public void setDivisas(List<CUENTA_REFERENCIA> divisas) {
        this.divisas = divisas;
    }


    public List<TRANSACCION> getReceptor() {
        return this.receptor;
    }

    public void setReceptor(List<TRANSACCION> receptor) {
        this.receptor = receptor;
    }

    public List<TRANSACCION> getEmisor() {
        return this.emisor;
    }

    public void setEmisor(List<TRANSACCION> emisor) {
        this.emisor = emisor;
    }
    
    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public void setCambioEuro(Double cambioEuro) {
        this.cambioEuro = cambioEuro;
    }
    
    public Double getCambioEuro() {
        return cambioEuro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DIVISA)) return false;
        DIVISA divisa = (DIVISA) o;
        return Objects.equals(getAbreviatura(), divisa.getAbreviatura()) && divisa.nombre.equalsIgnoreCase(this.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAbreviatura());
    }

	@Override
	public String toString() {
		return "DIVISA [abreviatura=" + abreviatura + ", nombre=" + nombre + ", simbolo=" + simbolo + ", cambioEuro="
				+ cambioEuro + "]";
	}

}