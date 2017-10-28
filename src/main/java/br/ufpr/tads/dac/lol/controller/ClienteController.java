package br.ufpr.tads.dac.lol.controller;

import br.ufpr.tads.dac.lol.facede.ClienteFacede;
import br.ufpr.tads.dac.lol.facede.CrudFacede;
import br.ufpr.tads.dac.lol.model.Cliente;
import java.io.IOException;
import javax.servlet.ServletException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucas
 */
@WebServlet(name = "ClienteController", urlPatterns = {"/cliente/*"})
public class ClienteController extends CrudController<Cliente> {

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

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
    protected Cliente getModel() {
        return new Cliente();
    }

    @Override
    protected CrudFacede<Cliente> getFacede() {
        return new ClienteFacede();
    }

    @Override
    protected String getBasePath() {
        return "cliente";
    }

}
