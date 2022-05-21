/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vista;

import es.uma.proyecto.entidades.Usuario;
import es.uma.proyecto.entidades.Empresa;
import es.uma.proyecto.CuentasUsuarios;
import es.uma.proyecto.GestionCuentasUsuarios;
import es.uma.proyecto.entidades.Cliente;
import es.uma.proyecto.entidades.PersonaAutorizada;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author francis
 */
@Named(value = "infoSesion")
@SessionScoped
public class InfoSesion implements Serializable {

    @EJB
    private GestionCuentasUsuarios cuentas;
    private Usuario usuario;
    
    /**
     * Creates a new instance of InfoSesion
     */
    public InfoSesion() {
    }

    public synchronized void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public synchronized Usuario getUsuario() {
        return usuario;
    }
    
    public synchronized Boolean getEsAdministrativo()
    {
        if (usuario != null)
        {
            return usuario.getEsAdministrativo();
        }
        return null;
    }
    
    public synchronized Cliente getCliente()
    {
        if (usuario != null)
        {
            return usuario.getCliente();
        }
        return null;
    }
    
    public synchronized PersonaAutorizada getPersonaAutorizada () {
        if (usuario != null)
        {
            return usuario.getPersonaAutorizada();
        }
        return null;
    }

    public synchronized void refrescarUsuario () {
        
    }

    /*
    public synchronized void refrescarUsuario()
    {
        try {
        if (usuario != null)
        {
            usuario = negocio.refrescarUsuario(usuario);
            System.out.println(usuario.getContactos().size());
        } 
        }
        catch (AgendaException e) {
            // TODO
        }
    }
    */
}
