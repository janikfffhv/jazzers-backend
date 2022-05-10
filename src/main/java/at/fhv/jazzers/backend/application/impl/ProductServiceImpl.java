package at.fhv.jazzers.backend.application.impl;

import at.fhv.jazzers.backend.application.api.ProductService;
import at.fhv.jazzers.backend.domain.model.product.Medium;
import at.fhv.jazzers.backend.domain.repository.ProductRepository;
import at.fhv.jazzers.shared.dto.ProductOverviewDTO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ProductServiceImpl implements ProductService {
    @EJB
    private ProductRepository productRepository;

    public ProductServiceImpl() {

    }

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