package at.fhv.jazzers.backend.infrastructure;

import at.fhv.jazzers.backend.ServiceRegistry;
import at.fhv.jazzers.backend.domain.model.sale.Sale;
import at.fhv.jazzers.backend.domain.repository.SaleRepository;

import javax.persistence.EntityManager;

public class HibernateSaleRepository implements SaleRepository {
    private final EntityManager entityManager = ServiceRegistry.entityManager();

    @Override
    public void save(Sale sale) {
        entityManager.persist(sale);
    }
}
