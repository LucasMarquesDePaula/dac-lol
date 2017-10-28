package br.ufpr.tads.dac.lol.controller;

import br.ufpr.tads.dac.lol.facede.CrudFacede;
import br.ufpr.tads.dac.lol.facede.TipoRoupaFacede;
import br.ufpr.tads.dac.lol.model.TipoRoupa;
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
@WebServlet(name = "TipoRoupaController", urlPatterns = {"/tipo-roupa/*"})
public class TipoRoupaController extends CrudController<TipoRoupa> {

    private static final Logger logger = LoggerFactory.getLogger(TipoRoupaController.class);

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
    protected TipoRoupa getModel() {
        return new TipoRoupa();
    }

    @Override
    protected CrudFacede<TipoRoupa> getFacede() {
        return new TipoRoupaFacede();
    }

    @Override
    protected String getBasePath() {
        return "tipo-roupa";
    }
}
