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
    private String IBAN;
    private String SWIFT;

    @OneToMany(mappedBy = "Destino")
    private List<TRANSACCION> Destino;

    @OneToMany(mappedBy = "Origen")
    private List<TRANSACCION> Origen;

    public CUENTA(){

    }
    public CUENTA(String iban){
        this.IBAN = iban;
    }
    public CUENTA(String ib, String sw){
    	this.IBAN = ib;
    	this.SWIFT = sw;
    }


    public List<TRANSACCION> getDestino() {
        return this.Destino;
    }

    public void setDestino(List<TRANSACCION> Destino) {
        this.Destino = Destino;
    }

    public List<TRANSACCION> getOrigen() {
        return this.Origen;
    }

    public void setOrigen(List<TRANSACCION> Origen) {
        this.Origen = Origen;
    }


    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public String getSWIFT() {
        return SWIFT;
    }

    public void setSWIFT(String SWIFT) {
        this.SWIFT = SWIFT;
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
		return "CUENTA [IBAN=" + IBAN + ", SWIFT=" + SWIFT + "]";
	}
}