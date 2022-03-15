package at.fhv.jazzers.domain.model.sale;

import at.fhv.jazzers.domain.model.customer.Customer;
import at.fhv.jazzers.domain.model.employee.Employee;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Sale {
    // Properties
    @Id
    @GeneratedValue
    private Long saleIdInternal;

    @Embedded
    private SaleId saleId;

    private SaleType saleType;

    @OneToMany
    private Set<Line> lines;

    @ManyToOne
    private Employee employee;

    @ManyToOne()
    private Customer customer;



    // Constructors
    protected Sale() {

    }

    public Sale(SaleId saleId, SaleType saleType, Set<Line> lines, Employee employee, Customer customer) {
        this.saleId = saleId;
        this.saleType = saleType;
        this.lines = lines;
        this.employee = employee;
        this.customer = customer;
    }



    // Domain Methods
    // -



    // Getters
    public SaleId saleId() {
        return saleId;
    }

    public SaleType saleType() {
        return saleType;
    }

    public HashSet<Line> lines() {
        return (HashSet<Line>) Set.copyOf(lines);
    }

    public Employee employee() {
        return employee;
    }

    public Customer customer() {
        return customer;
    }
}
