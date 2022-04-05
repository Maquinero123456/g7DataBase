package es.uma.proyecto;

import javax.persistence.*;
import java.util.Objects;

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
}