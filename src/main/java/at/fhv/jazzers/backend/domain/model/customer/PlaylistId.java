package at.fhv.jazzers.backend.domain.model.customer;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class PlaylistId implements Serializable {
    // Properties
    @Type(type = "uuid-char")
    private UUID id;



    // Constructors
    protected PlaylistId() {

    }

    public PlaylistId(UUID id) {
        this.id = id;
    }



    // Getters
    public UUID id() {
        return id;
    }



    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaylistId that = (PlaylistId) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
