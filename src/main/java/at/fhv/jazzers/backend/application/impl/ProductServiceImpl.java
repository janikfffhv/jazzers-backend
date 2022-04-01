package at.fhv.jazzers.backend.application.impl;

import at.fhv.jazzers.backend.application.api.ProductService;
import at.fhv.jazzers.backend.domain.model.product.Medium;
import at.fhv.jazzers.backend.domain.repository.ProductRepository;
import at.fhv.jazzers.shared.dto.ProductOverviewDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductOverviewDTO> searchAnalog(String titleOrInterpret) {
        return search(titleOrInterpret)
                .stream()
                .filter(product -> !product.getMedium().equals(Medium.MP3.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductOverviewDTO> searchDigital(String titleOrInterpret) {
        return search(titleOrInterpret)
                .stream()
                .filter(product -> product.getMedium().equals(Medium.MP3.getName()))
                .collect(Collectors.toList());
    }

    private List<ProductOverviewDTO> search(String titleOrInterpret) {
        return productRepository
                .search(titleOrInterpret)
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