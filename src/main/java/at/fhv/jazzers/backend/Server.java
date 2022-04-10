package at.fhv.jazzers.backend;

import at.fhv.jazzers.backend.communication.rmi.RMI_Server;

public class Server {
    public static void main(String[] args) {
        try {
            // Create initial entries (they are cleared because of "create-drop" in persistence.xml)
            DataGenerator.main(args);

            RMI_Server.start();

            // RMI_SessionFactory sessionFactory = new RMI_SessionFactory();
            // boolean answer = sessionFactory.authenticate("cpe2877", "password");

            // Session sessionFactory = new Session();
            // boolean answer = sessionFactory.credentialsValid("cpe2877", "PssWrd");
            // System.out.println("Answer: " + answer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}