package br.ufpr.tads.dac.lol.ws;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class WebClient {

    private static final String BASE_URI = "http://localhost:8080/lol/ws";

    private final WebTarget webTarget;
    private final Client client;

    public WebClient() {
        client = ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("ds");
    }

    public void put(Message message) throws ClientErrorException {
        webTarget.request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(message, MediaType.APPLICATION_JSON));
    }

    public Message get() throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource
                .request(MediaType.APPLICATION_JSON)
                .get(Message.class);
    }

    public void close() {
        client.close();
    }

}
