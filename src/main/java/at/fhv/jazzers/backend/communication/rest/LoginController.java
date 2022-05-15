package at.fhv.jazzers.backend.communication.rest;

import at.fhv.jazzers.backend.infrastructure.CredentialService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {
    @EJB
    private CredentialService credentialService;

    @GET
    @Path("/customer")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchDigital(@QueryParam("username") @DefaultValue("") String username, @QueryParam("password") @DefaultValue("") String password) {
        boolean loginSuccessful = credentialService.findCustomerInLdap(username, password);

        if (loginSuccessful) {
            return Response.status(Response.Status.OK).build();
        }
        else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
