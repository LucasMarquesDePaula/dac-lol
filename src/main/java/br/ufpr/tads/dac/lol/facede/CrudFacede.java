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
                throw new NotFoundException("Item não foi encontrado");
            }
            return model;
        } catch (NotFoundException e) {
            logger.error("", e);
            throw e;
        }
    }

    public void save(T model) throws ValidationException {
        Dao<T> dao = getDao();
        try {
            dao.beginTransaction();
            beforeSave(model, dao);
            dao.save(model);
            dao.commit();
        } catch (ValidationException ve) {
            try {
                dao.rollback();
            } catch (Exception ex) {
            }
            try {
                dao.flush();
            } catch (Exception ex) {
            }
            throw ve;
        }
    }

    @SuppressWarnings("unchecked")
    public QueryResult<T> list(Example example, Integer limit, Integer offset, Map<String, String> sort) {
        Criteria criteria = getDao().createCriteria();
        Criteria criteriaCount = getDao().createCriteria();

        if (example != null) {
            criteria.add(example);
            criteriaCount.add(example);
        }
        if (limit != null && limit > 0) {
            criteria.setMaxResults(limit);
        }
        if (offset != null && offset > 0) {
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

        processListCriteria(criteria);
        
        List<T> list = (List<T>) criteria.list();

        criteriaCount.setProjection(Projections.rowCount());
        Long count = (Long) criteriaCount.uniqueResult();

        return new QueryResult<>(count == null ? 0L : count, list);
    }

    public void delete(T model) throws IllegalOperationException {
        Dao<T> dao = getDao();
        try {
            dao.beginTransaction();
            beforeDelete(model, dao);
            dao.delete(model);
            dao.commit();
        } catch (Exception ex) {
            dao.flush();
        } finally {
            try {
                dao.rollback();
            } catch (Exception ex) {
            }
        }
    }

    protected void processListCriteria(Criteria criteria) {
        
    }

    public class QueryResult<T> {

        private final long count;
        private final List<T> list;

        protected QueryResult(long count, List<T> list) {
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
