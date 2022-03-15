package at.fhv.jazzers.domain.model.work;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class WorkId {
    // Properties
    private UUID id;



    // Constructors
    protected WorkId() {

    }

    public WorkId(UUID id) {
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
        WorkId workId = (WorkId) o;
        return id.equals(workId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
