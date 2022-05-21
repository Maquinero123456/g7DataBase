package es.uma.proyecto;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uma.proyecto.entidades.Usuario;

@Singleton
@Startup
public class IniciazlizBBDD {
    
    @PersistenceContext(name = "proyectoEJB")
	EntityManager em;

    @PostConstruct
    public void inicializar(){
        Usuario comprobacion = em.find(Usuario.class, "Juanito");
        if (comprobacion !=null) {
            return;
        }

        Usuario user = new Usuario("Juanito", "Juanito123", false, "juanito@gmail.com");
        em.persist(user);
    }
}
