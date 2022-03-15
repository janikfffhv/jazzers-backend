import at.fhv.jazzers.domain.model.customer.Customer;
import at.fhv.jazzers.domain.model.customer.CustomerId;

import javax.persistence.*;

// FOR TESTING PURPOSES ONLY (WILL BE DELETED LATER)
// PRESS EITHER GREEN TRIANGLE ON THE LEFT TO EXECUTE
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        // HERE WE CAN CREATE THINGS
        // Customer c = new Customer(...);

        // AND THEN PERSIST THEM TO THE DATABASE
        // em.persist(c);

        em.getTransaction().commit();
    }
}
