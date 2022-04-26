package es.uma.proyecto;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uma.proyecto.exceptions.UsuarioException;

@Stateless
public class CuentasUsuarios implements GestionCuentasUsuarios{

    private static final Logger LOG = Logger.getLogger(CuentasUsuarios.class.getCanonicalName())

    @PersistenceContext(name="Cache")
    private EntityManager em;

    @Override
    public Usuario CrearUsuario(String usuario, String password, Boolean esAdministrativo) throws UsuarioException {
        Usuario user = em.find(Usuario.class, usuario);
        if(user != null){
            throw new UsuarioException("Usuario ya existe");
        }
        user = new Usuario(usuario, password, esAdministrativo);
        em.persist(user);
        return user;
    }
    
}
