package br.ufpr.tads.dac.lol.controller.report;

import br.ufpr.tads.dac.lol.controller.Controller;
import br.ufpr.tads.dac.lol.dao.Dao;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperRunManager;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Lucas
 */
public abstract class ReportController extends Controller {
    
    private final Logger logger = LoggerFactory.getLogger(ReportController.class);
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Map<String, Object> parameters) throws ServletException, IOException {
        byte[] report = processReport(parameters);
        try {
            response.setContentType("application/pdf");
            InputStream in = new BufferedInputStream(new ByteArrayInputStream(report));
            OutputStream out = new BufferedOutputStream(response.getOutputStream());
            IOUtils.copy(in, out);
        } catch (Exception ex) {
            logger.error("", ex);
            throw ex;
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Process path
            String pathInfo = request.getRequestURI();
            String[] pathParts = pathInfo.split("/");
            String page = pathParts.length >= 4 ? pathParts[3] : "grid";
            String id = pathParts.length >= 5 ? pathParts[4].replaceAll("[^0-9]", "") : "";

            request.setAttribute("basePath", getBasePath());
            switch (page) {
                case "generate":
                    request.getRequestDispatcher(viewPath(String.format("%s/generate.jsp", getBasePath()))).forward(request, response);
                default:
                    throw new NotCrudActionException(page, pathParts);
            }
        } catch (IOException e) {
            logger.error("", e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

    }
    
    protected final byte[] processReport(Map<String, Object> parameters) {
        final Report report = new Report();
        Dao.getSession().doWork((Connection connection) -> {
            byte[] data = null;
            try {
                data = JasperRunManager.runReportToPdf(getTemplate(), parameters, connection);
            } catch (Exception ex) {
                logger.error("", ex);
            }
            report.setData(data);
        });
        return report.getData();
    }
    
    private InputStream getTemplate() {
        return ReportController.class
                .getClassLoader()
                .getResourceAsStream(String.format("report/%s.jasper", getReportName()));
    }
       
    abstract String getReportName();
    
    private class Report {
        
        private byte[] data;
        
        public byte[] getData() {
            return data;
        }
        
        public void setData(byte[] data) {
            this.data = data;
        }
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
