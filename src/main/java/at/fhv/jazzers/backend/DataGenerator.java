package at.fhv.jazzers.backend;

import at.fhv.jazzers.backend.domain.model.customer.Customer;
import at.fhv.jazzers.backend.domain.model.customer.CustomerId;
import at.fhv.jazzers.backend.domain.model.customer.Playlist;
import at.fhv.jazzers.backend.domain.model.employee.Employee;
import at.fhv.jazzers.backend.domain.model.employee.EmployeeId;
import at.fhv.jazzers.backend.domain.model.employee.Role;
import at.fhv.jazzers.backend.domain.model.interpret.Interpret;
import at.fhv.jazzers.backend.domain.model.interpret.InterpretId;
import at.fhv.jazzers.backend.domain.model.product.*;
import at.fhv.jazzers.backend.domain.model.sale.Line;
import at.fhv.jazzers.backend.domain.model.sale.Sale;
import at.fhv.jazzers.backend.domain.model.work.Genre;
import at.fhv.jazzers.backend.domain.model.work.Work;
import at.fhv.jazzers.backend.domain.model.work.WorkId;
import at.fhv.jazzers.backend.infrastructure.JMSMessageConsumer;
import at.fhv.jazzers.backend.infrastructure.JMSMessageProducer;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Startup
@Singleton
public class DataGenerator {
    @EJB
    private JMSMessageConsumer jmsMessageConsumer;
    @EJB
    private JMSMessageProducer jmsMessageProducer;

    private final List<Customer> customers = new ArrayList<>();
    private final List<Playlist> playlists = new ArrayList<>();
    private final List<Employee> employees = new ArrayList<>();
    private final List<Interpret> interprets = new ArrayList<>();
    private final List<Label> labels = new ArrayList<>();
    private final List<Product> products = new ArrayList<>();
    private final List<Supplier> suppliers = new ArrayList<>();
    private final List<Line> lines = new ArrayList<>();
    private final List<Sale> sales = new ArrayList<>();
    private final List<Work> works = new ArrayList<>();

    @PostConstruct
    public void init() {
        generateData();
        persistData();
        createDurableSubscribers();
        createInitialTopicMessages();
    }

    private void generateData() {
        Customer customer1 = new Customer("aar9086");
        Customer customer2 = new Customer("ace9467");
        Customer customer3 = new Customer("bte3268");
        Customer customer4 = new Customer("cpe2877");
        Customer customer5 = new Customer("eha7244");
        Customer customer6 = new Customer("jfu5402");
        Customer customer7 = new Customer("ppl8596");
        Customer customer8 = new Customer("tf-test");
        customers.addAll(List.of(customer1, customer2, customer3, customer4, customer5, customer6, customer7, customer8));

        List<Role> allRoles = Arrays.stream(Role.values()).collect(Collectors.toList());
        List<Genre> allTopics = Arrays.stream(Genre.values()).collect(Collectors.toList());

        Employee employee1 = new Employee(new EmployeeId("aar9086"), allRoles, allTopics);
        Employee employee2 = new Employee(new EmployeeId("ace9467"), allRoles, allTopics);
        Employee employee3 = new Employee(new EmployeeId("bte3268"), allRoles, allTopics);
        Employee employee4 = new Employee(new EmployeeId("cpe2877"), allRoles, List.of(Genre.METAL));
        Employee employee5 = new Employee(new EmployeeId("eha7244"), allRoles, allTopics);
        Employee employee6 = new Employee(new EmployeeId("jfu5402"), allRoles, allTopics);
        Employee employee7 = new Employee(new EmployeeId("ppl8596"), allRoles, allTopics);
        Employee employee8 = new Employee(new EmployeeId("tf-test"), allRoles, allTopics);
        Employee employee9 = new Employee(new EmployeeId("roles-standard"), List.of(Role.STANDARD), List.of());
        Employee employee10 = new Employee(new EmployeeId("roles-operator"), List.of(Role.OPERATOR), List.of());
        Employee employee11 = new Employee(new EmployeeId("roles-standard-and-operator"), allRoles, List.of());
        employees.addAll(List.of(employee1, employee2, employee3, employee4, employee5, employee6, employee7, employee8, employee9, employee10, employee11));

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

    private void persistData() {
        EntityManager em = ServiceRegistry.entityManager();
        em.getTransaction().begin();

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

    private void createDurableSubscribers() {
        jmsMessageConsumer.createDurableSubscribersFor(employees);
    }

    private void createInitialTopicMessages() {
        jmsMessageProducer.publish("Jazz", "Jazz-Title 1", "Some amazing message about Jazz!");

        jmsMessageProducer.publish("Rock", "Rock-Title 1", "Some amazing message about Rock!");
        jmsMessageProducer.publish("Rock", "Rock-Title 2", "Some amazing message about Rock!!");
        jmsMessageProducer.publish("Rock", "Rock-Title 3", "Some amazing message about Rock!!!");
        jmsMessageProducer.publish("Rock", "Rock-Title 4", "Some amazing message about Rock!!!!");

        jmsMessageProducer.publish("Metal", "Metal-Title 1", "Some amazing message about Metal!");
        jmsMessageProducer.publish("Metal", "Metal-Title 2", "Some amazing message about Metal!!");
    }
}
