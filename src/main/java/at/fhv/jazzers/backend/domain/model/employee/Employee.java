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

    private String email;

    private String userId;

    private String password;

    private String firstName;

    private String lastName;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Role> role;

    // Constructors
    protected Employee() {

    }

    public Employee(EmployeeId employeeId, String email, String userId, String password, String firstName, String lastName, List<Role> role) {
        this.employeeId = employeeId;
        this.email = email;
        this.userId = userId;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }



    // Domain Methods
    // -



    // Getters
    public EmployeeId employeeId() {
        return employeeId;
    }

    public String email() {
        return email;
    }

    public String userId() {
        return userId;
    }

    public String password() {
        return password;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public List<Role> role() {
        return List.copyOf(role);
    }
}
