package at.fhv.jazzers.backend.domain.repository;

import at.fhv.jazzers.backend.domain.model.sale.Sale;
import at.fhv.jazzers.backend.domain.model.sale.SaleId;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface SaleRepository {
    Optional<Sale> byId(SaleId saleId);
    void save(Sale sale);
    List<Sale> getAll();
}
