package at.fhv.jazzers.backend.unit.communication;

import at.fhv.jazzers.backend.application.api.ProductService;
import at.fhv.jazzers.backend.communication.RMI_ProductServiceImpl;
import at.fhv.jazzers.shared.api.RMI_ProductService;
import at.fhv.jazzers.shared.dto.ProductOverviewDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RMIProductServiceTests {
    private ProductService productService;
    private RMI_ProductService rmi_productService;

    @BeforeEach
    void setup() throws RemoteException {
        productService = mock(ProductService.class);
        rmi_productService = new RMI_ProductServiceImpl(productService);
    }

    @Test
    public void when_searching_analog_with_empty_query_then_all_analog_products() throws RemoteException {
        // Prepare Data
        ProductOverviewDTO productOverviewDTO1 = new ProductOverviewDTO(UUID.randomUUID(), "Interpret1" , "Title1", "Vinyl", 11, 1.11d);
        ProductOverviewDTO productOverviewDTO2 = new ProductOverviewDTO(UUID.randomUUID(), "Interpret2" , "Title2", "CD", 22, 2.22d);
        List<ProductOverviewDTO> productOverviewsDTO = List.of(productOverviewDTO1, productOverviewDTO2);

        // Mocking
        when(productService.searchAnalog("")).thenReturn(productOverviewsDTO);

        // Execute Use Case
        List<ProductOverviewDTO> actual = rmi_productService.searchAnalog("");

        // Assertion / Verification
        assertEquals(productOverviewsDTO, actual);
        verify(productService).searchAnalog("");
    }
}
