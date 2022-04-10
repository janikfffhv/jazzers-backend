package at.fhv.jazzers.backend.communication.common.api;

import at.fhv.jazzers.backend.application.api.ProductService;
import at.fhv.jazzers.backend.application.api.SaleService;
import at.fhv.jazzers.shared.api.RMI_CustomerService;

public interface Session {
    String username();

    RMI_CustomerService rmi_customerService();

    ProductService productService();

    SaleService saleService();
}
