package es.uma.proyecto;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="AUTORIZACION")
public class Autorizacion {
    
    @Id
	@ManyToOne
    private PersonaAutorizada persona;

    @Id
	@ManyToOne
    private Empresa empresa;

	@Column(nullable=false)
    private String tipo;

	
	public Autorizacion(String tipo, PersonaAutorizada per, Empresa emp) {
		this.tipo = tipo;
		this.empresa = emp;
		this.persona = per;
	}
	
	public Autorizacion() {
		
	}
	
	public void setPersona(PersonaAutorizada persona) {
		this.persona = persona;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
    
	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public PersonaAutorizada getPersona() {
		return persona;
	}

	public Empresa getEmpresa() {
		return empresa;
	}
	
	@Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Autorizacion)) {
            return false;
        }
        Autorizacion aut = (Autorizacion) o;
        return Objects.equals(persona, aut.persona) && Objects.equals(empresa, aut.empresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(persona, empresa);
    }

	@Override
	public String toString() {
		return "Autorizacion [persona=" + persona + ", empresa=" + empresa + ", tipo=" + tipo + "]";
	}

	
}
