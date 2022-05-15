package at.fhv.jazzers.backend.infrastructure;

import at.fhv.jazzers.backend.ServiceRegistry;
import at.fhv.jazzers.backend.domain.model.customer.Customer;
import at.fhv.jazzers.backend.domain.model.customer.CustomerId;
import at.fhv.jazzers.backend.domain.repository.CustomerRepository;
import at.fhv.jazzers.shared.api.RMI_CustomerService;
import at.fhv.jazzers.shared.dto.CustomerDetailDTO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Stateless
public class HibernateCustomerRepository implements CustomerRepository {
    private final EntityManager entityManager;

    public HibernateCustomerRepository() {
        this.entityManager = ServiceRegistry.entityManager();
    }

    @Override
    public Optional<Customer> byId(CustomerId customerId) {
        return entityManager
                .createQuery("SELECT c FROM Customer c WHERE c.customerId = :customerId", Customer.class)
                .setParameter("customerId", customerId)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public Optional<Customer> byUsername(String username) {
        return entityManager
                .createQuery("SELECT c FROM Customer c WHERE c.username = :username", Customer.class)
                .setParameter("username", username)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public void save(Customer customer) {
        entityManager.persist(customer);
    }
}
