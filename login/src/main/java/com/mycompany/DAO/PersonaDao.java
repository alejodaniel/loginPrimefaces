
package com.mycompany.DAO;

import com.mycompany.dominio.Personas;
import com.mycompany.dominio.Usuario;
import java.util.List;
import javax.persistence.Query;

public class PersonaDao extends DAOAbstract<Personas> {

    public PersonaDao(Personas persona) {
        //super llama al constructor padre
        super(persona);
    }

    @Override
    public List<Personas> buscarTodos() {
//        Query query = this.getEntityManager().createQuery("Select * from Persona ");   SQL
        Query query = this.getEntityManager().createQuery("Select p from Persona p");
        return query.getResultList();
    }

    public Personas getPersonaById(int idPersona) {
        Query query = this.getEntityManager().createQuery("Select p from Persona p where p.idPersona=" + idPersona);
        return (Personas) query.getSingleResult();
    }

    public List<Personas> buscarPorCriterio(String agregar) {
        Query query = this.getEntityManager().createQuery("Select p from Persona p where p.nombre like '%" + agregar + "%'");
        return query.getResultList();

    }

    public Usuario getUsuarioById(int idPersona) {
        Query query = this.getEntityManager().createQuery("Select p from Usuario p where p.idUsuario=" + idPersona);
        return (Usuario) query.getSingleResult();

    }

    public boolean editarPersona(Personas persona) {
        PersonaDao pd = new PersonaDao(persona);
        try {
            pd.update();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean guardarPersona(Personas persona) {
        PersonaDao pd = new PersonaDao(persona);
        try {
            pd.persist();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
