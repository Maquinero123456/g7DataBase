package es.uma.proyecto;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@IdClass(PERSONA_CLIENTE_FK.class)
@Table(name="AUTORIZACION")
public class AUTORIZACION {
    
    @Id
    private PERSONA_AUTORIZADA persona;

    @Id
    private CLIENTE cliente;

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
