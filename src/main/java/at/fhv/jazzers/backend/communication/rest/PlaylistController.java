package at.fhv.jazzers.backend.communication.rest;

import at.fhv.jazzers.backend.application.api.CustomerService;
import at.fhv.jazzers.backend.infrastructure.CredentialService;
import at.fhv.jazzers.shared.dto.ProductOverviewDTO;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/playlist")
public class PlaylistController {
    @EJB
    private CustomerService customerService;

    @EJB
    private CredentialService credentialService;

    @GET
    @Path("/collection")
    @Produces(MediaType.APPLICATION_JSON)
    public Response collection(@QueryParam("username") @DefaultValue("") String username, @QueryParam("password") @DefaultValue("") String password) {
        boolean customerExistsInLDAP = credentialService.findCustomerInLdap(username, password);

        if (customerExistsInLDAP) {
            List<ProductOverviewDTO> productsDTO = customerService.collection(username);
            return Response.status(Response.Status.OK).entity(productsDTO).build();
        }
        else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
