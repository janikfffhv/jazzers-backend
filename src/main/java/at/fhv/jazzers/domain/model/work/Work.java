package at.fhv.jazzers.domain.model.work;

import javax.persistence.*;

@Entity
public class Work {
    // Properties
    @Id
    @GeneratedValue
    private Long workIdInternal;

    @Embedded
    private WorkId workId;

    private String title;

    private int durationInSeconds;

    private int recordingYear;

    @ManyToOne
    private Interpret interpret;

    @Enumerated(EnumType.STRING)
    private Genre genre;



    // Constructors
    protected Work() {

    }

    public Work(WorkId workId, String title, int durationInSeconds, int recordingYear, Interpret interpret, Genre genre) {
        this.workId = workId;
        this.title = title;
        this.durationInSeconds = durationInSeconds;
        this.recordingYear = recordingYear;
        this.interpret = interpret;
        this.genre = genre;
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

    public Interpret interpret() {
        return interpret;
    }

    public Genre genre() {
        return genre;
    }
}
