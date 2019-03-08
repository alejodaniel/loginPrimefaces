package com.mycompany.DAO;

import com.mycompany.dominio.Usuario;
import java.util.List;
import javax.persistence.Query;
import javax.swing.JOptionPane;

public class UsuarioDao extends DAOAbstract<Usuario> {

    public UsuarioDao(Usuario usuario) {
        super(usuario);
    }

    @Override
    public List<Usuario> buscarTodos() {
        Query query = this.getEntityManager().createQuery("Select u from Usuario u");
        return query.getResultList();

    }

    public Usuario getUsuarioById(int idUsuario) {
        Query query = this.getEntityManager().createQuery("Select u from Usuario u where p.idUsuario=" + idUsuario);
        return (Usuario) query.getSingleResult();
    }

    public boolean verificacionLogin(String user, String pass) {
        Query query = this.getEntityManager().createQuery("Select u from Usuario u where u.user= '" + user + "' AND u.password= '" + pass + "'");

        if (query.getResultList().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public Usuario obtenerUsuario(String user, String pass) {
        Query query = this.getEntityManager().createQuery("Select u from Usuario u where u.user= '" + user + "' AND u.password= '" + pass + "'");
        return (Usuario) query.getSingleResult();
    }

    public List<Usuario> buscarPorCriterio(String escribir) {
        Query query = this.getEntityManager().createQuery("Select u from Usuario u where u.nombreUsuario like '%" + escribir + "%'");
        return query.getResultList();
    }

    public boolean usuariosIguales(String user) {
        Query query = this.getEntityManager().createQuery("Select u from Usuario u where u.user= '" + user + "'");

        if (query.getResultList().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean editarUsuario(Usuario usuario) {
        UsuarioDao ud = new UsuarioDao(usuario);
        try {
            ud.update();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean guardarUsuario(Usuario usuario) {
        UsuarioDao ud = new UsuarioDao(usuario);
        try {
            ud.persist();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean insertarAdmin() {
        Query query = this.getEntityManager().createQuery("Select u from Usuario u");
        if (query.getResultList().isEmpty()) {
            return false;
        } else {
            return true;
        }

    }
}
