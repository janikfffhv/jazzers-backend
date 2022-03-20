package at.fhv.jazzers.infrastructure;

import at.fhv.jazzers.ServiceRegistry;
import at.fhv.jazzers.domain.model.product.Product;
import at.fhv.jazzers.domain.repository.ProductRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class HibernateProductRepository implements ProductRepository {
    private final EntityManager entityManager = ServiceRegistry.entityManager();

    @Override
    public List<Product> searchByTitleOrInterpret(String searchString) {
        // TODO: Make it search ArtistName too with a JOIN or something.
        return entityManager
                .createQuery("SELECT p FROM Product p WHERE p.title = :searchString", Product.class)
                .setParameter("searchString", searchString)
                .getResultList();
    }
}
