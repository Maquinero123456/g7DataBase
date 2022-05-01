package es.uma.proyecto;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.sun.istack.Pool;
import es.uma.proyecto.entidades.*;
import es.uma.proyecto.exceptions.PersonaAutorizadaException;

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
		
		Divisa euro = new Divisa("eur", "euro", 1.0);
		em.persist(euro);
		Divisa dolar = new Divisa("usd", "dolar", 0.95);
		em.persist(dolar);
		CuentaReferencia cr = new CuentaReferencia("cuentaOrigen", "LaCuentaDePaco", 1000.0);
		cr.setDivisa(euro);
		em.persist(cr);
		CuentaReferencia cr2 = new CuentaReferencia("cuentaDestino", "LaCuentaDeJuan", 100.0);
		cr2.setDivisa(dolar);
		em.persist(cr2);
		Individual ind = new Individual("Paco", "Individual", "Alta", utilDate, utilDate, "Una calle", "Alguna", "No", "No existe", "Juanito", "Perez", utilDate);
		em.persist(ind);
		Segregada seg = new Segregada("Uno aleatorio", "No", true, utilDate, utilDate, "No hay", 0.0);
		seg.setCliente(ind);
		seg.setCuentaReferencia(cr);
		em.persist(seg);

		CuentaReferencia cref = new CuentaReferencia("8", "Cuenta Prueba", 0.00);
		cref.setDivisa(dolar);
		em.persist(cref);
		
		CuentaReferencia cref2 = new CuentaReferencia("9", "Cuenta Prueba 2", 0.00);
		cref2.setDivisa(euro);
		em.persist(cref2);

		

		Divisa falsoEuro = new Divisa("fe", "falsoEuro", 1.0);
		em.persist(falsoEuro);
		PersonaAutorizada persAuto = new PersonaAutorizada("Pablo", "Pablo", "Vazques", "Calle falsa", utilDate, "Baja", utilDate, utilDate);
		em.persist(persAuto);
		Empresa emp = new Empresa("empTrans", "aa", "aaa", utilDate, utilDate, "Calle falsa", "Cuidad falsa", "falso", "Uno", "tesla");
		em.persist(emp);
		Query query = em.createQuery("SELECT c FROM PersonaAutorizada c WHERE c.identificacion LIKE :fident");
		query.setParameter("fident", "Pablo");
		persAuto= (PersonaAutorizada) query.getSingleResult();
		query = em.createQuery("SELECT c FROM PersonaAutorizada c WHERE c.identificacion LIKE :fident");
		query.setParameter("fident", "Pablo");
		persAuto= (PersonaAutorizada) query.getSingleResult();
		CuentaReferencia cuenta2 = new CuentaReferencia("autorizadoOrigen", "No lo sabe nadie", 1000.0);
		cuenta2.setDivisa(falsoEuro);
		em.persist(cuenta2);
		CuentaReferencia cuenta3 = new CuentaReferencia("autorizadoDestino", "Alguien lo sabra", 100.0);
		cuenta3.setDivisa(falsoEuro);
		em.persist(cuenta3);
		Segregada unaSegregada = new Segregada("segregadaFalsisima", "No", true, utilDate, utilDate, "AAAAAAAAAAAAA", 0.0);
		unaSegregada.setCliente(emp);
		unaSegregada.setCuentaReferencia(cuenta2);
		em.persist(unaSegregada);


		//Test apertura cuenta agrupada
		Cliente apertCuentaAgrupadaCliente = new Cliente("apertCuentaAgrupadaCliente", "Cliente", "Alta", utilDate, utilDate, "Calle falsa", "Ciudad falsa", "CP falso", "Pais falso");
		em.persist(apertCuentaAgrupadaCliente);

		//Test apertura cuenta Segregada
		Cliente apertCuentaSegregadaCliente = new Cliente("apertCuentaSegregadaCliente", "Cliente", "Alta", utilDate, utilDate, "Calle falsa", "Ciudad falsa", "CP falso", "Pais falso");
		em.persist(apertCuentaSegregadaCliente);
		CuentaReferencia apertCuentaSegregadaReferencia = new CuentaReferencia("apertCuentaSegregadaReferencia", "apertCuentaSegregadaReferencia", 100.0);
		apertCuentaSegregadaReferencia.setDivisa(falsoEuro);
		em.persist(apertCuentaSegregadaReferencia);


		//TEST DIVISAS PRUEBA
		Divisa euro1 = new Divisa("eur1", "euro1", 1.0);
		em.persist(euro1);
		Divisa dolar1 = new Divisa("usd1", "dolar1", 0.95);
		em.persist(dolar1);

		CuentaReferencia cref3 = new CuentaReferencia("c3", "Cuenta Prueba", 100.00);
		cref.setDivisa(euro1);
		em.persist(cref3);

		CuentaReferencia cref4 = new CuentaReferencia("c4", "Cuenta Prueba", 100.00);
		cref.setDivisa(dolar1);
		em.persist(cref4);

		Cliente clienteDivisa = new Cliente("testDivisa", "fisica", "Alta", utilDate, "Avenida 123", "Maracay", "123", "PaisesBajos");
		em.persist(clienteDivisa);

		PooledAccount pooledAccountDivisa = new PooledAccount("ES44",null, true, utilDate, null, "agrupada");
		pooledAccountDivisa.setCliente(clienteDivisa);
		em.persist(pooledAccountDivisa);

		DepositadaEn depositadaEnDivisa3 = new DepositadaEn(new CuentaRefPoolAccPK(cref3.getIBAN(), pooledAccountDivisa.getIBAN()), cref3, pooledAccountDivisa, cref3.getSaldo());
		em.persist(depositadaEnDivisa3);

		DepositadaEn depositadaEnDivisa4 = new DepositadaEn(new CuentaRefPoolAccPK(cref4.getIBAN(), pooledAccountDivisa.getIBAN()), cref4, pooledAccountDivisa, cref4.getSaldo());
		em.persist(depositadaEnDivisa4);

		//Modificar Autorizado
		PersonaAutorizada modificadAutorizad = new PersonaAutorizada("PabloDejaDeTocar", "Pablo", "Vazques", "Quien sabe", utilDate, "Alta", utilDate, null);
		em.persist(modificadAutorizad);


		//No tocar abajo
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
}
