package es.uma.proyecto;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uma.proyecto.entidades.Usuario;
import es.uma.proyecto.exceptions.ClienteException;
import es.uma.proyecto.exceptions.UsuarioException;

@Stateless
public class CuentasUsuarios implements GestionCuentasUsuarios{

    private static final Logger LOG = Logger.getLogger(CuentasUsuarios.class.getCanonicalName());

    @PersistenceContext(name="proyectoEJB")
    private EntityManager em;

    @Override
    public void CrearUsuario(Usuario user) throws UsuarioException {
        Usuario usuario = em.find(Usuario.class, user.getNombre());
        if(usuario != null){
            throw new UsuarioException("El usuario ya existe.");
        }
        em.persist(user);
    }

    @Override
    public Usuario getUsuario(String nombre) throws UsuarioException {
        Usuario user = em.find(Usuario.class, nombre);
        if(user == null){
            throw new UsuarioException("El usuario no existe.");
        }
        return user;
    }

    @Override
    public Usuario iniciarSesion(String nombre, String password) throws UsuarioException {
        Usuario user = em.find(Usuario.class, nombre);
        if(user == null){
            throw new UsuarioException("El usuario no existe.");
        }
        if(!user.getPassword().equals(password)){
            throw new UsuarioException("Password incorrecta");
        }
        if(user.getEsAdministrativo()){
            throw new UsuarioException("No puedes iniciar sesion como administrativo aqui");
        }
        System.out.println(user);
        return user;
    }
    
    
}
