package br.ufpr.tads.dac.lol.controller;

import br.ufpr.tads.dac.lol.facede.CrudFacede;
import br.ufpr.tads.dac.lol.facede.PedidoFacede;
import br.ufpr.tads.dac.lol.facede.TipoRoupaFacede;
import br.ufpr.tads.dac.lol.model.Authenticable;
import br.ufpr.tads.dac.lol.model.Cliente;
import br.ufpr.tads.dac.lol.model.Funcionario;
import br.ufpr.tads.dac.lol.model.Pedido;
import br.ufpr.tads.dac.lol.model.TipoRoupa;
import br.ufpr.tads.dac.lol.ws.Message;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Lucas
 */
@WebServlet(name = "PedidoController", urlPatterns = {"/pedido/*"})
public class PedidoController extends CrudController<Pedido> {

    private Date dataInicial;
    private Date dataFinal;
    private Cliente cliente;

    private static Logger logger = LoggerFactory.getLogger(PedidoController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            super.doGet(request, response);
        } catch (NotCrudActionException actionException) {
            // Se não for ações tipo crud
            String page = actionException.getAction();
            switch (page) {
                case "fast-edit":
                    request.getRequestDispatcher(viewPath(String.format("%s/fast-edit.jsp", getBasePath()))).forward(request, response);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    @Override
    protected void processNotCrudRequest(HttpServletRequest request, HttpServletResponse response, NotCrudActionException actionException) {
        try {
            String action = actionException.getAction();
            String[] pathParts = actionException.getPathParts();
            Integer id = Integer.parseInt(pathParts[2]);

            PedidoFacede facede = new PedidoFacede();

            TipoRoupa tipoRoupa = null;
            Funcionario funcionario = null;
            Pedido pedido = null;
            Client client = null;
            Message message = null;

            try {
                Integer idTipoRoupa = Integer.parseInt(request.getParameter("tipoRoupa"));
                tipoRoupa = new TipoRoupaFacede().find(idTipoRoupa);
            } catch (Exception ignored) {

            }

            try {
                funcionario = (Funcionario) request.getSession().getAttribute(Authenticable.class.getSimpleName());
            } catch (Exception ignored) {

            }
            switch (action) {
                case "add-item":
                    Integer quantidade = Integer.parseInt(request.getParameter("quantidade"));
                    request.setAttribute("model", facede.adicionarItem(id, tipoRoupa, quantidade));
                    request.setAttribute("message", "Item Adicionado com sucesso!");
                    break;

                case "remove-item":
                    request.setAttribute("model", facede.removerItem(id, tipoRoupa));
                    request.setAttribute("message", "Item Removido com sucesso!");
                    break;

                case "confirm-order":
                    request.setAttribute("model", facede.confirmarOrcamento(id, new Date()));
                    request.setAttribute("message", "Orçamento confirmado com sucesso!");
                    break;

                case "done":
                    request.setAttribute("model", facede.confirmarRealizacao(id, funcionario, new Date()));
                    request.setAttribute("message", "Realização gravada com sucesso!");
                    break;
                case "confirm-receivement":
                    request.setAttribute("model", facede.confirmarRecebimento(id, new Date()));
                    request.setAttribute("message", "Pedido recebido com sucesso!");
                    break;
                case "confirm-payment":
                    request.setAttribute("model", facede.confirmarPagamento(id, funcionario, new Date()));
                    request.setAttribute("message", "Pagamento registrado com sucesso!");
                    break;

                case "cancel":
                    request.setAttribute("model", facede.cancelarPedido(id, funcionario, new Date()));
                    request.setAttribute("message", "Pedido Cancelado com sucesso!");
                    break;

                case "fast-edit":
                    // Nenhum checkbox selecionado
                    if (request.getParameter("confirmarPagamento") == null && request.getParameter("confirmarLavagem") == null) {
                        throw actionException;
                    }

                    // Confirmar Pagamento apenas
                    if (request.getParameter("confirmarLavagem") == null) {
                        request.setAttribute("model", facede.confirmarPagamento(id, funcionario, new Date()));
                        request.setAttribute("message", "Pagamento confirmado com sucesso!");
                        return;
                    }

                    // Confirmar Lavagem apenas
                    if (request.getParameter("confirmarPagamento") == null) {
                        request.setAttribute("model", facede.confirmarRealizacao(id, funcionario, new Date()));
                        request.setAttribute("message", "Lavagem confirmada com sucesso!");
                        request.setAttribute("paginaAtual", "fast-edit");
                        return;
                    }

                    // Confirmar Lavagem e Confirmar Pagamento
                    request.setAttribute("model", facede.confirmarPagamento(id, funcionario, new Date()));
                    request.setAttribute("model", facede.confirmarRealizacao(id, funcionario, new Date()));
                    request.setAttribute("message", "Pagamento e lavagem confirmados com sucesso!");

                    break;

                case "post-delivery":
                    pedido = facede.find(id);
                    client = ClientBuilder.newClient();

                    message = new Message(
                            "nomeCliente", pedido.getCliente().getNome(),
                            "endereco", pedido.getEnderecoEntrega(),
                            "observacao", pedido.getObservacaoCliente(),
                            "pedidoId", pedido.getId().toString()
                    );

                    message = client.target("http://localhost:8080/ds/webresources/ws/")
                            .request(MediaType.APPLICATION_JSON)
                            .post(Entity.entity(message, MediaType.APPLICATION_JSON), Message.class);

                    pedido.setEntregaId(Integer.parseInt(message.getParameter("entregaId")));

                    facede.save(pedido);
                    request.setAttribute("model", pedido);
                    request.setAttribute("message", "Entrega criada com sucesso!");
                    break;
            }

        } catch (Exception ex) {
            request.setAttribute("message", ex.getMessage());
            getLogger().debug("", ex);
        }

    }

    @Override
    protected void beforeProcessRequest(HttpServletRequest request, HttpServletResponse response) {
        DateTimeFormatter df = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        try {
            dataInicial = new Date(df.parseMillis(request.getParameter("dataInicial") + " 00:00:00"));
        } catch (Exception ignored) {
        }

        try {
            dataFinal = new Date(df.parseMillis(request.getParameter("dataFinal") + " 23:59:59"));
        } catch (Exception ignored) {
        }

        try {
            cliente = (Cliente) request.getSession().getAttribute(Authenticable.class.getSimpleName());
        } catch (Exception ignored) {
        }

        request.setAttribute("tiposRoupa", new TipoRoupaFacede() {
            @Override
            protected void processListCriteria(Criteria criteria) {
                criteria.add(Restrictions.eq("ativo", (byte) 0x1));
            }
        }.list(null, null, null, new HashMap<String, String>() {
            {
                put("descricao", "asc");
            }
        }).getList());
    }

    @Override
    protected void beforeCreate(HttpServletRequest request, HttpServletResponse response, Pedido model) {
        model.setDataHoraCadastro(new Date());
        model.setCliente(cliente);

        model.setOrcamentoConfirmado((byte) 0x0);
        model.setCancelado((byte) 0x0);
        model.setRealizado((byte) 0x0);
        model.setRecebido((byte) 0x0);
        model.setEntregaFrustrada((byte) 0x0);
        model.setEntregue((byte) 0x0);
        model.setPago((byte) 0x0);
    }

//    @Override;
//    protected void beforeUpdate(HttpServletRequest request, HttpServletResponse response, Pedido model) {
//        model.setCliente(cliente);
//    }
    @Override
    protected Logger getLogger() {
        return logger;
    }

    @Override
    protected Pedido getModel() {
        return new Pedido();
    }

    @Override
    protected CrudFacede<Pedido> getFacede() {
        return new PedidoFacede() {
            @Override
            protected void processListCriteria(Criteria criteria) {
                if (dataInicial != null) {
                    criteria.add(Restrictions.ge("dataHoraCadastro", dataInicial));
                }

                if (dataFinal != null) {
                    criteria.add(Restrictions.le("dataHoraCadastro", dataFinal));
                }

                if (cliente != null) {
                    criteria.add(Restrictions.eq("cliente", cliente));
                }
            }
        };
    }

    @Override
    protected String getBasePath() {
        return "pedido";
    }
}
