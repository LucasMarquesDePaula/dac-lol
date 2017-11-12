package br.ufpr.tads.dac.lol.controller;

import br.ufpr.tads.dac.lol.facede.CrudFacede;
import br.ufpr.tads.dac.lol.facede.PedidoFacede;
import br.ufpr.tads.dac.lol.facede.TipoRoupaFacede;
import br.ufpr.tads.dac.lol.model.Cliente;
import br.ufpr.tads.dac.lol.model.Pedido;
import br.ufpr.tads.dac.lol.model.TipoRoupa;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
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
            String action = actionException.getAction();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            super.doPost(request, response);
        } catch (NotCrudActionException actionException) {
            // Se não for ações tipo crud

            try {
                String action = actionException.getAction();
                String[] pathParts = actionException.getPathParts();
                Integer id = Integer.parseInt(pathParts[2]);

                switch (action) {
                    case "add-item":
                        Integer quantidade = Integer.parseInt(request.getParameter("quantidade"));
                        Integer idTipoRoupa = Integer.parseInt(request.getParameter("tipoRoupa"));
                        TipoRoupa tipoRoupa = new TipoRoupaFacede().find(idTipoRoupa);

                        PedidoFacede facede = new PedidoFacede();
                        Pedido model = facede.adicionarItem(id, tipoRoupa, quantidade);

                        request.setAttribute("model", model);
                        request.setAttribute("message", "Item Adicionado com sucesso!");
                        break;
                }

            } catch (Exception ex) {
                request.setAttribute("message", ex.getMessage());
                getLogger().debug("", ex);
            } finally {
                request.getRequestDispatcher(viewPath(String.format("%s/form.jsp", getBasePath()))).forward(request, response);
            }

        }
    }

    @Override
    protected void beforeProcessRequest(HttpServletRequest request, HttpServletResponse response
    ) {
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
            cliente = (Cliente) request.getSession().getAttribute("authenticable");
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
