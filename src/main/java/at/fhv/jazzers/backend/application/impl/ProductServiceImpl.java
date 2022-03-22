package at.fhv.jazzers.backend.application.impl;

import at.fhv.jazzers.backend.ServiceRegistry;
import at.fhv.jazzers.backend.domain.repository.ProductRepository;
import at.fhv.jazzers.shared.api.ProductService;
import at.fhv.jazzers.shared.dto.ProductOverviewDTO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl extends UnicastRemoteObject implements ProductService {
    private final ProductRepository productRepository = ServiceRegistry.productRepository();

    public ProductServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public List<ProductOverviewDTO> searchByTitleOrInterpret(String searchString) {
        if (searchString.length() < 2) {
            throw new IllegalArgumentException("The search string is too short.");
        }

        return productRepository
                .searchByTitleOrInterpret(searchString)
                .stream()
                .map(product -> new ProductOverviewDTO(
                        product.productId().id(),
                        product.interpret().name(),
                        product.title(),
                        product.medium().getName(),
                        product.stock(),
                        product.price()))
                .collect(Collectors.toList());
    }
}
