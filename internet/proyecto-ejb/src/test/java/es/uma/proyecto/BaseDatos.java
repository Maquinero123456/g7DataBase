package es.uma.proyecto;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.uma.proyecto.entidades.Cliente;
import es.uma.proyecto.entidades.CuentaFintech;
import es.uma.proyecto.entidades.Empresa;
import es.uma.proyecto.entidades.Individual;

public class BaseDatos {
	public static void inicializaBaseDatos(String nombreUnidadPersistencia) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombreUnidadPersistencia);
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		//No tocar arriba
		
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		
		Individual c1 = new Individual("uno", "fisica", "activa", sqlDate, sqlDate, "Avenida 123", "Maracay", "0", "PaisesBajos", "Juan", "Perez Castillo");	
		em.persist(c1);
		Individual c2 = new Individual("dos", "fisica", "activa", sqlDate, sqlDate, "Avenida 123", "Maracay", "1", "Francia", "Juan", "Elsa Capunta");
		em.persist(c2);
		Individual c3 = new Individual("tres", "fisica", "activa", sqlDate, sqlDate, "Avenida 123", "Maracay", "2", "Alemania", "Juan", "Benito Camela");
		em.persist(c3);
		Individual c4 = new Individual("cuatro", "fisica", "activa", sqlDate, sqlDate, "Avenida 123", "Maracay", "3", "Espana", "Juana", "Gomez Vazquez");
		em.persist(c4);
		Individual c5 = new Individual("cinco", "fisica", "activa", sqlDate, sqlDate, "Avenida 123", "Maracay", "4", "Alemania", "Tractor", "Vazquez Vera");
		em.persist(c5);
		Individual c6 = new Individual("seis", "fisica", "bloqueada", sqlDate, sqlDate, "Avenida 123", "Maracay", "4", "PaisesBajos", "Juan", "Navarro Jimena");
		em.persist(c6);
		Empresa c7 = new Empresa("siete", "juridica", "activa", sqlDate, sqlDate, "Avenida 123", "Maracay", "2", "PaisesBajos", "Activision Blizzard");
		em.persist(c7);
		
		CuentaFintech cf1 = new CuentaFintech("15", true, sqlDate, sqlDate, "segregada");
		cf1.setCliente(c1);
		em.persist(cf1);
		CuentaFintech cf2 = new CuentaFintech("2", true, sqlDate, sqlDate, "segregada");
		cf2.setCliente(c2);
		em.persist(cf2);
		CuentaFintech cf3 = new CuentaFintech("3", true, sqlDate, sqlDate, "segregada");
		cf3.setCliente(c3);
		em.persist(cf3);
		CuentaFintech cf4 = new CuentaFintech("4", true, sqlDate, sqlDate, "segregada");
		cf4.setCliente(c4);
		em.persist(cf4);
		CuentaFintech cf5 = new CuentaFintech("5", true, sqlDate,sqlDate, "segregada");
		cf5.setCliente(c5);
		em.persist(cf5);
		CuentaFintech cf6 = new CuentaFintech("6", false, sqlDate, sqlDate, "segregada");
		cf6.setCliente(c6);
		em.persist(cf6);
		CuentaFintech cf7 = new CuentaFintech("7", true, sqlDate, sqlDate, "segregada");
		cf7.setCliente(c7);
		em.persist(cf7);
		
		
		//No tocar abajo
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
}
