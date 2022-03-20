package at.fhv.jazzers.application.impl;

import at.fhv.jazzers.ServiceRegistry;
import at.fhv.jazzers.application.api.ProductService;
import at.fhv.jazzers.application.dto.ProductOverviewDTO;
import at.fhv.jazzers.domain.repository.ProductRepository;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository = ServiceRegistry.productRepository();

    @Override
    public List<ProductOverviewDTO> searchByTitleOrInterpret(String searchString) {
        if (searchString.length() < 2) {
            throw new IllegalArgumentException("The search string is too short.");
        }

        return ProductOverviewDTO.createAsList(productRepository.searchByTitleOrInterpret(searchString));
    }
}
