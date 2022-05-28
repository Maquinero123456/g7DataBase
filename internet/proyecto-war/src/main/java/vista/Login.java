package vista;

import es.uma.proyecto.GestionAdministrativos;
import es.uma.proyecto.GestionCuentasUsuarios;
import es.uma.proyecto.entidades.Usuario;
import es.uma.proyecto.exceptions.AdministrativoException;
import es.uma.proyecto.exceptions.PasswordException;
import es.uma.proyecto.exceptions.UsuarioException;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "login")
@RequestScoped
public class Login {

    @EJB
    private GestionCuentasUsuarios cuentas;
    @EJB 
    private GestionAdministrativos administratitivos;

    @Inject
    private InfoSesion sesion;

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
    		if(cuentas.getUsuario(usuario.getNombre()).getEsAdministrativo()){
                Usuario user = administratitivos.iniciarSesion(usuario.getNombre(), usuario.getPassword());
                sesion.setUsuario(user);
                return "admin.xhtml";
            }else{
                Usuario user = cuentas.iniciarSesion(usuario.getNombre(), usuario.getPassword());
                sesion.setUsuario(user);
                return "index.xhtml";
            }
		} catch (UsuarioException e) {
			FacesMessage fm = new FacesMessage("La cuenta no existe");
            FacesContext.getCurrentInstance().addMessage("login:user", fm);
		} catch (AdministrativoException e) {
            FacesMessage fm = new FacesMessage("El admin no existe");
            FacesContext.getCurrentInstance().addMessage("login:user", fm);
        } catch (NullPointerException e) {
            FacesMessage fm = new FacesMessage("El usuario no existe");
            FacesContext.getCurrentInstance().addMessage("login:userMessage", fm);
        } catch (PasswordException e) {
            FacesMessage fm = new FacesMessage("La contraseña no coincide");
            FacesContext.getCurrentInstance().addMessage("login:pass", fm);
        }
    	return "Error al iniciar sesion.";
    }
}
