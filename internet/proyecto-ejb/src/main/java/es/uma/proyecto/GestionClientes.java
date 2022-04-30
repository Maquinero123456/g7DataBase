package es.uma.proyecto;

import javax.ejb.Local;

import es.uma.proyecto.entidades.Cliente;
import es.uma.proyecto.entidades.Empresa;
import es.uma.proyecto.exceptions.ClienteException;
import es.uma.proyecto.exceptions.EmpresaException;

@Local
public interface GestionClientes {
    public void crearCliente(Cliente client) throws ClienteException;

    public Cliente getCliente(String id) throws ClienteException;

    public void crearEmpresa(Empresa emp) throws EmpresaException;

    public Empresa getEmpresa(String identificacion) throws EmpresaException;
}
