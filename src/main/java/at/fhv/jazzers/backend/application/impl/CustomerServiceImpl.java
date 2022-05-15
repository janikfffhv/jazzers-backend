package at.fhv.jazzers.backend.application.impl;

import at.fhv.jazzers.backend.application.api.CustomerService;
import at.fhv.jazzers.backend.domain.model.product.Medium;
import at.fhv.jazzers.backend.domain.repository.CustomerRepository;
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
    public List<ProductOverviewDTO> collection(String username) {
        return customerRepository.collection(username).orElseThrow().products().stream().map(product -> new ProductOverviewDTO(product.productId().id(), product.interpret().name(), product.title(), Medium.MP3.getName(), 0, product.price())).collect(Collectors.toList());
    }
}
