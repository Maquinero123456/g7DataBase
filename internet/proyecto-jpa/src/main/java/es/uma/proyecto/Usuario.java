package es.uma.proyecto;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIO")
public class Usuario {
    
    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Boolean esAdministrativo;

    @OneToOne(optional = false, mappedBy = "usuario")
    private Individual individual;

    @OneToOne(optional = false, mappedBy = "usuario")
    private PersonaAutorizada personaAutorizada;


    public Usuario() {
    }
    

    public Long getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isEsAdministrativo() {
        return this.esAdministrativo;
    }

    public Boolean getEsAdministrativo() {
        return this.esAdministrativo;
    }

    public void setEsAdministrativo(Boolean esAdministrativo) {
        this.esAdministrativo = esAdministrativo;
    }

    public Individual getIndividual() {
        return this.individual;
    }

    public void setIndividual(Individual indv) {
        this.individual = indv;
    }

    public PersonaAutorizada getPersonaAutorizada() {
        return this.personaAutorizada;
    }

    public void setPersonaAutorizada(PersonaAutorizada personaAutorizada) {
        this.personaAutorizada = personaAutorizada;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Usuario)) {
            return false;
        }
        Usuario usuario = (Usuario) o;
        return Objects.equals(nombre, usuario.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }


	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", password=" + password + ", esAdministrativo="
				+ esAdministrativo + ", individual=" + individual + ", personaAutorizada=" + personaAutorizada + "]";
	}

}
