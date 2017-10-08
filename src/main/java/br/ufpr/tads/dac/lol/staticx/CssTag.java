/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.lol.staticx;

/**
 *
 * @author Lucas
 */
public class CssTag extends StaticTag {

    public CssTag() {
        super("lib");
    }

    @Override
    String out() {
        String res = getRes();
        if (res != null) {
            return String.format("<link type=\"text/css\" rel=\"stylesheet\" href=\"%s/%s\" />", getBase(), res);
        }
        return String.format("<style type=\"text/css\">\n%s\n</style>", getBody());
    }
}
