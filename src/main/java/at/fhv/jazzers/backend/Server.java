package at.fhv.jazzers.backend;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) {
        try {
            // Create initial entries (they are cleared because of "create-drop" in persistence.xml)
            DataGenerator.main(args);

            // Create Registry
            LocateRegistry.createRegistry(Integer.parseInt(System.getenv("RMI_PORT")));

            // Remote Objects try to listen to docker ip, but they need to listen to localhost (local .env) or application-server-ip (azure .env)
            System.setProperty("java.rmi.server.hostname", System.getenv("RMI_HOST"));

            // Bind Services
            Naming.rebind("rmi://localhost/productService", ServiceRegistry.rmi_productService());
            System.out.println("Product Service bound on PORT " + System.getenv("RMI_PORT"));

            Naming.rebind("rmi://localhost/saleService", ServiceRegistry.rmi_saleService());
            System.out.println("Sale Service bound on PORT " + System.getenv("RMI_PORT"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}