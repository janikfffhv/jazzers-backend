package at.fhv.jazzers.backend.unit.infrastructure;

import at.fhv.jazzers.backend.ServiceRegistry;
import at.fhv.jazzers.backend.domain.repository.ProductRepository;
import org.junit.jupiter.api.Test;

public class HibernateProductRepositoryTests {
    private final ProductRepository productRepository = ServiceRegistry.productRepository();

    @Test
    public void given_x_when_y_then_z() {
        // asserts
    }
}
