/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.lol.facede;

import java.util.Date;

import br.ufpr.tads.dac.lol.dao.PedidoDao;
import br.ufpr.tads.dac.lol.model.Funcionario;
import br.ufpr.tads.dac.lol.model.Pedido;

/**
 *
 * @author Lucas
 */
public class PedidoFacede {

	public static Pedido buscarPedidoPeloCodigo(int codigo) throws NotFoundException {
		Pedido pedido = new PedidoDao().findById(codigo);
		if (pedido == null) {
			throw new NotFoundException(String.format("Pedido [%d] não foi encontrado", codigo));
		}

		new PedidoDao().save(pedido);
		return pedido;
	}

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
    	
    	pedido.setCancelado((byte)0x1);
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

}
