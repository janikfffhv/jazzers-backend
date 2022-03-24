package at.fhv.jazzers.backend.application.api;

import at.fhv.jazzers.shared.dto.ProductOverviewDTO;

import java.util.List;

public interface ProductService {
    List<ProductOverviewDTO> searchAnalog(String titleOrInterpret);
    List<ProductOverviewDTO> searchDigital(String titleOrInterpret);
}