package at.fhv.jazzers.domain.model.product;

import at.fhv.jazzers.domain.model.customer.Customer;
import at.fhv.jazzers.domain.model.sale.Sale;
import at.fhv.jazzers.domain.model.work.Work;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
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

    private ProductType productType;

    private Medium medium;

    @ManyToOne
    private Label label;

    @ManyToMany
    private Set<Supplier> supplier;

    @ManyToMany
    private Set<Work> works;

    @ManyToMany
    private Set<Sale> sales;

    @ManyToMany
    private Set<Customer> customers;



    // Constructors
    protected Product() {

    }

    public Product(ProductId productId, String title, int releaseYear, double price, int stock, ProductType productType, Medium medium, Label label, HashSet<Supplier> supplier, HashSet<Work> works, HashSet<Sale> sales, HashSet<Customer> customers) {
        this.productId = productId;
        this.title = title;
        this.releaseYear = releaseYear;
        this.price = price;
        this.stock = stock;
        this.productType = productType;
        this.medium = medium;
        this.label = label;
        this.supplier = supplier;
        this.works = works;
        this.sales = sales;
        this.customers = customers;
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

    public ProductType productType() {
        return productType;
    }

    public Medium medium() {
        return medium;
    }

    public Label label() {
        return label;
    }

    public HashSet<Supplier> supplier() {
        return (HashSet<Supplier>) Set.copyOf(supplier);
    }

    public HashSet<Work> works() {
        return (HashSet<Work>) Set.copyOf(works);
    }

    public HashSet<Sale> sales() {
        return (HashSet<Sale>) Set.copyOf(sales);
    }

    public HashSet<Customer> customers() {
        return (HashSet<Customer>) Set.copyOf(customers);
    }
}
