package vista;

import es.uma.proyecto.Administrativos;
import es.uma.proyecto.CuentasUsuarios;
import es.uma.proyecto.GestionAdministratitivos;
import es.uma.proyecto.GestionCuentasUsuarios;
import es.uma.proyecto.entidades.*;
import es.uma.proyecto.exceptions.AdministrativoException;
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
    @EJB
    private GestionCuentasUsuarios cuentas;
    private GestionAdministratitivos administratitivos;

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

    public String entrar(){
    	try{
    		if(cuentas.getUsuario(usuario.getNombre()).isEsAdministrativo()){
                Usuario user = administratitivos.iniciarSesion(usuario.getNombre(), usuario.getPassword());
                return "administrador.xhtml";
            }else{
                Usuario user = cuentas.iniciarSesion(usuario.getNombre(), usuario.getPassword());
                return "index.xhtml";
            }
		} catch (UsuarioException e) {
			FacesMessage fm = new FacesMessage("La cuenta no existe");
            FacesContext.getCurrentInstance().addMessage("registro:user", fm);
		} catch (AdministrativoException e) {
            FacesMessage fm = new FacesMessage("El administrador no existe");
            FacesContext.getCurrentInstance().addMessage("registro:user", fm);
        } catch (NullPointerException e) {
            FacesMessage fm = new FacesMessage("Usuario no existe");
            FacesContext.getCurrentInstance().addMessage("registro:user", fm);
        }
    	return "Error al iniciar sesion.";
    }
}
