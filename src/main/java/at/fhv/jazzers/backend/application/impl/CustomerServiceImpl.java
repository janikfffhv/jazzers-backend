package at.fhv.jazzers.backend.application.impl;

import at.fhv.jazzers.backend.application.api.CustomerService;
import at.fhv.jazzers.backend.domain.model.product.Medium;
import at.fhv.jazzers.backend.domain.repository.CustomerRepository;
import at.fhv.jazzers.shared.dto.CustomerAccountDTO;
import at.fhv.jazzers.shared.dto.DigitalProductDTO;
import at.fhv.jazzers.shared.dto.ProductOverviewDTO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class CustomerServiceImpl implements CustomerService {
    @EJB
    private CustomerRepository customerRepository;

    public CustomerServiceImpl() {

    }

    @Override
    public CustomerAccountDTO accountInfo(String username) {
        return customerRepository
                .byUsername(username)
                .map(customer -> new CustomerAccountDTO(
                        customer.customerId().id(),
                        customer.firstName(),
                        customer.lastName(),
                        customer.iban()))
                .orElseThrow();
    }

    @Override
    public List<DigitalProductDTO> collection(String username) {
        return customerRepository
                .collection(username).orElseThrow()
                .products()
                .stream()
                .filter(product -> product.medium().equals(Medium.MP3))
                .map(product -> new DigitalProductDTO(
                    product.productId().id(),
                    product.title(),
                    product.interpret().name(),
                    product.medium().getName(),
                    product.works().get(0).genre().getName(),
                    product.price(),
                    product.downloadLink()))
                .collect(Collectors.toList());
    }
}
