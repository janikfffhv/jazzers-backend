package at.fhv.jazzers.domain.model.customer;

import at.fhv.jazzers.domain.model.work.Work;

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

    @ManyToMany()
    private List<Work> works;



    // Constructors
    protected Playlist() {

    }

    public Playlist(PlaylistId playlistId, String name, Customer customer, List<Work> works) {
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

    public List<Work> works() {
        return List.copyOf(works);
    }
}
