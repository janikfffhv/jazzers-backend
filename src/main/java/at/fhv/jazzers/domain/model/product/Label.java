package at.fhv.jazzers.domain.model.product;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Label {
    // Properties
    @Id
    @GeneratedValue
    private Long labelIdInternal;

    private String name;



    // Constructors
    protected Label() {

    }

    public Label(String name) {
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
        Label label = (Label) o;
        return name.equals(label.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
