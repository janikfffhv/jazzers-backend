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
    private List<Role> roles;

    // Constructors
    protected Employee() {

    }

    public Employee(EmployeeId employeeId, List<Role> roles) {
        this.employeeId = employeeId;
        this.roles = roles;
    }



    // Domain Methods
    // -



    // Getters
    public EmployeeId employeeId() {
        return employeeId;
    }

    public List<Role> roles() {
        return List.copyOf(roles);
    }
}
