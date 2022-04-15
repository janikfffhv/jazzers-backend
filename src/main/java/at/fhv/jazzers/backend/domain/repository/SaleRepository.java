package at.fhv.jazzers.backend.domain.repository;

import at.fhv.jazzers.backend.domain.model.sale.Sale;
import at.fhv.jazzers.backend.domain.model.sale.SaleId;

import java.util.Optional;

public interface SaleRepository {
    Optional<Sale> byId(SaleId saleId);
    void save(Sale sale);
}
