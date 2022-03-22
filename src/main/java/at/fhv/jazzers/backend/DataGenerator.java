package at.fhv.jazzers.backend;

import at.fhv.jazzers.backend.domain.model.customer.Address;
import at.fhv.jazzers.backend.domain.model.customer.Customer;
import at.fhv.jazzers.backend.domain.model.customer.CustomerId;
import at.fhv.jazzers.backend.domain.model.customer.Playlist;
import at.fhv.jazzers.backend.domain.model.employee.Employee;
import at.fhv.jazzers.backend.domain.model.interpret.Interpret;
import at.fhv.jazzers.backend.domain.model.interpret.InterpretId;
import at.fhv.jazzers.backend.domain.model.product.*;
import at.fhv.jazzers.backend.domain.model.sale.Line;
import at.fhv.jazzers.backend.domain.model.sale.Sale;
import at.fhv.jazzers.backend.domain.model.work.Genre;
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
    private static final List<Interpret> interprets = new ArrayList<>();
    private static final List<Label> labels = new ArrayList<>();
    private static final List<Product> products = new ArrayList<>();
    private static final List<Supplier> suppliers = new ArrayList<>();
    private static final List<Line> lines = new ArrayList<>();
    private static final List<Sale> sales = new ArrayList<>();
    private static final List<Work> works = new ArrayList<>();

    public static void main(String[] args) {
        generateData();
        persistData();
    }

    private static void generateData() {
        Address address1 = new Address("Musterstraße", "1", "0000", "Muster");

        Customer customer1 = new Customer(new CustomerId(UUID.randomUUID()), "maxmustermann@gmail.com", "maxie55", "ichliebemaxine", "Max", "Mustermann", LocalDate.of(1999, 10, 11), "AT00 0000 0000 0000", address1, List.of(), List.of());
        customers.add(customer1);

        Supplier supplier1 = new Supplier("Supplier 1");
        Supplier supplier2 = new Supplier("Supplier 2");
        Supplier supplier3 = new Supplier("Supplier 3");
        Supplier supplier4 = new Supplier("Supplier 4");
        suppliers.addAll(List.of(supplier1, supplier2, supplier3, supplier4));

        Label columbiaRecords = new Label("Columbia Records");
        Label warnerBrothersRecords = new Label("Warner Brothers Records");
        Label risingRecords = new Label("Rising Records");
        labels.addAll(List.of(columbiaRecords, warnerBrothersRecords, risingRecords));

        Interpret milesDavis = new Interpret(new InterpretId(UUID.randomUUID()), "Miles Davis");
        Interpret linkinPark = new Interpret(new InterpretId(UUID.randomUUID()), "Linkin Park");
        Interpret bleedFromWithin = new Interpret(new InterpretId(UUID.randomUUID()), "Bleed From Within");
        interprets.addAll(List.of(milesDavis, linkinPark, bleedFromWithin));

        Work kindOfBlue1 = new Work(new WorkId(UUID.randomUUID()), "So What", 545, 1959, milesDavis, Genre.JAZZ);
        Work kindOfBlue2 = new Work(new WorkId(UUID.randomUUID()), "Freddie Freeloader", 975, 1959, milesDavis, Genre.JAZZ);
        Work kindOfBlue3 = new Work(new WorkId(UUID.randomUUID()), "Blue in Green", 328, 1959, milesDavis, Genre.JAZZ);
        Work kindOfBlue4 = new Work(new WorkId(UUID.randomUUID()), "All Blues", 693, 1959, milesDavis, Genre.JAZZ);
        Work kindOfBlue5 = new Work(new WorkId(UUID.randomUUID()), "Flamenco Sketches", 565, 1959, milesDavis, Genre.JAZZ);
        List<Work> kindOfBlueWorks = List.of(kindOfBlue1, kindOfBlue2, kindOfBlue3, kindOfBlue4, kindOfBlue5);
        works.addAll(kindOfBlueWorks);

        Work meteora1 = new Work(new WorkId(UUID.randomUUID()), "Don't Stay", 187,2003, linkinPark, Genre.ROCK);
        Work meteora2 = new Work(new WorkId(UUID.randomUUID()), "Somewhere I Belong", 197, 2003, linkinPark, Genre.ROCK);
        Work meteora3 = new Work(new WorkId(UUID.randomUUID()), "Lying from You", 175, 2003, linkinPark, Genre.ROCK);
        Work meteora4 = new Work(new WorkId(UUID.randomUUID()), "Hit the Floor", 164, 2003, linkinPark, Genre.ROCK);
        Work meteora5 = new Work(new WorkId(UUID.randomUUID()), "Easier to Run", 204, 2003, linkinPark, Genre.ROCK);
        List<Work> meteoraWorks = List.of(meteora1, meteora2, meteora3, meteora4, meteora5);
        works.addAll(meteoraWorks);

        Work humanity1 = new Work(new WorkId(UUID.randomUUID()), "The Awakening", 125, 2009, bleedFromWithin, Genre.METAL);
        Work humanity2 = new Work(new WorkId(UUID.randomUUID()), "Damnation", 205, 2009, bleedFromWithin, Genre.METAL);
        Work humanity3 = new Work(new WorkId(UUID.randomUUID()), "Messiah", 213, 2009, bleedFromWithin, Genre.METAL);
        Work humanity4 = new Work(new WorkId(UUID.randomUUID()), "The Fall of Man", 204, 2009, bleedFromWithin, Genre.METAL);
        Work humanity5 = new Work(new WorkId(UUID.randomUUID()), "Monster", 173, 2009, bleedFromWithin, Genre.METAL);
        List<Work> humanityWorks = List.of(humanity1, humanity2, humanity3, humanity4, humanity5);
        works.addAll(humanityWorks);

        Product kindOfBlueVinyl = new Product(new ProductId(UUID.randomUUID()), "Kind Of Blue", milesDavis, 1959, 22.99d, 20, Medium.VINYL, columbiaRecords, List.of(supplier1, supplier2, supplier3, supplier4), kindOfBlueWorks);
        Product meteoraVinyl = new Product(new ProductId(UUID.randomUUID()), "Meteora", linkinPark, 2003, 14.99d, 12, Medium.VINYL, warnerBrothersRecords, List.of(supplier1, supplier2), meteoraWorks);
        Product meteoraCD = new Product(new ProductId(UUID.randomUUID()), "Meteora", linkinPark, 2005, 9.99d, 33, Medium.CD, warnerBrothersRecords, List.of(supplier2, supplier3), meteoraWorks);
        Product humanityCD = new Product(new ProductId(UUID.randomUUID()), "Humanity", bleedFromWithin, 2009, 19.99d, 2, Medium.CD, risingRecords, List.of(supplier3, supplier4), humanityWorks);
        Product humanityMP3 = new Product(new ProductId(UUID.randomUUID()), "Humanity", bleedFromWithin, 2009, 19.99d, 0, Medium.MP3, risingRecords, List.of(), humanityWorks);
        products.addAll(List.of(kindOfBlueVinyl, meteoraVinyl, meteoraCD, humanityCD, humanityMP3));
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
