package at.fhv.jazzers.backend.communication.rest;

import at.fhv.jazzers.backend.application.api.CustomerService;
import at.fhv.jazzers.backend.application.api.ProductService;
import at.fhv.jazzers.backend.infrastructure.CredentialService;
import at.fhv.jazzers.shared.dto.DigitalProductDTO;
import at.fhv.jazzers.shared.dto.ProductOverviewDTO;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Path("/product")
public class ProductController {
    @EJB
    private ProductService productService;

    @EJB
    private CustomerService customerService;

    @EJB
    private CredentialService credentialService;

    @GET
    @Path("/searchDigital")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchDigital(@QueryParam("titleOrInterpret") @DefaultValue("") String titleOrInterpret) {
        List<DigitalProductDTO> matchingProducts = productService.searchDigital(titleOrInterpret);
        return Response.status(Response.Status.OK).entity(matchingProducts).build();
    }

    @GET
    @Path("/download")
    @Produces(MediaType.APPLICATION_JSON)
    public Response byProductName(@QueryParam("username") @DefaultValue("") String username, @QueryParam("password") @DefaultValue("") String password, @QueryParam("productId") @DefaultValue("") String productId) {
        boolean customerExistsInLDAP = credentialService.findCustomerInLdap(username, password);
        boolean ownsProduct = customerService.collection(username).stream().map(DigitalProductDTO::getProductId).collect(Collectors.toList()).contains(UUID.fromString(productId));

        System.out.println("Owns Product: " + ownsProduct);

        if (customerExistsInLDAP && ownsProduct) {
            String downloadLink = productService.downloadLink(UUID.fromString(productId));
            return Response.status(Response.Status.OK).entity(downloadLink).build();
        }
        else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}