/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.lol.controller.report;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Wesley
 */
@WebServlet(name = "ReceitasReportController", urlPatterns = {"/report/receitas"})
public class ReceitasReportController extends ReportController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response, new HashMap<>());
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
