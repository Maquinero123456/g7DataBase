package es.uma.proyecto;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="AUTORIZACION")
public class AUTORIZACION {

	@Column(nullable=false)
    private String Tipo;

	public String getTipo() {
		return this.Tipo;
	}

	public void setTipo(String tipo) {
		this.Tipo = tipo;
	}
	
	@Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AUTORIZACION)) {
            return false;
        }
        AUTORIZACION aut = (AUTORIZACION) o;
        return Objects.equals(Tipo, aut.Tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Tipo);
    }
}
