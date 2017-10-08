package br.ufpr.tads.dac.lol.facede;

import br.ufpr.tads.dac.lol.dao.ClienteDao;
import br.ufpr.tads.dac.lol.dao.Dao;
import br.ufpr.tads.dac.lol.model.Cliente;

/**
 *
 * @author Lucas
 */
public class ClienteFacede extends CrudFacede<Cliente> {

    private static Dao<Cliente> dao;

    @Override
    protected void beforeSave(Cliente model, Dao<Cliente> dao) throws ValidationException {

    }

    @Override
    protected void beforeDelete(Cliente model, Dao<Cliente> dao) throws IllegalOperationException {

    }

    @Override
    protected Dao<Cliente> getDao() {
        if (dao == null) {
            dao = new ClienteDao();
        }
        return dao;
    }
}
