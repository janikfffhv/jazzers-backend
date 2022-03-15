package at.fhv.jazzers.domain.model.sale;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class SaleId {
    // Properties
    private UUID id;



    // Constructors
    protected SaleId() {

    }

    public SaleId(UUID id) {
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
        SaleId saleId = (SaleId) o;
        return id.equals(saleId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
