package br.ufpr.tads.dac.lol.staticx;

/**
 *
 * @author Lucas
 */
public class JsTag extends StaticTag {

    public JsTag() {
        super("lib");
    }

    @Override
    String out() {
        String res = getRes();
        if (res != null) {
            return String.format("<script type=\"text/javascript\" src=\"%s/%s\"></script>", getBase(), res);
        }
        return String.format("<script type=\"text/javascript\">%s</script>", getBody());
    }
}
