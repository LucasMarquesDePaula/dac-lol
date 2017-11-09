package br.ufpr.tads.dac.lol.controller;

import br.ufpr.tads.dac.lol.facede.CrudFacede;
import br.ufpr.tads.dac.lol.facede.PedidoTipoRoupaFacede;
import br.ufpr.tads.dac.lol.model.Pedido;
import br.ufpr.tads.dac.lol.model.PedidoTipoRoupa;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tom
 */
@WebServlet(name = "PedidoItemController", urlPatterns = {"/pedido-item/*"})
public class PedidoTipoRoupaController extends CrudController<Pedido> {

    private static Logger logger = LoggerFactory.getLogger(PedidoTipoRoupaController.class);

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doGet(request, response);
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        return new PedidoTipoRoupaFacede();
    }

    @Override
    protected String getBasePath() {
        return "pedido-item";
    }

}
