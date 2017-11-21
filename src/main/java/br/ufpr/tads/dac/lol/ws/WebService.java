package br.ufpr.tads.dac.lol.ws;

import br.ufpr.tads.dac.lol.facede.CrudFacede;
import br.ufpr.tads.dac.lol.facede.IllegalOperationException;
import br.ufpr.tads.dac.lol.facede.NotFoundException;
import br.ufpr.tads.dac.lol.facede.PedidoFacede;
import br.ufpr.tads.dac.lol.facede.ValidationException;
import br.ufpr.tads.dac.lol.model.Pedido;
import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Lucas
 */
@Path("ws")
public class WebService {

    @PUT
    @Path("delivery-frustrated/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void putDeliveryFrustrated(@PathParam("id") Integer id, Message message) throws NotFoundException, ValidationException, ValidationException, IllegalOperationException, IllegalOperationException {
        PedidoFacede facede = new PedidoFacede();
        facede.confirmarFrustracaoEntregaPedido(id, message.getParameter("justificativa"), new Date());
    }

    @PUT
    @Path("delivery-done/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void putDeliveryDone(@PathParam("id") Integer id, Message message) throws NotFoundException, ValidationException, IllegalOperationException {
        PedidoFacede facede = new PedidoFacede();
        facede.confirmarEntregaFeita(id);
    }
}
