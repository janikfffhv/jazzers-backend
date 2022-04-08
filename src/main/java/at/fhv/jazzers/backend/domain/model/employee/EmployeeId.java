package at.fhv.jazzers.backend.domain.model.employee;

import javax.persistence.*;
import java.util.Objects;

@Embeddable
public class EmployeeId {
    // Properties
    private String id;



    // Constructors
    protected EmployeeId() {

    }

    public EmployeeId(String id) {
        this.id = id;
    }



    // Getters
    public String id() {
        return id;
    }



    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeId that = (EmployeeId) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
