package br.ufpr.tads.dac.lol.controller;

import br.ufpr.tads.dac.lol.facede.CrudFacede;
import br.ufpr.tads.dac.lol.facede.IllegalOperationException;
import br.ufpr.tads.dac.lol.facede.NotFoundException;
import br.ufpr.tads.dac.lol.facede.ValidationException;
import br.ufpr.tads.dac.lol.model.Model;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.DateConverter;
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
        } catch (NumberFormatException ignored) {
            // Silence is golden
        }

        try {
            offset = Integer.parseInt(request.getParameter("offset"));
        } catch (NumberFormatException ignored) {
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
            BeanUtilsBean beanUtilsBean = BeanUtilsBean.getInstance();

            Date dateDefaultValue = null;
            DateConverter dateConverter = new DateConverter(dateDefaultValue);
            dateConverter.setPattern("yyyy-MM-dd");
            beanUtilsBean.getConvertUtils().register(dateConverter, java.util.Date.class);

            BigDecimal bigDecimalDefaultValue = null;;
            BigDecimalConverter bigDecimalConverter = new BigDecimalConverter(bigDecimalDefaultValue);
            beanUtilsBean.getConvertUtils().register(bigDecimalConverter, BigDecimal.class);

            model = getModel();
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
            beforeProcessRequest(request, response);

            T model = null;
            CrudFacede<T> facede = getFacede();

            // Process path
            String pathInfo = request.getRequestURI();
            String[] pathParts = pathInfo.split("/");
            String page = pathParts.length >= 4 ? pathParts[3] : "grid";
            String id = pathParts.length >= 5 ? pathParts[4].replaceAll("[^0-9]", "") : "";

            // Find entity
            if (!id.isEmpty()) {
                try {
                    model = facede.find(Integer.parseInt(id));
                    request.setAttribute("model", model);
                } catch (NotFoundException | NumberFormatException e) {
                    getLogger().error("", e);
                    request.setAttribute("message", e.getMessage());
                }
            }

            request.setAttribute("basePath", getBasePath());
            switch (page) {
                case "grid":
                    doList(request, model, facede);
                    request.getRequestDispatcher(viewPath(String.format("%s/grid.jsp", getBasePath()))).forward(request, response);
                    break;
                case "form":
                    request.getRequestDispatcher(viewPath(String.format("%s/form.jsp", getBasePath()))).forward(request, response);
                case "view":
                    request.getRequestDispatcher(viewPath(String.format("%s/view.jsp", getBasePath()))).forward(request, response);
                default:
                    throw new NotCrudActionException(page, pathParts);
            }
        } catch (IOException e) {
            getLogger().error("", e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            beforeProcessRequest(request, response);

            T model = getModel();
            CrudFacede<T> facede = getFacede();

            try {
                Date defaultValue = null;
                DateConverter converter = new DateConverter(defaultValue);
                converter.setPattern("yyyy-MM-dd");
                BeanUtilsBean beanUtilsBean = BeanUtilsBean.getInstance();
                beanUtilsBean.getConvertUtils().register(converter, java.util.Date.class);

                BeanUtils.populate(model, request.getParameterMap());
                request.setAttribute("model", model);
            } catch (IllegalAccessException | InvocationTargetException ex) {
                getLogger().error("", ex);
            }

            // Process path
            String pathInfo = request.getPathInfo();
            String[] pathParts = pathInfo.split("/");
            String action = pathParts[1];

            request.setAttribute("basePath", getBasePath());
            switch (action) {
                case "create":
                    model.setId(null);
                    beforeCreate(request, response, model);
                    facede.save(model);
                    request.setAttribute("message", "Cadastrado com sucesso!");
                    break;
                case "update":
                    beforeUpdate(request, response, model);
                    facede.save(model);
                    request.setAttribute("message", "Alterado com sucesso!");
                    break;
                case "delete":
                    model = facede.find(Integer.parseInt(pathParts[2]));
                    beforeDelete(request, response, model);
                    facede.delete(model);
                    request.setAttribute("message", "Deletado com sucesso!");
                    break;
                default:
                    throw new NotCrudActionException(action, pathParts);
            }
        } catch (IllegalOperationException | ValidationException ex) {
            getLogger().debug("", ex);
            request.setAttribute("message", ex.getMessage());
            request.setAttribute("messages", ex.getMessages());
//            request.getRequestDispatcher(viewPath(String.format("%s/form.jsp", getBasePath()))).forward(request, response);;
        } catch (NotCrudActionException ex) {
            processNotCrudRequest(request, response, ex);
//            if (request.getParameter("paginaAtual") != null) {
//                if (request.getParameter("paginaAtual").equals("fast-edit")) {
//                    request.getRequestDispatcher(viewPath(String.format("%s/fast-edit.jsp", getBasePath())))
//                            .forward(request, response);
//                }
//            };
        } catch (Exception ex) {
            request.setAttribute("message", ex.getMessage());
            getLogger().debug("", ex);
        } finally {
            request.getRequestDispatcher(viewPath(String.format("%s/form.jsp", getBasePath())))
                    .forward(request, response);
        }
    }

    protected void beforeProcessRequest(HttpServletRequest request, HttpServletResponse response) {
    }

    protected void beforeCreate(HttpServletRequest request, HttpServletResponse response, T model) {
    }

    protected void beforeUpdate(HttpServletRequest request, HttpServletResponse response, T model) {
    }

    protected void beforeDelete(HttpServletRequest request, HttpServletResponse response, T model) {
    }

    protected void processNotCrudRequest(HttpServletRequest request, HttpServletResponse response, NotCrudActionException actionException) {

    }

    static class NotCrudActionException extends ServletException {

        private final String action;
        private final String[] pathParts;

        NotCrudActionException(String action, String[] pathParts) {
            super(String.format("Invalid action [%s]", action));
            this.action = action;
            this.pathParts = pathParts;
        }

        public String getAction() {
            return action;
        }

        public String[] getPathParts() {
            return pathParts;
        }
    }
}
