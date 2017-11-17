package br.ufpr.tads.dac.lol.ws;

import br.ufpr.tads.dac.lol.facede.CrudFacede;
import br.ufpr.tads.dac.lol.facede.NotFoundException;
import br.ufpr.tads.dac.lol.facede.PedidoFacede;
import br.ufpr.tads.dac.lol.facede.ValidationException;
import br.ufpr.tads.dac.lol.model.Pedido;
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
    public void putDeliveryFrustrated(@PathParam("id") Integer id, Message message) throws NotFoundException, ValidationException, ValidationException {
        CrudFacede<Pedido> facede = new PedidoFacede();
        Pedido model = facede.find(id);
        
        model.setEntregaFrustrada((byte) 0x1);
        model.setEntregaFrustradaJustificativa(message.getParameter("justificativa"));
        facede.save(model);
    }
    
    @PUT
    @Path("delivery-done/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void putDeliveryDone(@PathParam("id") Integer id, Message message) throws NotFoundException, ValidationException {
        CrudFacede<Pedido> facede = new PedidoFacede();
        Pedido model = facede.find(id);
        
        model.setEntregue((byte) 0x1);
        facede.save(model);
    }
}
