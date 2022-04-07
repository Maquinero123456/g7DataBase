package es.uma.proyecto;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@IdClass(PERSONA_EMPRESA_FK.class)
@Table(name="AUTORIZACION")
public class AUTORIZACION {
    
    @Id
    private PERSONA_AUTORIZADA persona;

    @Id
    private EMPRESA empresa;

	@Column(nullable=false)
    private String Tipo;

	
	public AUTORIZACION(String tipo, PERSONA_AUTORIZADA per, EMPRESA emp) {
		this.Tipo = tipo;
		this.empresa = emp;
		this.persona = per;
	}
	
	public AUTORIZACION() {
		
	}
    
	public String getTipo() {
		return this.Tipo;
	}

	public void setTipo(String tipo) {
		this.Tipo = tipo;
	}
	
	public PERSONA_AUTORIZADA getPersona() {
		return persona;
	}

	public EMPRESA getEmpresa() {
		return empresa;
	}
	
	@Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AUTORIZACION)) {
            return false;
        }
        AUTORIZACION aut = (AUTORIZACION) o;
        return Objects.equals(persona, aut.persona) && Objects.equals(empresa, aut.empresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(persona, empresa);
    }

	@Override
	public String toString() {
		return "AUTORIZACION [persona=" + persona + ", empresa=" + empresa + ", Tipo=" + Tipo + "]";
	}

	
}
