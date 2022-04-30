package es.uma.proyecto;

import java.util.logging.Logger;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.uma.proyecto.entidades.Cliente;
import es.uma.proyecto.entidades.Empresa;
import es.uma.proyecto.exceptions.ClienteException;
import es.uma.proyecto.exceptions.EmpresaException;

@Stateless
public class Clientes implements GestionClientes{

    private static final Logger LOG = Logger.getLogger(Clientes.class.getCanonicalName());

    @PersistenceContext(name="proyectoEJB")
    private EntityManager em;

    @Override
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

    @Override
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

    @Override
    public void crearEmpresa(Empresa emp) throws EmpresaException{
        Query query = em.createQuery("SELECT cl from Cliente cl WHERE cl.identificacion = :fidentificacion");
		query.setParameter("fidentificacion", emp.getIdentificacion()); 
        Empresa cli = null;
        try{
            cli = (Empresa) query.getSingleResult();
        }catch(NoResultException e){
            em.persist(emp);
        }
        if(cli!=null){
            throw new EmpresaException("Cliente ya existe");
        }
    }


    @Override
    public Empresa getEmpresa(String identificacion) throws EmpresaException{
        Query query = em.createQuery("SELECT cl from Cliente cl WHERE cl.identificacion = :fidentificacion");
		query.setParameter("fidentificacion", identificacion); 
        Empresa cli = null;
        try{
            cli = (Empresa) query.getSingleResult();
        }catch(NoResultException e){
            throw new EmpresaException("Empresa no existe");
        }
        return cli;
    }

    public Clientes(){
    }
    
}
