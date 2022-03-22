package at.fhv.jazzers.backend;

import at.fhv.jazzers.backend.application.impl.ProductServiceImpl;
import at.fhv.jazzers.backend.domain.repository.ProductRepository;
import at.fhv.jazzers.backend.infrastructure.HibernateProductRepository;
import at.fhv.jazzers.shared.api.ProductService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.rmi.RemoteException;

public class ServiceRegistry {
    private static EntityManagerFactory entityManagerFactory;
    private static ProductService productService;
    private static ProductRepository productRepository;

    public static EntityManager entityManager() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("Test");
        }
        return entityManagerFactory.createEntityManager();
    }

    public static ProductService productService() throws RemoteException {
        if (productService == null) {
            productService = new ProductServiceImpl();
        }
        return productService;
    }

    public static ProductRepository productRepository() {
        if (productRepository == null) {
            productRepository = new HibernateProductRepository();
        }
        return productRepository;
    }
}