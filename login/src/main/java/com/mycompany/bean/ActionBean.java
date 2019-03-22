/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;


import com.mycompany.DAO.UsuarioDao;
import com.mycompany.dominio.Usuario;
import com.mycompany.ucc.Usuarioucc;
import com.mycompany.util.SessionUtils;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

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
    private Usuario usuario24;
    private static List<Usuario> lista = new ArrayList();
    private UIData datosObtenidos;

    public ActionBean() {

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        LoginBean lv = (LoginBean) session.getAttribute("loginBean");
        user = lv.getUser();
        usuarios = lv.getUsuarios();
    }

    public void editUser(ActionEvent event){
        System.out.println("Se selecciono"+((Usuario)datosObtenidos.getRowData()));
        
        
    }
    public List<Usuario> getUsuario() {
        UsuarioDao ud = new UsuarioDao(usuario24);
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

    public void eliminar(Usuario us) {
        System.out.println("El Usuario :" + us + "fue eliminado por :" + user);
        Usuarioucc ucc = new Usuarioucc();
        if (ucc.deleteUsuario(us) == true) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "El usuario:" + us + "se ha eliminado correctamente"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "El usuario:" + us + "no se ha eliminado"));
        }

    }

    public void listar() {
        Usuario u = new Usuario();
        u.setIdLogin(1);
        u.setUser(getUser());
        u.setPassword(getPassword());
        getLista().add(u);

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
        usuario24 = usuario;
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
