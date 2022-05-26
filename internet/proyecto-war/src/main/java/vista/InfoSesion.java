package vista;

import es.uma.proyecto.GestionCuentasUsuarios;
import es.uma.proyecto.entidades.*;
import es.uma.proyecto.exceptions.UsuarioException;

import java.io.Serializable;

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
     * Crea una nueva instancia
     */
    public InfoSesion() {
    }

    public synchronized void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public synchronized Usuario getUsuario() {
        return usuario;
    }
    
    public synchronized String invalidarSesion(){
        if (usuario != null){
            usuario = null;
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        }
        return "exit.xhtml";
    }
    
    public synchronized void refrescarUsuario(){
        try {
        if (usuario != null){
            usuario = cuentas.getUsuario(usuario.getNombre());
        } 
        } catch (NullPointerException e) {
        	System.out.println(e);
        } catch (UsuarioException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
