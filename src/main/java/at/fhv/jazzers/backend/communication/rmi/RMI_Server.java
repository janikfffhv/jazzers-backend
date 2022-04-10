package at.fhv.jazzers.backend.communication.rmi;

import at.fhv.jazzers.backend.ServiceRegistry;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMI_Server {
    public static void start() throws RemoteException, MalformedURLException {
        // Remote Objects try to listen to docker ip, but they need to listen to localhost (local .env) or application-server-ip (azure .env)
        System.setProperty("java.rmi.server.hostname", System.getenv("JAZZERS_RMI_HOST"));
        System.setProperty("com.sun.management.jmxremote.port", System.getenv("JAZZERS_RMI_PORT"));
        System.setProperty("com.sun.management.jmxremote.rmi.port", System.getenv("JAZZERS_RMI_PORT"));

        // Create Registry
        LocateRegistry.createRegistry(Integer.parseInt(System.getenv("JAZZERS_RMI_PORT")));

        // Bind Services
        Naming.rebind("rmi://localhost/sessionFactory", ServiceRegistry.rmi_sessionFactory());
        System.out.println("Session Factory bound on PORT " + System.getenv("JAZZERS_RMI_PORT"));
    }
}
