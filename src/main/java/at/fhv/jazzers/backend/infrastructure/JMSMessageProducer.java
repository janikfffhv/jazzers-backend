package at.fhv.jazzers.backend.infrastructure;

import at.fhv.jazzers.backend.ServiceRegistry;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jms.*;

@LocalBean
@Stateless
public class JMSMessageProducer {
    private Connection connection;
    private Session session;

    public JMSMessageProducer() {

    }

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
            Destination destination = session.createTopic(topic);

            TextMessage textMessage = session.createTextMessage(message);
            textMessage.setStringProperty("topic", topic);
            textMessage.setStringProperty("title", title);

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
