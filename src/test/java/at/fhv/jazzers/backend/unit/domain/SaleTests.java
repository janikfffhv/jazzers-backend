package at.fhv.jazzers.backend.unit.domain;

import at.fhv.jazzers.backend.domain.model.customer.Customer;
import at.fhv.jazzers.backend.domain.model.product.Product;
import at.fhv.jazzers.backend.domain.model.sale.Line;
import at.fhv.jazzers.backend.domain.model.sale.Sale;
import at.fhv.jazzers.backend.domain.model.sale.SaleId;
import at.fhv.jazzers.backend.domain.model.sale.SaleType;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class SaleTests {
    @Test
    public void given_no_lines_when_creating_sale_then_exception() {
        assertThrows(IllegalArgumentException.class, () -> Sale.create(new SaleId(UUID.randomUUID()), SaleType.PURCHASE, List.of(), null));
    }

    @Test
    public void given_at_least_one_line_when_creating_sale_then_ok() {
        SaleId saleId = new SaleId(UUID.randomUUID());
        SaleType saleType = SaleType.PURCHASE;
        List<Line> lines = List.of(new Line(2, null));

        Sale sale = Sale.create(saleId, saleType, lines, null);

        assertEquals(saleId, sale.saleId());
        assertEquals(saleType, sale.saleType());
        assertEquals(lines, sale.lines());
        assertNull(sale.customer());
    }
}