package at.fhv.jazzers.backend.domain.model.work;

public enum Genre {
    JAZZ("Jazz"),
    ROCK("Rock"),
    METAL("Metal"),
    NIGHTCORE("Nightcore");

    private final String name;

    Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
