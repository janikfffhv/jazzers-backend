package at.fhv.jazzers.backend;

import at.fhv.jazzers.shared.api.RMI_CustomerService;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.rmi.Naming;

public class ServiceRegistry {
    private static EntityManager entityManager;
    private static RMI_CustomerService rmi_customerService;
    private static ActiveMQConnectionFactory activeMQConnectionFactory;

    public static EntityManager entityManager() {
        if (entityManager == null) {
            entityManager = Persistence.createEntityManagerFactory("JazzersBackend").createEntityManager();
        }
        return entityManager;
    }

    public static RMI_CustomerService rmi_customerService() {
        if (rmi_customerService == null) {
            try {
                rmi_customerService = (RMI_CustomerService) Naming.lookup("rmi://" + System.getenv("CUSTOMER_RMI_HOST") + ":1100/customerService");
            } catch (Exception e) {
                e.printStackTrace();
                throw new IllegalStateException("Unable to connect to customer service.");
            }
        }
        return rmi_customerService;
    }

    public static ActiveMQConnectionFactory activeMQConnectionFactory() {
        if (activeMQConnectionFactory == null) {
            activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://jazzersactivemq:61616?jms.prefetchPolicy.all=0");
        }
        return activeMQConnectionFactory;
    }
}