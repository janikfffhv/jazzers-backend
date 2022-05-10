package at.fhv.jazzers.backend.application.api;

import at.fhv.jazzers.shared.dto.ProductOverviewDTO;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProductService {
    List<ProductOverviewDTO> searchAnalog(String titleOrInterpret);
    List<ProductOverviewDTO> searchDigital(String titleOrInterpret);
}