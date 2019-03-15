/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ucc;

import com.mycompany.DAO.UsuarioDao;
import com.mycompany.dominio.Usuario;

/**
 *
 * @author Iepiadm
 */
public class Usuarioucc {
    public boolean editarUsuario(Usuario us) {
        UsuarioDao ud = new UsuarioDao(us);
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

    public boolean deleteUsuario(Usuario usuario) {
        UsuarioDao ud = new UsuarioDao(usuario);
        try {
            ud.remove();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

