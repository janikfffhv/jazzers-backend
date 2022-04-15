package at.fhv.jazzers.backend.unit.infrastructure;

import at.fhv.jazzers.backend.ServiceRegistry;
import at.fhv.jazzers.backend.domain.model.customer.Customer;
import at.fhv.jazzers.backend.domain.model.customer.CustomerId;
import at.fhv.jazzers.backend.domain.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HibernateCustomerRepositoryTests {
    private EntityManager entityManager;
    private final CustomerRepository customerRepository = ServiceRegistry.customerRepository();

    @BeforeEach
    void setUp() {
        this.entityManager = ServiceRegistry.entityManager();
    }

    @Test
    public void when_searching_customers_by_id_then_matching_customer() {
        // Generate Test Data
        CustomerId barbarosId = new CustomerId(UUID.randomUUID());
        CustomerId christophId = new CustomerId(UUID.randomUUID());
        CustomerId eliasId = new CustomerId(UUID.randomUUID());

        Customer barbaros = new Customer(barbarosId, List.of(), List.of());
        Customer christoph = new Customer(christophId, List.of(), List.of());
        Customer elias = new Customer(eliasId, List.of(), List.of());

        List<Customer> customers = List.of(barbaros, christoph, elias);

        // Flush Data (load data into database but do not persist)
        entityManager.getTransaction().begin();
        customers.forEach(entityManager::persist);
        entityManager.flush();
        Optional<Customer> actual = customerRepository.byId(eliasId);
        entityManager.getTransaction().rollback();

        // Assertions
        assertNotNull(actual);
        actual.ifPresent(customer -> assertEquals(eliasId, customer.customerId()));
    }

    @Test
    public void when_searching_customers_by_invalid_id_then_optional_empty() {
        // Generate Test Data
        CustomerId barbarosId = new CustomerId(UUID.randomUUID());
        CustomerId christophId = new CustomerId(UUID.randomUUID());
        CustomerId eliasId = new CustomerId(UUID.randomUUID());

        Customer barbaros = new Customer(barbarosId, List.of(), List.of());
        Customer christoph = new Customer(christophId, List.of(), List.of());
        Customer elias = new Customer(eliasId, List.of(), List.of());

        List<Customer> customers = List.of(barbaros, christoph, elias);

        // Flush Data (load data into database but do not persist)
        entityManager.getTransaction().begin();
        customers.forEach(entityManager::persist);
        entityManager.flush();
        Optional<Customer> actual = customerRepository.byId(new CustomerId(UUID.randomUUID()));
        entityManager.getTransaction().rollback();

        // Assertions
        assertEquals(Optional.empty(), actual);
    }
}
