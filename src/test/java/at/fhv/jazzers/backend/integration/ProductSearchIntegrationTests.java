package at.fhv.jazzers.backend.integration;

import at.fhv.jazzers.backend.ServiceRegistry;
import at.fhv.jazzers.backend.application.api.ProductService;
import at.fhv.jazzers.backend.domain.model.interpret.Interpret;
import at.fhv.jazzers.backend.domain.model.interpret.InterpretId;
import at.fhv.jazzers.backend.domain.model.product.Medium;
import at.fhv.jazzers.backend.domain.model.product.Product;
import at.fhv.jazzers.backend.domain.model.product.ProductId;
import at.fhv.jazzers.shared.dto.ProductOverviewDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductSearchIntegrationTests {
    private EntityManager entityManager;
    private ProductService productService;
    private List<Object> domainObjects;

    @BeforeEach
    void setup() {
        entityManager = ServiceRegistry.entityManager();
        productService = ServiceRegistry.productService();
    }

    @Test
    public void when_searching_analog_then_analog_products() {
        // Prepare Data
        Interpret interpret1 = new Interpret(new InterpretId(UUID.randomUUID()), "Interpret1");
        Interpret interpret2 = new Interpret(new InterpretId(UUID.randomUUID()), "Interpret2");
        Product product1 = new Product(new ProductId(UUID.randomUUID()), "Title1", interpret1, 1111, 1.11d, 11, Medium.MP3, null, null, List.of());
        Product product2 = new Product(new ProductId(UUID.randomUUID()), "Title2", interpret2, 2222, 2.22d, 22, Medium.VINYL, null, null, List.of());
        List<Product> products = List.of(product1, product2);

        List<ProductOverviewDTO> productsDTO = products
                .stream()
                .map(product -> new ProductOverviewDTO(
                        product.productId().id(),
                        product.interpret().name(),
                        product.title(),
                        product.medium().getName(),
                        product.stock(),
                        product.price()))
                .collect(Collectors.toList());

        List<ProductOverviewDTO> analogProductsDTO = productsDTO
                .stream()
                .filter(product -> !product.getMedium().equals(Medium.MP3.getName()))
                .collect(Collectors.toList());

        // Execute Use Case
        var transaction = entityManager.getTransaction();
        transaction.begin();
        domainObjects = List.of(interpret1, interpret2, product1, product2);
        domainObjects.forEach(entityManager::persist);
        entityManager.flush();
        List<ProductOverviewDTO> actual = productService.searchAnalog("title");
        transaction.rollback();

        // Assertion / Verification
        assertEquals(analogProductsDTO, actual);
    }

    @Test
    public void when_searching_digital_then_digital_products() {
        // Prepare Data
        Interpret interpret1 = new Interpret(new InterpretId(UUID.randomUUID()), "Interpret1");
        Interpret interpret2 = new Interpret(new InterpretId(UUID.randomUUID()), "Interpret2");
        Product product1 = new Product(new ProductId(UUID.randomUUID()), "Title1", interpret1, 1111, 1.11d, 11, Medium.MP3, null, null, List.of());
        Product product2 = new Product(new ProductId(UUID.randomUUID()), "Title2", interpret2, 2222, 2.22d, 22, Medium.VINYL, null, null, List.of());
        List<Product> products = List.of(product1, product2);

        List<ProductOverviewDTO> productsDTO = products
                .stream()
                .map(product -> new ProductOverviewDTO(
                        product.productId().id(),
                        product.interpret().name(),
                        product.title(),
                        product.medium().getName(),
                        product.stock(),
                        product.price()))
                .collect(Collectors.toList());

        List<ProductOverviewDTO> digitalProductsDTO = productsDTO
                .stream()
                .filter(product -> product.getMedium().equals(Medium.MP3.getName()))
                .collect(Collectors.toList());

        // Execute Use Case
        var transaction = entityManager.getTransaction();
        transaction.begin();
        domainObjects = List.of(interpret1, interpret2, product1, product2);
        domainObjects.forEach(entityManager::persist);
        entityManager.flush();
        List<ProductOverviewDTO> actual = productService.searchDigital("title");
        transaction.rollback();

        // Assertion / Verification
        assertEquals(digitalProductsDTO, actual);
    }
}
