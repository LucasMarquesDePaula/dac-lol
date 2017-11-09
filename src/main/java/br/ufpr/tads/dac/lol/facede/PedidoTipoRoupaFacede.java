/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.lol.facede;

import br.ufpr.tads.dac.lol.dao.Dao;
import br.ufpr.tads.dac.lol.dao.PedidoDao;
import br.ufpr.tads.dac.lol.dao.PedidoTipoRoupaDao;
import br.ufpr.tads.dac.lol.model.Pedido;
import br.ufpr.tads.dac.lol.model.PedidoTipoRoupa;

/**
 *
 * @author Tom
 */
public class PedidoTipoRoupaFacede extends CrudFacede<PedidoTipoRoupa> {

    private static PedidoTipoRoupaDao dao;

    @Override
    protected Dao<PedidoTipoRoupa> getDao() {
        if (dao == null) {
            dao = new PedidoTipoRoupaDao();
        }
        return dao;
    }

    @Override
    protected void beforeSave(PedidoTipoRoupa model, Dao<PedidoTipoRoupa> dao) throws ValidationException {
        
    }

    @Override
    protected void beforeDelete(PedidoTipoRoupa model, Dao<PedidoTipoRoupa> dao) throws IllegalOperationException {
        
    }

}
