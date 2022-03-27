package es.uma.proyecto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("proyecto");
		EntityManager em = emf.createEntityManager();
		
		em.close();
		emf.close();

	}

}
