package at.fhv.jazzers.backend.domain.repository;

import at.fhv.jazzers.backend.domain.model.product.Product;
import at.fhv.jazzers.backend.domain.model.product.ProductId;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface ProductRepository {
    Optional<Product> byId(ProductId productId);
    List<Product> search(String titleOrInterpret);
}
