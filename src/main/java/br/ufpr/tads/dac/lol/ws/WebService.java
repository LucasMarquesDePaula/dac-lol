package br.ufpr.tads.dac.lol.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


/**
 *
 * @author Lucas
 */
@javax.jws.WebService(serviceName = "WebService")
public class WebService {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Message get(Message message) {
        return new Message();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void put(Message message) {
    }
}
