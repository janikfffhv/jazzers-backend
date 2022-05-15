package at.fhv.jazzers.backend.communication.ejb;

import at.fhv.jazzers.backend.ServiceRegistry;
import at.fhv.jazzers.backend.domain.model.employee.Employee;
import at.fhv.jazzers.backend.domain.model.employee.EmployeeId;
import at.fhv.jazzers.backend.domain.model.employee.Role;
import at.fhv.jazzers.backend.domain.repository.EmployeeRepository;
import at.fhv.jazzers.backend.infrastructure.CredentialService;
import at.fhv.jazzers.shared.api.*;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.Optional;

@Stateful
public class RemoteSessionImpl implements RemoteSession {
    private Employee employee;

    private RMI_CustomerService rmi_customerService;

    @EJB
    private RemoteMessageConsumerService messageConsumerService;

    @EJB
    private RemoteMessagePublisherService messagePublisherService;

    @EJB
    private RemoteProductService productService;

    @EJB
    private RemoteSaleService saleService;

    @EJB
    private EmployeeRepository employeeRepository;

    @EJB
    private CredentialService credentialService;

    public RemoteSessionImpl() {

    }

    @Override
    public boolean authenticate(String username, String password) {
        if (!credentialService.findEmployeeInLdap(username, password)) {
            throw new IllegalArgumentException("User does not exist in LDAP.");
        }

        Optional<Employee> employee = employeeRepository.byId(new EmployeeId(username));

        if (employee.isEmpty()) {
            throw new IllegalArgumentException("User does not exist in POSTGRES.");
        }

        this.employee = employee.get();
        this.rmi_customerService = ServiceRegistry.rmi_customerService();
        return true;
    }

    @Override
    public String username() {
        return employee.employeeId().username();
    }

    @Override
    public RMI_CustomerService rmi_customerService() {
        if (employee != null && employee.roles().contains(Role.STANDARD)) {
            return rmi_customerService;
        }

        throw new IllegalArgumentException("Insufficient permissions.");
    }

    @Override
    public RemoteMessageConsumerService messageConsumerService() {
        if (employee != null && employee.roles().contains(Role.STANDARD)) {
            return messageConsumerService;
        }

        throw new IllegalArgumentException("Insufficient permissions.");
    }

    @Override
    public RemoteMessagePublisherService messagePublisherService() {
        if (employee != null && employee.roles().contains(Role.OPERATOR)) {
            return messagePublisherService;
        }

        throw new IllegalArgumentException("Insufficient permissions.");
    }

    @Override
    public RemoteProductService productService() {
        if (employee != null && employee.roles().contains(Role.STANDARD)) {
            return productService;
        }

        throw new IllegalArgumentException("Insufficient permissions.");
    }

    @Override
    public RemoteSaleService saleService() {
        if (employee != null && employee.roles().contains(Role.STANDARD)) {
            return saleService;
        }

        throw new IllegalArgumentException("Insufficient permissions.");
    }
}