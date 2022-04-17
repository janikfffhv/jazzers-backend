package at.fhv.jazzers.backend.communication.common.api;

import at.fhv.jazzers.backend.application.api.MessageConsumerService;
import at.fhv.jazzers.backend.application.api.MessagePublisherService;
import at.fhv.jazzers.backend.application.api.ProductService;
import at.fhv.jazzers.backend.application.api.SaleService;
import at.fhv.jazzers.shared.api.RMI_CustomerService;

public interface Session {
    String username();

    RMI_CustomerService rmi_customerService();

    MessageConsumerService messageConsumerService();

    MessagePublisherService messagePublisherService();

    ProductService productService();

    SaleService saleService();
}
