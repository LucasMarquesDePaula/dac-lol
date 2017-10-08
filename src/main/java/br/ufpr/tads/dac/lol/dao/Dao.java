package br.ufpr.tads.dac.lol.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class Dao<E> {

    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static Session session;

    private final Class<E> entityClass;

    public Dao(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    public static Session getSession() {
        if (session == null) {
            session = sessionFactory.openSession();
        }
        return session;

    }

    public Transaction beginTransaction() {
        return getSession().beginTransaction();
    }

    public void commit() {
        getSession().getTransaction().commit();
    }

    public void rollback() {
        getSession().getTransaction().rollback();
    }

    public E findById(Serializable id) {
        return (E) getSession().get(this.entityClass, id);
    }

    public E save(E entity) {
        getSession().save(entity);
        return entity;
    }

    public E update(E entity) {
        getSession().save(entity);
        return entity;
    }

    public void delete(E entity) {
        getSession().delete(entity);
    }

    public void deleteAll() {
        List<E> entities = findAll();
        for (E entity : entities) {
            getSession().delete(entity);
        }
    }

    public List<E> findAll() {
        return getSession().createCriteria(this.entityClass).list();
    }

    public Criteria createCriteria() {
        return getSession().createCriteria(this.entityClass);
    }

    public void clear() {
        getSession().clear();
    }

    public void flush() {
        getSession().flush();
    }

}
