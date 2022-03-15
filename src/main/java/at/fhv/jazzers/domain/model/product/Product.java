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

    private Type type;

    private LocalDate releaseDate;

    private double price;

    private Medium medium;

    private int stock;

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

    public Product(ProductId productId, String title, Type type, LocalDate releaseDate, double price, Medium medium, int stock, Label label, HashSet<Supplier> supplier, HashSet<Work> works, HashSet<Sale> sales, HashSet<Customer> customers) {
        this.productId = productId;
        this.title = title;
        this.type = type;
        this.releaseDate = releaseDate;
        this.price = price;
        this.medium = medium;
        this.stock = stock;
        this.label = label;
        this.supplier = supplier;
        this.works = works;
        this.sales = sales;
        this.customers = customers;
    }



    // Domain Methods
    // -



    // Getters
    public ProductId getProductId() {
        return productId;
    }

    public String getTitle() {
        return title;
    }

    public Type getType() {
        return type;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public double getPrice() {
        return price;
    }

    public Medium getMedium() {
        return medium;
    }

    public int getStock() {
        return stock;
    }

    public Label getLabel() {
        return label;
    }

    public HashSet<Supplier> getSupplier() {
        return (HashSet<Supplier>) Set.copyOf(supplier);
    }

    public HashSet<Work> getWorks() {
        return (HashSet<Work>) Set.copyOf(works);
    }

    public HashSet<Sale> getSales() {
        return (HashSet<Sale>) Set.copyOf(sales);
    }

    public HashSet<Customer> getCustomers() {
        return (HashSet<Customer>) Set.copyOf(customers);
    }
}
