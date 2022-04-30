package es.uma.proyecto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.uma.proyecto.entidades.Cliente;
import es.uma.proyecto.entidades.CuentaFintech;
import es.uma.proyecto.entidades.CuentaReferencia;

public class BaseDatos {
	public static void inicializaBaseDatos(String nombreUnidadPersistencia) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombreUnidadPersistencia);
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		//No tocar arriba
		
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		
		Cliente c1 = new Cliente("uno", "fisica", "activa", sqlDate, "Avenida 123", "Maracay", "123", "PaisesBajos");
		em.persist(c1);
		Cliente c2 = new Cliente("dos", "fisica", "activa", sqlDate, "Avenida 123", "Maracay", "123", "Francia");
		em.persist(c2);
		Cliente c3 = new Cliente("tres", "fisica", "activa", sqlDate, "Avenida 123", "Maracay", "123", "Alemania");
		em.persist(c3);
		Cliente c4 = new Cliente("cuatro", "fisica", "activa", sqlDate, "Avenida 123", "Maracay", "123", "Espana");
		em.persist(c4);
		Cliente c5 = new Cliente("cinco", "fisica", "activa", sqlDate, "Avenida 123", "Maracay", "123", "Alemania");
		em.persist(c5);
		Cliente c6 = new Cliente("seis", "fisica", "activa", sqlDate, "Avenida 123", "Maracay", "123", "PaisesBajos");
		em.persist(c6);
		Cliente c7 = new Cliente("siete", "fisica", "activa", sqlDate, "Avenida 123", "Maracay", "123", "PaisesBajos");
		em.persist(c7);
		
		CuentaFintech cf1 = new CuentaFintech("1", true, sqlDate, null, "segregada");
		cf1.setCliente(c1);
		em.persist(c1);
		CuentaFintech cf2 = new CuentaFintech("2", true, sqlDate, null, "segregada");
		cf2.setCliente(c2);
		em.persist(cf2);
		CuentaFintech cf3 = new CuentaFintech("3", true, sqlDate, null, "segregada");
		cf3.setCliente(c3);
		em.persist(cf3);
		CuentaFintech cf4 = new CuentaFintech("4", true, sqlDate, null, "segregada");
		cf4.setCliente(c4);
		em.persist(cf4);
		CuentaFintech cf5 = new CuentaFintech("5", true, sqlDate, null, "segregada");
		cf5.setCliente(c5);
		em.persist(cf5);
		CuentaFintech cf6 = new CuentaFintech("6", true, sqlDate, null, "segregada");
		cf6.setCliente(c6);
		em.persist(cf6);
		CuentaFintech cf7 = new CuentaFintech("7", true, sqlDate, null, "segregada");
		cf7.setCliente(c7);
		em.persist(cf7);

		CuentaReferencia cref = new CuentaReferencia("7", "Cuenta Prueba", 1.00);
		em.persist(cref);
		
		//No tocar abajo
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
}
