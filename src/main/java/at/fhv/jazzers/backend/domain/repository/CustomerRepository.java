package at.fhv.jazzers.backend.domain.repository;

import at.fhv.jazzers.backend.domain.model.customer.Customer;
import at.fhv.jazzers.backend.domain.model.customer.CustomerId;

import java.util.Optional;

public interface CustomerRepository {
    Optional<Customer> byId(CustomerId customerId);
    void save(Customer customer);
}
