package at.fhv.jazzers.backend;

import at.fhv.jazzers.backend.communication.RMI_Server;

public class Server {
    public static void main(String[] args) {
        try {
            // Create initial entries (they are cleared because of "create-drop" in persistence.xml)
            DataGenerator.main(args);

            RMI_Server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}