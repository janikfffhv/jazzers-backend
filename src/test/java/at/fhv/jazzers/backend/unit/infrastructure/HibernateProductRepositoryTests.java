// package at.fhv.jazzers.backend.unit.infrastructure;
//
// import at.fhv.jazzers.backend.ServiceRegistry;
// import at.fhv.jazzers.backend.domain.model.interpret.Interpret;
// import at.fhv.jazzers.backend.domain.model.interpret.InterpretId;
// import at.fhv.jazzers.backend.domain.model.product.Label;
// import at.fhv.jazzers.backend.domain.model.product.Medium;
// import at.fhv.jazzers.backend.domain.model.product.Product;
// import at.fhv.jazzers.backend.domain.model.product.ProductId;
// import at.fhv.jazzers.backend.domain.repository.ProductRepository;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
//
// import javax.ejb.EJB;
// import javax.persistence.EntityManager;
// import java.util.List;
// import java.util.Optional;
// import java.util.UUID;
//
// import static org.junit.jupiter.api.Assertions.*;
//
// public class HibernateProductRepositoryTests {
//     private EntityManager entityManager;
//     @EJB
//     private ProductRepository productRepository;
//
//     @BeforeEach
//     void setUp() {
//         this.entityManager = ServiceRegistry.entityManager();
//     }
//
//     @Test
//     public void when_searching_products_by_id_then_matching_product() {
//         // Generate Test Data
//         Interpret milesDavis = new Interpret(new InterpretId(UUID.randomUUID()), "Miles Davis");
//         Interpret linkinPark = new Interpret(new InterpretId(UUID.randomUUID()), "Linkin Park");
//         Interpret bleedFromWithin = new Interpret(new InterpretId(UUID.randomUUID()), "Bleed From Within");
//         List<Interpret> interprets = List.of(milesDavis, linkinPark, bleedFromWithin);
//
//         Label columbiaRecords = new Label("Columbia Records");
//         Label warnerBrothersRecords = new Label("Warner Brothers Records");
//         Label risingRecords = new Label("Rising Records");
//         List<Label> labels = List.of(columbiaRecords, warnerBrothersRecords, risingRecords);
//
//         ProductId meteoraVinylId = new ProductId(UUID.randomUUID());
//
//         Product kindOfBlueVinyl = new Product(new ProductId(UUID.randomUUID()), "Kind Of Blue", milesDavis, 1959, 22.99d, 20, Medium.VINYL, columbiaRecords, List.of(), List.of());
//         Product meteoraVinyl = new Product(meteoraVinylId, "Meteora", linkinPark, 2003, 14.99d, 12, Medium.VINYL, warnerBrothersRecords, List.of(), List.of());
//         Product meteoraCD = new Product(new ProductId(UUID.randomUUID()), "Meteora", linkinPark, 2005, 9.99d, 33, Medium.CD, warnerBrothersRecords, List.of(), List.of());
//         Product humanityCD = new Product(new ProductId(UUID.randomUUID()), "Humanity", bleedFromWithin, 2009, 19.99d, 2, Medium.CD, risingRecords, List.of(), List.of());
//         Product humanityMP3 = new Product(new ProductId(UUID.randomUUID()), "Humanity", bleedFromWithin, 2009, 19.99d, 0, Medium.MP3, risingRecords, List.of(), List.of());
//         List<Product> products = List.of(kindOfBlueVinyl, meteoraVinyl, meteoraCD, humanityCD, humanityMP3);
//
//         // Flush Data (load data into database but do not persist)
//         entityManager.getTransaction().begin();
//         interprets.forEach(entityManager::persist);
//         labels.forEach(entityManager::persist);
//         products.forEach(entityManager::persist);
//         entityManager.flush();
//         Optional<Product> actual = productRepository.byId(meteoraVinylId);
//         entityManager.getTransaction().rollback();
//
//         // Assertions
//         assertNotNull(actual);
//         actual.ifPresent(product -> assertEquals(meteoraVinylId, product.productId()));
//     }
//
//     @Test
//     public void when_searching_products_by_invalid_id_then_optional_empty() {
//         // Generate Test Data
//         Interpret milesDavis = new Interpret(new InterpretId(UUID.randomUUID()), "Miles Davis");
//         Interpret linkinPark = new Interpret(new InterpretId(UUID.randomUUID()), "Linkin Park");
//         Interpret bleedFromWithin = new Interpret(new InterpretId(UUID.randomUUID()), "Bleed From Within");
//         List<Interpret> interprets = List.of(milesDavis, linkinPark, bleedFromWithin);
//
//         Label columbiaRecords = new Label("Columbia Records");
//         Label warnerBrothersRecords = new Label("Warner Brothers Records");
//         Label risingRecords = new Label("Rising Records");
//         List<Label> labels = List.of(columbiaRecords, warnerBrothersRecords, risingRecords);
//
//         Product kindOfBlueVinyl = new Product(new ProductId(UUID.randomUUID()), "Kind Of Blue", milesDavis, 1959, 22.99d, 20, Medium.VINYL, columbiaRecords, List.of(), List.of());
//         Product meteoraVinyl = new Product(new ProductId(UUID.randomUUID()), "Meteora", linkinPark, 2003, 14.99d, 12, Medium.VINYL, warnerBrothersRecords, List.of(), List.of());
//         Product meteoraCD = new Product(new ProductId(UUID.randomUUID()), "Meteora", linkinPark, 2005, 9.99d, 33, Medium.CD, warnerBrothersRecords, List.of(), List.of());
//         Product humanityCD = new Product(new ProductId(UUID.randomUUID()), "Humanity", bleedFromWithin, 2009, 19.99d, 2, Medium.CD, risingRecords, List.of(), List.of());
//         Product humanityMP3 = new Product(new ProductId(UUID.randomUUID()), "Humanity", bleedFromWithin, 2009, 19.99d, 0, Medium.MP3, risingRecords, List.of(), List.of());
//         List<Product> products = List.of(kindOfBlueVinyl, meteoraVinyl, meteoraCD, humanityCD, humanityMP3);
//
//         // Flush Data (load data into database but do not persist)
//         entityManager.getTransaction().begin();
//         interprets.forEach(entityManager::persist);
//         labels.forEach(entityManager::persist);
//         products.forEach(entityManager::persist);
//         entityManager.flush();
//         Optional<Product> actual = productRepository.byId(new ProductId(UUID.randomUUID()));
//         entityManager.getTransaction().rollback();
//
//         // Assertions
//         assertEquals(Optional.empty(), actual);
//     }
//
//     @Test
//     public void given_products_when_empty_query_then_all_products() {
//         // Generate Test Data
//         Interpret milesDavis = new Interpret(new InterpretId(UUID.randomUUID()), "Miles Davis");
//         Interpret linkinPark = new Interpret(new InterpretId(UUID.randomUUID()), "Linkin Park");
//         Interpret bleedFromWithin = new Interpret(new InterpretId(UUID.randomUUID()), "Bleed From Within");
//         List<Interpret> interprets = List.of(milesDavis, linkinPark, bleedFromWithin);
//
//         Label columbiaRecords = new Label("Columbia Records");
//         Label warnerBrothersRecords = new Label("Warner Brothers Records");
//         Label risingRecords = new Label("Rising Records");
//         List<Label> labels = List.of(columbiaRecords, warnerBrothersRecords, risingRecords);
//
//         Product kindOfBlueVinyl = new Product(new ProductId(UUID.randomUUID()), "Kind Of Blue", milesDavis, 1959, 22.99d, 20, Medium.VINYL, columbiaRecords, List.of(), List.of());
//         Product meteoraVinyl = new Product(new ProductId(UUID.randomUUID()), "Meteora", linkinPark, 2003, 14.99d, 12, Medium.VINYL, warnerBrothersRecords, List.of(), List.of());
//         Product meteoraCD = new Product(new ProductId(UUID.randomUUID()), "Meteora", linkinPark, 2005, 9.99d, 33, Medium.CD, warnerBrothersRecords, List.of(), List.of());
//         Product humanityCD = new Product(new ProductId(UUID.randomUUID()), "Humanity", bleedFromWithin, 2009, 19.99d, 2, Medium.CD, risingRecords, List.of(), List.of());
//         Product humanityMP3 = new Product(new ProductId(UUID.randomUUID()), "Humanity", bleedFromWithin, 2009, 19.99d, 0, Medium.MP3, risingRecords, List.of(), List.of());
//         List<Product> products = List.of(kindOfBlueVinyl, meteoraVinyl, meteoraCD, humanityCD, humanityMP3);
//
//         // Flush Data (load data into database but do not persist)
//         entityManager.getTransaction().begin();
//         interprets.forEach(entityManager::persist);
//         labels.forEach(entityManager::persist);
//         products.forEach(entityManager::persist);
//         entityManager.flush();
//         List<Product> result = productRepository.search("");
//         entityManager.getTransaction().rollback();
//
//         // Assertions
//         assertEquals(products.size(), result.size());
//     }
//
//     @Test
//     public void given_products_when_query_then_matching_products() {
//         // Generate Test Data
//         Interpret milesDavis = new Interpret(new InterpretId(UUID.randomUUID()), "Miles Davis");
//         Interpret linkinPark = new Interpret(new InterpretId(UUID.randomUUID()), "Linkin Park");
//         Interpret bleedFromWithin = new Interpret(new InterpretId(UUID.randomUUID()), "Bleed From Within");
//         List<Interpret> interprets = List.of(milesDavis, linkinPark, bleedFromWithin);
//
//         Label columbiaRecords = new Label("Columbia Records");
//         Label warnerBrothersRecords = new Label("Warner Brothers Records");
//         Label risingRecords = new Label("Rising Records");
//         List<Label> labels = List.of(columbiaRecords, warnerBrothersRecords, risingRecords);
//
//         Product kindOfBlueVinyl = new Product(new ProductId(UUID.randomUUID()), "Kind Of Blue", milesDavis, 1959, 22.99d, 20, Medium.VINYL, columbiaRecords, List.of(), List.of());
//         Product meteoraVinyl = new Product(new ProductId(UUID.randomUUID()), "Meteora", linkinPark, 2003, 14.99d, 12, Medium.VINYL, warnerBrothersRecords, List.of(), List.of());
//         Product meteoraCD = new Product(new ProductId(UUID.randomUUID()), "Meteora", linkinPark, 2005, 9.99d, 33, Medium.CD, warnerBrothersRecords, List.of(), List.of());
//         Product humanityCD = new Product(new ProductId(UUID.randomUUID()), "Humanity", bleedFromWithin, 2009, 19.99d, 2, Medium.CD, risingRecords, List.of(), List.of());
//         Product humanityMP3 = new Product(new ProductId(UUID.randomUUID()), "Humanity", bleedFromWithin, 2009, 19.99d, 0, Medium.MP3, risingRecords, List.of(), List.of());
//         List<Product> products = List.of(kindOfBlueVinyl, meteoraVinyl, meteoraCD, humanityCD, humanityMP3);
//         List<Product> wantedProducts = List.of(meteoraVinyl, meteoraCD);
//
//         // Flush Data (load data into database but do not persist)
//         entityManager.getTransaction().begin();
//         interprets.forEach(entityManager::persist);
//         labels.forEach(entityManager::persist);
//         products.forEach(entityManager::persist);
//         entityManager.flush();
//         List<Product> result = productRepository.search("meteora");
//         entityManager.getTransaction().rollback();
//
//         // Assertions
//         assertEquals(wantedProducts.size(), result.size());
//     }
// }