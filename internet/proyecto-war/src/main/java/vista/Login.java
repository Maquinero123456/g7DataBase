/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import es.uma.informatica.sii.agendaee.entidades.Usuario;
import es.uma.informatica.sii.agendaee.negocio.AgendaException;
import es.uma.informatica.sii.agendaee.negocio.ContraseniaInvalidaException;
import es.uma.informatica.sii.agendaee.negocio.CuentaInactivaException;
import es.uma.informatica.sii.agendaee.negocio.CuentaInexistenteException;
import es.uma.informatica.sii.agendaee.negocio.Negocio;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author francis
 */
@Named(value = "login")
@RequestScoped
public class Login {

    @Inject
    private Negocio negocio;

    @Inject
    private InfoSesion sesion;

    private Usuario usuario;

    /**
     * Creates a new instance of login
     */
    public Login() {
        usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String entrar() {
        try {
            negocio.compruebaLogin(usuario);
            sesion.setUsuario(negocio.refrescarUsuario(usuario));
            return "contactos.xhtml";

        } catch (CuentaInexistenteException e) {
            FacesMessage fm = new FacesMessage("La cuenta no existe");
            FacesContext.getCurrentInstance().addMessage("login:user", fm);
        } catch (ContraseniaInvalidaException e) {
            FacesMessage fm = new FacesMessage("La contraseña no es correcta");
            FacesContext.getCurrentInstance().addMessage("login:pass", fm);
        } catch (CuentaInactivaException e) {
            FacesMessage fm = new FacesMessage("La cuenta existe pero no está activa");
            FacesContext.getCurrentInstance().addMessage("login:user", fm);
        } catch (AgendaException e) {
            FacesMessage fm = new FacesMessage("Error: " + e);
            FacesContext.getCurrentInstance().addMessage(null, fm);
        }
        return null;
    }

}
