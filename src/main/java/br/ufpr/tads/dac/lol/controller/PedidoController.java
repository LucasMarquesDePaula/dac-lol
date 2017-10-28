package br.ufpr.tads.dac.lol.controller;

import br.ufpr.tads.dac.lol.facede.CrudFacede;
import br.ufpr.tads.dac.lol.facede.PedidoFacede;
import br.ufpr.tads.dac.lol.model.Cliente;
import br.ufpr.tads.dac.lol.model.Pedido;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    private static Logger logger = LoggerFactory.getLogger(PedidoController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dataInicial = null;
        dataFinal = null;
        DateTimeFormatter df = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        try {
            dataInicial = new Date(df.parseMillis(request.getParameter("dataInicial") + " 00:00:00"));
        } catch (Exception ex) {

        }

        try {
            dataFinal = new Date(df.parseMillis(request.getParameter("dataFinal") + " 23:59:59"));
        } catch (Exception ex) {

        }
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    @Override
    protected void beforeCreate(HttpServletRequest request, HttpServletResponse response, Pedido model) {
        model.setDataHoraCadastro(new Date());
        model.setCliente((Cliente)request.getSession().getAttribute("authenticable"));
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
                if(dataInicial != null){
                    criteria.add(Restrictions.ge("dataHoraCadastro", dataInicial));
                }
                
                if(dataFinal != null){
                    criteria.add(Restrictions.le("dataHoraCadastro", dataFinal));
                }
            }
        };
    }

    @Override
    protected String getBasePath() {
        return "pedido";
    }
}
