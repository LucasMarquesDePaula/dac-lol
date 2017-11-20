/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.lol.controller.report;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author Wesley
 */
@WebServlet(name = "ReceitasReportController", urlPatterns = {"/report/receitas"})
public class ReceitasReportController extends ReportController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> parameters = new HashMap<>();

        DateTimeFormatter df = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

        try {
            Date dataInicial = new Date(df.parseMillis(request.getParameter("dataInicial") + " 00:00:00"));
            parameters.put("dataInicial", dataInicial);
        } catch (Exception ignored) {
        }

        try {
            Date dataFinal = new Date(df.parseMillis(request.getParameter("dataFinal") + " 23:59:59"));
            parameters.put("dataFinal", dataFinal);
        } catch (Exception ignored) {
        }

        super.doGet(request, response, parameters);
    }

    @Override
    protected String getBasePath() {
        return "report/receitas";
    }

    @Override
    protected String getReportTemplateName() {
        return "RelatorioReceitas";
    }

}
