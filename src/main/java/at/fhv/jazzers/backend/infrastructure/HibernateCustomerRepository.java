package at.fhv.jazzers.backend.infrastructure;

import at.fhv.jazzers.backend.ServiceRegistry;
import at.fhv.jazzers.backend.domain.model.customer.Customer;
import at.fhv.jazzers.backend.domain.model.customer.CustomerId;
import at.fhv.jazzers.backend.domain.repository.CustomerRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

public class HibernateCustomerRepository implements CustomerRepository {
    private final EntityManager entityManager = ServiceRegistry.entityManager();

    @Override
    public Optional<Customer> byId(CustomerId customerId) {
        return entityManager
                .createQuery("SELECT c FROM Customer c WHERE c.customerId = :customerId", Customer.class)
                .setParameter("customerId", customerId)
                .getResultList()
                .stream()
                .findFirst();
    }
}
