/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import com.mycompany.DAO.DataSource;
import com.mycompany.DAO.UsuarioDao;
import com.mycompany.dominio.Usuario;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.swing.JOptionPane;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Iepiadm
 */
//se crea un bean 
@SessionScoped
@ManagedBean(name = "registerBean")
public class RegisterBean {
    private Usuario usuario;
    private String user;
    private String password;
    private boolean logeado;

    public RegisterBean() {
        DataSource.getEntityManager();

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

    public void Register(ActionEvent action) {
        UsuarioDao usuarioDao = new UsuarioDao(usuario);

        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
         Usuario us = new Usuario();
         us.setUser(getUser());
         us.setPassword(getPassword());
       
        boolean estado = usuarioDao.guardarUsuario(us);
        if (estado == true) {
           msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El dato se gaurdo",user);
            setLogeado(true);
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se guardo el dato",null);
            setLogeado(false);
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("estaLogeado", isLogeado());
        if (isLogeado()) {
            context.addCallbackParam("view", "home.xhtml");
        } else if (estado == false) {
            context.addCallbackParam("view", "login.xhtml");
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
}
