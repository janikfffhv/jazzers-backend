package at.fhv.jazzers.domain.repository;

import at.fhv.jazzers.domain.model.product.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> searchByTitleOrInterpret(String searchString);
}
