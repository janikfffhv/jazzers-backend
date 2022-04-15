package at.fhv.jazzers.backend.domain.model.customer;

import at.fhv.jazzers.backend.domain.model.sale.Sale;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {
    // Properties
    @Id
    @GeneratedValue
    private Long customerIdInternal;

    @Embedded
    private CustomerId customerId;

    @OneToMany
    private List<Sale> sales;

    @OneToMany
    private List<Playlist> playlists;



    // Constructors
    protected Customer() {

    }

    public Customer(CustomerId customerId, List<Sale> sales, List<Playlist> playlists) {
        this.customerId = customerId;
        this.sales = sales;
        this.playlists = playlists;
    }



    // Domain Methods
    // -



    // Getters
    public CustomerId customerId() {
        return customerId;
    }

    public List<Sale> sales() {
        return List.copyOf(sales);
    }

    public List<Playlist> playlists() {
        return List.copyOf(playlists);
    }
}
