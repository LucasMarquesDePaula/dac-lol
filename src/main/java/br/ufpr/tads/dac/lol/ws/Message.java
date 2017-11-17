package br.ufpr.tads.dac.lol.ws;

import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author Lucas
 */
public class Message implements Serializable {

    private String action;
    private Map<String, String> parameters;

    public Message(String action, Map<String, String> parameters) {
        this.action = action;
        this.parameters = parameters;
    }

    public Message() {
    }
    
    public String getAction() {
        return action;
    }

    void setAction(String action) {
        this.action = action;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }
}
