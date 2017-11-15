package br.ufpr.tads.dac.lol.controller.report;

import br.ufpr.tads.dac.lol.controller.Controller;
import br.ufpr.tads.dac.lol.dao.Dao;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.commons.io.IOUtils;
import org.hibernate.jdbc.Work;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Lucas
 */
@WebServlet(name = "ReportController", urlPatterns = {"/report"})
public class ReportController extends Controller {

    private final Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String basePath = getBasePath();
        request.setAttribute("basePath", basePath);
        request.getRequestDispatcher(viewPath(String.format("%s/index.jsp", basePath))).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response, Map<String, Object> parameters) throws ServletException, IOException {
        byte[] report = processReport(parameters);
        InputStream in = null;
        OutputStream out = null;

        try {
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", String.format("inline; filename=%s.pdf", getReportTemplateName()));
            response.setContentLength(report.length);

            in = new ByteArrayInputStream(report);
            out = response.getOutputStream();

            IOUtils.copy(in, out);

        } catch (Exception ex) {
            logger.error("", ex);
            throw ex;
        } finally {
            try {
                in.close();
            } catch (Exception ignored) {
            }
            try {
                out.close();
            } catch (Exception ignored) {
            }
        }
    }

    protected final byte[] processReport(Map<String, Object> parameters) {
        final Report report = new Report();
        Dao.getSession().doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                byte[] data = null;
                try {
//                    JasperReport jasperReport = JasperCompileManager.compileReport(getTemplate());
                    JasperPrint jasperPrint = JasperFillManager.fillReport(getTemplate(), null, connection);
                    data = JasperExportManager.exportReportToPdf(jasperPrint);
                } catch (Exception ex) {
                    logger.error("", ex);
                }
                report.setData(data);
            }
        });
        return report.getData();
    }

    private InputStream getTemplate() throws IOException {
        return ReportController.class
                .getClassLoader()
                .getResourceAsStream(String.format("report/%s.jasper", getReportTemplateName()));
//        File file = new File(String.format("d:/report/%s.jasper", getReportTemplateName()));
//        return new FileInputStream(file);
    }

    protected String getReportTemplateName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected String getBasePath() {
        return "report";
    }

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
