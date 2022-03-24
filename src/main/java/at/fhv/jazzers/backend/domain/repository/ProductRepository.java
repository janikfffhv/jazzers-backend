package at.fhv.jazzers.backend.domain.repository;

import at.fhv.jazzers.backend.domain.model.product.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> search(String titleOrInterpret);
}
