package at.fhv.jazzers.domain.model.work;

import at.fhv.jazzers.domain.model.customer.Playlist;
import at.fhv.jazzers.domain.model.product.Product;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Work {
    // Properties
    @Id @GeneratedValue
    private Long workIdInternal;

    @Embedded
    private WorkId workId;

    private String title;

    private int durationInSeconds;

    private int recordingYear;

    @ManyToMany
    private Set<Product> products;

    @ManyToOne
    private Interpret interpret;

    private Genre genre;

    @ManyToMany
    private Set<Playlist> playlists;



    // Constructors
    protected Work() {

    }

    public Work(WorkId workId, String title, int durationInSeconds, int recordingYear, HashSet<Product> products, Interpret interpret, Genre genre, HashSet<Playlist> playlists) {
        this.workId = workId;
        this.title = title;
        this.durationInSeconds = durationInSeconds;
        this.recordingYear = recordingYear;
        this.products = products;
        this.interpret = interpret;
        this.genre = genre;
        this.playlists = playlists;
    }



    // Domain Methods
    // -



    // Getters
    public WorkId workId() {
        return workId;
    }

    public String title() {
        return title;
    }

    public int durationInSeconds() {
        return durationInSeconds;
    }

    public int recordingYear() {
        return recordingYear;
    }

    public HashSet<Product> products() {
        return (HashSet<Product>) Set.copyOf(products);
    }

    public Interpret interpret() {
        return interpret;
    }

    public Genre genre() {
        return genre;
    }

    public HashSet<Playlist> playlists() {
        return (HashSet<Playlist>) Set.copyOf(playlists);
    }
}
