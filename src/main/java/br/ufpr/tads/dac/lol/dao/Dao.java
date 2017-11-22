package br.ufpr.tads.dac.lol.dao;

import br.ufpr.tads.dac.lol.model.Model;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class Dao<E extends Model> {

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
        try {
            if (entity.getId() == null) {
                getSession().persist(entity);
            } else {
                getSession().merge(entity);
            }
        } catch (Exception ex) {
            this.flush();
            throw ex;
        }
        return entity;
    }

    public void delete(E entity) {
        try {
            getSession().delete(entity);
        } catch (Exception ex) {
            this.flush();
            throw ex;
        }
    }

    public void deleteAll() {
        try {
            List<E> entities = findAll();
            for (E entity : entities) {
                getSession().delete(entity);
            }
        } catch (Exception ex) {
            this.flush();
            throw ex;
        }
    }

    public List<E> findAll() {
        try {
            return getSession().createCriteria(this.entityClass).list();
        } catch (Exception ex) {
            this.flush();
            throw ex;
        }
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
