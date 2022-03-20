package at.fhv.jazzers.application.dto;

import at.fhv.jazzers.domain.model.product.Medium;
import at.fhv.jazzers.domain.model.product.Product;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProductOverviewDTO {
    private final UUID productId;
    private final String interpret;
    private final String title;
    private final Medium medium;
    private final double price;

    private ProductOverviewDTO(UUID productId, String interpret, String title, Medium medium, double price) {
        this.productId = productId;
        this.interpret = interpret;
        this.title = title;
        this.medium = medium;
        this.price = price;
    }

    public static ProductOverviewDTO create(Product product) {
        return new ProductOverviewDTO(product.productId().id(), product.interpret().name(), product.title(), product.medium(), product.price());
    }

    public static List<ProductOverviewDTO> createAsList(List<Product> products) {
        return products.stream().map(ProductOverviewDTO::create).collect(Collectors.toList());
    }

    public UUID productId() {
        return productId;
    }

    public String interpret() {
        return interpret;
    }

    public String title() {
        return title;
    }

    public Medium medium() {
        return medium;
    }

    public double price() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductOverviewDTO that = (ProductOverviewDTO) o;
        return Double.compare(that.price, price) == 0 && productId.equals(that.productId) && interpret.equals(that.interpret) && title.equals(that.title) && medium == that.medium;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, interpret, title, medium, price);
    }
}
