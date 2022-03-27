package at.fhv.jazzers.backend.domain.repository;

import at.fhv.jazzers.backend.domain.model.sale.Sale;

public interface SaleRepository {
    void save(Sale sale);
}
