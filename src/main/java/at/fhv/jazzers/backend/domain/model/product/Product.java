package at.fhv.jazzers.backend.domain.model.product;

import at.fhv.jazzers.backend.domain.model.interpret.Interpret;
import at.fhv.jazzers.backend.domain.model.work.Work;

import javax.persistence.*;
import java.util.List;

@Entity
public class Product {
    // Properties
    @Id
    @GeneratedValue
    private Long productIdInternal;

    @Embedded
    private ProductId productId;

    private String title;

    @ManyToOne
    private Interpret interpret;

    private int releaseYear;

    private double price;

    private int stock;

    private String downloadLink;

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

    public Product(ProductId productId, String title, Interpret interpret, int releaseYear, double price, int stock, String downloadLink, Medium medium, Label label, List<Supplier> supplier, List<Work> works) {
        this.productId = productId;
        this.title = title;
        this.interpret = interpret;
        this.releaseYear = releaseYear;
        this.price = price;
        this.stock = stock;
        this.downloadLink = downloadLink;
        this.medium = medium;
        this.label = label;
        this.supplier = supplier;
        this.works = works;
    }



    // Domain Methods
    public void takeFromStock(int amount) {
        if (canMeetDemand(amount)) {
            if (!medium().equals(Medium.MP3)) {
                stock -= amount;
            }
        } else {
            throw new IllegalArgumentException("Not enough stock available.");
        }
    }

    public void addToStock(int amount) {
        if (!medium().equals(Medium.MP3)) {
            stock += amount;
        }
    }



    // Getters
    public ProductId productId() {
        return productId;
    }

    public String title() {
        return title;
    }

    public Interpret interpret() {
        return interpret;
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

    public String downloadLink() {
        return downloadLink;
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

    public boolean canMeetDemand(int demand) {
        return medium().equals(Medium.MP3) || demand <= stock;
    }
}
