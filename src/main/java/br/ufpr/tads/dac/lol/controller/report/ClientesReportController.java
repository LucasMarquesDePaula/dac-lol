package br.ufpr.tads.dac.lol.controller.report;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucas
 */
@WebServlet(name = "ClientesReportController", urlPatterns = {"/report/clientes"})
public class ClientesReportController extends ReportController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response, new HashMap<>());
    }

    @Override
    protected String getBasePath() {
        return "report/clientes";
    }

    @Override
    protected String getReportTemplateName() {
        return "RelatorioClientes";
    }

}
