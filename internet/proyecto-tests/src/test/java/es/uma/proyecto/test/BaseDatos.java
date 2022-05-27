package es.uma.proyecto.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import es.uma.proyecto.entidades.*;

public class BaseDatos {
	public static void inicializaBaseDatos(String nombreUnidadPersistencia, Map<String,String> propiedadesExtra) throws ParseException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombreUnidadPersistencia);
		EntityManager em = emf.createEntityManager();
	
		
		em.getTransaction().begin();
		//No tocar arriba
		
		Date utilDate = new Date(System.currentTimeMillis());
		Date fechaPasada =new SimpleDateFormat("dd/MM/yyyy").parse("15/08/2005");
		
		Individual c1 = new Individual("uno", "fisica", "activa", utilDate, utilDate, "Avenida 123", "Maracay", "0", "PaisesBajos", "Juan", "Perez Castillo");	
		em.persist(c1);
		Individual c2 = new Individual("dos", "fisica", "activa", utilDate, utilDate, "Avenida 123", "Maracay", "1", "Francia", "Juan", "Elsa Capunta");
		em.persist(c2);
		Individual c3 = new Individual("tres", "fisica", "activa", utilDate, fechaPasada, "Avenida 123", "Maracay", "2", "Alemania", "Juan", "Benito Camela");
		em.persist(c3);
		Individual c4 = new Individual("cuatro", "fisica", "activa", utilDate, utilDate, "Avenida 123", "Maracay", "3", "Espana", "Juana", "Gomez Vazquez");
		em.persist(c4);
		Individual c5 = new Individual("cinco", "fisica", "activa", utilDate, utilDate, "Avenida 123", "Maracay", "4", "Alemania", "Tractor", "Navarro Jimena");
		em.persist(c5);
		Individual c6 = new Individual("seis", "fisica", "bloqueada", utilDate, utilDate, "Avenida 123", "Maracay", "4", "PaisesBajos", "Juan", "Vazquez Vera");
		em.persist(c6);
		Empresa c7 = new Empresa("siete", "juridica", "activa", utilDate, utilDate, "Avenida 123", "Maracay", "2", "PaisesBajos", "Activision Blizzard");
		em.persist(c7);
		Individual c8 = new Individual("ocho", "fisica", "activa", utilDate, utilDate, "Av Falsa", "Berlin", "4", "Alemania", "Rodolfo", "Garcia Marquez");
		em.persist(c8);
		Individual c9 = new Individual("nueve", "fisica", "activa", utilDate, utilDate, "Av Siempre viva", "Munich", "4", "Alemania", "Morongo", "Griman Marcano", utilDate);
		em.persist(c9);
		Individual c10 = new Individual("diez", "fisica", "activa", utilDate, fechaPasada, "Av Reyes Catolicos", "Nuremberg", "4", "Alemania", "Sorocotopo", "Antony Oliviera", utilDate);
		em.persist(c10);
		Individual c11 = new Individual("once", "fisica", "activa", utilDate, utilDate, "Bulevar Louis Pasteur", "Hamburgo", "4", "Alemania", "Brunga", "Rectangulo", utilDate);
		em.persist(c11);
		
		CuentaFintech cf1 = new CuentaFintech("15", true, utilDate, utilDate, "segregada");
		cf1.setCliente(c1);
		em.persist(cf1);
		CuentaFintech cf2 = new CuentaFintech("2", true, utilDate, utilDate, "segregada");
		cf2.setCliente(c2);
		em.persist(cf2);
		CuentaFintech cf3 = new CuentaFintech("3", true, utilDate, utilDate, "segregada");
		cf3.setCliente(c3);
		em.persist(cf3);
		CuentaFintech cf4 = new CuentaFintech("4", true, utilDate, utilDate, "segregada");
		cf4.setCliente(c4);
		em.persist(cf4);
		CuentaFintech cf5 = new CuentaFintech("5", true, utilDate,utilDate, "segregada");
		cf5.setCliente(c5);
		em.persist(cf5);
		CuentaFintech cf6 = new CuentaFintech("6", false, utilDate, utilDate, "segregada");
		cf6.setCliente(c6);
		em.persist(cf6);
		CuentaFintech cf7 = new CuentaFintech("7", true, utilDate, utilDate, "segregada");
		cf7.setCliente(c7);
		em.persist(cf7);
		CuentaFintech cf8 = new CuentaFintech("8", true, utilDate, utilDate, "segregada");
		cf8.setCliente(c8);
		em.persist(cf8);
		CuentaFintech cf9 = new CuentaFintech("9", false, utilDate, utilDate, "segregada");
		cf9.setCliente(c9);
		em.persist(cf9);
		CuentaFintech cf10 = new CuentaFintech("10", true, utilDate, utilDate, "segregada");
		cf10.setCliente(c10);
		em.persist(cf10);
		CuentaFintech cf11 = new CuentaFintech("11", false, utilDate, utilDate, "segregada");
		cf11.setCliente(c11);
		em.persist(cf11);

		//No tocar abajo
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
}
