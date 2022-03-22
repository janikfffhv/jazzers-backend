package at.fhv.jazzers.backend.domain.model.employee;

public enum Role {
    STANDARD("Standard"),
    OPERATOR("Operator");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
