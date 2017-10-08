package br.ufpr.tads.dac.lol.controller;

import br.ufpr.tads.dac.lol.facede.CrudFacede;
import br.ufpr.tads.dac.lol.facede.NotFoundException;
import br.ufpr.tads.dac.lol.facede.ValidationException;
import br.ufpr.tads.dac.lol.model.Model;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.slf4j.Logger;

/**
 *
 * @author Lucas
 */
public abstract class CrudController<T extends Model> extends Controller {

    protected abstract Logger getLogger();

    protected abstract T getModel();

    protected abstract CrudFacede<T> getFacede();

    protected void doList(HttpServletRequest request, T model, CrudFacede<T> facede) {
        int limit = 5;
        int offset = 0;

        try {
            limit = Integer.parseInt(request.getParameter("limit"));
        } catch (NumberFormatException e) {
            // Silence is golden
        }

        try {
            offset = Integer.parseInt(request.getParameter("offset"));
        } catch (NumberFormatException e) {
            // Silence is golden
        }

        Map<String, String> sort = null;
        if (request.getParameter("sortField") != null) {
            String sortField = request.getParameter("sortField");
            String sortDirection = request.getParameter("sortDirection");
            sort = new HashMap<>();
            sort.put(sortField, sortDirection == null ? "asc" : sortDirection);
        }

        try {
            BeanUtils.populate(model, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException ex) {
            getLogger().error("", ex);
        }

        request.setAttribute("limit", limit);
        request.setAttribute("offset", offset);
        request.setAttribute("queryResult", facede.list(Example.create(model).enableLike(MatchMode.ANYWHERE), limit, offset, sort));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            T model = null;
            CrudFacede<T> facede = getFacede();

            // Process path
            String pathInfo = request.getRequestURI();
            String[] pathParts = pathInfo.split("/");
            String base = pathParts[2];
            String page = pathParts.length >= 4 ? pathParts[3] : "grid";
            String id = pathParts.length >= 5 ? pathParts[4].replaceAll("[^0-9]", "") : "";

            // Find entity
            if (!id.isEmpty()) {
                try {
                    model = facede.find(Integer.parseInt(id));
                    request.setAttribute("model", model);
                } catch (NotFoundException | NumberFormatException e) {
                    getLogger().error("", e);
                }
            }

            switch (page) {
                case "grid":
                    doList(request, model, facede);
                    request.getRequestDispatcher(viewPath(String.format("%s/grid.jsp", base))).forward(request, response);
                    break;
                case "form":
                    request.getRequestDispatcher(viewPath(String.format("%s/form.jsp", base))).forward(request, response);
                case "view":
                    request.getRequestDispatcher(viewPath(String.format("%s/view.jsp", base))).forward(request, response);
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (IOException | ServletException e) {
            getLogger().error("", e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            T model = null;
            CrudFacede<T> facede = getFacede();

            // Process path
            String pathInfo = request.getPathInfo();
            String[] pathParts = pathInfo.split("/");
            String action = pathParts[1];

            switch (action) {
                case "create":
                    try {
                        model = getModel();
                        BeanUtils.populate(model, request.getParameterMap());
                    } catch (IllegalAccessException | InvocationTargetException ex) {
                        getLogger().error("", ex);
                    }
                    break;
                case "update":
                    model = facede.find(Integer.parseInt(pathParts[2]));
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                    return;
            }

            facede.save(model);

            request.setAttribute("message", "Cadastrado com sucesso!");
            request.getRequestDispatcher(viewPath("message/success.jsp")).forward(request, response);

        } catch (Exception ex) {
            getLogger().debug("", ex);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
