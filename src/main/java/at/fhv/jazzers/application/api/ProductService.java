package at.fhv.jazzers.application.api;

import at.fhv.jazzers.application.dto.ProductOverviewDTO;

import java.util.List;

public interface ProductService {
    List<ProductOverviewDTO> searchByTitleOrInterpret(String searchString);
}