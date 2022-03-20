package at.fhv.jazzers.domain.model.product;

import at.fhv.jazzers.domain.model.customer.Customer;
import at.fhv.jazzers.domain.model.sale.Sale;
import at.fhv.jazzers.domain.model.work.Interpret;
import at.fhv.jazzers.domain.model.work.Work;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Product {
    // Properties
    @Id
    @GeneratedValue
    private Long productIdInternal;

    @Embedded
    private ProductId productId;

    private String title;

    private int releaseYear;

    private double price;

    private int stock;

    @Enumerated(EnumType.STRING)
    private Medium medium;

    @ManyToOne
    private Label label;

    @ManyToMany
    private List<Supplier> supplier;

    @ManyToMany
    private List<Work> works;



    // Constructors
    protected Product() {

    }

    public Product(ProductId productId, String title, int releaseYear, double price, int stock, Medium medium, Label label, List<Supplier> supplier, List<Work> works) {
        this.productId = productId;
        this.title = title;
        this.releaseYear = releaseYear;
        this.price = price;
        this.stock = stock;
        this.medium = medium;
        this.label = label;
        this.supplier = supplier;
        this.works = works;
    }



    // Domain Methods
    // -



    // Getters
    public ProductId productId() {
        return productId;
    }

    public String title() {
        return title;
    }

    public int releaseYear() {
        return releaseYear;
    }

    public double price() {
        return price;
    }

    public int stock() {
        return stock;
    }

    public Medium medium() {
        return medium;
    }

    public Label label() {
        return label;
    }

    public List<Supplier> supplier() {
        return List.copyOf(supplier);
    }

    public List<Work> works() {
        return List.copyOf(works);
    }

    public Interpret interpret() {
        return works().get(0).interpret();
    }
}
