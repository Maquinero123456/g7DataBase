package es.uma.proyecto;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uma.proyecto.exceptions.AdministrativoException;
import es.uma.proyecto.exceptions.ClienteException;

@Stateless
public class Administrativos implements GestionAdministratitivos{

    private static final Logger LOG = Logger.getLogger(Administrativos.class.getCanonicalName());

    @PersistenceContext(name="Cache")
    private EntityManager em;

    @Override
    public Usuario iniciarSesion(String usuario, String password) throws AdministrativoException {
        Usuario user = em.find(Usuario.class, usuario);
        if(user == null){
            throw new AdministrativoException("Usuario no encontrado");
        }
        if(!user.getPassword().equals(password)){
            throw new AdministrativoException("Contraseña erronea");
        }
        return user;
    }

	@Override
	public boolean darAltaCliente(Cliente cliente, boolean individual) throws ClienteException {
		Cliente client = em.find(Cliente.class, cliente.getID());
		if(client == null){
			throw new ClienteException("Cliente no encontrado");
		}
		if(individual){
			client.setTipoCliente("Individual");
		}else{
			cliente.setTipoCliente("PersonaAutorizada");
		}
		return true;

	}

	@Override
	public boolean darBajaCliente(Cliente cliente) throws ClienteException {
		Usuario user = em.find(Usuario.class, usuario);
        if(user == null){
            throw new ClienteException("Usuario no encontrado");
        }
        
        cliente.setTipoCliente("Baja");
        
		return true;
	}

	@Override
	public Cliente modificarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}
    
}
