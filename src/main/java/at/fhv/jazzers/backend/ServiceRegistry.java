package at.fhv.jazzers.backend;

import at.fhv.jazzers.backend.application.api.ProductService;
import at.fhv.jazzers.backend.application.api.SaleService;
import at.fhv.jazzers.backend.application.impl.ProductServiceImpl;
import at.fhv.jazzers.backend.application.impl.SaleServiceImpl;
import at.fhv.jazzers.backend.communication.RMI_ProductServiceImpl;
import at.fhv.jazzers.backend.communication.RMI_SaleServiceImpl;
import at.fhv.jazzers.backend.domain.repository.CustomerRepository;
import at.fhv.jazzers.backend.domain.repository.ProductRepository;
import at.fhv.jazzers.backend.domain.repository.SaleRepository;
import at.fhv.jazzers.backend.infrastructure.HibernateCustomerRepository;
import at.fhv.jazzers.backend.infrastructure.HibernateProductRepository;
import at.fhv.jazzers.backend.infrastructure.HibernateSaleRepository;
import at.fhv.jazzers.shared.api.RMI_ProductService;
import at.fhv.jazzers.shared.api.RMI_SaleService;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class ServiceRegistry {
    private static EntityManager entityManager;

    private static ProductService productService;
    private static SaleService saleService;

    private static RMI_ProductService rmi_productService;
    private static RMI_SaleService rmi_saleService;

    private static CustomerRepository customerRepository;
    private static ProductRepository productRepository;
    private static SaleRepository saleRepository;

    public static EntityManager entityManager() {
        if (entityManager == null) {
            Map<String, Object> configOverrides = new HashMap<>();
            configOverrides.put("javax.persistence.jdbc.user", System.getenv("JAZZERS_POSTGRES_USER"));
            configOverrides.put("javax.persistence.jdbc.password", System.getenv("JAZZERS_POSTGRES_PASSWORD"));

            entityManager = Persistence.createEntityManagerFactory("JazzersBackend", configOverrides).createEntityManager();
        }
        return entityManager;
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

    public static RMI_ProductService rmi_productService() throws RemoteException {
        if (rmi_productService == null) {
            rmi_productService = new RMI_ProductServiceImpl(productService());
        }
        return rmi_productService;
    }

    public static RMI_SaleService rmi_saleService() throws RemoteException {
        if (rmi_saleService == null) {
            rmi_saleService = new RMI_SaleServiceImpl(saleService());
        }
        return rmi_saleService;
    }

    public static CustomerRepository customerRepository() {
        if (customerRepository == null) {
            customerRepository = new HibernateCustomerRepository();
        }
        return customerRepository;
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
}