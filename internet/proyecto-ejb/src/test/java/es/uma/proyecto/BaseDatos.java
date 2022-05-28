package es.uma.proyecto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import es.uma.proyecto.entidades.*;

public class BaseDatos {
	public static void inicializaBaseDatos(String nombreUnidadPersistencia) throws ParseException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombreUnidadPersistencia);
		EntityManager em = emf.createEntityManager();
	
		
		em.getTransaction().begin();
		//No tocar arriba
		
		Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-28");
		Date fechaPasada = new SimpleDateFormat("yyyy-MM-dd").parse("2015-08-15");
		
		Date f1 = new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-25");
		Date f2 = new SimpleDateFormat("yyyy-MM-dd").parse("2022-06-27");
		Date f3 = new SimpleDateFormat("yyyy-MM-dd").parse("2020-11-25");
		Date f4 = new SimpleDateFormat("yyyy-MM-dd").parse("2018-08-15");
		Date f5 = new SimpleDateFormat("yyyy-MM-dd").parse("2017-03-08");
		Date f6 = new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-16");
		Date f7 = new SimpleDateFormat("yyyy-MM-dd").parse("2020-08-15");
		
		Date nac1 = new SimpleDateFormat("yyyy-MM-dd").parse("1969-03-08");
		Date nac2 = new SimpleDateFormat("yyyy-MM-dd").parse("1945-05-16");
		Date nac3 = new SimpleDateFormat("yyyy-MM-dd").parse("2001-08-15");
		
		
		Individual c1 = new Individual("uno", "fisica", "activa", f1, null, "Avenida 123", "Maracay", "0", "PaisesBajos", "Juan", "Perez Castillo");	
		em.persist(c1);
		Individual c2 = new Individual("dos", "fisica", "activa", f2, utilDate, "Avenida 123", "Maracay", "1", "Francia", "Juan", "Elsa Capunta");
		em.persist(c2);
		Individual c3 = new Individual("tres", "fisica", "activa", f3, fechaPasada, "Avenida 123", "Maracay", "2", "Alemania", "Juan", "Benito Camela");
		em.persist(c3);
		Individual c4 = new Individual("cuatro", "fisica", "activa", f4, utilDate, "Avenida 123", "Maracay", "3", "Espana", "Juana", "Gomez Vazquez");
		em.persist(c4);
		Individual c5 = new Individual("cinco", "fisica", "activa", utilDate, utilDate, "Avenida 123", "Maracay", "4", "Alemania", "Tractor", "Navarro Jimena");
		em.persist(c5);
		Individual c6 = new Individual("seis", "fisica", "bloqueada", f5, utilDate, "Avenida 123", "Maracay", "4", "PaisesBajos", "Juan", "Vazquez Vera");
		em.persist(c6);
		Empresa c7 = new Empresa("siete", "juridica", "activa", f6, utilDate, "Avenida 123", "Maracay", "2", "PaisesBajos", "Activision Blizzard");
		em.persist(c7);
		Individual c8 = new Individual("ocho", "fisica", "activa", f2, null, "Av Falsa", "Berlin", "4", "Alemania", "Rodolfo", "Garcia Marquez");
		em.persist(c8);
		Individual c9 = new Individual("nueve", "fisica", "activa", f7, null, "Av Siempre viva", "Munich", "4", "Alemania", "Morongo", "Griman Marcano", nac1);
		em.persist(c9);
		Individual c10 = new Individual("diez", "fisica", "activa", f4, fechaPasada, "Av Reyes Catolicos", "Nuremberg", "4", "Alemania", "Sorocotopo", "Antony Oliviera", nac2);
		em.persist(c10);
		Individual c11 = new Individual("once", "fisica", "activa", f1, utilDate, "Bulevar Louis Pasteur", "Hamburgo", "4", "Alemania", "Brunga", "Rectangulo", nac3);
		em.persist(c11);
		
		CuentaFintech cf1 = new CuentaFintech("15", true, c1.getFechaAlta(), c1.getFechaBaja(), "segregada");
		cf1.setCliente(c1);
		em.persist(cf1);
		CuentaFintech cf2 = new CuentaFintech("2", true,  c2.getFechaAlta(), c2.getFechaBaja(), "segregada");
		cf2.setCliente(c2);
		em.persist(cf2);
		CuentaFintech cf3 = new CuentaFintech("3", true,  c3.getFechaAlta(), c3.getFechaBaja(), "segregada");
		cf3.setCliente(c3);
		em.persist(cf3);
		CuentaFintech cf4 = new CuentaFintech("4", true,  c4.getFechaAlta(), c4.getFechaBaja(), "segregada");
		cf4.setCliente(c4);
		em.persist(cf4);
		CuentaFintech cf5 = new CuentaFintech("5", true,  c5.getFechaAlta(), c5.getFechaBaja(), "segregada");
		cf5.setCliente(c5);
		em.persist(cf5);
		CuentaFintech cf6 = new CuentaFintech("6", false,  c6.getFechaAlta(), c6.getFechaBaja(), "segregada");
		cf6.setCliente(c6);
		em.persist(cf6);
		CuentaFintech cf7 = new CuentaFintech("7", true,  c7.getFechaAlta(), c7.getFechaBaja(), "segregada");
		cf7.setCliente(c7);
		em.persist(cf7);
		CuentaFintech cf8 = new CuentaFintech("8", true,  c8.getFechaAlta(), c8.getFechaBaja(), "segregada");
		cf8.setCliente(c8);
		em.persist(cf8);
		CuentaFintech cf9 = new CuentaFintech("9", false,  c9.getFechaAlta(), c9.getFechaBaja(), "segregada");
		cf9.setCliente(c9);
		em.persist(cf9);
		CuentaFintech cf10 = new CuentaFintech("10", true,  c10.getFechaAlta(), c10.getFechaBaja(), "segregada");
		cf10.setCliente(c10);
		em.persist(cf10);
		CuentaFintech cf11 = new CuentaFintech("11", false,  c11.getFechaAlta(), c11.getFechaBaja(), "segregada");
		cf11.setCliente(c11);
		em.persist(cf11);
		
		/*
		//Test transaccion individual
		Divisa euro = new Divisa("eur", "euro", 1.0);
		em.persist(euro);
		CuentaReferencia cr = new CuentaReferencia("cuentaOrigen", "LaCuentaDePaco", 1000.0);
		cr.setDivisa(euro);
		em.persist(cr);
		CuentaReferencia cr2 = new CuentaReferencia("cuentaDestino", "LaCuentaDeJuan", 100.0);
		cr2.setDivisa(euro);
		em.persist(cr2);
		Individual ind = new Individual("Paco", "Individual", "Alta", utilDate, utilDate, "Una calle", "Alguna", "No", "No existe", "Juanito", "Perez", utilDate);
		em.persist(ind);
		Segregada seg = new Segregada("Uno aleatorio", "No", true, utilDate, utilDate, "No hay", 0.0);
		seg.setCliente(ind);
		seg.setCuentaReferencia(cr);
		em.persist(seg);

		CuentaReferencia cref = new CuentaReferencia("8", "Cuenta Prueba", 0.00);
		cref.setDivisa(euro);
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
		Divisa euro1 = new Divisa("eu1", "euro1", 1.0);
		em.persist(euro1);
		Divisa dolar1 = new Divisa("us1", "dolar1", 0.95);
		em.persist(dolar1);

		CuentaReferencia cref3 = new CuentaReferencia("c3", "Cuenta Prueba", 100.00);
		cref3.setDivisa(euro1);
		em.persist(cref3);

		CuentaReferencia cref4 = new CuentaReferencia("c4", "Cuenta Prueba", 100.00);
		cref4.setDivisa(dolar1);
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
		 */

		//No tocar abajo
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
}
