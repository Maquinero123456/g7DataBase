package es.uma.proyecto;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.uma.proyecto.entidades.Autorizacion;
import es.uma.proyecto.entidades.CuentaFintech;
import es.uma.proyecto.entidades.Empresa;
import es.uma.proyecto.entidades.EmpresaPersAutoPK;
import es.uma.proyecto.entidades.PersonaAutorizada;
import es.uma.proyecto.exceptions.AutorizacionException;
import es.uma.proyecto.exceptions.CuentaException;
import es.uma.proyecto.exceptions.EmpresaException;
import es.uma.proyecto.exceptions.EmpresaPersAutoPKException;
import es.uma.proyecto.exceptions.PersonaAutorizadaException;

@Stateless
public class Autorizados implements GestionAutorizados{
    @SuppressWarnings("unused")
    private static final Logger LOG = Logger.getLogger(Autorizados.class.getCanonicalName());

    @PersistenceContext(name="proyectoEJB")
    private EntityManager em;

    @Override
    public void addPersonaAutorizada(PersonaAutorizada pers) throws PersonaAutorizadaException {
        Query query = em.createQuery("SELECT cl from PersonaAutorizada cl WHERE cl.identificacion = :fidentificacion");
		query.setParameter("fidentificacion", pers.getIdentificacion()); 
        PersonaAutorizada cli = null;
        try{
            cli = (PersonaAutorizada) query.getSingleResult();
        }catch(NoResultException e){
            em.persist(pers);
        }
        if(cli!=null){
            throw new PersonaAutorizadaException("PersonaAutorizada ya existe");
        }
    }

    @Override
    public PersonaAutorizada getPersonaAutorizada(String identificacion) throws PersonaAutorizadaException {
        Query query = em.createQuery("SELECT cl from PersonaAutorizada cl WHERE cl.identificacion = :fidentificacion");
		query.setParameter("fidentificacion", identificacion); 
        PersonaAutorizada cli = null;
        try{
            cli = (PersonaAutorizada) query.getSingleResult();
        }catch(NoResultException e){
            throw new PersonaAutorizadaException("PersonaAutorizada no existe");
        }
        return cli;
        
    }

    @Override
    public void addAutorizacion(String pers, String emp)
            throws PersonaAutorizadaException, EmpresaException, EmpresaPersAutoPKException, AutorizacionException {
        PersonaAutorizada persona = getPersonaAutorizada(pers);
        Empresa empresa = getEmpresa(emp);
        EmpresaPersAutoPK fk = new EmpresaPersAutoPK(empresa.getId(), persona.getID());
        Autorizacion auto= em.find(Autorizacion.class, fk);
        if(auto != null){
            throw new AutorizacionException("Autorizacion ya existe");
        }
        auto= new Autorizacion(fk);
        auto.setEmpresa(empresa);
        auto.setPersona(persona);
        em.persist(auto);
    }

    @Override
    public Autorizacion getAutorizacion(String pers, String emp) throws EmpresaPersAutoPKException, AutorizacionException, EmpresaException, PersonaAutorizadaException {
        PersonaAutorizada persona = getPersonaAutorizada(pers);
        Empresa empresa = getEmpresa(emp);
        
        Autorizacion auto = em.find(Autorizacion.class, new EmpresaPersAutoPK(persona.getID(), empresa.getId()));
        if(auto == null){
            throw new AutorizacionException("Autorizacion no existe");
        }
        
        return auto;
    }

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
    public List<PersonaAutorizada> getPersonaAutorizadaNombre(String nombre, String apellido)
            throws PersonaAutorizadaException {
        Query query;
        if(nombre == null){
            query = em.createQuery("SELECT cl from PersonaAutorizada cl WHERE cl.apellidos = :fapellido");
            query.setParameter("fapellido", apellido); 
        }else if(apellido == null){
            query = em.createQuery("SELECT cl from PersonaAutorizada cl WHERE cl.nombre = :fnombre");
            query.setParameter("fnombre", nombre);
        }else{
            query = em.createQuery("SELECT cl from PersonaAutorizada cl WHERE cl.nombre = :fnombre AND cl.apellidos = :fapellido");
            query.setParameter("fnombre", nombre);
            query.setParameter("fapellido", apellido); 
        }
        
        if(query.getResultList() == null){
            throw new PersonaAutorizadaException("No existen personas autorizadas con esos apellidos o nombres");
        }
        
        return query.getResultList();
    }
    

    @Override
    public List<CuentaFintech> getCuentasPersonaAutorizada(String id) throws PersonaAutorizadaException, CuentaException{
        PersonaAutorizada pa = getPersonaAutorizada(id);
        List<CuentaFintech> cuentas = new ArrayList<>();
        for(Autorizacion e : pa.getAutorizacion()){
            cuentas.addAll(e.getEmpresa().getCuentas());
        }
        return cuentas;
    }
}
