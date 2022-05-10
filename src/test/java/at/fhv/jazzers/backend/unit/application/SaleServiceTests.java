// package at.fhv.jazzers.backend.unit.application;
//
// import at.fhv.jazzers.backend.ServiceRegistry;
// import at.fhv.jazzers.backend.application.api.SaleService;
// import at.fhv.jazzers.backend.application.impl.SaleServiceImpl;
// import at.fhv.jazzers.backend.domain.model.interpret.Interpret;
// import at.fhv.jazzers.backend.domain.model.interpret.InterpretId;
// import at.fhv.jazzers.backend.domain.model.product.Medium;
// import at.fhv.jazzers.backend.domain.model.product.Product;
// import at.fhv.jazzers.backend.domain.model.product.ProductId;
// import at.fhv.jazzers.backend.domain.repository.CustomerRepository;
// import at.fhv.jazzers.backend.domain.repository.ProductRepository;
// import at.fhv.jazzers.backend.domain.repository.SaleRepository;
// import at.fhv.jazzers.shared.api.RMI_CustomerService;
// import at.fhv.jazzers.shared.dto.LineDTO;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
//
// import java.util.List;
// import java.util.Optional;
// import java.util.UUID;
//
// import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.mockito.Mockito.*;
//
// public class SaleServiceTests {
//     private RMI_CustomerService rmi_customerService;
//     private CustomerRepository customerRepository;
//     private ProductRepository productRepository;
//     private SaleRepository saleRepository;
//     private SaleService saleService;
//
//     @BeforeEach
//     void setup() {
//         rmi_customerService = mock(RMI_CustomerService.class);
//         customerRepository = mock(CustomerRepository.class);
//         productRepository = mock(ProductRepository.class);
//         saleRepository = mock(SaleRepository.class);
//         saleService = new SaleServiceImpl(ServiceRegistry.entityManager(), rmi_customerService, customerRepository, productRepository, saleRepository);
//     }
//
//     @Test
//     public void when_purchasing_non_existent_product_then_exception() {
//         // Prepare Data
//
//         // Mocking
//         when(productRepository.byId(new ProductId(UUID.randomUUID()))).thenThrow();
//
//         // Execute Use Case
//         assertThrows(Exception.class, () -> saleService.purchase(null, List.of()));
//
//         // Assertion / Verification
//     }
//
//     @Test
//     public void when_purchasing_products_then_ok() {
//         // Prepare Data
//         Product product1 = new Product(new ProductId(UUID.randomUUID()), "Title1", new Interpret(new InterpretId(UUID.randomUUID()), "Interpret1"), 1111, 1.11d, 11, Medium.MP3, null, null, List.of());
//         Product product2 = new Product(new ProductId(UUID.randomUUID()), "Title2", new Interpret(new InterpretId(UUID.randomUUID()), "Interpret2"), 2222, 2.22d, 22, Medium.VINYL, null, null, List.of());
//
//         LineDTO lineDTO1 = new LineDTO(UUID.randomUUID(), product1.productId().id(), product1.title(), product1.price(), 2, 0);
//         LineDTO lineDTO2 = new LineDTO(UUID.randomUUID(), product1.productId().id(), product1.title(), product1.price(), 4, 0);
//         List<LineDTO> linesDTO = List.of(lineDTO1, lineDTO2);
//
//         // Mocking
//         when(customerRepository.byId(any())).thenReturn(Optional.empty());
//         when(productRepository.byId(product1.productId())).thenReturn(Optional.of(product1));
//         when(productRepository.byId(product2.productId())).thenReturn(Optional.of(product2));
//
//         // Execute Use Case
//         saleService.purchase(null, linesDTO);
//
//         // Assertion / Verification
//         verify(saleRepository).save(any());
//     }
//
//     @Test
//     public void when_refunding_products_then_ok() {
//         // // Prepare Data
//         // Product product1 = new Product(new ProductId(UUID.randomUUID()), "Title1", new Interpret(new InterpretId(UUID.randomUUID()), "Interpret1"), 1111, 1.11d, 11, Medium.MP3, null, null, List.of());
//         // Product product2 = new Product(new ProductId(UUID.randomUUID()), "Title2", new Interpret(new InterpretId(UUID.randomUUID()), "Interpret2"), 2222, 2.22d, 22, Medium.VINYL, null, null, List.of());
//         //
//         // LineDTO lineDTO1 = new LineDTO(UUID.randomUUID(), product1.productId().id(), product1.title(), product1.price(), 2, 0);
//         // LineDTO lineDTO2 = new LineDTO(UUID.randomUUID(), product1.productId().id(), product1.title(), product1.price(), 4, 0);
//         // List<LineDTO> linesDTO = List.of(lineDTO1, lineDTO2);
//         //
//         // // Mocking
//         // when(customerRepository.byId(any())).thenReturn(Optional.empty());
//         // when(productRepository.byId(product1.productId())).thenReturn(Optional.of(product1));
//         // when(productRepository.byId(product2.productId())).thenReturn(Optional.of(product2));
//         //
//         // // Execute Use Case
//         // saleService.refund(any(), linesDTO);
//         //
//         // // Assertion / Verification
//         // verify(saleRepository).save(any());
//     }
// }