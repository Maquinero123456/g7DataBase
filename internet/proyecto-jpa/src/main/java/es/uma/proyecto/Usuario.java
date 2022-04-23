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

    @OneToOne
    private Individual Individual;

    @OneToOne
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
        return this.Individual;
    }

    public void setIndividual(Individual Individual) {
        this.Individual = Individual;
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

}
