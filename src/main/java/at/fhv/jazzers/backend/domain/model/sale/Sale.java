package at.fhv.jazzers.backend.domain.model.sale;

import at.fhv.jazzers.backend.domain.model.customer.Customer;

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

    @OneToMany(cascade = CascadeType.ALL)
    private List<Line> lines;

    @ManyToOne()
    private Customer customer;



    // Constructors
    protected Sale() {

    }

    private Sale(SaleId saleId, SaleType saleType, List<Line> lines, Customer customer) {
        this.saleId = saleId;
        this.saleType = saleType;
        this.lines = lines;
        this.customer = customer;
    }

    public static Sale create(SaleId saleId, SaleType saleType, List<Line> lines, Customer customer) {
        if (lines.size() <= 0) {
            throw new IllegalArgumentException("The sale must have at least one line.");
        }

        return new Sale(saleId, saleType, lines, customer);
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

    public Customer customer() {
        return customer;
    }

    public double saleTotal() {
        double saleTotal = 0;

        for (Line line : lines) {
            saleTotal += line.lineTotal();
        }

        return saleTotal;
    }
}
