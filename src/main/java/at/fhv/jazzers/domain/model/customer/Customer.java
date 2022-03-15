package at.fhv.jazzers.domain.model.customer;

import at.fhv.jazzers.domain.model.sale.Sale;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Customer {
    // Properties
    @Id
    @GeneratedValue
    private Long customerIdInternal;

    @Embedded
    private CustomerId customerId;

    private String email;

    private String userId;

    private String password;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String iban;

    @Embedded
    private Address address;

    @OneToMany()
    private Set<Sale> sales;

    @OneToMany()
    private Set<Playlist> playlists;



    // Constructors
    protected Customer() {

    }

    public Customer(CustomerId customerId, String email, String userId, String password, String firstName, String lastName, LocalDate birthDate, String iban, Address address, HashSet<Sale> sales, HashSet<Playlist> playlists) {
        this.customerId = customerId;
        this.email = email;
        this.userId = userId;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.iban = iban;
        this.address = address;
        this.sales = sales;
        this.playlists = playlists;
    }



    // Domain Methods
    // -



    // Getters
    public CustomerId customerId() {
        return customerId;
    }

    public String email() {
        return email;
    }

    public String userId() {
        return userId;
    }

    public String password() {
        return password;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public LocalDate birthDate() {
        return birthDate;
    }

    public String iban() {
        return iban;
    }

    public Address address() {
        return address;
    }

    public HashSet<Sale> sales() {
        return (HashSet<Sale>) Set.copyOf(sales);
    }

    public HashSet<Playlist> playlists() {
        return (HashSet<Playlist>) Set.copyOf(playlists);
    }
}
