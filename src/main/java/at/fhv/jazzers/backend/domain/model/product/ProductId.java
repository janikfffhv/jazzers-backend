package at.fhv.jazzers.backend.domain.model.product;

import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class ProductId {
    // Properties
    private UUID id;



    // Constructors
    protected ProductId() {

    }

    public ProductId(UUID id) {
        this.id = id;
    }



    // Getters
    public UUID id() {
        return id;
    }



    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductId productId = (ProductId) o;
        return id.equals(productId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
