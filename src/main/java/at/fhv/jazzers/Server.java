package at.fhv.jazzers;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);

            Naming.rebind("rmi://localhost/productService", ServiceRegistry.productServiceRMI());

            // In Frontend:
            // ProductServiceRMI productService = (ProductServiceRMI) Naming.lookup("rmi://localhost/productService");
            // productService.searchByTitleOrInterpret(searchString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
