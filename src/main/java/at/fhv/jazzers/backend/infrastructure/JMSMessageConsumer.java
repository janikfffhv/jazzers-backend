package at.fhv.jazzers.backend.infrastructure;

import at.fhv.jazzers.backend.ServiceRegistry;

import javax.jms.*;

public class JMSMessageConsumer {
    private Connection connection;
    private Session session;

    public String consume(String topic) {
        connectToMessagingService();
        String message = readMessageFromTopic(topic);
        disconnectFromMessagingService();
        return message;
    }

    private void connectToMessagingService() {
        try {
            connection = ServiceRegistry.activeMQConnectionFactory().createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        } catch (Exception e) {
            throw new RuntimeException("Unable to connect to messaging service.");
        }
    }

    private String readMessageFromTopic(String topic) {
        try {
            // ToDo: Use Topic instead of Queue
            // Destination destination = session.createTopic(topic);
            Destination destination = session.createQueue(topic);
            MessageConsumer consumer = session.createConsumer(destination);

            Message message = consumer.receive();
            String text = (message instanceof TextMessage) ? ((TextMessage) message).getText() : "INVALID TYPE";
            consumer.close();

            return text;
        } catch (Exception e) {
            throw new RuntimeException("Unable to read message from topic");
        }
    }

    private void disconnectFromMessagingService() {
        try {
            session.close();
            connection.close();
        } catch (Exception e) {
            throw new RuntimeException("Unable to disconnect from messaging service.");
        }
    }
}
