package at.fhv.jazzers.domain.model.work;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Interpret {
    // Properties
    @Id @GeneratedValue
    private long interpretIdInternal;

    private String name;



    // Constructors
    protected Interpret() {

    }

    public Interpret(String name) {
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
        Interpret interpret = (Interpret) o;
        return name.equals(interpret.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
