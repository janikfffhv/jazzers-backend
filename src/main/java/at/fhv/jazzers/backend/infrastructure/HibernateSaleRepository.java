package at.fhv.jazzers.backend.infrastructure;

import at.fhv.jazzers.backend.ServiceRegistry;
import at.fhv.jazzers.backend.domain.model.customer.CustomerId;
import at.fhv.jazzers.backend.domain.model.product.Product;
import at.fhv.jazzers.backend.domain.model.sale.Sale;
import at.fhv.jazzers.backend.domain.model.sale.SaleId;
import at.fhv.jazzers.backend.domain.repository.SaleRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Stateless
public class HibernateSaleRepository implements SaleRepository {
    private final EntityManager entityManager;

    public HibernateSaleRepository() {
        this.entityManager = ServiceRegistry.entityManager();
    }

    @Override
    public Optional<Sale> byId(SaleId saleId) {
        return entityManager
                .createQuery("SELECT s FROM Sale s WHERE s.saleId = :saleId", Sale.class)
                .setParameter("saleId", saleId)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public void save(Sale sale) {
        entityManager.persist(sale);
    }

    @Override
    public List<Sale> getAll() {
        return entityManager
                .createQuery("SELECT s FROM Sale s", Sale.class)
                .getResultList();
    }
}
