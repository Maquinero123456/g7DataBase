package es.uma.proyecto;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
