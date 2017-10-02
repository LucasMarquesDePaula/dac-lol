package br.ufpr.tads.dac.lol.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lucas
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login", "/logout"})
public class LoginController extends Controller {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (String.format("%s/login", request.getContextPath()).equals(request.getRequestURI())) {
            doLogin(request, response);
        } else {
            doLogout(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (String.format("%s/login", request.getContextPath()).equals(request.getRequestURI())) {
            request.getRequestDispatcher(viewPath("login/form.jsp")).forward(request, response);
        } else {
            doLogout(request, response);
        }
    }

    protected void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Map<String, String> messages = new HashMap<>();

        if (username == null || username.isEmpty()) {
            messages.put("username", "Informe o usuário");
        }

        if (password == null || password.isEmpty()) {
            messages.put("password", "Informe a senha");
        }

        if (messages.isEmpty()) {
            if (username != null) {
                request.getSession().setAttribute("user", username);
                response.sendRedirect(request.getContextPath());
                return;
            } else {
                messages.put("login", "Unknown login, please try again");
            }
        }

        request.setAttribute("messages", messages);
        request.getRequestDispatcher(viewPath("login/form.jsp")).forward(request, response);
    }

    protected void doLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect(request.getContextPath());
    }

    @Override
    public String getServletInfo() {
        return "Login";
    }

}
