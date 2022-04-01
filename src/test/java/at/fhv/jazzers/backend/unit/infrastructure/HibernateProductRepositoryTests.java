package at.fhv.jazzers.backend.unit.infrastructure;

import at.fhv.jazzers.backend.ServiceRegistry;
import at.fhv.jazzers.backend.domain.model.interpret.Interpret;
import at.fhv.jazzers.backend.domain.model.interpret.InterpretId;
import at.fhv.jazzers.backend.domain.model.product.Label;
import at.fhv.jazzers.backend.domain.model.product.Medium;
import at.fhv.jazzers.backend.domain.model.product.Product;
import at.fhv.jazzers.backend.domain.model.product.ProductId;
import at.fhv.jazzers.backend.domain.repository.ProductRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HibernateProductRepositoryTests {
    private EntityManager entityManager;
    private final ProductRepository productRepository = ServiceRegistry.productRepository();

    @BeforeEach
    void setUp() {
        this.entityManager = ServiceRegistry.entityManager();
    }

    @Test
    public void given_products_when_empty_query_then_all_products() {
        // Generate Test Data
        Interpret milesDavis = new Interpret(new InterpretId(UUID.randomUUID()), "Miles Davis");
        Interpret linkinPark = new Interpret(new InterpretId(UUID.randomUUID()), "Linkin Park");
        Interpret bleedFromWithin = new Interpret(new InterpretId(UUID.randomUUID()), "Bleed From Within");
        List<Interpret> interprets = List.of(milesDavis, linkinPark, bleedFromWithin);

        Label columbiaRecords = new Label("Columbia Records");
        Label warnerBrothersRecords = new Label("Warner Brothers Records");
        Label risingRecords = new Label("Rising Records");
        List<Label> labels = List.of(columbiaRecords, warnerBrothersRecords, risingRecords);

        Product kindOfBlueVinyl = new Product(new ProductId(UUID.randomUUID()), "Kind Of Blue", milesDavis, 1959, 22.99d, 20, Medium.VINYL, columbiaRecords, List.of(), List.of());
        Product meteoraVinyl = new Product(new ProductId(UUID.randomUUID()), "Meteora", linkinPark, 2003, 14.99d, 12, Medium.VINYL, warnerBrothersRecords, List.of(), List.of());
        Product meteoraCD = new Product(new ProductId(UUID.randomUUID()), "Meteora", linkinPark, 2005, 9.99d, 33, Medium.CD, warnerBrothersRecords, List.of(), List.of());
        Product humanityCD = new Product(new ProductId(UUID.randomUUID()), "Humanity", bleedFromWithin, 2009, 19.99d, 2, Medium.CD, risingRecords, List.of(), List.of());
        Product humanityMP3 = new Product(new ProductId(UUID.randomUUID()), "Humanity", bleedFromWithin, 2009, 19.99d, 0, Medium.MP3, risingRecords, List.of(), List.of());
        List<Product> products = List.of(kindOfBlueVinyl, meteoraVinyl, meteoraCD, humanityCD, humanityMP3);

        // Flush Data (load data into database but do not persist)
        var transaction = entityManager.getTransaction();
        transaction.begin();
        interprets.forEach(entityManager::persist);
        labels.forEach(entityManager::persist);
        products.forEach(entityManager::persist);
        entityManager.flush();
        List<Product> result = productRepository.search("");
        transaction.rollback();

        // Assertions
        assertEquals(products.size(), result.size());
    }

    @Test
    public void given_products_when_query_then_matching_products() {
        // Generate Test Data
        Interpret milesDavis = new Interpret(new InterpretId(UUID.randomUUID()), "Miles Davis");
        Interpret linkinPark = new Interpret(new InterpretId(UUID.randomUUID()), "Linkin Park");
        Interpret bleedFromWithin = new Interpret(new InterpretId(UUID.randomUUID()), "Bleed From Within");
        List<Interpret> interprets = List.of(milesDavis, linkinPark, bleedFromWithin);

        Label columbiaRecords = new Label("Columbia Records");
        Label warnerBrothersRecords = new Label("Warner Brothers Records");
        Label risingRecords = new Label("Rising Records");
        List<Label> labels = List.of(columbiaRecords, warnerBrothersRecords, risingRecords);

        Product kindOfBlueVinyl = new Product(new ProductId(UUID.randomUUID()), "Kind Of Blue", milesDavis, 1959, 22.99d, 20, Medium.VINYL, columbiaRecords, List.of(), List.of());
        Product meteoraVinyl = new Product(new ProductId(UUID.randomUUID()), "Meteora", linkinPark, 2003, 14.99d, 12, Medium.VINYL, warnerBrothersRecords, List.of(), List.of());
        Product meteoraCD = new Product(new ProductId(UUID.randomUUID()), "Meteora", linkinPark, 2005, 9.99d, 33, Medium.CD, warnerBrothersRecords, List.of(), List.of());
        Product humanityCD = new Product(new ProductId(UUID.randomUUID()), "Humanity", bleedFromWithin, 2009, 19.99d, 2, Medium.CD, risingRecords, List.of(), List.of());
        Product humanityMP3 = new Product(new ProductId(UUID.randomUUID()), "Humanity", bleedFromWithin, 2009, 19.99d, 0, Medium.MP3, risingRecords, List.of(), List.of());
        List<Product> products = List.of(kindOfBlueVinyl, meteoraVinyl, meteoraCD, humanityCD, humanityMP3);
        List<Product> wantedProducts = List.of(meteoraVinyl, meteoraCD);

        // Flush Data (load data into database but do not persist)
        var transaction = entityManager.getTransaction();
        transaction.begin();
        interprets.forEach(entityManager::persist);
        labels.forEach(entityManager::persist);
        products.forEach(entityManager::persist);
        entityManager.flush();
        List<Product> result = productRepository.search("meteora");
        transaction.rollback();

        // Assertions
        assertEquals(wantedProducts.size(), result.size());
    }
}