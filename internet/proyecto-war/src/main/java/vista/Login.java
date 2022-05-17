package vista;

import es.uma.proyecto.CuentasUsuarios;
import es.uma.proyecto.entidades.*;
import es.uma.proyecto.exceptions.UsuarioException;

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
    private CuentasUsuarios cuentas;

    private Usuario usuario;

    /**
     * Crea una nueva instancia de 
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
    	try{
    		Usuario user = cuentas.iniciarSesion(usuario.getNombre(), usuario.getPassword());
    		return "index.xhtml";
		}catch (UsuarioException e) {
			FacesMessage fm = new FacesMessage("La cuenta no existe");
            FacesContext.getCurrentInstance().addMessage("registro:user", fm);
		}
    	return "Error al iniciar sesion.";
    }
}
