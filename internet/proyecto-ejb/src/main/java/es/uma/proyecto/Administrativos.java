package es.uma.proyecto;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uma.proyecto.Autorizacion;
import es.uma.proyecto.Cliente;
import es.uma.proyecto.Empresa;
import es.uma.proyecto.EmpresaPersAutoPK;
import es.uma.proyecto.PersonaAutorizada;
import es.uma.proyecto.Usuario;
import es.uma.proyecto.exceptions.AdministrativoException;
import es.uma.proyecto.exceptions.AutorizacionException;
import es.uma.proyecto.exceptions.ClienteException;
import es.uma.proyecto.exceptions.CuentaException;
import es.uma.proyecto.exceptions.PersonaAutorizadaException;

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
	public void darAltaCliente(Cliente cliente) throws ClienteException {
		Cliente client = em.find(Cliente.class, cliente.getID());
		if(client == null){
			throw new ClienteException("Cliente no encontrado");
		}
		
		cliente.setEstado("Alta");
	}

	
	@Override
	public void darBajaCliente(Cliente cliente) throws ClienteException {
		Cliente client = em.find(Cliente.class, cliente.getID());
		if(client == null){
			throw new ClienteException("Cliente no encontrado");
		}
        
        client.setEstado("Alta");
	}
	

	@Override
	public Cliente modificarCliente(Cliente cliente) throws ClienteException {
		Cliente client = em.find(Cliente.class, cliente);
		if(client == null){
			throw new ClienteException("Cliente no encontrado");
		}
		client.setCiudad(cliente.getCiudad());
		client.setCodigoPostal(cliente.getCodigoPostal());
		client.setDireccion(cliente.getDireccion());
		client.setDireccion(cliente.getDireccion());
		client.setPais(cliente.getPais());
		client.setFechaBaja(cliente.getFechaBaja());
		client.setCuentas(cliente.getCuentas());
		client.setIdentificacion(cliente.getIdentificacion());
		return client;
	}

	@Override
	public void aperturaCuenta() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAutorizados(Empresa empresa, PersonaAutorizada persona, String tipo) throws ClienteException, PersonaAutorizadaException, AutorizacionException{
		Empresa emp = em.find(Empresa.class, empresa.getID());
		if(emp == null){
			throw new ClienteException("Empresa no encontrada");
		}
		PersonaAutorizada pers = em.find(PersonaAutorizada.class, persona.getID());
		if(pers == null){
			throw new PersonaAutorizadaException("Persona no encontrada");
		}
		EmpresaPersAutoPK fk = new EmpresaPersAutoPK(emp.getID(), pers.getID().toString());
		Autorizacion aut = new Autorizacion(fk, tipo, pers, emp);
		Autorizacion aut2 = em.find(Autorizacion.class, fk);
		if(aut2!=null){
			throw new AutorizacionException("Autorizacion ya existente");
		}
		em.persist(fk);
		em.persist(aut);
	}

	@Override
	public void modificarAutorizado(PersonaAutorizada persona) throws PersonaAutorizadaException{
		PersonaAutorizada per = em.find(PersonaAutorizada.class, persona.getID());
		if(per == null){
			throw new PersonaAutorizadaException("PersonaAutorizada no encontrada");
		}

		per.setApellidos(persona.getApellidos());
		per.setDireccion(persona.getDireccion());
		per.setEstado(persona.getEstado());
		per.setFechaFin(persona.getFechaFin());
		per.setFecha_Inicio(persona.getFecha_Inicio());
		per.setFecha_Nacimiento(persona.getFecha_Nacimiento());
		per.setIdentificacion(persona.getIdentificacion());
		per.setNombre(persona.getNombre());
		
	}

	@Override
	public void eliminarAutorizado(Empresa empresa, PersonaAutorizada persona) throws ClienteException, PersonaAutorizadaException, AutorizacionException{
		Empresa emp = em.find(Empresa.class, empresa.getID());
		if(emp == null){
			throw new ClienteException("Empresa no encontrado");
		}
		PersonaAutorizada pers = em.find(PersonaAutorizada.class, persona.getID());
		if(pers == null){
			throw new PersonaAutorizadaException("Persona no encontrada");
		}
		Autorizacion aut = em.find(Autorizacion.class, new Autorizacion(new EmpresaPersAutoPK(empresa.getID(), persona.getID().toString())));
		if(aut == null){
			throw new AutorizacionException("Autorizacion no encontrada");
		}
		em.remove(aut);
	}

	@Override
	public void cerrarCuenta() throws CuentaException{
		
		
	}
    
}
