package at.fhv.jazzers.backend.domain.model.employee;

import javax.persistence.*;
import java.util.Objects;

@Embeddable
public class EmployeeId {
    // Properties
    private String username;



    // Constructors
    protected EmployeeId() {

    }

    public EmployeeId(String id) {
        this.username = id;
    }



    // Getters
    public String username() {
        return username;
    }



    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeId that = (EmployeeId) o;
        return username.equals(that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
