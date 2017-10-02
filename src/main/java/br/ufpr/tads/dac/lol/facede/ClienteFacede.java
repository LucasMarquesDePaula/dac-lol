package br.ufpr.tads.dac.lol.facede;

import br.ufpr.tads.dac.lol.dao.ClienteDao;
import br.ufpr.tads.dac.lol.dao.Dao;
import br.ufpr.tads.dac.lol.model.Cliente;

/**
 *
 * @author Lucas
 */
public class ClienteFacede extends CrudFacede<Cliente> {
    @Override
    protected Dao<Cliente> getDao() {
        return new ClienteDao();
    }
}
