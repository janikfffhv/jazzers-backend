package at.fhv.jazzers.backend.communication.rest;

import at.fhv.jazzers.backend.application.api.CustomerService;
import at.fhv.jazzers.backend.infrastructure.CredentialService;
import at.fhv.jazzers.shared.dto.CustomerAccountDTO;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {
    @EJB
    private CredentialService credentialService;

    @EJB
    private CustomerService customerService;

    @GET
    @Path("/customer")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@QueryParam("username") @DefaultValue("") String username, @QueryParam("password") @DefaultValue("") String password) {
        boolean loginSuccessful = credentialService.findCustomerInLdap(username, password);

        if (loginSuccessful) {
            CustomerAccountDTO accountInfo = customerService.accountInfo(username);
            return Response.status(Response.Status.OK).entity(accountInfo).build();
        }
        else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
