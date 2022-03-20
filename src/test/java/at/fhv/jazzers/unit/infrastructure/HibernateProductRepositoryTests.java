package at.fhv.jazzers.unit.infrastructure;

import at.fhv.jazzers.ServiceRegistry;
import at.fhv.jazzers.domain.repository.ProductRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HibernateProductRepositoryTests {
    private final ProductRepository productRepository = ServiceRegistry.productRepository();

    @Test
    public void given_x_when_y_then_z() {
        // asserts
    }
}
