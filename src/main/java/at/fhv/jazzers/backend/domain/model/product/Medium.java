package at.fhv.jazzers.backend.domain.model.product;

public enum Medium {
    CD("CD"),
    MP3("MP3"),
    VINYL("Vinyl");

    private final String name;

    Medium(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}