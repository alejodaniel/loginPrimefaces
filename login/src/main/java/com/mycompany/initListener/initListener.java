package com.mycompany.initListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mycompany.DAO.DataSource;
import javax.naming.Context;
//import javax.enterprise.context.spi.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author Iepiadm
 */
@WebListener
public class initListener implements ServletContextListener {

    public initListener() {

    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DataSource fuenteDatos = null;
        Context ctx;
        try {
            ServletContext sc = sce.getServletContext();
            ctx = new InitialContext();
//            DataSource.getEntityManager();

        } catch (NamingException e) {
            throw new RuntimeException(e.getMessage());

        }
    }

//    EntityManager emf;

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

//        emf.getEntityManagerFactory().close();
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
