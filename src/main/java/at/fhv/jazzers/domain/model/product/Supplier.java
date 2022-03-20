package at.fhv.jazzers.domain.model.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Supplier {
    // Properties
    @Id
    @GeneratedValue
    private Long supplierIdInternal;

    private String name;



    // Constructors
    protected Supplier() {

    }

    public Supplier(String name) {
        this.name = name;
    }



    // Getters
    public String name() {
        return name;
    }



    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return name.equals(supplier.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
