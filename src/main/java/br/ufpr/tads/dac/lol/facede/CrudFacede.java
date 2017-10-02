package br.ufpr.tads.dac.lol.facede;

import br.ufpr.tads.dac.lol.dao.Dao;
import br.ufpr.tads.dac.lol.model.Model;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Lucas
 */
public abstract class CrudFacede<T extends Model> {

    private static final Logger logger = LoggerFactory.getLogger(CrudFacede.class);

    protected abstract Dao<T> getDao();

    public T find(Serializable id) throws NotFoundException {
        try {
            T model = getDao().findById(id);
            if (model == null) {
                throw new NotFoundException();
            }
            return model;
        } catch (Exception e) {
            logger.error("", e);
            throw new NotFoundException(e.getMessage());
        }
    }

    public void save(T model) throws IllegalOperationException {
        try {
            Dao<T> dao = getDao();
            dao.beginTransaction();
            dao.save(model);
            dao.commit();
        } catch (Exception e) {
        	logger.error("", e);
            throw new IllegalOperationException(e.getMessage());
        }
    }

    public List<T> list(int limit, int offset) {
        return getDao()
                .createCriteria()
                .setFirstResult(offset)
                .setMaxResults(limit)
                .list();
    }

    public List<T> list(Example example, int limit, int offset) {
        return (List<T>)getDao()
        		.createCriteria()
                .add(example)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .list();
    }

    public void delete(T model) throws IllegalOperationException {
        try {
            Dao<T> dao = getDao();
            dao.beginTransaction();
            dao.delete(model);
            dao.commit();
        } catch (Exception e) {
        	logger.error("", e);
            throw new IllegalOperationException(e.getMessage());
        }
    }
}
