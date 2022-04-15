package at.fhv.jazzers.backend.domain.model.interpret;

import org.hibernate.annotations.Type;

import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class InterpretId {
    // Properties
    @Type(type = "uuid-char")
    private UUID id;



    // Constructors
    protected InterpretId() {

    }

    public InterpretId(UUID id) {
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
        InterpretId that = (InterpretId) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
