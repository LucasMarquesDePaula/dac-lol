package br.ufpr.tads.dac.lol.controller;

import br.ufpr.tads.dac.lol.facede.ClienteFacede;
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

/**
 *
 * @author Lucas
 */
@WebServlet(name = "ClienteController", urlPatterns = { "/cliente/*" })
public class ClienteController extends Controller {
	private Logger logger = LoggerFactory.getLogger(ClienteController.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getRequestURI();
		logger.debug(pathInfo);

		try {
			String[] pathParts = pathInfo.split("/");
			String page = pathParts[pathParts.length - 1];
			
			if("grid".equals(page)) {
				request.setAttribute("items", new ClienteFacede().list(200, 0));
				request.getRequestDispatcher(viewPath("cliente/grid.jsp")).forward(request, response);
			} else if (page.replaceAll("[^0-9]", "g").equals(page)) {
				ClienteFacede facede = new ClienteFacede();
				Cliente cliente = facede.find(Integer.parseInt(page));
				request.setAttribute("model", cliente);
				request.getRequestDispatcher(viewPath("cliente/form.jsp")).forward(request, response);
			} else {
				request.getRequestDispatcher(viewPath("cliente/form.jsp")).forward(request, response);
			}
		} catch (Exception e) {
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

}
