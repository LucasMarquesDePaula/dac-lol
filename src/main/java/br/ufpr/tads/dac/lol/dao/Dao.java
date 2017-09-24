package br.ufpr.tads.dac.lol.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class Dao<E> {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private final Class<E> entityClass;

    public Dao(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public E findById(final Serializable id) {
        return (E) getSession().get(this.entityClass, id);
    }

    public Serializable save(E entity) {
        return getSession().save(entity);
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
    
    public void clear() {
        getSession().clear();
    }

    public void flush() {
        getSession().flush();
    }

}
