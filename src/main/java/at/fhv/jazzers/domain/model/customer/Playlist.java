package at.fhv.jazzers.domain.model.customer;

import at.fhv.jazzers.domain.model.work.Work;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Playlist {
    // Properties
    @Id
    @GeneratedValue
    private Long playlistIdInternal;

    @Embedded
    private PlaylistId playlistId;

    private String name;

    @ManyToMany()
    private Set<Work> works;



    // Constructors
    protected Playlist() {

    }

    public Playlist(PlaylistId playlistId, String name, Customer customer, Set<Work> works) {
        this.playlistId = playlistId;
        this.name = name;
        this.works = works;
    }



    // Domain Methods
    // -



    // Getters
    public PlaylistId playlistId() {
        return playlistId;
    }

    public String name() {
        return name;
    }

    public HashSet<Work> works() {
        return (HashSet<Work>) Set.copyOf(works);
    }
}
