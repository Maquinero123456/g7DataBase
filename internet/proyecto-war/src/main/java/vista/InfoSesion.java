/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vista;

import es.uma.proyecto.GestionCuentasUsuarios;
import es.uma.proyecto.entidades.*;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

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
    
    public synchronized String invalidarSesion()
    {
        if (usuario != null)
        {
            usuario = null;
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        }
        return "login.xhtml";
    }
    
    /*public synchronized void refrescarUsuario()
    {
        try {
        if (usuario != null)
        {
            usuario = cuentas.refrescarUsuario(usuario);
            System.out.println(usuario.getContactos().size());
        } 
        }
        catch (AgendaException e) {
            // TODO
        }
    }*/
    
}
