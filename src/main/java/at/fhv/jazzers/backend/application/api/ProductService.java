package at.fhv.jazzers.backend.application.api;

import at.fhv.jazzers.backend.application.dto.ProductOverviewDTO;

import java.util.List;

public interface ProductService {
    List<ProductOverviewDTO> searchByTitleOrInterpret(String searchString);
}