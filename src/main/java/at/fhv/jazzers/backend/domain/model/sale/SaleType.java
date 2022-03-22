package at.fhv.jazzers.backend.domain.model.sale;

public enum SaleType {
    PURCHASE("Purchase"),
    REFUND("Refund");

    private final String name;

    SaleType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
