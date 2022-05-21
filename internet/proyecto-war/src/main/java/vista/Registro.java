package vista;

import java.util.NoSuchElementException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import es.uma.proyecto.GestionCuentasUsuarios;
import es.uma.proyecto.entidades.*;
import es.uma.proyecto.exceptions.UsuarioException;

@Named(value = "registro")
@RequestScoped
public class Registro{
	
	private static final String PARAM_VALIDACION="codigoValidacion";
	private static final String PARAM_CUENTA = "cuenta";
    
    @EJB
    private GestionCuentasUsuarios cuentas;
    
    private Usuario usuario;
    private String repass;

    private String cuenta;
    private String codigoValidacion;

    private String mensajeValidacion;
    private boolean validacionOK;

    private boolean registroOK;

    public boolean isRegistroOK() {
        return registroOK;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getCodigoValidacion() {
        return codigoValidacion;
    }

    public void setCodigoValidacion(String codigoValidacion) {
        this.codigoValidacion = codigoValidacion;
    }

    public Registro() {
        usuario = new Usuario();
    }

    public String getRepass() {
        return repass;
    }

    public void setRepass(String repass) {
        this.repass = repass;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String registrarUsuario() {
        try {
            if (!usuario.getPassword().equals(repass)) {
                FacesMessage fm = new FacesMessage("Las contraseñas deben coincidir.");
                FacesContext.getCurrentInstance().addMessage("registro:repass", fm);
                return null;
            }
        }catch(NoSuchElementException e){}

           
        try {
        	cuentas.CrearUsuario(usuario);
        	registroOK = true;
            return "exitoRegistro.xhtml";
		} catch (UsuarioException e) {
			 FacesMessage fm = new FacesMessage("Existe un usuario con la misma cuenta.");
	         FacesContext.getCurrentInstance().addMessage("registro:user", fm);
		}
		return null;
    }

    /*public String validarCuenta() {
        try {
            if (cuenta != null && codigoValidacion != null) {
                cuentas.validarCuenta(cuenta, codigoValidacion);
            }
            mensajeValidacion = "La validación ha sido correcta, ahora puede acceder con este usuario.";
            validacionOK = true;
        } catch (AgendaException e) {
            mensajeValidacion = "Ha habido un error con la validación, compruebe que la URL es correcta.";
            validacionOK = false;
        }
        return null;
    }*/

    public String getMensajeValidacion() {
        return mensajeValidacion;
    }

    public boolean isValidacionOK() {
        return validacionOK;
    }

}
