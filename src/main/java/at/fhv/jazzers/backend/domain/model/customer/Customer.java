package at.fhv.jazzers.backend.domain.model.customer;

import at.fhv.jazzers.backend.domain.model.product.Product;
import at.fhv.jazzers.backend.domain.model.sale.Sale;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Customer {
    // Properties
    @Id
    @GeneratedValue
    private Long customerIdInternal;

    @Embedded
    private CustomerId customerId;

    private String username;

    private String firstName;

    private String lastName;

    private String iban;

    @OneToMany
    private List<Sale> sales;

    @OneToOne(cascade = CascadeType.ALL)
    private Playlist collection;



    // Constructors
    protected Customer() {

    }

    public Customer(String username, String firstName, String lastName, String iban) {
        this.customerId = new CustomerId(UUID.randomUUID());
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.iban = iban;
        this.sales = new ArrayList<>();
        this.collection = new Playlist(new PlaylistId(UUID.randomUUID()), "Collection", new ArrayList<>());
    }

    public Customer(CustomerId customerId, List<Sale> sales) {
        this.customerId = customerId;
        this.username = "";
        this.firstName = "";
        this.lastName = "";
        this.iban = "";
        this.sales = sales;
        this.collection = new Playlist(new PlaylistId(UUID.randomUUID()), "Collection", new ArrayList<>());
    }



    // Domain Methods
    public void addProductToCollection(Product product) {
        collection.addProduct(product);
    }



    // Getters
    public CustomerId customerId() {
        return customerId;
    }

    public String username() {
        return username;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public String iban() {
        return iban;
    }

    public List<Sale> sales() {
        return List.copyOf(sales);
    }

    public Playlist collection() {
        return collection;
    }
}
