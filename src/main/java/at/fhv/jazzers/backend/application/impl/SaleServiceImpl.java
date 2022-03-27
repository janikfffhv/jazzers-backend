package at.fhv.jazzers.backend.application.impl;

import at.fhv.jazzers.backend.application.api.SaleService;
import at.fhv.jazzers.backend.domain.model.customer.Customer;
import at.fhv.jazzers.backend.domain.model.customer.CustomerId;
import at.fhv.jazzers.backend.domain.model.product.ProductId;
import at.fhv.jazzers.backend.domain.model.sale.Line;
import at.fhv.jazzers.backend.domain.model.sale.Sale;
import at.fhv.jazzers.backend.domain.model.sale.SaleId;
import at.fhv.jazzers.backend.domain.model.sale.SaleType;
import at.fhv.jazzers.backend.domain.repository.CustomerRepository;
import at.fhv.jazzers.backend.domain.repository.ProductRepository;
import at.fhv.jazzers.backend.domain.repository.SaleRepository;
import at.fhv.jazzers.shared.dto.LineDTO;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class SaleServiceImpl implements SaleService {
    private final EntityManager entityManager;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;

    public SaleServiceImpl(EntityManager entityManager, CustomerRepository customerRepository, ProductRepository productRepository, SaleRepository saleRepository) {
        this.entityManager = entityManager;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.saleRepository = saleRepository;
    }

    @Override
    public void purchase(UUID customerId, List<LineDTO> linesDTO) {
        Optional<Customer> customer = customerRepository.byId(new CustomerId(customerId));

        List<Line> lines = linesDTO
                .stream()
                .map(lineDTO -> new Line(lineDTO.amount(), productRepository.byId(new ProductId(lineDTO.productId())).orElseThrow()))
                .collect(Collectors.toList());

        Sale sale = Sale.create(new SaleId(UUID.randomUUID()), SaleType.PURCHASE, lines, customer.orElse(null));

        entityManager.getTransaction().begin();

        lines.forEach(line -> line.product().takeFromStock(line.amount()));
        saleRepository.save(sale);

        entityManager.getTransaction().commit();
    }

    @Override
    public void refund(UUID customerId, List<LineDTO> linesDTO) {
        Optional<Customer> customer = customerRepository.byId(new CustomerId(customerId));

        List<Line> lines = linesDTO
                .stream()
                .map(lineDTO -> new Line(lineDTO.amount(), productRepository.byId(new ProductId(lineDTO.productId())).orElseThrow()))
                .collect(Collectors.toList());

        Sale sale = Sale.create(new SaleId(UUID.randomUUID()), SaleType.REFUND, lines, customer.orElse(null));

        entityManager.getTransaction().begin();

        lines.forEach(line -> line.product().addToStock(line.amount()));
        saleRepository.save(sale);

        entityManager.getTransaction().commit();
    }
}
