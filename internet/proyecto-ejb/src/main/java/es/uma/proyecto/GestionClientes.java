package es.uma.proyecto;

import javax.ejb.Local;

import es.uma.proyecto.entidades.Cliente;
import es.uma.proyecto.exceptions.ClienteException;

@Local
public interface GestionClientes {
    public void crearCliente(Cliente client) throws ClienteException;

    public Cliente getCliente(String id) throws ClienteException;
}
