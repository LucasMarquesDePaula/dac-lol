package br.ufpr.tads.dac.lol.controller;

import br.ufpr.tads.dac.lol.facede.CrudFacede;
import br.ufpr.tads.dac.lol.facede.PedidoFacede;
import br.ufpr.tads.dac.lol.model.Pedido;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Lucas
 */
@WebServlet(name = "PedidoController", urlPatterns = {"/pedido"})
public class PedidoController extends CrudController<Pedido> {

    private static Logger logger = LoggerFactory.getLogger(PedidoController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
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
        return new PedidoFacede();
    }

    @Override
    protected String getBasePath() {
        return "pedido";
    }
}
