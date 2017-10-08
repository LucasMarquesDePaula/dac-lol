package br.ufpr.tads.dac.lol.facede;

import br.ufpr.tads.dac.lol.dao.Dao;
import br.ufpr.tads.dac.lol.dao.TipoRoupaDao;
import br.ufpr.tads.dac.lol.model.TipoRoupa;

/**
 *
 * @author Lucas
 */
public class TipoRoupaFacede extends CrudFacede<TipoRoupa> {

    private static TipoRoupaDao dao;

    @Override
    protected void beforeSave(TipoRoupa model, Dao<TipoRoupa> dao) throws ValidationException {
    }

    @Override
    protected void beforeDelete(TipoRoupa model, Dao<TipoRoupa> dao) throws IllegalOperationException {
    }

    @Override
    protected Dao<TipoRoupa> getDao() {
        if (dao == null) {
            dao = new TipoRoupaDao();
        }
        return dao;
    }
}
