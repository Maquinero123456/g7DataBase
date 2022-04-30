package es.uma.proyecto.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;


@Embeddable
public class EmpresaPersAutoPK implements Serializable{
    private long empresa;
    private long personaAutorizada;


    public EmpresaPersAutoPK() {
    }

    public EmpresaPersAutoPK(long empresa, long personaAutorizada){
        this.empresa = empresa;
        this.personaAutorizada = personaAutorizada;
    }

    public long getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(long empresa) {
        this.empresa = empresa;
    }

    public long getPersonaAutorizada() {
        return this.personaAutorizada;
    }

    public void setPersonaAutorizada(long personaAutorizada) {
        this.personaAutorizada = personaAutorizada;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof EmpresaPersAutoPK)) {
            return false;
        }
        EmpresaPersAutoPK empresaPersAutoPK = (EmpresaPersAutoPK) o;
        return Objects.equals(empresa, empresaPersAutoPK.empresa) && Objects.equals(personaAutorizada, empresaPersAutoPK.personaAutorizada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empresa, personaAutorizada);
    }


    @Override
    public String toString() {
        return "{" +
            " empresa='" + getEmpresa() + "'" +
            ", personaAutorizada='" + getPersonaAutorizada() + "'" +
            "}";
    }


}
