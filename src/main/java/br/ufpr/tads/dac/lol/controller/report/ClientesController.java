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
@WebServlet(name = "ClientesController", urlPatterns = {"/report/clientes"})
public class ClientesController extends ReportController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response, new HashMap<>());
    }

    @Override
    protected String getBasePath() {
        return "report/clientes";
    }

    @Override
    String getReportName() {
        return "RelatorioClientes";
    }

}
