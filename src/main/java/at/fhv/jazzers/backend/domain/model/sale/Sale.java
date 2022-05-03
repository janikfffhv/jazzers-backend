package at.fhv.jazzers.backend.domain.model.sale;

import at.fhv.jazzers.backend.domain.model.customer.Customer;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Sale {
    // Properties
    @Id
    @GeneratedValue
    private Long saleIdInternal;

    @Embedded
    private SaleId saleId;

    private LocalDate saleDate;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Line> lines;

    @ManyToOne
    private Customer customer;


    // Constructors
    protected Sale() {

    }

    private Sale(SaleId saleId, List<Line> lines, Customer customer) {
        this.saleId = saleId;
        this.saleDate = LocalDate.now();
        this.lines = lines;
        this.customer = customer;
    }

    public static Sale create(SaleId saleId, List<Line> lines, Customer customer) {
        if (lines.size() <= 0) {
            throw new IllegalArgumentException("The sale must have at least one line.");
        }

        for (Line line : lines) {
            if (line.amountRefunded() != 0) {
                throw new IllegalArgumentException("A purchase must not have refunds!");
            }
        }

        return new Sale(saleId, lines, customer);
    }


    // Domain Methods
    public void updateRefunds(List<Line> newLines) {
        for (Line line : this.lines) {
            for (Line newLine : newLines) {
                if (line.lineId().equals(newLine.lineId())) {
                    line.updateAmountRefunded(newLine.amountRefunded());
                }
            }
        }
    }



    // Getters
    public SaleId saleId() {
        return saleId;
    }

    public LocalDate saleDate() {
        return saleDate;
    }

    public List<Line> lines() {
        return List.copyOf(lines);
    }

    public Customer customer() {
        return customer;
    }

    public double salePurchaseTotal() {
        double saleTotal = 0;

        for (Line line : lines) {
            saleTotal += line.linePurchaseTotal();
        }

        return saleTotal;
    }

    public double saleRefundTotal() {
        double saleTotal = 0;

        for (Line line : lines) {
            saleTotal += line.lineRefundTotal();
        }

        return saleTotal;
    }

    public double saleActualTotal() {
        double saleTotal = 0;

        for (Line line : lines) {
            saleTotal += line.lineActualTotal();
        }

        return saleTotal;
    }

    public int amountPurchasedTotal() {
        int amountTotal = 0;

        for (Line line : lines) {
            amountTotal += line.amountPurchased();
        }

        return amountTotal;
    }

    public int amountRefundedTotal() {
        int amountTotal = 0;

        for (Line line : lines) {
            amountTotal += line.amountRefunded();
        }

        return amountTotal;
    }

    public int amountActualTotal() {
        int amountTotal = 0;

        for (Line line : lines) {
            amountTotal += line.amountPurchased() - line.amountRefunded();
        }

        return amountTotal;
    }
}
