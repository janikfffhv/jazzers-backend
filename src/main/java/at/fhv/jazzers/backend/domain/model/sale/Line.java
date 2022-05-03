package at.fhv.jazzers.backend.domain.model.sale;

import at.fhv.jazzers.backend.domain.model.product.Product;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Line implements Serializable {
    // Properties
    @Id
    @GeneratedValue
    private Long lineIdInternal;

    @Embedded
    private LineId lineId;

    private int amountPurchased;

    private int amountRefunded;

    @OneToOne(cascade = CascadeType.ALL)
    private Product product;



    // Constructors
    protected Line() {

    }

    public Line(LineId lineId, int amountPurchased, int amountRefunded, Product product) {
        this.lineId = lineId;
        this.amountPurchased = amountPurchased;
        this.amountRefunded = amountRefunded;
        this.product = product;
    }

    // Domain Methods
    public void updateAmountRefunded(int amountRefunded) {
        if (amountRefunded < 0) {
            throw new IllegalArgumentException("Refund amount must not be negative!");
        }

        if (this.amountPurchased < amountRefunded) {
            throw new IllegalArgumentException("You can not refund more than you have bought!");
        }

        int difference = amountRefunded - this.amountRefunded;

        if (difference < 0) {
            throw new IllegalArgumentException("You can not refund refunds!");
        }

        this.amountRefunded += difference;
        this.product.addToStock(difference);
    }

    // Getters
    public LineId lineId() {
        return lineId;
    }

    public int amountPurchased() {
        return amountPurchased;
    }

    public int amountRefunded() {
        return amountRefunded;
    }

    public Product product() {
        return product;
    }

    public double linePurchaseTotal() {
        return amountPurchased * product().price();
    }

    public double lineRefundTotal() {
        return amountRefunded * product().price();
    }

    public double lineActualTotal() {
        return linePurchaseTotal() - lineRefundTotal();
    }
}
