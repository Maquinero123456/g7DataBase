package es.uma.proyecto;

import javax.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Divisa")
public class Divisa {
	@Id
    @Column(nullable = false, length = 3)
    private String abreviatura;
    @Column(nullable = false)
    private String nombre;
    private String simbolo;
    @Column(nullable = false)
    private Double cambioEuro;


    @OneToMany(mappedBy = "divisa")
    private List<CuentaReferencia> divisas;

    @OneToMany(mappedBy = "receptor")
    private List<Transaccion> receptor;


    @OneToMany(mappedBy = "emisor")
    private List<Transaccion> emisor;

    public Divisa(String abreviatura, String nombre, String simbolo, Double cambioEuro) {
		this.abreviatura = abreviatura;
		this.nombre = nombre;
		this.simbolo = simbolo;
		this.cambioEuro = cambioEuro;
	}

    public Divisa(String abreviatura, String nombre, Double cambioEuro) {
    	this.abreviatura = abreviatura;
    	this.nombre = nombre;
    	this.cambioEuro = cambioEuro;
	}
    
    public Divisa() {
    	
    }


    public List<CuentaReferencia> getDivisas() {
        return this.divisas;
    }

    public void setDivisas(List<CuentaReferencia> divisas) {
        this.divisas = divisas;
    }


    public List<Transaccion> getReceptor() {
        return this.receptor;
    }

    public void setReceptor(List<Transaccion> receptor) {
        this.receptor = receptor;
    }

    public List<Transaccion> getEmisor() {
        return this.emisor;
    }

    public void setEmisor(List<Transaccion> emisor) {
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
        if (!(o instanceof Divisa)) return false;
        Divisa divisa = (Divisa) o;
        return Objects.equals(getAbreviatura(), divisa.getAbreviatura()) && divisa.nombre.equalsIgnoreCase(this.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAbreviatura());
    }

	@Override
	public String toString() {
		return "Divisa [abreviatura=" + abreviatura + ", nombre=" + nombre + ", simbolo=" + simbolo + ", cambioEuro="
				+ cambioEuro + "]";
	}

}