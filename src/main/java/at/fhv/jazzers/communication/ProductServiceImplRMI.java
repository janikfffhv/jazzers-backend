package at.fhv.jazzers.communication;

import at.fhv.jazzers.ServiceRegistry;
import at.fhv.jazzers.application.api.ProductService;
import at.fhv.jazzers.application.dto.ProductOverviewDTO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ProductServiceImplRMI extends UnicastRemoteObject implements ProductServiceRMI {
    public ProductServiceImplRMI() throws RemoteException {
        super();
    }

    @Override
    public List<ProductOverviewDTO> searchByTitleOrInterpret(String searchString) throws RemoteException {
        ProductService productService = ServiceRegistry.productService();

        return productService.searchByTitleOrInterpret(searchString);
    }
}