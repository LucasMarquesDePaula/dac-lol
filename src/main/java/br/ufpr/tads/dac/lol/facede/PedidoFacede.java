package br.ufpr.tads.dac.lol.facede;

import br.ufpr.tads.dac.lol.dao.Dao;
import java.util.Date;

import br.ufpr.tads.dac.lol.dao.PedidoDao;
import br.ufpr.tads.dac.lol.model.Funcionario;
import br.ufpr.tads.dac.lol.model.Pedido;

/**
 *
 * @author Lucas
 */
public class PedidoFacede extends CrudFacede<Pedido> {

    private static PedidoDao dao;

    public static Pedido cancelarPedido(Pedido pedido, Funcionario funcionario, Date dataHora) throws IllegalOperationException {
//    	TODO
        if (pedido == null) {
            throw new IllegalOperationException("O Pedido não foi informado.");
        }

        if (funcionario == null) {
            throw new IllegalOperationException("O Pedido não pôde ser cancelado pois o funcionário não foi informado ");
        }

        if (dataHora == null) {
            throw new IllegalOperationException("O Pedido não pôde ser cancelado pois a data e hora não foram informadas.");
        }

        pedido.setCancelado((byte) 0x1);
        pedido.setDataHoraCancelamento(dataHora);

        new PedidoDao().save(pedido);
        return pedido;
    }

    public static Pedido confirmarOrcamentoPedido(Pedido pedido) throws IllegalOperationException {
        // TODO
        if (pedido == null) {
            throw new IllegalOperationException(
                    String.format("Pedido [%d] não pôde ter o orçameto confirmado pois...", pedido.getId()));
        }

        new PedidoDao().save(pedido);
        return pedido;
    }

    public static Pedido confirmarRealizacaoPedido(Pedido pedido) throws IllegalOperationException {
        // TODO
        if (pedido == null) {
            throw new IllegalOperationException(
                    String.format("Pedido [%d] não pôde ter o orçameto confirmado pois...", pedido.getId()));
        }

        new PedidoDao().save(pedido);
        return pedido;
    }

    public static Pedido confirmarPagamentoPedido(Pedido pedido) throws IllegalOperationException {
        // TODO
        if (pedido == null) {
            throw new IllegalOperationException(
                    String.format("Pedido [%d] não pôde ter o pagamento confirmado pois...", pedido.getId()));
        }

        new PedidoDao().save(pedido);
        return pedido;
    }

    public static Pedido confirmarFrustracaoEntregaPedido(Pedido pedido) throws IllegalOperationException {
        // TODO
        if (pedido == null) {
            throw new IllegalOperationException(
                    String.format("Pedido [%d] não pôde ter o pagamento confirmado pois...", pedido.getId()));
        }

        new PedidoDao().save(pedido);
        return pedido;
    }

    public static Pedido confirmarEntregaPedido(Pedido pedido) throws IllegalOperationException {
        // TODO
        if (pedido == null) {
            throw new IllegalOperationException(
                    String.format("Pedido [%d] não pôde ter o pagamento confirmado pois...", pedido.getId()));
        }

        new PedidoDao().save(pedido);
        return pedido;
    }

    public static Pedido gravarNovoPedido(Pedido pedido) throws IllegalOperationException {
        // TODO
        if (pedido == null) {
            throw new IllegalOperationException(
                    String.format("Pedido [%d] não pôde ser salvo pois...", pedido.getId()));
        }

        new PedidoDao().save(pedido);
        return pedido;
    }

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
