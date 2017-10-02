package br.ufpr.tads.dac.lol.staticx;

import java.io.IOException;
import java.io.StringWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Lucas
 */
public abstract class StaticTag extends SimpleTagSupport {

    protected static String BASE_DIR = "lol/static";

    private String res;
    private String base;
    private String body;

    protected StaticTag(String base) {
        this.base = String.format("/%s/%s", BASE_DIR, base);
    }

    abstract String out();

    @Override
    public void doTag() throws JspException, IOException {
        StringWriter sw = new StringWriter();
        JspFragment jspFragment = getJspBody();
        if (jspFragment != null) {
            jspFragment.invoke(sw);
            setBody(sw.toString());
        }
        getJspContext()
                .getOut()
                .println(out());
    }

    public String getRes() {
        return this.res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
