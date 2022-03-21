package at.fhv.jazzers.backend;

import at.fhv.jazzers.backend.domain.model.customer.Address;
import at.fhv.jazzers.backend.domain.model.customer.Customer;
import at.fhv.jazzers.backend.domain.model.customer.CustomerId;
import at.fhv.jazzers.backend.domain.model.customer.Playlist;
import at.fhv.jazzers.backend.domain.model.employee.Employee;
import at.fhv.jazzers.backend.domain.model.product.*;
import at.fhv.jazzers.backend.domain.model.sale.Line;
import at.fhv.jazzers.backend.domain.model.sale.Sale;
import at.fhv.jazzers.backend.domain.model.work.Genre;
import at.fhv.jazzers.backend.domain.model.work.Interpret;
import at.fhv.jazzers.backend.domain.model.work.Work;
import at.fhv.jazzers.backend.domain.model.work.WorkId;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataGenerator {
    private static final List<Customer> customers = new ArrayList<>();
    private static final List<Playlist> playlists = new ArrayList<>();

    private static final List<Employee> employees = new ArrayList<>();

    private static final List<Label> labels = new ArrayList<>();
    private static final List<Product> products = new ArrayList<>();
    private static final List<Supplier> suppliers = new ArrayList<>();

    private static final List<Line> lines = new ArrayList<>();
    private static final List<Sale> sales = new ArrayList<>();

    private static final List<Interpret> interprets = new ArrayList<>();
    private static final List<Work> works = new ArrayList<>();

    public static void main(String[] args) {
        generateData();
        persistData();
    }

    private static void generateData() {
        Address address1 = new Address("Musterstraße", "1", "0000", "Muster");

        Customer customer1 = new Customer(new CustomerId(UUID.randomUUID()), "maxmustermann@gmail.com", "maxie55", "ichliebemaxine", "Max", "Mustermann", LocalDate.of(1999, 10, 11), "AT00 0000 0000 0000", address1, List.of(), List.of());
        customers.add(customer1);

        Interpret interpret1 = new Interpret("Interpret 1");
        Interpret interpret2 = new Interpret("Interpret 2");
        Interpret interpret3 = new Interpret("Interpret 3");
        Interpret interpret4 = new Interpret("Interpret 4");
        interprets.add(interpret1);
        interprets.add(interpret2);
        interprets.add(interpret3);
        interprets.add(interpret4);

        Supplier supplier1 = new Supplier("Supplier 1");
        Supplier supplier2 = new Supplier("Supplier 2");
        Supplier supplier3 = new Supplier("Supplier 3");
        Supplier supplier4 = new Supplier("Supplier 4");
        suppliers.add(supplier1);
        suppliers.add(supplier2);
        suppliers.add(supplier3);
        suppliers.add(supplier4);

        Label label1 = new Label("Label 1");
        Label label2 = new Label("Label 2");
        Label label3 = new Label("Label 3");
        Label label4 = new Label("Label 4");
        labels.add(label1);
        labels.add(label2);
        labels.add(label3);
        labels.add(label4);

        Work work1 = new Work(new WorkId(UUID.randomUUID()), "Work 1", 123, 2020, interpret1, Genre.JAZZ);
        Work work2 = new Work(new WorkId(UUID.randomUUID()), "Work 2", 189, 2020, interpret2, Genre.ROCK);
        Work work3 = new Work(new WorkId(UUID.randomUUID()), "Work 3", 154, 2020, interpret3, Genre.METAL);
        Work work4 = new Work(new WorkId(UUID.randomUUID()), "Work 4", 154, 2020, interpret4, Genre.NIGHTCORE);
        works.add(work1);
        works.add(work2);
        works.add(work3);
        works.add(work4);

        Product product1 = new Product(new ProductId(UUID.randomUUID()), "Product 1", 2020, 9.99d, 10, Medium.CD, label1, List.of(supplier1, supplier2), List.of(work1, work2, work3));
        Product product2 = new Product(new ProductId(UUID.randomUUID()), "Product 2", 2020, 24.99d, 20, Medium.VINYL, label2, List.of(supplier3, supplier4), List.of(work4));
        products.add(product1);
        products.add(product2);
    }

    private static void persistData() {
        EntityManager em = ServiceRegistry.entityManager();
        em.getTransaction().begin();

        // ToDo: Maybe just persist Aggregate Roots?
        customers.forEach(em::persist);
        playlists.forEach(em::persist);

        employees.forEach(em::persist);

        labels.forEach(em::persist);
        products.forEach(em::persist);
        suppliers.forEach(em::persist);

        lines.forEach(em::persist);
        sales.forEach(em::persist);

        interprets.forEach(em::persist);
        works.forEach(em::persist);

        em.getTransaction().commit();
    }
}
