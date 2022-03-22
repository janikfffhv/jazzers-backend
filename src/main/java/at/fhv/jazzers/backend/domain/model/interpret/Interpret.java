package at.fhv.jazzers.backend.domain.model.interpret;

import at.fhv.jazzers.backend.domain.model.product.Product;
import at.fhv.jazzers.backend.domain.model.work.Work;

import javax.persistence.*;
import java.util.List;

@Entity
public class Interpret {
    // Properties
    @Id
    @GeneratedValue
    private long interpretIdInternal;

    @Embedded
    private InterpretId interpretId;

    private String name;



    // Constructors
    protected Interpret() {

    }

    public Interpret(InterpretId interpretId, String name) {
        this.interpretId = interpretId;
        this.name = name;
    }



    // Domain Methods
    // -



    // Getters
    public InterpretId interpretId() {
        return interpretId;
    }

    public String name() {
        return name;
    }
}
