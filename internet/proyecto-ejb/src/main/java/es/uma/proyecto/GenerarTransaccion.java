package es.uma.proyecto;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uma.proyecto.exceptions.ClienteException;
import es.uma.proyecto.exceptions.CuentaException;

@Stateless
public class GenerarTransaccion implements GestionTransaccion{

	    private static final Logger LOG = Logger.getLogger(GenerarTransaccion.class.getCanonicalName());

	    @PersistenceContext(name="Cache")
	    private EntityManager em;

		@Override
		public void transaccion(String id, String ib1, String ib2, double trans) throws ClienteException, CuentaException {
			Cliente c1 = em.find(Cliente.class, id);
			if(c1 == null){
				throw new ClienteException("No existe el usuario indicado.");
			}
			
			List<CuentaFintech> lista = c1.getCuentas();
			boolean esta = false;
			CuentaFintech aux = new CuentaFintech();
			
			for(CuentaFintech c:lista) {
				if(c.getIBAN() == ib1) {
					esta = true;
					aux = c;
				}
			}
			
			if(!esta) {
				throw new ClienteException("La cuenta origen no pertenece al cliente o no existe.");
			}
			
			CuentaReferencia origen = em.find(CuentaReferencia.class, aux.getIBAN());
			
			CuentaReferencia destino = em.find(CuentaReferencia.class, ib2);
			if(destino == null){
				throw new CuentaException("No existe la cuenta destino indicada.");
			}
			
			destino.setSaldo(trans+destino.getSaldo());
			origen.setSaldo(origen.getSaldo()-trans);
		}
		
	    
}
