package es.uma.proyecto;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PostLoad;
import javax.persistence.Query;

import es.uma.proyecto.entidades.Autorizacion;
import es.uma.proyecto.entidades.Cliente;
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
    public void inicializar(){
        Usuario comprobacion = em.find(Usuario.class, "ponciano");
        if (comprobacion !=null) {
            return;
        }

        Date utilDate = new Date(System.currentTimeMillis());

        Usuario user = new Usuario("ponciano", "Ponciano123", true, "ponciano@gmail.com");
        em.persist(user);
        
        Usuario admin = new Usuario("admin", "admin", true, "admin@gualet.com");
        em.persist(admin);

        Divisa euro = new Divisa("EUR", "Euro", 1.0);
        Divisa libra = new Divisa("GBP", "Libra", 1.17);
        Divisa dolar = new Divisa("USD", "Dolar", 0.93);

        em.persist(euro);
        em.persist(libra);
        em.persist(dolar);

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

        em.persist(client);

        PersonaAutorizada pers = new PersonaAutorizada("Y4001267V", "Victor", "Rodriguez", "una calle", utilDate, "Alta", utilDate, null);
        em.persist(pers);

        CuentaReferencia vg57 = new CuentaReferencia();
        vg57.setIBAN("VG57DDVS5173214964983931");
        vg57.setSaldo(100.0);
        vg57.setNombreBanco("Pablo Dineros");
        vg57.setDivisa(dolar);
        em.persist(vg57);

        Segregada seg = new Segregada();
        seg.setIBAN("NL63ABNA6548268733");
        seg.setCliente(emp);
        seg.setEstado(true);
        seg.setFechaApertura(utilDate);
        seg.setCuentaReferencia(vg57);
        em.persist(seg);

        CuentaReferencia hn47 = new CuentaReferencia();
        hn47.setIBAN("HN47QUXH11325678769785549996");
        hn47.setSaldo(100.0);
        hn47.setNombreBanco("Pablo Dineros");
        hn47.setDivisa(dolar);
        em.persist(hn47);

        Segregada seg2 = new Segregada();
        seg2.setIBAN("FR5514508000502273293129K55");
        seg2.setCliente(emp);
        seg2.setEstado(true);
        seg2.setFechaApertura(utilDate);
        seg2.setCuentaReferencia(hn47);
        em.persist(seg2);

        CuentaReferencia refFalsa = new CuentaReferencia();
        refFalsa.setIBAN("HN47QUXH113256787697855");
        refFalsa.setSaldo(100.0);
        refFalsa.setNombreBanco("Pablo Dineros");
        refFalsa.setDivisa(dolar);
        refFalsa.setEstado(false);
        em.persist(refFalsa);

        Segregada seg3 = new Segregada();
        seg3.setIBAN("DE31500105179261215675");
        seg3.setCliente(emp);
        seg3.setEstado(false);
        seg3.setFechaApertura(utilDate);
        seg3.setCuentaReferencia(refFalsa);
        em.persist(seg3);

        CuentaReferencia ES71 = new CuentaReferencia();
        ES71.setIBAN("ES7121007487367264321882");
        ES71.setSaldo(100.0);
        ES71.setNombreBanco("Pablo Dineros");
        ES71.setDivisa(euro);
        em.persist(ES71);

        CuentaReferencia VG88 = new CuentaReferencia();
        VG88.setIBAN("VG88HBIJ4257959912673134");
        VG88.setSaldo(200.0);
        VG88.setNombreBanco("Pablo Dineros");
        VG88.setDivisa(dolar);
        em.persist(VG88);

        CuentaReferencia GB79 = new CuentaReferencia();
        GB79.setIBAN("GB79BARC20040134265953");
        GB79.setSaldo(134.0);
        GB79.setNombreBanco("Pablo Dineros");
        GB79.setDivisa(libra);
        em.persist(GB79);
        
    }

    @PostLoad
    public void inicio2(){
        Usuario comprobacion = em.find(Usuario.class, "ponciano");
        if (comprobacion !=null) {
            return;
        }

        Date utilDate = new Date(System.currentTimeMillis());


        Empresa emp = null;
        PersonaAutorizada pers = null;

        Query query = em.createQuery("SELECT c FROM PersonaAutorizada c WHERE c.identificacion LIKE :fident");
		query.setParameter("fident", "Y4001267V");
		pers = (PersonaAutorizada) query.getSingleResult();
        query = em.createQuery("SELECT c FROM Empresa c WHERE c.identificacion LIKE :fident");
		query.setParameter("fident", emp.getIdentificacion());
		emp = (Empresa) query.getSingleResult();

        Autorizacion aut = new Autorizacion(new EmpresaPersAutoPK(emp.getID(), pers.getID()), "No", pers, emp);
        em.persist(aut);

        Usuario juan = new Usuario("juan", "Juanito123", false, "juan@juan.com");
        query = em.createQuery("SELECT c FROM Individual c WHERE c.identificacion LIKE :fident");
		query.setParameter("fident", "63937528N");
        juan.setCliente((Individual) query.getSingleResult());
        em.persist(juan);

        Usuario ana = new Usuario("ana", "Anita123", false, "ana@ana.com");
        ana.setPersonaAutorizada(pers);
        em.persist(ana);

        PooledAccount pool = new PooledAccount("ES8400817251647192321264", null, true, utilDate, null, null);
        query = em.createQuery("SELECT c FROM Cliente c WHERE c.identificacion LIKE :fident");
		query.setParameter("fident", "63937528N");
		System.out.print(false);
        pool.setCliente((Cliente) query.getSingleResult());
        em.persist(pool);

        CuentaReferencia ES71 = em.find(CuentaReferencia.class, "ES7121007487367264321882");
        CuentaReferencia VG88 = em.find(CuentaReferencia.class, "VG88HBIJ4257959912673134");
        CuentaReferencia GB79 = em.find(CuentaReferencia.class, "GB79BARC20040134265953");

        DepositadaEn dep1 = new DepositadaEn(new CuentaRefPoolAccPK(ES71.getIBAN(), pool.getIBAN()), ES71, pool, ES71.getSaldo());
        em.persist(dep1);
        DepositadaEn dep2 = new DepositadaEn(new CuentaRefPoolAccPK(VG88.getIBAN(), pool.getIBAN()), VG88, pool, VG88.getSaldo());
        em.persist(dep2);
        DepositadaEn dep3 = new DepositadaEn(new CuentaRefPoolAccPK(GB79.getIBAN(), pool.getIBAN()), GB79, pool, GB79.getSaldo());
        em.persist(dep3);

        Divisa dolar = em.find(Divisa.class, "USD");

        Segregada seg3 = em.find(Segregada.class, "NL63ABNA6548268733");


        Transaccion tr = new Transaccion(utilDate, 200, "Transaccion", pool, seg3, dolar, dolar);
        em.persist(tr);
    }
}
