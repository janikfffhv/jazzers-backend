package at.fhv.jazzers.backend.communication;

import at.fhv.jazzers.backend.ServiceRegistry;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMI_Server {
    public static void start() throws RemoteException, MalformedURLException {
        // Create Registry
        LocateRegistry.createRegistry(Integer.parseInt(System.getenv("RMI_APPLICATION_PORT")));

        // Remote Objects try to listen to docker ip, but they need to listen to localhost (local .env) or application-server-ip (azure .env)
        System.setProperty("java.rmi.server.hostname", System.getenv("RMI_APPLICATION_HOST"));

        // Bind Services
        Naming.rebind("rmi://localhost/productService", ServiceRegistry.rmi_productService());
        System.out.println("Product Service bound on PORT " + System.getenv("RMI_APPLICATION_PORT"));

        Naming.rebind("rmi://localhost/saleService", ServiceRegistry.rmi_saleService());
        System.out.println("Sale Service bound on PORT " + System.getenv("RMI_APPLICATION_PORT"));
    }
}
