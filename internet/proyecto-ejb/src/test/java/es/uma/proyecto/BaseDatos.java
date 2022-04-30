package es.uma.proyecto;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import es.uma.proyecto.entidades.Autorizacion;
import es.uma.proyecto.entidades.Cliente;
import es.uma.proyecto.entidades.CuentaFintech;
import es.uma.proyecto.entidades.CuentaReferencia;
import es.uma.proyecto.entidades.Divisa;
import es.uma.proyecto.entidades.Empresa;
import es.uma.proyecto.entidades.EmpresaPersAutoPK;
import es.uma.proyecto.entidades.Individual;
import es.uma.proyecto.entidades.PersonaAutorizada;
import es.uma.proyecto.entidades.Segregada;

public class BaseDatos {
	public static void inicializaBaseDatos(String nombreUnidadPersistencia) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombreUnidadPersistencia);
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		//No tocar arriba
		
		Date utilDate = new Date(System.currentTimeMillis());
		
		Individual c1 = new Individual("uno", "fisica", "activa", utilDate, utilDate, "Avenida 123", "Maracay", "0", "PaisesBajos", "Juan", "Perez Castillo");	
		em.persist(c1);
		Individual c2 = new Individual("dos", "fisica", "activa", utilDate, utilDate, "Avenida 123", "Maracay", "1", "Francia", "Juan", "Elsa Capunta");
		em.persist(c2);
		Individual c3 = new Individual("tres", "fisica", "activa", utilDate, utilDate, "Avenida 123", "Maracay", "2", "Alemania", "Juan", "Benito Camela");
		em.persist(c3);
		Individual c4 = new Individual("cuatro", "fisica", "activa", utilDate, utilDate, "Avenida 123", "Maracay", "3", "Espana", "Juana", "Gomez Vazquez");
		em.persist(c4);
		Individual c5 = new Individual("cinco", "fisica", "activa", utilDate, utilDate, "Avenida 123", "Maracay", "4", "Alemania", "Tractor", "Navarro Jimena");
		em.persist(c5);
		Individual c6 = new Individual("seis", "fisica", "bloqueada", utilDate, utilDate, "Avenida 123", "Maracay", "4", "PaisesBajos", "Juan", "Vazquez Vera");
		em.persist(c6);
		Empresa c7 = new Empresa("siete", "juridica", "activa", utilDate, utilDate, "Avenida 123", "Maracay", "2", "PaisesBajos", "Activision Blizzard");
		em.persist(c7);
		
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
		
		Divisa divisa = new Divisa("a", "e", 1.0);
		em.persist(divisa);
		CuentaReferencia cr = new CuentaReferencia("cuentaOrigen", "LaCuentaDePaco", 1000.0);
		cr.setDivisa(divisa);
		em.persist(cr);
		CuentaReferencia cr2 = new CuentaReferencia("cuentaDestino", "LaCuentaDeJuan", 100.0);
		cr2.setDivisa(divisa);
		em.persist(cr2);
		Individual ind = new Individual("Paco", "Individual", "Alta", sqlDate, sqlDate, "Una calle", "Alguna", "No", "No existe", "Juanito", "Perez", sqlDate);
		em.persist(ind);
		Segregada seg = new Segregada("Uno aleatorio", "No", true, sqlDate, sqlDate, "No hay", 0.0);
		seg.setCliente(ind);
		seg.setCuentaReferencia(cr);
		em.persist(seg);

		CuentaReferencia cref = new CuentaReferencia("8", "Cuenta Prueba", 0.00);
		em.persist(cref);
		
		Divisa divisa2 = new Divisa("fe", "falsoEuro", 1.0);
		em.persist(divisa2);
		CuentaReferencia cr3 = new CuentaReferencia("autoOrigen", "LaCuentaDeNoPaco", 1000.0);
		cr3.setDivisa(divisa);
		em.persist(cr3);
		CuentaReferencia cr4 = new CuentaReferencia("autoDestino", "LaCuentaDeNoJuan", 100.0);
		cr4.setDivisa(divisa);
		em.persist(cr4);
		PersonaAutorizada ind2 = new PersonaAutorizada("testTransaccionPer", "Pablo", "Vazques", "Una calle", sqlDate, "Quien sabe", sqlDate, sqlDate);
		em.persist(ind2);
		Empresa emp = new Empresa("LaDePablo", "aa", "aaa", sqlDate, sqlDate, "Falso", "No existe", "Quien sabe", "Venezuela", "Tesla");
		em.persist(emp);
		Query query = em.createQuery("Select c from PersonaAutorizada c where c.identificacion LIKE :fident");
		query.setParameter("fident", ind2.getIdentificacion());
		ind2 = (PersonaAutorizada) query.getSingleResult();
		query = em.createQuery("Select c from Empresa c where c.identificacion LIKE :fident");
		query.setParameter("fident", ind2.getIdentificacion());
		emp = (Empresa) query.getSingleResult();
		Autorizacion aut = new Autorizacion(new EmpresaPersAutoPK(emp.getID(), ind2.getID()), "Algundo", ind2, emp);
		em.persist(aut);
		Segregada seg2 = new Segregada("Dos Aleatorio", "No", true, sqlDate, sqlDate, "No hay", 0.0);
		seg2.setCliente(emp);
		seg2.setCuentaReferencia(cr3);
		em.persist(seg2);


		//No tocar abajo
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
}
