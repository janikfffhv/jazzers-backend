package at.fhv.jazzers.backend;

import at.fhv.jazzers.backend.communication.RMI_Server;
import at.fhv.jazzers.shared.api.RMI_CustomerService;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) {
        try {
            // Create initial entries (they are cleared because of "create-drop" in persistence.xml)
            DataGenerator.main(args);

            RMI_Server.start();

            // TEST FOR:     LOCAL-Docker  <--RMI-->  LOCAL-Docker
            // RMI_CustomerService customerService = (RMI_CustomerService) Naming.lookup("rmi://localhost:1100/customerService");
            // customerService.search("max").forEach(customer -> System.out.println("Customer: " + customer.getFirstName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}