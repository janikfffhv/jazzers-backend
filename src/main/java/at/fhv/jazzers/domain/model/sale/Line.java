package at.fhv.jazzers.domain.model.sale;

import at.fhv.jazzers.domain.model.product.Product;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Line {
    // Properties
    @Id
    @GeneratedValue
    private Long lineIdInternal;

    private int amount;

    @OneToOne
    private Product product;



    // Constructors
    protected Line() {

    }

    public Line(int amount, Product product) {
        this.amount = amount;
        this.product = product;
    }



    // Getters
    public int amount() {
        return amount;
    }

    public Product product() {
        return product;
    }



    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return amount == line.amount && product.equals(line.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, product);
    }
}
