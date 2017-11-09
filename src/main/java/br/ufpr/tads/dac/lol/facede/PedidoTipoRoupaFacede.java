package br.ufpr.tads.dac.lol.facede;

import br.ufpr.tads.dac.lol.dao.Dao;
import br.ufpr.tads.dac.lol.dao.PedidoDao;
import br.ufpr.tads.dac.lol.model.Pedido;
import br.ufpr.tads.dac.lol.model.PedidoTipoRoupa;

/**
 *
 * @author Tom
 */
public class PedidoTipoRoupaFacede extends CrudFacede<Pedido> {

    private static PedidoDao dao;

    @Override
    protected Dao<Pedido> getDao() {
        if (dao == null) {
            dao = new PedidoDao();
        }
        return dao;
    }

    @Override
    protected void beforeSave(Pedido model, Dao<Pedido> dao) throws ValidationException {
        
    }

    @Override
    protected void beforeDelete(Pedido model, Dao<Pedido> dao) throws IllegalOperationException {
        
    }

}
