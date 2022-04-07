package es.uma.proyecto;

import javax.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "DIVISA")
public class DIVISA {
	@Id
    @Column(nullable = false, length = 3)
    private String Abreviatura;
    @Column(nullable = false)
    private String Nombre;
    private String Simbolo;
    @Column(nullable = false)
    private Double CambioEuro;


    @OneToMany(mappedBy = "Divisa")
    private List<CUENTA_REFERENCIA> divisas;

    @OneToMany(mappedBy = "Receptor")
    private List<TRANSACCION> Receptor;


    @OneToMany(mappedBy = "Emisor")
    private List<TRANSACCION> Emisor;

    public DIVISA(String abreviatura, String nombre, String simbolo, Double cambioEuro) {
		Abreviatura = abreviatura;
		Nombre = nombre;
		Simbolo = simbolo;
		CambioEuro = cambioEuro;
	}

    public DIVISA(String abreviatura, String nombre, Double cambioEuro) {
		Abreviatura = abreviatura;
		Nombre = nombre;
		CambioEuro = cambioEuro;
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
        return this.Receptor;
    }

    public void setReceptor(List<TRANSACCION> Receptor) {
        this.Receptor = Receptor;
    }

    public List<TRANSACCION> getEmisor() {
        return this.Emisor;
    }

    public void setEmisor(List<TRANSACCION> Emisor) {
        this.Emisor = Emisor;
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

	@Override
	public String toString() {
		return "DIVISA [Abreviatura=" + Abreviatura + ", Nombre=" + Nombre + ", Simbolo=" + Simbolo + ", CambioEuro="
				+ CambioEuro + "]";
	}

}