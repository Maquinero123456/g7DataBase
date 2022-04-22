package es.uma.proyecto;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;
import java.util.List;

public class Usuario {
    
    @Id @GeneratedValue
    private Long id;

    private String nombre;
    private String password;
    private Boolean esAdministrativo;

    @OneToOne
    private Individual Individual;

    @OneToOne
    private PersonaAutorizada personaAutorizada;
}
