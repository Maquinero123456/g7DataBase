package es.uma.proyecto;


import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.InheritanceType;
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

    public CUENTA(){

    }
    public CUENTA(String iban){
        this.IBAN = iban;
    }
    public CUENTA(String ib, String sw){
    	this.IBAN = ib;
    	this.SWIFT = sw;
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