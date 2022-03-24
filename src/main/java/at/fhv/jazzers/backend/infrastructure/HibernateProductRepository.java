package at.fhv.jazzers.backend.infrastructure;

import at.fhv.jazzers.backend.ServiceRegistry;
import at.fhv.jazzers.backend.domain.model.product.Product;
import at.fhv.jazzers.backend.domain.repository.ProductRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class HibernateProductRepository implements ProductRepository {
    private final EntityManager entityManager = ServiceRegistry.entityManager();

    @Override
    public List<Product> search(String titleOrInterpret) {
        return entityManager
                .createQuery("SELECT p FROM Product p LEFT JOIN Interpret i ON i.interpretIdInternal = p.interpret.interpretIdInternal WHERE LOWER(p.title) LIKE LOWER(:searchString) OR LOWER(i.name) LIKE LOWER(:searchString)", Product.class)
                .setParameter("searchString", "%" + titleOrInterpret + "%")
                .getResultList();
    }
}
