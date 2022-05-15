package at.fhv.jazzers.backend.communication.rest;

import at.fhv.jazzers.backend.application.api.SaleService;
import at.fhv.jazzers.backend.infrastructure.CredentialService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/sale")
public class SaleController {
    @EJB
    private SaleService saleService;

    @EJB
    private CredentialService credentialService;

    @GET
    @Path("/customerPurchase")
    @Produces(MediaType.APPLICATION_JSON)
    public Response customerPurchase(@QueryParam("username") @DefaultValue("") String username, @QueryParam("password") @DefaultValue("") String password, @QueryParam("productId") @DefaultValue("") String productId) {
        boolean customerExistsInLDAP = credentialService.findCustomerInLdap(username, password);

        if (customerExistsInLDAP) {
            saleService.customerPurchase(username, UUID.fromString(productId));
            return Response.status(Response.Status.OK).build();
        }
        else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
