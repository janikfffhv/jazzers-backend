package at.fhv.jazzers.backend.domain.repository;

import at.fhv.jazzers.backend.domain.model.product.Product;
import at.fhv.jazzers.backend.domain.model.product.ProductId;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> byId(ProductId productId);
    List<Product> search(String titleOrInterpret);
}
