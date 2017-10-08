package br.ufpr.tads.dac.lol.facede;

import java.util.Collections;
import java.util.Map;

/**
 *
 * @author Lucas
 */
public abstract class FacedeException extends Exception {

    private static final long serialVersionUID = 1L;
    private final Map<String, String> messages;

    public FacedeException() {
        this.messages = Collections.unmodifiableMap(null);
    }

    public FacedeException(String message) {
        super(message);
        this.messages = Collections.unmodifiableMap(null);
    }

    public FacedeException(String message, Map<String, String> messages) {
        super(message);
        this.messages = Collections.unmodifiableMap(messages);
    }

    public Map<String, String> getMessages() {
        return messages;
    }
}
