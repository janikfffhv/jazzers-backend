package at.fhv.jazzers.backend.domain.model.sale;

import org.hibernate.annotations.Type;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class LineId implements Serializable {
    // Properties
    @Type(type = "uuid-char")
    private UUID id;

    public LineId(UUID id) {
        this.id = id;
    }

    protected LineId() {

    }

    public UUID id() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineId lineId = (LineId) o;
        System.out.println("ID 1: " + id);
        System.out.println("ID 2: " + lineId.id);
        return id.equals(lineId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
