package br.ufpr.tads.dac.lol.facede;

import br.ufpr.tads.dac.lol.dao.Dao;
import br.ufpr.tads.dac.lol.model.Model;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Lucas
 */
public abstract class CrudFacede<T extends Model> {

    private static final Logger logger = LoggerFactory.getLogger(CrudFacede.class);

    protected abstract Dao<T> getDao();

    protected abstract void beforeSave(T model, Dao<T> dao) throws ValidationException;

    protected abstract void beforeDelete(T model, Dao<T> dao) throws IllegalOperationException;

    public T find(Serializable id) throws NotFoundException {
        try {
            T model = getDao().findById(id);
            if (model == null) {
                throw new NotFoundException();
            }
            return model;
        } catch (NotFoundException e) {
            logger.error("", e);
            throw new NotFoundException(e.getMessage());
        }
    }

    public void save(T model) throws ValidationException {
        Dao<T> dao = getDao();
        try {
            dao.beginTransaction();
            beforeSave(model, dao);
            dao.save(model);
            dao.commit();
        } finally {
            try {
                dao.rollback();
            } catch (Exception ex) {
            }
        }
    }

    @SuppressWarnings("unchecked")
    public QueryReturn<T> list(Example example, Integer limit, Integer offset, Map<String, String> sort) {
        Criteria criteria = getDao().createCriteria();

        if (example != null) {
            criteria.add(example);
        }
        if (limit != null) {
            criteria.setMaxResults(limit);
        }
        if (offset != null) {
            criteria.setFirstResult(offset);
        }

        if (sort != null) {
            sort.entrySet().forEach((Map.Entry<String, String> entry) -> {
                String key = entry.getKey();
                String value = entry.getValue();
                if ("desc".equals(value)) {
                    criteria.addOrder(Order.desc(key));
                } else {
                    criteria.addOrder(Order.asc(key));
                }
            });
        }

        List<T> list = (List<T>) criteria.list();

        criteria.setProjection(Projections.rowCount());
        Long count = (Long) criteria.uniqueResult();

        return new QueryReturn<>(count == null ? 0L : count, list);
    }

    public void delete(T model) throws IllegalOperationException {
        Dao<T> dao = getDao();
        try {
            dao.beginTransaction();
            beforeDelete(model, dao);
            dao.delete(model);
            dao.commit();
        } finally {
            try {
                dao.rollback();
            } catch (Exception ex) {
            }
        }
    }

    public class QueryReturn<T> {

        private final long count;
        private final List<T> list;

        protected QueryReturn(long count, List<T> list) {
            this.count = count;
            this.list = list;
        }

        public long getCount() {
            return count;
        }

        public List<T> getList() {
            return list;
        }
    }
}
