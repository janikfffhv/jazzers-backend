package at.fhv.jazzers.domain.model.sale;

import at.fhv.jazzers.domain.model.customer.Customer;
import at.fhv.jazzers.domain.model.employee.Employee;

import javax.persistence.*;
import java.util.List;

@Entity
public class Sale {
    // Properties
    @Id
    @GeneratedValue
    private Long saleIdInternal;

    @Embedded
    private SaleId saleId;

    @Enumerated(EnumType.STRING)
    private SaleType saleType;

    @OneToMany
    private List<Line> lines;

    @ManyToOne
    private Employee employee;

    @ManyToOne()
    private Customer customer;



    // Constructors
    protected Sale() {

    }

    public Sale(SaleId saleId, SaleType saleType, List<Line> lines, Employee employee, Customer customer) {
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

    public List<Line> lines() {
        return List.copyOf(lines);
    }

    public Employee employee() {
        return employee;
    }

    public Customer customer() {
        return customer;
    }
}
