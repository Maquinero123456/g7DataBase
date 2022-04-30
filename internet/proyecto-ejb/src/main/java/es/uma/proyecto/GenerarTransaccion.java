package es.uma.proyecto;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.uma.proyecto.entidades.Cliente;
import es.uma.proyecto.entidades.Cuenta;
import es.uma.proyecto.entidades.CuentaFintech;
import es.uma.proyecto.entidades.CuentaReferencia;
import es.uma.proyecto.entidades.DepositadaEn;
import es.uma.proyecto.entidades.Individual;
import es.uma.proyecto.entidades.Segregada;
import es.uma.proyecto.entidades.Transaccion;
import es.uma.proyecto.exceptions.ClienteException;
import es.uma.proyecto.exceptions.CuentaException;
import es.uma.proyecto.exceptions.IndividualException;
import es.uma.proyecto.exceptions.SaldoException;

@Stateless
public class GenerarTransaccion implements GestionTransaccion{

	    private static final Logger LOG = Logger.getLogger(GenerarTransaccion.class.getCanonicalName());

	    @PersistenceContext(name="proyectoEJB")
	    private EntityManager em;

		@Override
		public void transaccionIndividual(String identificacion, String ibanOrigen, String ibanDestino, Double cantidad, String tipo) throws ClienteException, CuentaException, IndividualException, SaldoException {
			Query query = em.createQuery("Select c from Individual c WHERE c.identificacion LIKE :fident");
			query.setParameter("fident", identificacion);
			
			Individual individual = (Individual) query.getSingleResult();
			if(individual == null){
				throw new IndividualException("Individual no existe");
			}

			query = em.createQuery("Select r from CuentaReferencia r, Individual i where i.identificacion like :fident AND r.iban like :fiban");
			query.setParameter("fident", identificacion);
			query.setParameter("fiban", ibanOrigen);
			CuentaReferencia cuentaOrigen = (CuentaReferencia) query.getSingleResult();
			if(cuentaOrigen.getSaldo()<cantidad){
				throw new SaldoException("No hay suficiente dinero");
			}
			CuentaReferencia cuentaDestino = em.find(CuentaReferencia.class, ibanDestino);


			cuentaOrigen.setSaldo(cuentaOrigen.getSaldo()-cantidad);
			cuentaDestino.setSaldo(cuentaDestino.getSaldo()+cantidad*cuentaOrigen.getDivisa().getCambioEuro()/cuentaDestino.getDivisa().getCambioEuro());
			em.merge(cuentaOrigen);
			em.merge(cuentaDestino);
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			Transaccion trans = new Transaccion(sqlDate, cantidad, tipo, cuentaDestino, cuentaOrigen, cuentaDestino.getDivisa(), cuentaOrigen.getDivisa());
			em.persist(trans);
		}

		@Override
		public void transaccionAutorizado(String identificacion, String ibanOrigen, String ibanDestino, Double cantidad, String tipo) throws ClienteException, CuentaException, IndividualException, SaldoException {
			Query query = em.createQuery("Select c from PersonaAutorizada c WHERE c.identificacion LIKE :fident");
			query.setParameter("fident", identificacion);
			
			Individual individual = (Individual) query.getSingleResult();
			if(individual == null){
				throw new IndividualException("Individual no existe");
			}

			query = em.createQuery("Select r from CuentaReferencia r, PersonaAutorizada i where i.identificacion like :fident AND r.iban like :fiban");
			query.setParameter("fident", identificacion);
			query.setParameter("fiban", ibanOrigen);
			CuentaReferencia cuentaOrigen = (CuentaReferencia) query.getSingleResult();
			if(cuentaOrigen.getSaldo()<cantidad){
				throw new SaldoException("No hay suficiente dinero");
			}
			CuentaReferencia cuentaDestino = em.find(CuentaReferencia.class, ibanDestino);


			cuentaOrigen.setSaldo(cuentaOrigen.getSaldo()-cantidad);
			cuentaDestino.setSaldo(cuentaDestino.getSaldo()+cantidad*cuentaOrigen.getDivisa().getCambioEuro()/cuentaDestino.getDivisa().getCambioEuro());
			em.merge(cuentaOrigen);
			em.merge(cuentaDestino);
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			Transaccion trans = new Transaccion(sqlDate, cantidad, tipo, cuentaDestino, cuentaOrigen, cuentaDestino.getDivisa(), cuentaOrigen.getDivisa());
			em.persist(trans);
		}
		
	    
}
