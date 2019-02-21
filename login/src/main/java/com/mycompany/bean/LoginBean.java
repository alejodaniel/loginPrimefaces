/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;


import com.mycompany.DAO.DataSource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Iepiadm
 */
//se crea un bean 
@SessionScoped
@ManagedBean(name = "loginBean")
public class LoginBean {

    private String texto;
    private String password;
    private boolean logeado;

    public LoginBean() {
       DataSource.getEntityManager();
       
    }

    /**
     * @return the texto
     */
    public String getTexto() {
        return texto;
    }

    /**
     * @param texto the texto to set
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    /**
     * @return the password
     */
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

    public void login(ActionEvent action) {
        
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        if (getTexto() != null && getTexto().equals("alejo") && getPassword() != null && getPassword().equals("12345")) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", getTexto());
            logeado = true;
           
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "ERROR AL ACCEDER", "Credenciales incorrectos");
            logeado = false;
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("estaLogeado", logeado);
        if (logeado) {
            context.addCallbackParam("view","home.xhtml");
        }
        

    }   
}
