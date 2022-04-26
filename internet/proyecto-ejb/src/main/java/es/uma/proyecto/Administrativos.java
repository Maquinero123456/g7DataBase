package es.uma.proyecto;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uma.proyecto.exceptions.AdministrativoException;

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
	public boolean darAltaCliente(Cliente cliente, boolean individual) throws AdministrativoException {
		Usuario user = em.find(Usuario.class, usuario);
        if(user == null){
            throw new AdministrativoException("Usuario no encontrado");
        }else {
        	cliente.
        }
		return false;
	}

	@Override
	public boolean darBajaCliente(Cliente cliente) throws AdministrativoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cliente modificarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}
    
}
