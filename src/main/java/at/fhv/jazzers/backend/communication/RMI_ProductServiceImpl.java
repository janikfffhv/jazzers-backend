package at.fhv.jazzers.backend.communication;

import at.fhv.jazzers.backend.application.api.ProductService;
import at.fhv.jazzers.shared.api.RMI_ProductService;
import at.fhv.jazzers.shared.dto.ProductOverviewDTO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMI_ProductServiceImpl extends UnicastRemoteObject implements RMI_ProductService {
    private final ProductService productService;

    public RMI_ProductServiceImpl(ProductService productService) throws RemoteException {
        super(Integer.parseInt(System.getenv("RMI_PORT")));
        this.productService = productService;
    }

    @Override
    public List<ProductOverviewDTO> searchAnalog(String titleOrInterpret) throws RemoteException {
        return productService.searchAnalog(titleOrInterpret);
    }

    @Override
    public List<ProductOverviewDTO> searchDigital(String titleOrInterpret) throws RemoteException {
        return productService.searchDigital(titleOrInterpret);
    }
}
