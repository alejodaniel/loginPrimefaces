/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import com.mycompany.DAO.DataSource;
import com.mycompany.DAO.UsuarioDao;
import static com.mycompany.bean.LoginBean.getLista;
import com.mycompany.dominio.Usuario;
import com.mycompany.ucc.Usuarioucc;
import com.mycompany.util.SessionUtils;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

/**
 *
 * @author Iepiadm
 */
//se crea un bean 
@SessionScoped
@ManagedBean(name = "loginBean")
public class LoginBean {

    private String user;
    private String accion;
    private String password;
    private boolean logeado = false;
    private List<Usuario> usuarios = null;
    private Usuario usuario24;
    private static List<Usuario> lista = new ArrayList();
    

    public List<Usuario> getUsuario() {
        UsuarioDao ud = new UsuarioDao(usuario24);
        usuarios = ud.buscarTodos();
        System.out.println("USER:" + usuarios);
        return usuarios;
    }

    public LoginBean() {
        DataSource.getEntityManager();

    }

    public static List<Usuario> getLista() {
        return lista;
    }

    /**
     * @param aLista the lista to set
     */
    public static void setLista(List<Usuario> aLista) {
        lista = aLista;
    }

    /**
     * @return the texto
     */
    public String getUser() {
        return user;
    }

    /**
     * @param texto the texto to set
     */
    public void setUser(String user) {
        this.user = user;
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

    public void listar() {
        Usuario u = new Usuario();
        u.setIdLogin(1);
        u.setUser(getUser());
        u.setPassword(getPassword());
        getLista().add(u);

    }

    public void login(ActionEvent action) {
        this.getUsuario();
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        // Usuario us = new Usuario();
        UsuarioDao ud = new UsuarioDao(null);
        boolean estado = ud.verificacionLogin(getUser(), getPassword());
        if (estado == true) {
            ud = new UsuarioDao(null);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", getUser());
            System.out.println("WELCOME:  "+user);
            setLogeado(true);
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "ERROR AL ACCEDER", "Credenciales incorrectos");
            setLogeado(false);
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("estaLogeado", isLogeado());
        if (isLogeado()) {
            System.out.println("hola");
            context.addCallbackParam("view", "faces/home.xhtml");
        } else if (estado == false) {
            context.addCallbackParam("view", "/index.xhtml");
        }

    }

    public String action() {
        logeado = true;
        return "/home.xhtml";
    }

    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "index";
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

    /**
     * @return the lista
     */
    public void leer(Usuario usuario) {
        usuario24 = usuario;
        this.setAccion("M");

    }

    public void modificar(Usuario us) {
        System.out.println("El Usuario  :" + us + "fue editado correctamente");

        Usuarioucc ucc = new Usuarioucc();
        ucc.editarUsuario(us);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se edito correctamente"));
    }

    public void eliminar(Usuario us) {
        System.out.println("El Usuario :" + us + "fue eliminado por :" +user);

        Usuarioucc ucc = new Usuarioucc();
        ucc.deleteUsuario(us);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha eliminado correctamente"));

    }

    /**
     * @return the accion
     */
    public String getAccion() {
        return accion;
    }

    /**
     * @param accion the accion to set
     */
    public void setAccion(String accion) {
        this.accion = accion;
    }

    /**
     * @return the selected
     */
 
}
