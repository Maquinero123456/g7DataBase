package es.uma.proyecto;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;


@Embeddable
public class EmpresaPersAutoPK implements Serializable{
    private String empresa;
    private String personaAutorizada;


    public EmpresaPersAutoPK() {
    }

    public EmpresaPersAutoPK(String empresa, String personaAutorizada){
        this.empresa = empresa;
        this.personaAutorizada = personaAutorizada;
    }

    public String getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getPersonaAutorizada() {
        return this.personaAutorizada;
    }

    public void setPersonaAutorizada(String personaAutorizada) {
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
