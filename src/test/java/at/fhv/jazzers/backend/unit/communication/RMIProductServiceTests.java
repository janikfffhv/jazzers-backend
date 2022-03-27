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
    public void when_searching_analog_then_analog_products() throws RemoteException {
        // Prepare Data
        ProductOverviewDTO productOverviewDTO1 = new ProductOverviewDTO(UUID.randomUUID(), "Interpret1" , "Title1", "Vinyl", 11, 1.11d);
        List<ProductOverviewDTO> analogProductsDTO = List.of(productOverviewDTO1);

        // Mocking
        when(productService.searchAnalog("")).thenReturn(analogProductsDTO);

        // Execute Use Case
        List<ProductOverviewDTO> actual = rmi_productService.searchAnalog("");

        // Assertion / Verification
        assertEquals(analogProductsDTO, actual);
        verify(productService).searchAnalog("");
    }

    @Test
    public void when_searching_digital_then_digital_products() throws RemoteException {
        // Prepare Data
        ProductOverviewDTO productOverviewDTO1 = new ProductOverviewDTO(UUID.randomUUID(), "Interpret1" , "Title1", "MP3", 11, 1.11d);
        List<ProductOverviewDTO> digitalProductsDTO = List.of(productOverviewDTO1);

        // Mocking
        when(productService.searchDigital("")).thenReturn(digitalProductsDTO);

        // Execute Use Case
        List<ProductOverviewDTO> actual = rmi_productService.searchDigital("");

        // Assertion / Verification
        assertEquals(digitalProductsDTO, actual);
        verify(productService).searchDigital("");
    }
}
