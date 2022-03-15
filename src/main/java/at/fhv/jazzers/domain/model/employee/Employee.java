package at.fhv.jazzers.domain.model.employee;

import at.fhv.jazzers.domain.model.sale.Sale;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Employee {
    // Properties
    @Id
    @GeneratedValue
    private Long employeeIdInternal;

    @Embedded
    private EmployeeId employeeId;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private Role role;

    @OneToMany
    private Set<Sale> sales;



    // Constructors
    protected Employee() {

    }

    public Employee(EmployeeId employeeId, String email, String password, String firstName, String lastName, Role role, HashSet<Sale> sales) {
        this.employeeId = employeeId;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.sales = sales;
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

    public String password() {
        return password;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public Role role() {
        return role;
    }

    public HashSet<Sale> sales() {
        return (HashSet<Sale>) Set.copyOf(sales);
    }
}
