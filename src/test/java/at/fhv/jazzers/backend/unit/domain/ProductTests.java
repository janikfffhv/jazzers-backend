package at.fhv.jazzers.backend.unit.domain;

import at.fhv.jazzers.backend.domain.model.product.Medium;
import at.fhv.jazzers.backend.domain.model.product.Product;
import at.fhv.jazzers.backend.domain.model.product.ProductId;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductTests {
    @Test
    public void given_no_stock_when_taking_from_stock_then_exception() {
        Product product = new Product(new ProductId(UUID.randomUUID()), "Kind Of Blue", null, 1959, 22.99d, 0, Medium.VINYL, null, List.of(), List.of());

        assertThrows(IllegalArgumentException.class, () -> product.takeFromStock(1));
    }

    @Test
    public void given_enough_stock_when_taking_from_stock_then_updated_stock() {
        Product product = new Product(new ProductId(UUID.randomUUID()), "Kind Of Blue", null, 1959, 22.99d, 20, Medium.VINYL, null, List.of(), List.of());

        product.takeFromStock(2);

        assertEquals(18, product.stock());
    }

    @Test
    public void when_adding_to_stock_then_updated_stock() {
        Product product = new Product(new ProductId(UUID.randomUUID()), "Kind Of Blue", null, 1959, 22.99d, 20, Medium.VINYL, null, List.of(), List.of());

        product.addToStock(2);

        assertEquals(22, product.stock());
    }
}