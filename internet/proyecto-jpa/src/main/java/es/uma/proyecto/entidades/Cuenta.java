package es.uma.proyecto.entidades;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Table(name = "CUENTA")
@Inheritance(strategy = InheritanceType.JOINED)
public class Cuenta {
    @Id
    @Column(nullable = false)
    private String iban;
    private String swift;

    @OneToMany(mappedBy = "destino")
    private List<Transaccion> destino;

    @OneToMany(mappedBy = "origen")
    private List<Transaccion> origen;

    public Cuenta(){

    }
    public Cuenta(String iban){
        this.iban = iban;
    }
    public Cuenta(String ib, String sw){
    	this.iban = ib;
    	this.swift = sw;
    }


    public List<Transaccion> getDestino() {
        return this.destino;
    }

    public void setDestino(List<Transaccion> destino) {
        this.destino = destino;
    }

    public List<Transaccion> getOrigen() {
        return this.origen;
    }

    public void setOrigen(List<Transaccion> origen) {
        this.origen = origen;
    }


    public String getIBAN() {
        return iban;
    }

    public void setIBAN(String iban) {
        this.iban = iban;
    }

    public String getSWIFT() {
        return swift;
    }

    public void setSWIFT(String swift) {
        this.swift = swift;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cuenta)) return false;
        Cuenta cuenta = (Cuenta) o;
        return Objects.equals(getIBAN(), cuenta.getIBAN());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIBAN());
    }

	@Override
	public String toString() {
		return "Cuenta [iban=" + iban + ", swift=" + swift + "]";
	}
}