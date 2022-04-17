package at.fhv.jazzers.backend;

import at.fhv.jazzers.backend.application.api.MessageConsumerService;
import at.fhv.jazzers.backend.application.api.MessagePublisherService;
import at.fhv.jazzers.backend.application.api.ProductService;
import at.fhv.jazzers.backend.application.api.SaleService;
import at.fhv.jazzers.backend.application.impl.MessageConsumerServiceImpl;
import at.fhv.jazzers.backend.application.impl.MessagePublisherServiceImpl;
import at.fhv.jazzers.backend.communication.common.api.SessionFactory;
import at.fhv.jazzers.backend.application.impl.ProductServiceImpl;
import at.fhv.jazzers.backend.application.impl.SaleServiceImpl;
import at.fhv.jazzers.backend.communication.common.impl.SessionFactoryImpl;
import at.fhv.jazzers.backend.communication.rmi.RMI_SessionFactoryImpl;
import at.fhv.jazzers.backend.domain.repository.CustomerRepository;
import at.fhv.jazzers.backend.domain.repository.EmployeeRepository;
import at.fhv.jazzers.backend.domain.repository.ProductRepository;
import at.fhv.jazzers.backend.domain.repository.SaleRepository;
import at.fhv.jazzers.backend.infrastructure.*;
import at.fhv.jazzers.shared.api.RMI_CustomerService;
import at.fhv.jazzers.shared.api.RMI_SessionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class ServiceRegistry {
    // General
    private static EntityManager entityManager;

    // Application
    private static MessageConsumerService messageConsumerService;
    private static MessagePublisherService messagePublisherService;
    private static ProductService productService;
    private static SaleService saleService;

    // Communication
    private static SessionFactory sessionFactory;
    private static RMI_SessionFactory rmi_sessionFactory;
    private static RMI_CustomerService rmi_customerService;

    // Domain
    private static CustomerRepository customerRepository;
    private static EmployeeRepository employeeRepository;
    private static ProductRepository productRepository;
    private static SaleRepository saleRepository;

    // Infrastructure
    private static JMSMessageProducer jmsMessageProducer;
    private static JMSMessageConsumer jmsMessageConsumer;
    private static ActiveMQConnectionFactory activeMQConnectionFactory;

    public static EntityManager entityManager() {
        if (entityManager == null) {
            Map<String, Object> configOverrides = new HashMap<>();
            configOverrides.put("javax.persistence.jdbc.user", System.getenv("JAZZERS_POSTGRES_USER"));
            configOverrides.put("javax.persistence.jdbc.password", System.getenv("JAZZERS_POSTGRES_PASSWORD"));

            entityManager = Persistence.createEntityManagerFactory("JazzersBackend", configOverrides).createEntityManager();
        }
        return entityManager;
    }

    public static MessageConsumerService messageConsumerService() {
        if (messageConsumerService == null) {
            messageConsumerService = new MessageConsumerServiceImpl();
        }
        return messageConsumerService;
    }

    public static MessagePublisherService messagePublisherService() {
        if (messagePublisherService == null) {
            messagePublisherService = new MessagePublisherServiceImpl();
        }
        return messagePublisherService;
    }

    public static ProductService productService() {
        if (productService == null) {
            productService = new ProductServiceImpl(productRepository());
        }
        return productService;
    }

    public static SaleService saleService() {
        if (saleService == null) {
            saleService = new SaleServiceImpl(entityManager(), customerRepository(), productRepository(), saleRepository());
        }
        return saleService;
    }

    public static SessionFactory sessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new SessionFactoryImpl();
        }
        return sessionFactory;
    }

    public static RMI_SessionFactory rmi_sessionFactory() throws RemoteException {
        if (rmi_sessionFactory == null) {
            rmi_sessionFactory = new RMI_SessionFactoryImpl(sessionFactory());
        }
        return rmi_sessionFactory;
    }

    public static RMI_CustomerService rmi_customerService() throws MalformedURLException, NotBoundException, RemoteException {
        if (rmi_customerService == null) {
            rmi_customerService = (RMI_CustomerService) Naming.lookup("rmi://" + System.getenv("CUSTOMER_RMI_HOST") + ":" + System.getenv("CUSTOMER_RMI_PORT") + "/customerService");
        }
        return rmi_customerService;
    }

    public static CustomerRepository customerRepository() {
        if (customerRepository == null) {
            customerRepository = new HibernateCustomerRepository();
        }
        return customerRepository;
    }

    public static EmployeeRepository employeeRepository() {
        if (employeeRepository == null) {
            employeeRepository = new HibernateEmployeeRepository();
        }
        return employeeRepository;
    }

    public static ProductRepository productRepository() {
        if (productRepository == null) {
            productRepository = new HibernateProductRepository();
        }
        return productRepository;
    }

    public static SaleRepository saleRepository() {
        if (saleRepository == null) {
            saleRepository = new HibernateSaleRepository();
        }
        return saleRepository;
    }

    public static JMSMessageProducer jmsMessageProducer() {
        if (jmsMessageProducer == null) {
            jmsMessageProducer = new JMSMessageProducer();
        }
        return jmsMessageProducer;
    }

    public static JMSMessageConsumer jmsMessageConsumer() {
        if (jmsMessageConsumer == null) {
            jmsMessageConsumer = new JMSMessageConsumer();
        }
        return jmsMessageConsumer;
    }

    public static ActiveMQConnectionFactory activeMQConnectionFactory() {
        if (activeMQConnectionFactory == null) {
            activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://jazzersactivemq:61616");
        }
        return activeMQConnectionFactory;
    }
}