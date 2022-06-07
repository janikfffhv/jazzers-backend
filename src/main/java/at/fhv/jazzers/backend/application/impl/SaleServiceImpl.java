package at.fhv.jazzers.backend.application.impl;

import at.fhv.jazzers.backend.ServiceRegistry;
import at.fhv.jazzers.backend.application.api.SaleService;
import at.fhv.jazzers.backend.domain.model.customer.Customer;
import at.fhv.jazzers.backend.domain.model.customer.CustomerId;
import at.fhv.jazzers.backend.domain.model.product.Product;
import at.fhv.jazzers.backend.domain.model.product.ProductId;
import at.fhv.jazzers.backend.domain.model.sale.*;
import at.fhv.jazzers.backend.domain.repository.CustomerRepository;
import at.fhv.jazzers.backend.domain.repository.ProductRepository;
import at.fhv.jazzers.backend.domain.repository.SaleRepository;
import at.fhv.jazzers.shared.api.RMI_CustomerService;
import at.fhv.jazzers.shared.dto.CustomerDetailDTO;
import at.fhv.jazzers.shared.dto.LineDTO;
import at.fhv.jazzers.shared.dto.SaleHistoryEntryDetailDTO;
import at.fhv.jazzers.shared.dto.SaleHistoryEntryOverviewDTO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Stateless
public class SaleServiceImpl implements SaleService {
    private EntityManager entityManager = ServiceRegistry.entityManager();
    private RMI_CustomerService rmi_customerService = ServiceRegistry.rmi_customerService();
    @EJB
    private CustomerRepository customerRepository;
    @EJB
    private ProductRepository productRepository;
    @EJB
    private SaleRepository saleRepository;

    public SaleServiceImpl() {

    }

    public SaleServiceImpl(EntityManager entityManager, RMI_CustomerService rmi_customerService, CustomerRepository customerRepository, ProductRepository productRepository, SaleRepository saleRepository) {
        this.entityManager = entityManager;
        this.rmi_customerService = rmi_customerService;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.saleRepository = saleRepository;
    }

    @Override
    public void customerPurchase(String username, UUID productId, String iban) {
        Optional<Customer> customer = customerRepository.byUsername(username);

        if (customer.isEmpty()) {
            throw new IllegalStateException("Customer does not exist in database");
        }

        if (!customer.get().iban().equalsIgnoreCase(iban)) {
            throw new IllegalArgumentException("The provided iban does not match the stored iban");
        }

        Optional<Product> product = productRepository.byId(new ProductId(productId));

        if (product.isEmpty()) {
            throw new IllegalStateException("Product does not exist in database");
        }

        Sale sale = Sale.create(new SaleId(UUID.randomUUID()), List.of(new Line(new LineId(UUID.randomUUID()), 1, 0, product.get())), customer.get());

        entityManager.getTransaction().begin();
        sale.lines().forEach(line -> line.product().takeFromStock(line.amountPurchased()));
        customer.get().addProductToCollection(product.get());
        saleRepository.save(sale);
        entityManager.getTransaction().commit();
    }

    @Override
    public void purchase(UUID customerId, List<LineDTO> linesDTO) {
        saveExternalCustomerLocallyIfExists(customerId);

        Optional<Customer> customer = customerRepository.byId(new CustomerId(customerId));

        List<Line> lines = linesDTO
                .stream()
                .map(lineDTO -> new Line(new LineId(lineDTO.getLineId()), lineDTO.getAmountPurchased(), lineDTO.getAmountRefunded(), productRepository.byId(new ProductId(lineDTO.getProductId())).orElseThrow()))
                .collect(Collectors.toList());

        Sale sale = Sale.create(new SaleId(UUID.randomUUID()), lines, customer.orElse(null));

        entityManager.getTransaction().begin();
        lines.forEach(line -> line.product().takeFromStock(line.amountPurchased()));
        saleRepository.save(sale);
        entityManager.getTransaction().commit();
    }

    @Override
    public void refund(UUID saleId, List<LineDTO> linesDTO) {
        Optional<Sale> optionalSale = saleRepository.byId(new SaleId(saleId));

        if (optionalSale.isEmpty()) {
            throw new IllegalArgumentException("The sale with the id " + saleId + " does not exist");
        }

        Optional<Customer> customer = customerRepository.byId(new CustomerId(optionalSale.get().saleId().id()));

        // List<Line> linesBefore = optionalSale.get().lines();

        List<Line> linesNew = linesDTO
                .stream()
                .map(lineDTO -> new Line(new LineId(lineDTO.getLineId()), lineDTO.getAmountPurchased(), lineDTO.getAmountRefunded(), productRepository.byId(new ProductId(lineDTO.getProductId())).orElseThrow()))
                .collect(Collectors.toList());

        optionalSale.get().updateRefunds(linesNew);

        entityManager.getTransaction().begin();
        // linesBefore.forEach(line -> line.product().takeFromStock(line.amountRefunded()));
        // linesNew.forEach(line -> line.product().addToStock(line.amountRefunded()));
        entityManager.persist(optionalSale.get());
        entityManager.getTransaction().commit();
    }

    private void saveExternalCustomerLocallyIfExists(UUID customerId) {
        Optional<Customer> internCustomer = customerRepository.byId(new CustomerId(customerId));
        CustomerDetailDTO externCustomer;

        try {
            externCustomer = rmi_customerService.searchById(customerId);
        } catch (Exception e) {
            // Abort when customer is not found or RMI has connection problems
            return;
        }

        if (externCustomer != null && internCustomer.isEmpty()) {
            entityManager.getTransaction().begin();
            customerRepository.save(new Customer(new CustomerId(customerId), List.of()));
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

                SaleHistoryEntryOverviewDTO saleDTO = new SaleHistoryEntryOverviewDTO(sale.saleId().id(), customerFirstName, customerLastName, sale.salePurchaseTotal(), sale.amountPurchasedTotal(), sale.saleDate());
                salesDTO.add(saleDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    @Override
    public SaleHistoryEntryDetailDTO saleDetail(UUID saleId) {
        Optional<Sale> sale = saleRepository.byId(new SaleId(saleId));

        if (sale.isEmpty()) {
            throw new IllegalArgumentException("The sale with the id + " + saleId + " does not exist!");
        }

        if (sale.get().customer() == null) {
            return new SaleHistoryEntryDetailDTO(null, "", "", null, saleId, sale.get().saleDate(), sale.get().lines().stream().map(line -> new LineDTO(line.lineId().id(), line.product().productId().id(), line.product().title(), line.product().price(), line.amountPurchased(), line.amountRefunded())).collect(Collectors.toList()));
        } else {
            try {
                UUID customerId = sale.get().customer().customerId().id();
                CustomerDetailDTO customerDTO = rmi_customerService.searchById(customerId);
                return new SaleHistoryEntryDetailDTO(customerId, customerDTO.getFirstName(), customerDTO.getLastName(), customerDTO.getAddressDTO(), saleId, sale.get().saleDate(), sale.get().lines().stream().map(line -> new LineDTO(line.lineId().id(), line.product().productId().id(), line.product().title(), line.product().price(), line.amountPurchased(), line.amountRefunded())).collect(Collectors.toList()));
            } catch (Exception e) {
                e.printStackTrace();
                throw new IllegalStateException("Jazzers-Backend is unable to use customer service. There may be a problem due to RMI.");
            }
        }
    }
}
