package br.ufpr.tads.dac.lol.controller;

import br.ufpr.tads.dac.lol.facede.ClienteFacede;
import br.ufpr.tads.dac.lol.facede.CrudFacede;
import br.ufpr.tads.dac.lol.model.Cliente;
import java.io.IOException;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Lucas
 */
@WebServlet(name = "ClienteController", urlPatterns = {"/cliente/*"})
public class ClienteController extends CrudController<Cliente> {

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getRequestURI();
        logger.debug(pathInfo);

        try {
            // Process path
            String[] pathParts = pathInfo.split("/");
            String page = pathParts.length >= 4 ? pathParts[3] : "grid";
            String id = pathParts.length >= 5 ? pathParts[4].replaceAll("[^0-9]", "") : "";

            // Find entity
            Cliente cliente = null;
            if (!"".equals(id)) {
                try {
                    ClienteFacede facede = new ClienteFacede();
                    cliente = facede.find(Integer.parseInt(id));
                    request.setAttribute("model", cliente);
                } catch (Exception e) {
                    logger.error("", e);
                }
            }

            switch (page) {
                case "grid":
                    doList(request, new Cliente(), new ClienteFacede());
                    request.getRequestDispatcher(viewPath("cliente/grid.jsp")).forward(request, response);
                    break;
                case "form":
                    request.getRequestDispatcher(viewPath("cliente/form.jsp")).forward(request, response);
                case "view":
                    request.getRequestDispatcher(viewPath("cliente/view.jsp")).forward(request, response);
                case "foto":
                    byte[] foto = cliente.getFoto();
                    response.setContentType(request.getServletContext().getMimeType(".png"));
                    response.setContentLength(foto.length);
                    IOUtils.write(foto, response.getOutputStream());
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (IOException | ServletException e) {
            logger.error("", e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String pathInfo = request.getPathInfo();
            logger.debug(pathInfo);

            String[] pathParts = pathInfo.split("/");
            String action = pathParts[1];

            ClienteFacede facede = new ClienteFacede();

            Cliente cliente;
            switch (action) {
                case "create":
                    cliente = new Cliente();
                    break;
                case "update":
                    cliente = facede.find(Integer.parseInt(pathParts[2]));
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                    return;
            }

            List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            for (FileItem item : multiparts) {

                switch (item.getFieldName()) {
                    case "nome":
                        cliente.setNome(item.getString());
                        break;
                    case "cpf":
                        cliente.setCpf(item.getString());
                        break;
                    case "email":
                        cliente.setEmail(item.getString());
                        break;
                    case "endereco":
                        cliente.setEmail(item.getString());
                        break;
                    case "foto":
                        cliente.setFoto(item.get());
                        break;
                    case "sexo":
                        cliente.setSexo(item.getString());
                        break;
                    default:
                        break;
                }

            }

            facede.save(cliente);

            request.setAttribute("message", "Cliente Cadastrado com sucesso!");
            request.getRequestDispatcher(viewPath("message/success.jsp")).forward(request, response);

        } catch (Exception e) {
            logger.debug("", e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
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

}
