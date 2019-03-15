/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import com.mycompany.DAO.DataSource;
import com.mycompany.DAO.UsuarioDao;
import com.mycompany.dominio.Usuario;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Iepiadm
 */
//se crea un bean 
@ViewScoped
@ManagedBean(name = "registerBean")
public class RegisterBean {

    private Usuario usuario;
    private String user;
    private String password;
    private boolean logeado;
    private List<Usuario> usuarios = null;

    public RegisterBean() {
//        DataSource.getEntityManager();

    }

    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLogeado() {
        return logeado;
    }

    /**
     * @param logeado the logeado to set
     */
    public void setLogeado(boolean logeado) {
        this.logeado = logeado;
    }

    public void register(ActionEvent action) {
        UsuarioDao usuarioDao = new UsuarioDao(usuario);
        System.out.println("user:"+user);
        System.out.println("pass:"+password);
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        Usuario us = new Usuario();
        us.setUser(getUser());
        us.setPassword(getPassword());

        boolean estado = usuarioDao.guardarUsuario(us);
        if (estado == true) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El Usuario y contraseña fueron guardados correctamente", user);
            setLogeado(true);
            DataSource dt = new DataSource();
            dt.getEntityManager();
            
           
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se guardaron los datos ingresados", null);
            setLogeado(false);
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("estaLogeado", isLogeado());
        if (isLogeado()) {
            context.addCallbackParam("view", "index.xhtml");
        } else if (estado == false) {
            context.addCallbackParam("view", "home.xhtml");
        }

    }

    public void newUser(ActionEvent action) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;

        UsuarioDao usuarioDao = new UsuarioDao(usuario);
        if (usuario != null) {
            usuario.setUser(user);
            usuario.setPassword(password);
            boolean estado = usuarioDao.editarUsuario(usuario);
            if (estado == true) {
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El usuario se edito correctamente", user);
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se editaron los datos", null);
            }
        } else {
            Usuario us = new Usuario();
            us.setUser(getUser());
            us.setPassword(getPassword());

            boolean estado = usuarioDao.guardarUsuario(us);
            if (estado == true) {
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El Usuario y contraseña fueron guardados correctamente", user);
                setLogeado(true);
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se guardaron los datos ingresados", null);
                setLogeado(false);
            }
            FacesContext.getCurrentInstance().addMessage(null, msg);
            context.addCallbackParam("estaLogeado", isLogeado());
            if (isLogeado()) {
                context.addCallbackParam("view", "home.xhtml");
            } else if (estado == false) {
                context.addCallbackParam("view", "index.xhtml");
            }

        }

    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the usuarios
     */
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
