package at.fhv.jazzers.backend.application.api;

import at.fhv.jazzers.shared.dto.DigitalProductDTO;
import at.fhv.jazzers.shared.dto.ProductOverviewDTO;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Local
public interface ProductService {
    List<ProductOverviewDTO> searchAnalog(String titleOrInterpret);
    List<DigitalProductDTO> searchDigital(String titleOrInterpret);
    String downloadLink(UUID productId);
}