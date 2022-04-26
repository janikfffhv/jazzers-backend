package at.fhv.jazzers.backend.infrastructure;

import at.fhv.jazzers.backend.ServiceRegistry;
import at.fhv.jazzers.backend.domain.model.employee.Employee;
import at.fhv.jazzers.backend.domain.model.work.Genre;

import javax.jms.*;
import java.util.ArrayList;
import java.util.List;

public class JMSMessageConsumer {
    private Connection connection;
    private Session session;

    public void createDurableSubscribersFor(List<Employee> employees) {
        connectToMessagingService();

        try {
            for (Employee employee : employees) {
                for (Genre subscribedTopic : employee.subscribedTopics()) {
                    Topic topic = session.createTopic(subscribedTopic.getName());
                    String topicName = topic.getTopicName();
                    String userName = employee.employeeId().username();

                    TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic, userName + "-" + topicName);
                    topicSubscriber.close();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Unable to create durable subscribers.");
        }

        disconnectFromMessagingService();
    }

    public List<TextMessage> getMessagesFromSubscribedTopics(Employee employee) {
        connectToMessagingService();

        List<TextMessage> textMessages = new ArrayList<>();

        try {
            for (Genre subscribedTopic : employee.subscribedTopics()) {
                String userName = employee.employeeId().username();
                String topicName = subscribedTopic.getName();
                Topic topic = session.createTopic(topicName);

                TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic, userName + "-" + topicName);

                Message message;
                while ((message = topicSubscriber.receiveNoWait()) != null) {
                    textMessages.add((TextMessage) message);
                }

                topicSubscriber.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("Unable to read messages.");
        }

        disconnectFromMessagingService();

        return textMessages;
    }

    private void connectToMessagingService() {
        try {
            connection = ServiceRegistry.activeMQConnectionFactory().createConnection();
            connection.setClientID("client");
            connection.start();
            session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        } catch (Exception e) {
            throw new RuntimeException("Unable to connect to messaging service.");
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
