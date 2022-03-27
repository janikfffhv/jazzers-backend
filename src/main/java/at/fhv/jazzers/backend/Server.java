package at.fhv.jazzers.backend;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            // Create Registry
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);

            // Bind Services
            Naming.rebind("rmi://localhost/productService", ServiceRegistry.rmi_productService());
            Naming.rebind("rmi://localhost/saleService", ServiceRegistry.rmi_saleService());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}