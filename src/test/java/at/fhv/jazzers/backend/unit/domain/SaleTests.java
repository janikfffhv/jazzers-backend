// package at.fhv.jazzers.backend.unit.domain;
//
// import at.fhv.jazzers.backend.domain.model.sale.*;
// import org.junit.jupiter.api.Test;
//
// import java.util.List;
// import java.util.UUID;
//
// import static org.junit.jupiter.api.Assertions.*;
//
// public class SaleTests {
//     @Test
//     public void given_no_lines_when_creating_sale_then_exception() {
//         assertThrows(IllegalArgumentException.class, () -> Sale.create(new SaleId(UUID.randomUUID()), List.of(), null));
//     }
//
//     @Test
//     public void given_at_least_one_line_when_creating_sale_then_ok() {
//         SaleId saleId = new SaleId(UUID.randomUUID());
//         List<Line> lines = List.of(new Line(new LineId(UUID.randomUUID()), 2, 0, null));
//
//         Sale sale = Sale.create(saleId, lines, null);
//
//         assertEquals(saleId, sale.saleId());
//         assertEquals(lines, sale.lines());
//         assertNull(sale.customer());
//     }
// }