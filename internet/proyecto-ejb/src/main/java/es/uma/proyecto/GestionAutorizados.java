package es.uma.proyecto;

import java.util.List;

import javax.ejb.Local;

import es.uma.proyecto.entidades.Autorizacion;
import es.uma.proyecto.entidades.CuentaFintech;
import es.uma.proyecto.entidades.PersonaAutorizada;
import es.uma.proyecto.exceptions.AutorizacionException;
import es.uma.proyecto.exceptions.CuentaException;
import es.uma.proyecto.exceptions.EmpresaException;
import es.uma.proyecto.exceptions.EmpresaPersAutoPKException;
import es.uma.proyecto.exceptions.PersonaAutorizadaException;

@Local
public interface GestionAutorizados {
    public void addPersonaAutorizada(PersonaAutorizada pers) throws PersonaAutorizadaException;
    public PersonaAutorizada getPersonaAutorizada(String identificacion) throws PersonaAutorizadaException;
    public void addAutorizacion(String pers, String emp) throws PersonaAutorizadaException, EmpresaException, EmpresaPersAutoPKException, AutorizacionException;
    public Autorizacion getAutorizacion(String pers, String emp)  throws EmpresaPersAutoPKException, AutorizacionException, EmpresaException, PersonaAutorizadaException;
    public List<PersonaAutorizada> getPersonaAutorizadaNombre(String nombre, String apellido) throws PersonaAutorizadaException;
    public List<CuentaFintech> getCuentasPersonaAutorizada(String id) throws PersonaAutorizadaException, CuentaException;
}
