package at.fhv.jazzers.backend.domain.model.customer;

import at.fhv.jazzers.backend.domain.model.product.Product;

import javax.persistence.*;
import java.util.List;

@Entity
public class Playlist {
    // Properties
    @Id
    @GeneratedValue
    private Long playlistIdInternal;

    @Embedded
    private PlaylistId playlistId;

    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Product> products;



    // Constructors
    protected Playlist() {

    }

    public Playlist(PlaylistId playlistId, String name, List<Product> products) {
        this.playlistId = playlistId;
        this.name = name;
        this.products = products;
    }



    // Domain Methods
    public void addProduct(Product product) {
        if (products.contains(product)) {
            if (name.equals("Collection")) {
                throw new IllegalStateException("Customer already owns this product");
            }
            else {
                throw new IllegalStateException("Product already in collection");
            }
        }

        products.add(product);
    }



    // Getters
    public PlaylistId playlistId() {
        return playlistId;
    }

    public String name() {
        return name;
    }

    public List<Product> products() {
        return List.copyOf(products);
    }
}
