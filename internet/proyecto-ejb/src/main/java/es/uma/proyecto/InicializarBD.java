package es.uma.proyecto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.uma.proyecto.entidades.Autorizacion;
import es.uma.proyecto.entidades.Cliente;
import es.uma.proyecto.entidades.CuentaFintech;
import es.uma.proyecto.entidades.CuentaRefPoolAccPK;
import es.uma.proyecto.entidades.CuentaReferencia;
import es.uma.proyecto.entidades.DepositadaEn;
import es.uma.proyecto.entidades.Divisa;
import es.uma.proyecto.entidades.Empresa;
import es.uma.proyecto.entidades.EmpresaPersAutoPK;
import es.uma.proyecto.entidades.Individual;
import es.uma.proyecto.entidades.PersonaAutorizada;
import es.uma.proyecto.entidades.PooledAccount;
import es.uma.proyecto.entidades.Segregada;
import es.uma.proyecto.entidades.Transaccion;
import es.uma.proyecto.entidades.Usuario;

@Singleton
@Startup
public class InicializarBD {
    
    @PersistenceContext(name = "proyectoEJB")
	EntityManager em;

    @PostConstruct
    public void inicializar() throws ParseException{

        Usuario comprobacion = em.find(Usuario.class, "ponciano");
        if (comprobacion !=null) {
            return;
        }

        Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-28");

        Usuario user = new Usuario("ponciano", "ponciano", true, "ponciano@gmail.com");
        em.persist(user);
        
        Usuario admin = new Usuario("admin", "admin", true, "admin@gualet.com");
        em.persist(admin);

        Divisa euro = new Divisa("EUR", "Euro", 1.0);
        Divisa libra = new Divisa("GBP", "Libra", 1.17);
        Divisa dolar = new Divisa("USD", "Dolar", 0.93);

        em.persist(euro);
        em.persist(libra);
        em.persist(dolar);


        Usuario ana = new Usuario("ana", "ana", false, "ana@ana.com");
        em.persist(ana);

        Empresa emp = new Empresa();
        emp.setIdentificacion("P3310693A");
        emp.setTipoCliente("Empresa");
        emp.setEstado("Alta");
        emp.setFechaAlta(utilDate);
        emp.setDireccion("Calle falsa 123");
        emp.setCiudad("Serbia");
        emp.setCodigoPostal("NoTiene");
        emp.setPais("Peninsula Balcanica");
        emp.setRazonSocial("PabloNoToques S.A.");
        em.persist(emp);

        Usuario juan = new Usuario("juan", "juan", false, "juan@juan.com");
        em.persist(juan);


        Individual client = new Individual();
        client.setIdentificacion("63937528N");
        client.setTipoCliente("Individual");
        client.setEstado("Alta");
        client.setFechaAlta(utilDate);
        client.setDireccion("Calle no falsa 123");
        client.setCiudad("Serbia");
        client.setCodigoPostal("NoTiene");
        client.setPais("Peninsula Balcanica");
        client.setNombre("Pablo");
        client.setApellidos("Vazquez");
        client.setUsuario(juan);
        em.persist(client);

        PersonaAutorizada pers = new PersonaAutorizada("Y4001267V", "Victor", "Rodriguez", "una calle", utilDate, "Alta", utilDate, null);
        pers.setUsuario(ana);
        em.persist(pers);


        Autorizacion aut = new Autorizacion(new EmpresaPersAutoPK(emp.getId(), pers.getID()), "No", pers, emp);
        em.persist(aut);

       


        CuentaReferencia vg57 = new CuentaReferencia();
        vg57.setIBAN("VG57DDVS5173214964983931");
        vg57.setSaldo(100.0);
        vg57.setNombreBanco("Bancos Pablo SA");
        vg57.setDivisa(dolar);
        em.persist(vg57);

        Segregada seg = new Segregada();
        seg.setIBAN("NL63ABNA6548268733");
        seg.setCliente(emp);
        seg.setEstado(true);
        seg.setFechaApertura(utilDate);
        seg.setCuentaReferencia(vg57);
        seg.setClasificacion("Segregada");
        em.persist(seg);

        CuentaReferencia hn47 = new CuentaReferencia();
        hn47.setIBAN("HN47QUXH11325678769785549996");
        hn47.setSaldo(100.0);
        hn47.setNombreBanco("Santander");
        hn47.setDivisa(dolar);
        em.persist(hn47);

        Segregada seg2 = new Segregada();
        seg2.setIBAN("FR5514508000502273293129K55");
        seg2.setCliente(emp);
        seg2.setEstado(true);
        seg2.setFechaApertura(utilDate);
        seg2.setCuentaReferencia(hn47);
        seg2.setClasificacion("Segregada");
        em.persist(seg2);

        CuentaReferencia refFalsa = new CuentaReferencia();
        refFalsa.setIBAN("HN47QUXH113256787697855");
        refFalsa.setSaldo(100.0);
        refFalsa.setNombreBanco("BBVA");
        refFalsa.setDivisa(dolar);
        refFalsa.setEstado(false);
        em.persist(refFalsa);

        Segregada seg3 = new Segregada();
        seg3.setIBAN("DE31500105179261215675");
        seg3.setCliente(emp);
        seg3.setEstado(false);
        seg3.setFechaApertura(utilDate);
        seg3.setCuentaReferencia(refFalsa);
        seg3.setClasificacion("Segregada");
        em.persist(seg3);

        CuentaReferencia ES71 = new CuentaReferencia();
        ES71.setIBAN("ES7121007487367264321882");
        ES71.setSaldo(100.0);
        ES71.setNombreBanco("Santander");
        ES71.setDivisa(euro);
        em.persist(ES71);

        CuentaReferencia VG88 = new CuentaReferencia();
        VG88.setIBAN("VG88HBIJ4257959912673134");
        VG88.setSaldo(200.0);
        VG88.setNombreBanco("BBVA");
        VG88.setDivisa(dolar);
        em.persist(VG88);

        CuentaReferencia GB79 = new CuentaReferencia();
        GB79.setIBAN("GB79BARC20040134265953");
        GB79.setSaldo(134.0);
        GB79.setNombreBanco("UniCaja");
        GB79.setDivisa(libra);
        em.persist(GB79);

        PooledAccount pool = new PooledAccount("ES8400817251647192321264", null, true, utilDate, null, null);
        pool.setCliente(client);
        pool.setClasificacion("Pooled");
        em.persist(pool);

        DepositadaEn dep1 = new DepositadaEn(new CuentaRefPoolAccPK(ES71.getIBAN(), pool.getIBAN()), ES71, pool, ES71.getSaldo());
        em.persist(dep1);
        DepositadaEn dep2 = new DepositadaEn(new CuentaRefPoolAccPK(VG88.getIBAN(), pool.getIBAN()), VG88, pool, VG88.getSaldo());
        em.persist(dep2);
        DepositadaEn dep3 = new DepositadaEn(new CuentaRefPoolAccPK(GB79.getIBAN(), pool.getIBAN()), GB79, pool, GB79.getSaldo());
        em.persist(dep3);

        Transaccion tr = new Transaccion(utilDate, 200, "Transaccion", pool, seg3, dolar, dolar);
        em.persist(tr);
        
        
        // PRUEBAS CSV DEL INFORME ALEMAN
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
    }
}
