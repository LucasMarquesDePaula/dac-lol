package br.ufpr.tads.dac.lol.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Lucas
 */
public class Controller extends HttpServlet {

    private static String BASE_DIR = "WEB-INF/view";

    protected static String viewPath(String view) {
        return String.format("/%s/%s", BASE_DIR, view).replaceAll("[/]{2,}", "/");
    }
}
