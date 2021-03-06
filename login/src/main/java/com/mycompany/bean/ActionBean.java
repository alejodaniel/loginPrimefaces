/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import com.mycompany.DAO.DataSource;
import com.mycompany.DAO.UsuarioDao;
import com.mycompany.dominio.Usuario;
import com.mycompany.ucc.Usuarioucc;
import com.mycompany.util.SessionUtils;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
//import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import org.primefaces.component.api.UIData;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Iepiadm
 */
@ViewScoped
//@SessionScoped
@ManagedBean(name = "actionBean")
public class ActionBean {

    private String user;
    private String accion;
    private String password;
    private boolean logeado = false;
    private List<Usuario> usuarios = null;
    private Usuario usuarioBase;
    private static List<Usuario> lista = new ArrayList();
    private UIData datosObtenidos;

    public ActionBean() {
        System.out.println("6h52562o65mea///////////////");
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        LoginBean lv = (LoginBean) session.getAttribute("loginBean");
        user = lv.getUser();
        usuarios = lv.getUsuarios();
        System.out.println("dasaa464++++++++++++++++++");
    }

//    public void obtener(ActionEvent event){
//        UIData data = (UIData) event.getComponent().findComponent("lista");
//        ActionBean ab = (ActionBean) data.getRowData();
//        int rowIndex = data.getRowIndex();
//        
//        
//    }
    public List<Usuario> getUsuario() {
        UsuarioDao ud = new UsuarioDao(usuarioBase);
        usuarios = ud.buscarTodos();
        System.out.println("USER:" + usuarios);
        return usuarios;
    }

    public void modificar(Usuario us) {
        System.out.println("El Usuario :" + us + "fue editado correctamente");
        Usuarioucc ucc = new Usuarioucc();
        ucc.editarUsuario(us);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se edito correctamente"));
    }

    public void eliminar(ActionEvent event) {
        Usuario u = (Usuario) datosObtenidos.getRowData();
        System.out.println("se ha seleccionado la tabla: " + u.getUser());
        Usuarioucc ucc = new Usuarioucc();
        if (ucc.deleteUsuario(u) == true) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "El usuario " + u + "fue eliminado correctamente"));
            getUsuario();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "El usuario " + u + "no fue eliminado correctamente"));
        }

    }

    public void register(ActionEvent action) {
        UsuarioDao usuarioDao = new UsuarioDao(usuarioBase);
        System.out.println("user:" + user);
        System.out.println("pass:" + password);
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        Usuario us = new Usuario();
        us.setUser(getUser());
        us.setPassword(getPassword());

        boolean estado = usuarioDao.guardarUsuario(us);
        if (estado == true) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El Usuario y contraseña fueron guardados correctamente", user);
            setLogeado(true);
            getUsuario();
            // DataSource dt = new DataSource();
            // dt.getEntityManager();

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

    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "index";
    }

    public static List<Usuario> getLista() {
        return lista;
    }

    public static void setLista(List<Usuario> aLista) {
        lista = aLista;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLogeado() {
        return logeado;
    }

    public void setLogeado(boolean logeado) {
        this.logeado = logeado;
    }

    public String action() {
        logeado = true;
        return "/home.xhtml";
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void leer(Usuario usuario) {
        usuarioBase = usuario;
        this.setAccion("M");

    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public UIData getDatosObtenidos() {
        return datosObtenidos;
    }

    public void setDatosObtenidos(UIData datosObtenidos) {
        this.datosObtenidos = datosObtenidos;
    }

}
