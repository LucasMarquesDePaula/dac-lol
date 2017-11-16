package br.ufpr.tads.dac.lol.controller;

import br.ufpr.tads.dac.lol.facede.ClienteFacede;
import br.ufpr.tads.dac.lol.facede.FuncionarioFacede;
import br.ufpr.tads.dac.lol.filter.Role;
import br.ufpr.tads.dac.lol.model.Admin;
import br.ufpr.tads.dac.lol.model.Authenticable;
import br.ufpr.tads.dac.lol.model.Cliente;
import br.ufpr.tads.dac.lol.model.Funcionario;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.criterion.Example;

/**
 *
 * @author Lucas
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login", "/logout"})
public class LoginController extends Controller {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (String.format("%s/%s", request.getContextPath(), getBasePath()).equals(request.getRequestURI())) {
            doLogin(request, response);
        } else {
            doLogout(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (String.format("%s/login", request.getContextPath()).equals(request.getRequestURI())) {
            request.getRequestDispatcher(viewPath(String.format("%s/form.jsp", getBasePath()))).forward(request, response);
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
            // Admin
            Admin admin = Admin.getInstance();
            if (admin.getUsername().equals(username)) {
                if (admin.getPassword().equals(password)) {
                    request.getSession().setAttribute(Role.class.getName(), Role.ADMIN);
                    request.getSession().setAttribute(Authenticable.class.getName(), admin);
                    response.sendRedirect(request.getContextPath());
                    return;
                }
            }

            // Funcionario
            Funcionario funcionario = Funcionario.getInstance();
            //Funcionario funcionario = new Funcionario();
            //funcionario.setUsername(username);
            //funcionario.setPassword(password);

            //FuncionarioFacede funcionarioFacede = new FuncionarioFacede();
            //List<Funcionario> funcionarioList = funcionarioFacede.list(Example.create(funcionario), null, null, null).getList();
            //if (funcionarioList.size() == 1) {
            
            if (funcionario.getUsername().equals(username)) {
                if (funcionario.getSenha().equals(password)) {
                    request.getSession().setAttribute(Role.class.getName(), Role.FUNCIONARIO);
                    //request.getSession().setAttribute(Authenticable.class.getName(), funcionarioList.get(0));
                    request.getSession().setAttribute(Authenticable.class.getName(), funcionario);
                    response.sendRedirect(request.getContextPath());
                    return;
                }
            }

            //}
            // Cliente
            Cliente cliente = new Cliente();
            cliente.setUsername(username);

            ClienteFacede clienteFacede = new ClienteFacede();
            List<Cliente> clienteList = clienteFacede.list(Example.create(cliente), null, null, null).getList();

            // Encontrou o cliente no banco de dados ?
            Cliente clienteFound = clienteList.size() == 1 ? clienteList.get(0) : null;

            if (clienteFound != null) {

                // A senha é a mesma ?
                cliente.setId(clienteFound.getId());
                cliente.setPassword(password);
                // TODO: Arrumar senha
                // if (cliente.getSenha().equals(clienteFound.getSenha())) {
                request.getSession().setAttribute(Role.class.getName(), Role.CLIENTE);
                request.getSession().setAttribute(Authenticable.class.getName(), clienteFound);
                response.sendRedirect(request.getContextPath());
                // }
                return;
            }

            messages.put("login", "Dados inválidos. Tente novamente");
        }

        request.setAttribute("messages", messages);
        request.getRequestDispatcher(viewPath(String.format("%s/form.jsp", getBasePath()))).forward(request, response);
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

    @Override
    protected String getBasePath() {
        return "login";
    }

}
