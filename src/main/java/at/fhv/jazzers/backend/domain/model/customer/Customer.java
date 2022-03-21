package at.fhv.jazzers.backend.domain.model.customer;

import at.fhv.jazzers.backend.domain.model.sale.Sale;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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
    private List<Sale> sales;

    @OneToMany()
    private List<Playlist> playlists;



    // Constructors
    protected Customer() {

    }

    public Customer(CustomerId customerId, String email, String userId, String password, String firstName, String lastName, LocalDate birthDate, String iban, Address address, List<Sale> sales, List<Playlist> playlists) {
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

    public List<Sale> sales() {
        return List.copyOf(sales);
    }

    public List<Playlist> playlists() {
        return List.copyOf(playlists);
    }
}
