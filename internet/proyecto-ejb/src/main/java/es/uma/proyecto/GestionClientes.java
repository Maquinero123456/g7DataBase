package es.uma.proyecto;

import java.util.List;

import javax.ejb.Local;

import es.uma.proyecto.entidades.Cliente;
import es.uma.proyecto.entidades.Empresa;
import es.uma.proyecto.entidades.Individual;
import es.uma.proyecto.exceptions.ClienteException;
import es.uma.proyecto.exceptions.EmpresaException;
import es.uma.proyecto.exceptions.IndividualException;

@Local
public interface GestionClientes {
    public void crearCliente(Cliente client) throws ClienteException;

    public Cliente getCliente(String id) throws ClienteException;

    public void crearEmpresa(Empresa emp) throws EmpresaException;

    public Empresa getEmpresa(String identificacion) throws EmpresaException;

    public Individual getIndividual(String identificacion) throws ClienteException;

    public List<Individual> getIndividualNombre(String nombre, String apellido) throws IndividualException;

    public List<Cliente> getClientesIBAN(String iban, Boolean status) throws ClienteException;
}
