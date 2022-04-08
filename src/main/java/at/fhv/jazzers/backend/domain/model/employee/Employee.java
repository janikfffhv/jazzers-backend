package at.fhv.jazzers.backend.domain.model.employee;

import javax.persistence.*;
import java.util.List;

@Entity
public class Employee {
    // Properties
    @Id
    @GeneratedValue
    private Long employeeIdInternal;

    @Embedded
    private EmployeeId employeeId;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Role> role;

    // Constructors
    protected Employee() {

    }

    public Employee(EmployeeId employeeId, List<Role> role) {
        this.employeeId = employeeId;
        this.role = role;
    }



    // Domain Methods
    // -



    // Getters
    public EmployeeId employeeId() {
        return employeeId;
    }

    public List<Role> role() {
        return List.copyOf(role);
    }
}
