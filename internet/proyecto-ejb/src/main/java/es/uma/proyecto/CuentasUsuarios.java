package es.uma.proyecto;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uma.proyecto.entidades.Usuario;
import es.uma.proyecto.exceptions.UsuarioException;

@Stateless
public class CuentasUsuarios implements GestionCuentasUsuarios{

    private static final Logger LOG = Logger.getLogger(CuentasUsuarios.class.getCanonicalName());

    @PersistenceContext(name="Proyecto")
    private EntityManager em;

    @Override
    public Usuario CrearUsuario(Usuario user) throws UsuarioException {
        Usuario usuario = em.find(Usuario.class, user.getNombre());
        if(usuario != null){
            throw new UsuarioException("Usuario ya existe");
        }
        em.persist(user);
        return user;
    }

    @Override
    public Usuario getUsuario(String nombre) throws UsuarioException {
        Usuario user = em.find(Usuario.class, nombre);
        if(user == null){
            throw new UsuarioException("Usuario no existe");
        }
        return user;
    }
    
}
