package at.fhv.jazzers.backend.communication.rest;

import at.fhv.jazzers.backend.application.api.ProductService;
import at.fhv.jazzers.backend.infrastructure.CredentialService;
import at.fhv.jazzers.shared.dto.DigitalProductDTO;
import at.fhv.jazzers.shared.dto.ProductOverviewDTO;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/product")
public class ProductController {
    @EJB
    private ProductService productService;

    @EJB
    private CredentialService credentialService;

    @GET
    @Path("/searchDigital")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchDigital(@QueryParam("titleOrInterpret") @DefaultValue("") String titleOrInterpret) {
        List<DigitalProductDTO> matchingProducts = productService.searchDigital(titleOrInterpret);
        return Response.status(Response.Status.OK).entity(matchingProducts).build();
    }
}