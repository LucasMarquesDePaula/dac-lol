package br.ufpr.tads.dac.lol.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucas
 */
@WebServlet(name = "RootController", urlPatterns = {"/", ""})
public class RootController extends Controller {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(String.format("%s/pedido?recebido=1&entregue=0&cancelado=0sortField=dataHoraCadastro&sortDirection=desc", request.getContextPath()));
//        request.getRequestDispatcher(viewPath(String.format("%s/index.jsp", getBasePath())))
//                .forward(request, response);
    }

    @Override
    protected String getBasePath() {
        return "index";
    }

}
