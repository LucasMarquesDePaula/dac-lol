package br.ufpr.tads.dac.lol.facede;

import br.ufpr.tads.dac.lol.dao.Dao;
import java.util.Date;

import br.ufpr.tads.dac.lol.dao.PedidoDao;
import br.ufpr.tads.dac.lol.model.Cliente;
import br.ufpr.tads.dac.lol.model.Funcionario;
import br.ufpr.tads.dac.lol.model.Pedido;
import br.ufpr.tads.dac.lol.model.PedidoTipoRoupa;
import br.ufpr.tads.dac.lol.model.PedidoTipoRoupaPK;
import br.ufpr.tads.dac.lol.model.TipoRoupa;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Lucas
 */
public class PedidoFacede extends CrudFacede<Pedido> {

    private static PedidoDao dao;

    // TODO melhorar validações
    public Pedido cancelarPedido(Integer id, Funcionario funcionario, Date dataHora) throws IllegalArgumentException, IllegalOperationException, NotFoundException, ValidationException {

        if (id == null) {
            throw new IllegalArgumentException("O Pedido não foi informado.");
        }

//        Se o funcionario não foi informado então quem cancelou foi o próprio cliente;;
//        if (funcionario == null) {;
//            throw new IllegalArgumentException("O Pedido não pôde ser cancelado pois o funcionário não foi informado ");
//        }
        if (dataHora == null) {
            throw new IllegalArgumentException("O Pedido não pôde ser cancelado pois a data e hora não foram informadas.");
        }

        Pedido pedido = this.find(id);

        if (pedido.getCancelado() == 0x1) {
            throw new IllegalOperationException("O Pedido já foi cancelado anteriormente");
        }

        if (pedido.getRealizado() == 0x1) {
            throw new IllegalOperationException("O Pedido já foi realizado");
        }

        pedido.setCancelado((byte) 0x1);
        pedido.setDataHoraCancelamento(dataHora);

        this.save(pedido);

        return pedido;
    }

    // TODO melhorar validações
    public Pedido confirmarOrcamento(Integer id, Date dataHora) throws IllegalOperationException, NotFoundException, ValidationException {

        if (id == null) {
            throw new IllegalArgumentException("O Pedido não foi informado.");
        }

        if (dataHora == null) {
            throw new IllegalArgumentException("O orçamento não foi confirmado pois a data e hora não foram informadas.");
        }

        Pedido pedido = this.find(id);

        if (pedido.getCancelado() == 0x1) {
            throw new IllegalOperationException("O pedido esta cancelado");
        }

        if (pedido.getOrcamentoConfirmado() == 0x1) {
            throw new IllegalOperationException("O pedido ja foi confirmado anteriormente");
        }

        pedido.setOrcamentoConfirmado((byte) 0x1);
        pedido.setDataHoraConfirmacaoOrcamento(dataHora);

        this.save(pedido);

        return pedido;
    }

    public Pedido confirmarRecebimento(Integer id, Date dataHora) throws NotFoundException, IllegalOperationException, ValidationException {
        if (id == null) {
            throw new IllegalArgumentException("O Pedido não foi informado.");
        }

        if (dataHora == null) {
            throw new IllegalArgumentException("O pedido não foi recebido pois a data e hora não foram informadas.");
        }

        Pedido pedido = this.find(id);

        if (pedido.getCancelado() == 0x1) {
            throw new IllegalOperationException("O pedido esta cancelado");
        }

        if (pedido.getRecebido()== 0x1) {
            throw new IllegalOperationException("O pedido ja foi recebido anteriormente");
        }

        pedido.setRecebido((byte) 0x1);
        pedido.setDataHoraRecebimento(dataHora);

        this.save(pedido);

        return pedido;
    }

    // TODO melhorar validações
    public Pedido confirmarRealizacao(Integer id, Funcionario funcionario, Date dataHora) throws IllegalOperationException, NotFoundException, ValidationException {
        if (id == null) {
            throw new IllegalArgumentException("O Pedido não foi informado.");
        }

        if (funcionario == null) {;
            throw new IllegalArgumentException("O funcionário não foi informado");
        }

        if (dataHora == null) {
            throw new IllegalArgumentException("Data e hora não foram informadas.");
        }

        Pedido pedido = this.find(id);

        if (pedido.getCancelado() == 0x1) {
            throw new IllegalOperationException("O pedido esta cancelado");
        }

        if (pedido.getRecebido() == 0x0) {
            throw new IllegalOperationException("O pedido não foi recebido");
        }

        if (pedido.getRealizado() == 0x1) {
            throw new IllegalOperationException("O pedido ja foi realizado anteriormente");
        }

        pedido.setRealizado((byte) 0x1);
        pedido.setDataHoraRealizacao(dataHora);
        pedido.setFuncionarioRealizacao(funcionario);

        this.save(pedido);
        return pedido;
    }

    // TODO melhorar validações
    public Pedido confirmarPagamento(Integer id, Funcionario funcionario, Date dataHora) throws IllegalOperationException, NotFoundException, ValidationException {
        if (id == null) {
            throw new IllegalArgumentException("O Pedido não foi informado.");
        }

        if (funcionario == null) {;
            throw new IllegalArgumentException("O funcionario não foi informado");
        }

        if (dataHora == null) {
            throw new IllegalArgumentException("Data e hora não foram informadas.");
        }

        Pedido pedido = this.find(id);

        pedido.setPago((byte) 0x1);
        pedido.setDataHoraPagamento(dataHora);

        this.save(pedido);
        return pedido;
    }

    // TODO melhorar validações
    public Pedido confirmarFrustracaoEntregaPedido(Integer id, String justificativa, Date dataHora) throws IllegalOperationException, NotFoundException, ValidationException {
        if (id == null) {
            throw new IllegalArgumentException("O Pedido não foi informado.");
        }

        try {
            justificativa = justificativa.trim();
            if (justificativa.isEmpty()) {
                throw new IllegalArgumentException("A jsutificativa não foi informada");
            }
        } catch (NullPointerException ex) {
            throw new IllegalArgumentException("A justificativa não foi informada");
        }

        if (dataHora == null) {
            throw new IllegalArgumentException("Data e hora não foram informadas.");
        }

        Pedido pedido = this.find(id);

        pedido.setEntregaFrustrada((byte) 0x1);
        pedido.setEntregaFrustradaJustificativa(justificativa);

        this.save(pedido);

        return pedido;
    }

    // TODO melhorar validações
    public Pedido confirmarEntregaPedido(Integer id) throws IllegalOperationException, ValidationException, NotFoundException {
        if (id == null) {
            throw new IllegalArgumentException("O Pedido não foi informado.");
        }

        Pedido pedido = this.find(id);

        if (pedido.getCancelado() == 0x1) {
            throw new IllegalOperationException("O pedido esta cancelado");
        }

        if (pedido.getRecebido() == 0x0) {
            throw new IllegalOperationException("O pedido não foi recebido");
        }

        if (pedido.getEntregue() == 0x1) {
            throw new IllegalOperationException("O pedido ja foi marcado como entregue anteriormente");
        }

        pedido.setEntregue((byte) 0x1);

        this.save(pedido);

        return pedido;
    }

    // TODO melhorar validações
    public Pedido gravarNovoPedido(Pedido pedido, Cliente cliente) throws IllegalOperationException, ValidationException {
        if (pedido == null) {
            throw new IllegalOperationException("O pedido não foi informado");
        }

        if (pedido.getId() != null) {
            throw new IllegalOperationException("Pedidos novos não devem ter código");
        }

        if (cliente == null) {
            throw new IllegalOperationException("O cliente não foi informado");
        }

        if (cliente.getId() == null) {
            throw new IllegalOperationException("O cliente não esta cadastrado");
        }

        if (cliente.getAtivo() == 0x0) {
            throw new IllegalOperationException("Clientes inativos não podem realizar novos pedidos");
        }

        this.save(pedido);

        return pedido;
    }

    // TODO melhorar validações
    public Pedido adicionarItem(Integer id, TipoRoupa tipoRoupa, Integer quantidade) throws IllegalOperationException, ValidationException, NotFoundException {

        if (id == null) {
            throw new IllegalOperationException("O pedido não foi informado");
        }

        if (tipoRoupa == null) {
            throw new IllegalOperationException("O tipo de roupa não foi informado");
        }

        if (tipoRoupa.getAtivo() == 0x0) {
            throw new IllegalOperationException("O tipo de roupa esta inativado");
        }

        if (quantidade == null) {
            throw new IllegalOperationException("A quantidade não foi informada");
        }

        if (quantidade <= 0) {
            throw new IllegalOperationException("A quantidade deve ser maior que 0");
        }

        Pedido pedido = this.find(id);

        if (pedido.getOrcamentoConfirmado() == 0x1) {
            throw new IllegalOperationException("Pedidos com orçamento confirmado não podem ter itens alterados");
        }

        if (pedido.getCliente().getAtivo() == 0x0) {
            throw new IllegalOperationException("Clientes inativos não podem alterar pedidos");
        }

        // Se ja possui ligação com o tipo de roupa atualiza a quatidae e valor
        boolean found = false;
        if (pedido.getPedidoTiposRoupa() != null) {
            Iterator<PedidoTipoRoupa> iterator = pedido.getPedidoTiposRoupa().iterator();
            while (iterator.hasNext()) {
                PedidoTipoRoupa pedidoTipoRoupa = iterator.next();
                if (pedidoTipoRoupa.getTipoRoupa().equals(tipoRoupa)) {
                    pedidoTipoRoupa.setQuantidade(quantidade);
                    pedidoTipoRoupa.setValorUnitario(tipoRoupa.getPrecoLavagem());
                    found = true;
                    break;
                }
            }
        }

        if (!found) {
            PedidoTipoRoupa pedidoTipoRoupa = new PedidoTipoRoupa();
            pedidoTipoRoupa.setId(new PedidoTipoRoupaPK());
            pedidoTipoRoupa.setPedido(pedido);
            pedidoTipoRoupa.setTipoRoupa(tipoRoupa);
            pedidoTipoRoupa.setQuantidade(quantidade);
            pedidoTipoRoupa.setValorUnitario(tipoRoupa.getPrecoLavagem());
            pedido.addPedidoTiposRoupa(pedidoTipoRoupa);
        }

        this.save(pedido);

        return pedido;
    }

    public Pedido removerItem(Integer id, TipoRoupa tipoRoupa) throws IllegalOperationException, ValidationException, NotFoundException {

        if (id == null) {
            throw new IllegalOperationException("O pedido não foi informado");
        }

        if (tipoRoupa == null) {
            throw new IllegalOperationException("O tipo de roupa não foi informado");
        }

        Pedido pedido = this.find(id);

        if (pedido.getOrcamentoConfirmado() == 0x1) {
            throw new IllegalOperationException("Pedidos com orçamento confirmado não podem ter itens alterados");
        }

        if (pedido.getCliente().getAtivo() == 0x0) {
            throw new IllegalOperationException("Clientes inativos não podem alterar pedidos");
        }

        // Se ja possui ligação com o tipo de roupa atualiza a quatidae e valor
        if (pedido.getPedidoTiposRoupa() != null) {
            Iterator<PedidoTipoRoupa> iterator = pedido.getPedidoTiposRoupa().iterator();
            while (iterator.hasNext()) {
                PedidoTipoRoupa pedidoTipoRoupa = iterator.next();
                if (pedidoTipoRoupa.getTipoRoupa().equals(tipoRoupa)) {
                    iterator.remove();
                    break;
                }
            }
        }

        this.save(pedido);

        return pedido;
    }

    @Override
    protected Dao<Pedido> getDao() {
        if (dao == null) {
            dao = new PedidoDao();
        }
        return dao;
    }

    // TODO criar validações
    @Override
    protected void beforeSave(Pedido model, Dao<Pedido> dao) throws ValidationException {

        Map<String, String> messages = new HashMap<>();

        if (model.getCliente() == null) {
            messages.put("cliente", "O cliente deve ser informado");
        }

        if (model.getPedidoTiposRoupa() != null) {
            for (PedidoTipoRoupa pedidoTipoRoupa : model.getPedidoTiposRoupa()) {
                Integer quantidade = pedidoTipoRoupa.getQuantidade();
                if (quantidade == null || quantidade.compareTo(0) <= 0) {
                    messages.put("quantidade", "Todos os itens devem possuir quantidade maior que zero");
                    break;
                }

                Float valorUnitario = pedidoTipoRoupa.getValorUnitario();
                if (valorUnitario == null || valorUnitario.compareTo(0F) == -1) {
                    messages.put("valorUnitario", "Todos os itens devem possuir valor unitario maior ou igual a zero");
                    break;
                }
            }
        }

        if (!messages.isEmpty()) {
            throw new ValidationException(messages);
        }
    }

    // TODO criar validações
    @Override
    protected void beforeDelete(Pedido model, Dao<Pedido> dao) throws IllegalOperationException {
        if (model.getRecebido() == 0x1) {
            throw new IllegalOperationException("Pedidos recebidos não podem ser excluidos");
        }
    }

}
