package at.fhv.jazzers.backend.communication.common.impl;

import at.fhv.jazzers.backend.ServiceRegistry;
import at.fhv.jazzers.backend.application.api.ProductService;
import at.fhv.jazzers.backend.application.api.SaleService;
import at.fhv.jazzers.backend.communication.common.api.Session;
import at.fhv.jazzers.backend.domain.model.employee.Employee;
import at.fhv.jazzers.backend.domain.model.employee.Role;
import at.fhv.jazzers.shared.api.RMI_CustomerService;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class SessionImpl implements Session {
    private String username;
    private RMI_CustomerService rmi_customerService;
    private ProductService productService;
    private SaleService saleService;

    public SessionImpl(Employee employee) throws MalformedURLException, NotBoundException, RemoteException {
        username = employee.employeeId().username();

        if (employee.roles().contains(Role.STANDARD)) {
            rmi_customerService = ServiceRegistry.rmi_customerService();
            productService = ServiceRegistry.productService();
            saleService = ServiceRegistry.saleService();
        }

        if (employee.roles().contains(Role.OPERATOR)) {
            // operatorService
        }
    }

    @Override
    public String username() {
        return username;
    }

    @Override
    public RMI_CustomerService rmi_customerService() {
        if (rmi_customerService == null) {
            throw new IllegalArgumentException("Insufficient permissions.");
        }
        return rmi_customerService;
    }

    @Override
    public ProductService productService() {
        if (productService == null) {
            throw new IllegalArgumentException("Insufficient permissions.");
        }
        return productService;
    }

    @Override
    public SaleService saleService() {
        if (saleService == null) {
            throw new IllegalArgumentException("Insufficient permissions.");
        }
        return saleService;
    }
}