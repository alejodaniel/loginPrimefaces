/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.DAO;

import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Alejandro
 */
public abstract class DAOAbstract<T> {

    private T instancia;
    private EntityManager entityManager;

    public DAOAbstract(T instancia) {
        this.instancia = instancia;
        this.entityManager = DataSource.getEntityManager();
    }
//persist de guardar

    public void persist() {
        this.entityManager.getTransaction().begin();
        try {

            this.entityManager.persist(this.instancia);
            this.entityManager.getTransaction().commit();
            this.entityManager.close();

        } catch (Exception e) {
            if (this.entityManager.getTransaction().isActive()) {
                this.entityManager.getTransaction().rollback();
                this.entityManager.close();
            }
            throw e;
        }

    }
//merge de actualizar

    public void update() {
        this.entityManager.getTransaction().begin();
        try {

            this.instancia = this.entityManager.merge(this.instancia);
            this.entityManager.getTransaction().commit();
            this.entityManager.close();
        } catch (Exception e) {
            if (this.entityManager.getTransaction().isActive()) {
                this.entityManager.getTransaction().rollback();
                this.entityManager.close();
            }
            throw e;
        }
    }

    //remove de borrar
    public void remove() {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.remove(this.instancia);
            this.entityManager.getTransaction().commit();
            this.entityManager.close();
        } catch (Exception e) {
            if (this.entityManager.getTransaction().isActive()) {
                this.entityManager.getTransaction().rollback();
                this.entityManager.close();
            }
            throw e;
        }
    }

    public void clearInstance() {
        this.entityManager.clear();
    }

    public abstract List<T> buscarTodos();

    /**
     * @return the instancia
     */
    public T getInstancia() {
        return instancia;
    }

    /**
     * @param instancia the instancia to set
     */
    public void setInstancia(T instancia) {
        this.instancia = instancia;
    }

    /**
     * @return the entityManager
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * @param entityManager the entityManager to set
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
