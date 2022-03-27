package at.fhv.jazzers.backend.application.api;

import at.fhv.jazzers.shared.dto.LineDTO;

import java.util.List;
import java.util.UUID;

public interface SaleService {
    void purchase(UUID customerId, List<LineDTO> linesDTO);
    void refund(UUID customerId, List<LineDTO> linesDTO);
}