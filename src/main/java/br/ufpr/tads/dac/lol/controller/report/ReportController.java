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
}
