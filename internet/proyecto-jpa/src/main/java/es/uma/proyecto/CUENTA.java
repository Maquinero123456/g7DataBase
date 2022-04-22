package es.uma.proyecto;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.DiscriminatorType;

@Entity
@Table(name = "CUENTA")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Tipo_Cuenta", discriminatorType = DiscriminatorType.CHAR)
@DiscriminatorValue("CUENTA")
public class CUENTA {
    @Id
    @Column(nullable = false)
    private String iban;
    private String swift;

    @OneToMany(mappedBy = "destino")
    private List<TRANSACCION> destino;

    @OneToMany(mappedBy = "origen")
    private List<TRANSACCION> origen;

    public CUENTA(){

    }
    public CUENTA(String iban){
        this.iban = iban;
    }
    public CUENTA(String ib, String sw){
    	this.iban = ib;
    	this.swift = sw;
    }


    public List<TRANSACCION> getDestino() {
        return this.destino;
    }

    public void setDestino(List<TRANSACCION> destino) {
        this.destino = destino;
    }

    public List<TRANSACCION> getOrigen() {
        return this.origen;
    }

    public void setOrigen(List<TRANSACCION> origen) {
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
        if (!(o instanceof CUENTA)) return false;
        CUENTA cuenta = (CUENTA) o;
        return Objects.equals(getIBAN(), cuenta.getIBAN());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIBAN());
    }

	@Override
	public String toString() {
		return "CUENTA [iban=" + iban + ", swift=" + swift + "]";
	}
}