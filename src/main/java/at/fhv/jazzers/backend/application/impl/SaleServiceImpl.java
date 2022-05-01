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
import at.fhv.jazzers.shared.api.RMI_CustomerService;
import at.fhv.jazzers.shared.dto.CustomerDetailDTO;
import at.fhv.jazzers.shared.dto.LineDTO;
import at.fhv.jazzers.shared.dto.SaleHistoryEntryOverviewDTO;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class SaleServiceImpl implements SaleService {
    private final EntityManager entityManager;
    private final RMI_CustomerService rmi_customerService;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;

    public SaleServiceImpl(EntityManager entityManager, RMI_CustomerService rmi_customerService, CustomerRepository customerRepository, ProductRepository productRepository, SaleRepository saleRepository) {
        this.entityManager = entityManager;
        this.rmi_customerService = rmi_customerService;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.saleRepository = saleRepository;
    }

    @Override
    public void purchase(UUID customerId, List<LineDTO> linesDTO) {
        saveExternalCustomerLocallyIfExists(customerId);

        Optional<Customer> customer = customerRepository.byId(new CustomerId(customerId));

        List<Line> lines = linesDTO
                .stream()
                .map(lineDTO -> new Line(lineDTO.getAmount(), productRepository.byId(new ProductId(lineDTO.getProductId())).orElseThrow()))
                .collect(Collectors.toList());

        Sale sale = Sale.create(new SaleId(UUID.randomUUID()), SaleType.PURCHASE, lines, customer.orElse(null));

        entityManager.getTransaction().begin();
        lines.forEach(line -> line.product().takeFromStock(line.amount()));
        saleRepository.save(sale);
        entityManager.getTransaction().commit();
    }

    @Override
    public void refund(UUID customerId, List<LineDTO> linesDTO) {
        saveExternalCustomerLocallyIfExists(customerId);

        Optional<Customer> customer = customerRepository.byId(new CustomerId(customerId));

        List<Line> lines = linesDTO
                .stream()
                .map(lineDTO -> new Line(lineDTO.getAmount(), productRepository.byId(new ProductId(lineDTO.getProductId())).orElseThrow()))
                .collect(Collectors.toList());

        Sale sale = Sale.create(new SaleId(UUID.randomUUID()), SaleType.REFUND, lines, customer.orElse(null));

        entityManager.getTransaction().begin();
        lines.forEach(line -> line.product().addToStock(line.amount()));
        saleRepository.save(sale);
        entityManager.getTransaction().commit();
    }

    private void saveExternalCustomerLocallyIfExists(UUID customerId) {
        Optional<Customer> internCustomer = customerRepository.byId(new CustomerId(customerId));
        CustomerDetailDTO externCustomer;

        try {
            externCustomer = rmi_customerService.searchById(customerId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Jazzers-Backend is unable to use customer service. There may be a problem due to RMI.");
        }

        if (externCustomer != null && internCustomer.isEmpty()) {
            entityManager.getTransaction().begin();
            customerRepository.save(new Customer(new CustomerId(customerId), List.of(), List.of()));
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public List<SaleHistoryEntryOverviewDTO> saleHistoryFull() {
        List<Sale> sales = saleRepository.getAll();
        List<SaleHistoryEntryOverviewDTO> salesDTO = new ArrayList<>();

        try {
            for (Sale sale : sales) {
                String customerFirstName = "";
                String customerLastName = "";

                if (sale.customer() != null) {
                    CustomerDetailDTO customerDTO = rmi_customerService.searchById(sale.customer().customerId().id());
                    customerFirstName = customerDTO.getFirstName();
                    customerLastName = customerDTO.getLastName();
                }

                SaleHistoryEntryOverviewDTO saleDTO = new SaleHistoryEntryOverviewDTO(sale.saleId().id(), customerFirstName, customerLastName, sale.saleTotal(), sale.amountTotal());
                salesDTO.add(saleDTO);
            }
        } catch (Exception e) {
            throw new IllegalStateException("Jazzers-Backend is unable to use customer service. There may be a problem due to RMI.");
        }

        return salesDTO;
    }

    @Override
    public List<SaleHistoryEntryOverviewDTO> saleHistoryBy(String customerNameOrSaleId) {
        return saleHistoryFull()
                .stream()
                .filter(sale -> sale.getSaleId().toString().contains(customerNameOrSaleId) || sale.getFirstName().concat(" " + sale.getLastName()).toLowerCase().contains(customerNameOrSaleId.toLowerCase()))
                .collect(Collectors.toList());
    }
}
