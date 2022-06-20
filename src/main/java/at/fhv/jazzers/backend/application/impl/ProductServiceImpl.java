package at.fhv.jazzers.backend.application.impl;

import at.fhv.jazzers.backend.application.api.ProductService;
import at.fhv.jazzers.backend.domain.model.product.Medium;
import at.fhv.jazzers.backend.domain.model.product.Product;
import at.fhv.jazzers.backend.domain.model.product.ProductId;
import at.fhv.jazzers.backend.domain.repository.ProductRepository;
import at.fhv.jazzers.shared.dto.DigitalProductDTO;
import at.fhv.jazzers.shared.dto.ProductOverviewDTO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
                .filter(product -> !product.medium().equals(Medium.MP3))
                .map(product -> new ProductOverviewDTO(
                        product.productId().id(),
                        product.interpret().name(),
                        product.title(),
                        product.medium().getName(),
                        product.stock(),
                        product.price()))
                .collect(Collectors.toList());
    }

    @Override
    public List<DigitalProductDTO> searchDigital(String titleOrInterpret) {
        return search(titleOrInterpret)
                .stream()
                .filter(product -> product.medium().equals(Medium.MP3))
                .map(product -> new DigitalProductDTO(
                        product.productId().id(),
                        product.title(),
                        product.interpret().name(),
                        product.medium().getName(),
                        product.works().get(0).genre().getName(),
                        product.price(),
                        product.downloadLink()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public String downloadLink(UUID productId) {
        return productRepository.byId(new ProductId(productId)).orElseThrow().downloadLink();
    }

    private List<Product> search(String titleOrInterpret) {
        return productRepository.search(titleOrInterpret);
    }
}
