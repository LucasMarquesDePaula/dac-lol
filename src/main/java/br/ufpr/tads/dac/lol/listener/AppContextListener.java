package br.ufpr.tads.dac.lol.listener;

import java.util.HashMap;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author Lucas
 */
@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String contextPath = sce.getServletContext().getContextPath();
        sce.getServletContext().setAttribute("app", new HashMap<String, Object>() {{
            put("name", "Laundry Online");
            put("contextPath", contextPath);
            put("staticResourcesDir", String.format("%s/static", contextPath));
        }});
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //Silence is golden
    }

}