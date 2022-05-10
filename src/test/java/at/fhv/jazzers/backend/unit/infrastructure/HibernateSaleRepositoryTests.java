// package at.fhv.jazzers.backend.unit.infrastructure;
//
// import at.fhv.jazzers.backend.ServiceRegistry;
// import at.fhv.jazzers.backend.domain.model.interpret.Interpret;
// import at.fhv.jazzers.backend.domain.model.interpret.InterpretId;
// import at.fhv.jazzers.backend.domain.model.product.Label;
// import at.fhv.jazzers.backend.domain.model.product.Medium;
// import at.fhv.jazzers.backend.domain.model.product.Product;
// import at.fhv.jazzers.backend.domain.model.product.ProductId;
// import at.fhv.jazzers.backend.domain.model.sale.*;
// import at.fhv.jazzers.backend.domain.repository.SaleRepository;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
//
// import javax.ejb.EJB;
// import javax.persistence.EntityManager;
// import java.util.List;
// import java.util.Optional;
// import java.util.UUID;
//
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
//
// public class HibernateSaleRepositoryTests {
//     private EntityManager entityManager;
//     @EJB
//     private SaleRepository saleRepository;
//
//     @BeforeEach
//     void setUp() {
//         this.entityManager = ServiceRegistry.entityManager();
//     }
//
//     @Test
//     public void when_searching_sales_by_id_then_matching_sale() {
//         // Generate Test Data
//         Interpret interpret = new Interpret(new InterpretId(UUID.randomUUID()), "Interpret");
//         Label label = new Label("Label");
//         Product product = new Product(new ProductId(UUID.randomUUID()), "Title", interpret, 2010, 10.0d, 100, Medium.VINYL, label, List.of(), List.of());
//
//         Line line1 = new Line(new LineId(UUID.randomUUID()), 1, 0, product);
//         Line line2 = new Line(new LineId(UUID.randomUUID()), 1, 0, product);
//         Line line3 = new Line(new LineId(UUID.randomUUID()), 1, 0, product);
//
//         SaleId sale1Id = new SaleId(UUID.randomUUID());
//         SaleId sale2Id = new SaleId(UUID.randomUUID());
//         SaleId sale3Id = new SaleId(UUID.randomUUID());
//
//         Sale sale1 = Sale.create(sale1Id, List.of(line1), null);
//         Sale sale2 = Sale.create(sale2Id, List.of(line2), null);
//         Sale sale3 = Sale.create(sale3Id, List.of(line3), null);
//
//         List<Sale> sales = List.of(sale1, sale2, sale3);
//
//         // Flush Data (load data into database but do not persist)
//         entityManager.getTransaction().begin();
//         List.of(interpret, label, product).forEach(entityManager::persist);
//         sales.forEach(entityManager::persist);
//         entityManager.flush();
//         Optional<Sale> actual = saleRepository.byId(sale2Id);
//         entityManager.getTransaction().rollback();
//
//         // Assertions
//         assertNotNull(actual);
//         actual.ifPresent(sale -> assertEquals(sale2Id, sale.saleId()));
//     }
//
//     @Test
//     public void when_searching_customers_by_invalid_id_then_optional_empty() {
//         // Generate Test Data
//         Interpret interpret = new Interpret(new InterpretId(UUID.randomUUID()), "Interpret");
//         Label label = new Label("Label");
//         Product product = new Product(new ProductId(UUID.randomUUID()), "Title", interpret, 2010, 10.0d, 100, Medium.VINYL, label, List.of(), List.of());
//
//         Line line1 = new Line(new LineId(UUID.randomUUID()), 1, 0, product);
//         Line line2 = new Line(new LineId(UUID.randomUUID()), 1, 0, product);
//         Line line3 = new Line(new LineId(UUID.randomUUID()), 1, 0, product);
//
//         SaleId sale1Id = new SaleId(UUID.randomUUID());
//         SaleId sale2Id = new SaleId(UUID.randomUUID());
//         SaleId sale3Id = new SaleId(UUID.randomUUID());
//
//         Sale sale1 = Sale.create(sale1Id, List.of(line1), null);
//         Sale sale2 = Sale.create(sale2Id, List.of(line2), null);
//         Sale sale3 = Sale.create(sale3Id, List.of(line3), null);
//
//         List<Sale> sales = List.of(sale1, sale2, sale3);
//
//         // Flush Data (load data into database but do not persist)
//         entityManager.getTransaction().begin();
//         List.of(interpret, label, product).forEach(entityManager::persist);
//         sales.forEach(entityManager::persist);
//         entityManager.flush();
//         Optional<Sale> actual = saleRepository.byId(new SaleId(UUID.randomUUID()));
//         entityManager.getTransaction().rollback();
//
//         // Assertions
//         assertEquals(Optional.empty(), actual);
//     }
// }
//