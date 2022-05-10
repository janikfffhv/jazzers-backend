// package at.fhv.jazzers.backend.unit.application;
//
// import at.fhv.jazzers.backend.application.api.ProductService;
// import at.fhv.jazzers.backend.application.impl.ProductServiceImpl;
// import at.fhv.jazzers.backend.domain.model.interpret.Interpret;
// import at.fhv.jazzers.backend.domain.model.interpret.InterpretId;
// import at.fhv.jazzers.backend.domain.model.product.Medium;
// import at.fhv.jazzers.backend.domain.model.product.Product;
// import at.fhv.jazzers.backend.domain.model.product.ProductId;
// import at.fhv.jazzers.backend.domain.repository.ProductRepository;
// import at.fhv.jazzers.shared.dto.ProductOverviewDTO;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
//
// import java.util.List;
// import java.util.UUID;
// import java.util.stream.Collectors;
//
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.Mockito.*;
//
// public class ProductServiceTests {
//     private ProductRepository productRepository;
//     private ProductService productService;
//
//     @BeforeEach
//     void setup() {
//         productRepository = mock(ProductRepository.class);
//         productService = new ProductServiceImpl(productRepository);
//     }
//
//     @Test
//     public void when_searching_analog_then_analog_products() {
//         // Prepare Data
//         Product product1 = new Product(new ProductId(UUID.randomUUID()), "Title1", new Interpret(new InterpretId(UUID.randomUUID()), "Interpret1"), 1111, 1.11d, 11, Medium.MP3, null, null, List.of());
//         Product product2 = new Product(new ProductId(UUID.randomUUID()), "Title2", new Interpret(new InterpretId(UUID.randomUUID()), "Interpret2"), 2222, 2.22d, 22, Medium.VINYL, null, null, List.of());
//         List<Product> products = List.of(product1, product2);
//
//         List<ProductOverviewDTO> productsDTO = products
//                 .stream()
//                 .map(product -> new ProductOverviewDTO(
//                         product.productId().id(),
//                         product.interpret().name(),
//                         product.title(),
//                         product.medium().getName(),
//                         product.stock(),
//                         product.price()))
//                 .collect(Collectors.toList());
//
//         List<ProductOverviewDTO> analogProductsDTO = productsDTO
//                 .stream()
//                 .filter(product -> !product.getMedium().equals(Medium.MP3.getName()))
//                 .collect(Collectors.toList());
//
//         // Mocking
//         when(productRepository.search("")).thenReturn(products);
//
//         // Execute Use Case
//         List<ProductOverviewDTO> actual = productService.searchAnalog("");
//
//         // Assertion / Verification
//         assertEquals(analogProductsDTO, actual);
//         verify(productRepository).search("");
//     }
//
//     @Test
//     public void when_searching_digital_then_digital_products() {
//         // Prepare Data
//         Product product1 = new Product(new ProductId(UUID.randomUUID()), "Title1", new Interpret(new InterpretId(UUID.randomUUID()), "Interpret1"), 1111, 1.11d, 11, Medium.MP3, null, null, List.of());
//         Product product2 = new Product(new ProductId(UUID.randomUUID()), "Title2", new Interpret(new InterpretId(UUID.randomUUID()), "Interpret2"), 2222, 2.22d, 22, Medium.VINYL, null, null, List.of());
//         List<Product> products = List.of(product1, product2);
//
//         List<ProductOverviewDTO> productsDTO = products
//                 .stream()
//                 .map(product -> new ProductOverviewDTO(
//                         product.productId().id(),
//                         product.interpret().name(),
//                         product.title(),
//                         product.medium().getName(),
//                         product.stock(),
//                         product.price()))
//                 .collect(Collectors.toList());
//
//         List<ProductOverviewDTO> digitalProductsDTO = productsDTO
//                 .stream()
//                 .filter(product -> product.getMedium().equals(Medium.MP3.getName()))
//                 .collect(Collectors.toList());
//
//         // Mocking
//         when(productRepository.search("")).thenReturn(products);
//
//         // Execute Use Case
//         List<ProductOverviewDTO> actual = productService.searchDigital("");
//
//         // Assertion / Verification
//         assertEquals(digitalProductsDTO, actual);
//         verify(productRepository).search("");
//     }
// }