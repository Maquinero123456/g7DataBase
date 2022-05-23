package es.uma.proyecto;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.uma.proyecto.entidades.Cliente;
import es.uma.proyecto.entidades.Empresa;
import es.uma.proyecto.entidades.Individual;
import es.uma.proyecto.exceptions.ClienteException;
import es.uma.proyecto.exceptions.EmpresaException;
import es.uma.proyecto.exceptions.IndividualException;

@Stateless
public class Clientes implements GestionClientes{
    @SuppressWarnings("unused")
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
        Query query = em.createQuery("SELECT cl from Empresa cl WHERE cl.identificacion = :fidentificacion");
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
        Query query = em.createQuery("SELECT cl from Empresa cl WHERE cl.identificacion = :fidentificacion");
		query.setParameter("fidentificacion", identificacion); 
        Empresa cli = null;
        try{
            cli = (Empresa) query.getSingleResult();
        }catch(NoResultException e){
            throw new EmpresaException("Empresa no existe");
        }
        return cli;
    }


    @Override
    public Individual getIndividual(String identificacion) throws ClienteException{
        Query query = em.createQuery("SELECT cl from Individual cl WHERE cl.identificacion = :fidentificacion");
		query.setParameter("fidentificacion", identificacion); 
        Individual cli = null;
        try{
            cli = (Individual) query.getSingleResult();
        }catch(NoResultException e){
            throw new ClienteException("Empresa no existe");
        }
        return cli;
    }

    @Override
    public List<Individual> getIndividualNombre(String nombre, String apellido) throws IndividualException {
        Query query;
        if(nombre == null){
            query = em.createQuery("SELECT cl from Individual cl WHERE cl.apellido = :fapellido");
            query.setParameter("fapellido", apellido); 
        }else if(apellido == null){
            query = em.createQuery("SELECT cl from Individual cl WHERE cl.nombre = :fnombre");
            query.setParameter("fnombre", nombre);
        }else{
            query = em.createQuery("SELECT cl from Individual cl WHERE cl.nombre = :fnombre AND cl.apellido = :fapellido");
            query.setParameter("fnombre", nombre);
            query.setParameter("fapellido", apellido); 
        }
        
        if(query.getResultList() == null){
            throw new IndividualException("No existe individuos con esos apellidos o nombres");
        }
        
        return query.getResultList();
    }

    @Override
    public List<Cliente> getClientesIBAN(String iban, Boolean status) throws ClienteException {
        Query query;
        if(iban == null){
            query = em.createQuery("SELECT cl from Cliente cl, CuentaFintech cf WHERE cf.estado = :festado");
            query.setParameter(":festado", status);
        }else if(status == null){
            query = em.createQuery("SELECT cl from Cliente cl, CuentaFintech cf WHERE cf.iban = :fiban");
            query.setParameter(":fiban", iban);
        }else{
            query = em.createQuery("SELECT cl from Cliente cl, CuentaFintech cf WHERE cf.iban = :fiban AND cf.estado = :festado");
            query.setParameter(":fiban", iban);
            query.setParameter(":festado", status);
        }
        return query.getResultList();
    }
    
}
