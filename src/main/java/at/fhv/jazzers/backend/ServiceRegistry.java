package at.fhv.jazzers.backend;

import at.fhv.jazzers.backend.application.api.ProductService;
import at.fhv.jazzers.backend.application.impl.ProductServiceImpl;
import at.fhv.jazzers.backend.communication.ProductServiceImplRMI;
import at.fhv.jazzers.backend.communication.ProductServiceRMI;
import at.fhv.jazzers.backend.domain.repository.ProductRepository;
import at.fhv.jazzers.backend.infrastructure.HibernateProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.rmi.RemoteException;

public class ServiceRegistry {
    private static EntityManagerFactory entityManagerFactory;
    private static ProductServiceImplRMI productServiceRMI;
    private static ProductService productService;
    private static ProductRepository productRepository;

    public static EntityManager entityManager() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("Test");
        }
        return entityManagerFactory.createEntityManager();
    }

    public static ProductService productService() {
        if (productService == null) {
            productService = new ProductServiceImpl();
        }
        return productService;
    }

    public static ProductServiceRMI productServiceRMI() throws RemoteException {
        if (productServiceRMI == null) {
            productServiceRMI = new ProductServiceImplRMI();
        }
        return productServiceRMI;
    }

    public static ProductRepository productRepository() {
        if (productRepository == null) {
            productRepository = new HibernateProductRepository();
        }
        return productRepository;
    }
}