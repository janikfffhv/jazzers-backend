package at.fhv.jazzers.backend.domain.model.employee;

import at.fhv.jazzers.backend.domain.model.work.Genre;

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

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Genre> subscribedTopics;

    // Constructors
    protected Employee() {

    }

    public Employee(EmployeeId employeeId, List<Role> roles, List<Genre> subscribedTopics) {
        this.employeeId = employeeId;
        this.roles = roles;
        this.subscribedTopics = subscribedTopics;
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

    public List<Genre> subscribedTopics() {
        return List.copyOf(subscribedTopics);
    }
}
