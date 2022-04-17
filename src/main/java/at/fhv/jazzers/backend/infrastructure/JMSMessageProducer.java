package at.fhv.jazzers.backend.infrastructure;

import at.fhv.jazzers.backend.ServiceRegistry;

import javax.jms.*;

public class JMSMessageProducer {
    private Connection connection;
    private Session session;

    public void publish(String topic, String title, String message) {
        connectToMessagingService();
        sendMessageToTopic(topic, title, message);
        disconnectFromMessagingService();
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

    private void sendMessageToTopic(String topic, String title, String message) {
        try {
            // ToDo: Use Topic instead of Queue
            // Destination destination = session.createTopic(topic);
            Destination destination = session.createQueue(topic);
            TextMessage textMessage = session.createTextMessage(title + "\n" + message);


            MessageProducer messageProducer = session.createProducer(destination);
            messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
            messageProducer.send(textMessage);
        } catch (Exception e) {
            throw new RuntimeException("Unable to send message to topic.");
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
