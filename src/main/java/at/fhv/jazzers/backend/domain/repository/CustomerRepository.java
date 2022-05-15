package at.fhv.jazzers.backend.domain.repository;

import at.fhv.jazzers.backend.domain.model.customer.Customer;
import at.fhv.jazzers.backend.domain.model.customer.CustomerId;

import javax.ejb.Local;
import java.util.Optional;

@Local
public interface CustomerRepository {
    Optional<Customer> byId(CustomerId customerId);
    Optional<Customer> byUsername(String username);
    void save(Customer customer);
}
