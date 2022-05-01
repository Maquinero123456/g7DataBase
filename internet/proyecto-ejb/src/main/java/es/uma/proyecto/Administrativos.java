package es.uma.proyecto;

import java.util.Date;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.uma.proyecto.entidades.Autorizacion;
import es.uma.proyecto.entidades.Cliente;
import es.uma.proyecto.entidades.CuentaFintech;
import es.uma.proyecto.entidades.CuentaReferencia;
import es.uma.proyecto.entidades.DepositadaEn;
import es.uma.proyecto.entidades.Empresa;
import es.uma.proyecto.entidades.EmpresaPersAutoPK;
import es.uma.proyecto.entidades.PersonaAutorizada;
import es.uma.proyecto.entidades.PooledAccount;
import es.uma.proyecto.entidades.Segregada;
import es.uma.proyecto.entidades.Usuario;
import es.uma.proyecto.exceptions.AdministrativoException;
import es.uma.proyecto.exceptions.AutorizacionException;
import es.uma.proyecto.exceptions.ClienteException;
import es.uma.proyecto.exceptions.CuentaException;
import es.uma.proyecto.exceptions.PersonaAutorizadaException;

@Stateless
public class Administrativos implements GestionAdministratitivos{
	
	@SuppressWarnings("unused")
    private static final Logger LOG = Logger.getLogger(Administrativos.class.getCanonicalName());

    @PersistenceContext(name="proyectoEJB")
    private EntityManager em;

    @Override
    public Usuario iniciarSesion(String usuario, String password) throws AdministrativoException {
        Usuario user = em.find(Usuario.class, usuario);
        if(user == null){
            throw new AdministrativoException("Usuario no encontrado");
        }
        if(!user.getPassword().equals(password)){
            throw new AdministrativoException("Password incorrecta");
        }
        if(!user.getEsAdministrativo()) {
        	throw new AdministrativoException("El usuario NO es administrativo");
        }
        
        return user;
    }

    
	@Override
	public void darAltaCliente(String id) throws ClienteException {
		Cliente cliente = null;
		try{
			cliente = getCliente(id);
		}catch(ClienteException e){
			throw new ClienteException("No va");
		}
		
		if(cliente == null){
			throw new ClienteException("Cliente no encontrado");
		}
		
		cliente.setEstado("Alta");
		em.persist(cliente);
	}

	
	@Override
	public void darBajaCliente(String id) throws ClienteException {
		Cliente cliente = getCliente(id);
		if(cliente == null){
			throw new ClienteException("Cliente no encontrado");
		}
        
        cliente.setEstado("Baja");
        em.persist(cliente);
	}
	

	@Override
	public void modificarCliente(Cliente cliente) throws ClienteException {
		Cliente client = getCliente(cliente.getIdentificacion());
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
		em.merge(client);
	}

	
	@Override
	public void aperturaCuentaAgrupada(String iban, String id) throws CuentaException, ClienteException {
		PooledAccount account = em.find(PooledAccount.class, iban);

		if(account != null){
			throw new CuentaException("Ya existe una cuenta asociada al IBAN: " + iban+ ".");
		}

		Cliente c1 = getCliente(id);
		
		
		Date utilDate = new Date(System.currentTimeMillis());
		account = new PooledAccount(iban, null, true, utilDate, null, "Agrupada");
		account.setCliente(c1);
		em.persist(account);
	}

	@Override
	public void aperturaCuentaSegregada(String iban, String id, CuentaReferencia cuentaRef) throws CuentaException, ClienteException {
		Segregada account = em.find(Segregada.class, iban);

		if(account != null){
			throw new CuentaException("Ya existe una cuenta asociada al IBAN: " + iban+ ".");
		}

		Cliente c1 = null;
		Query query = em.createQuery("Select c from Cliente c where c.identificacion LIKE :fident");
		query.setParameter("fident", id);
		
		try{
			c1 = (Cliente) query.getSingleResult();
		}catch(NoResultException e){
			throw new ClienteException("EL cliente no existe");
		}
		
		if(cuentaRef == null) {
			throw new CuentaException("Cuenta referencia nula");
		}

		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

		account = new Segregada(iban, null, true, sqlDate, null, "Segregada");
		account.setCliente(c1);
		account.setCuentaReferencia(cuentaRef);
		em.persist(account);
	}

	
	@Override
	public void addAutorizados(long idEmp, long idPer, String tipo) throws ClienteException, PersonaAutorizadaException, AutorizacionException{
		Empresa emp = em.find(Empresa.class, idEmp);
		if(emp == null){
			throw new ClienteException("Empresa no encontrada.");
		}
		PersonaAutorizada pers = em.find(PersonaAutorizada.class, idPer);
		if(pers == null){
			throw new PersonaAutorizadaException("Persona no encontrada.");
		}
		
		EmpresaPersAutoPK fk = new EmpresaPersAutoPK(idEmp, idPer);
		Autorizacion autAux = em.find(Autorizacion.class, fk);
		if(autAux!=null){
			throw new AutorizacionException(pers.getApellidos() + " ya tiene autorización en " + emp.getRazonSocial() + ".");
		}
		
		Autorizacion aut = new Autorizacion(fk, tipo, pers, emp);
		
		em.persist(aut);
	}

	
	
	@Override
	public void modificarAutorizado(PersonaAutorizada persona) throws PersonaAutorizadaException{
		Query query = em.createQuery("Select c from PersonaAutorizada c where c.identificacion like :fident");
		query.setParameter("fident", persona.getIdentificacion());
		PersonaAutorizada per = (PersonaAutorizada) query.getSingleResult();
		if(per == null){
			throw new PersonaAutorizadaException("PersonaAutorizada no encontrada");
		}

		
		per.setApellidos(persona.getApellidos());
		per.setDireccion(persona.getDireccion());
		per.setEstado(persona.getEstado());
		per.setFechaFin(persona.getFechaFin());
		per.setFecha_Inicio(persona.getFecha_Inicio());
		per.setFecha_Nacimiento(persona.getFecha_Nacimiento());
		per.setNombre(persona.getNombre());
		em.merge(per);
	}

	
	@Override
	public void eliminarAutorizado(long idEmpresa, long idAutorizado) throws ClienteException, PersonaAutorizadaException, AutorizacionException{
		Empresa emp = em.find(Empresa.class, idEmpresa);
		if(emp == null){
			throw new ClienteException("Empresa no encontrada.");
		}
		PersonaAutorizada pers = em.find(PersonaAutorizada.class, idAutorizado);
		if(pers == null){
			throw new PersonaAutorizadaException("Persona no encontrada.");
		}
		Autorizacion aut = em.find(Autorizacion.class, new EmpresaPersAutoPK(idEmpresa, idAutorizado));
		if(aut == null){
			throw new AutorizacionException("La persona indicada no cuenta con autorización en la empresa: "+ emp.getRazonSocial()+".");
		}
		em.remove(aut);
	}

	
	
	@Override
	public void cerrarCuenta(String iban) throws CuentaException{
		CuentaFintech account = em.find(CuentaFintech.class, iban);
		if(account == null){
			throw new CuentaException("No existe ninguna cuenta asociada al IBAN: "+ iban +".");
		}
		
		if(account.getClasificacion().equalsIgnoreCase("segregada")) {
			Segregada saccount = em.find(Segregada.class, iban);
			if(saccount != null){
				if(saccount.getCuentaReferencia().getSaldo() == 0.0){
					saccount.setEstado(false);
				}
				else {
					throw new CuentaException("No se pudo dar de baja la cuenta, el saldo no es 0.");
				}
			}
		}
		
		else if(account.getClasificacion().equalsIgnoreCase("agrupada")) {
			PooledAccount paccount = em.find(PooledAccount.class, iban);
			if(paccount != null){
				boolean sinSaldo = true;
				if(!paccount.getDepositadaEn().isEmpty()) {
					for (DepositadaEn d : paccount.getDepositadaEn()) {
						if (d.getSaldo() > 0.0) {
							sinSaldo = false;
						}
					}
				}
				if(sinSaldo){
					paccount.setEstado(false);
				}
				else {
					throw new CuentaException("No se pudo dar de baja la cuenta, el saldo no es 0.");
				}
			}
		}
	}

    public void crearCliente(Cliente client) throws ClienteException {
        Query query = em.createQuery("SELECT cl from Cliente cl WHERE cl.identificacion = :fidentificacion");
		query.setParameter("fidentificacion", client.getIdentificacion()); 
        Cliente cli = null;
        try{
            cli = (Cliente) query.getSingleResult();
        }catch(NoResultException e){
            em.persist(client);
        }
        if(cli!=null){
            throw new ClienteException("Cliente ya existe");
        }
       
        
    }

    public Cliente getCliente(String id) throws ClienteException {
        Query query = em.createQuery("SELECT cl from Cliente cl WHERE cl.identificacion = :fidentificacion");
		query.setParameter("fidentificacion", id); 
        Cliente cli = null;
        try{
            cli = (Cliente) query.getSingleResult();
        }catch(NoResultException e){
            throw new ClienteException("Cliente no existe");
        }
        return cli;
        
    }
    
}
