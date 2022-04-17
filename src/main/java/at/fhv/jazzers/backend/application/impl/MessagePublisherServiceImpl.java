package at.fhv.jazzers.backend.application.impl;

import at.fhv.jazzers.backend.ServiceRegistry;
import at.fhv.jazzers.backend.application.api.MessagePublisherService;
import at.fhv.jazzers.backend.domain.model.work.Genre;
import at.fhv.jazzers.backend.infrastructure.JMSMessageProducer;

public class MessagePublisherServiceImpl implements MessagePublisherService {
    JMSMessageProducer jmsMessageProducer;

    public MessagePublisherServiceImpl() {
        jmsMessageProducer = ServiceRegistry.jmsMessageProducer();
    }

    @Override
    public void publish(String topic, String title, String message) {
        if (Genre.isNotMember(topic)) {
            throw new IllegalArgumentException("Your topic (" + topic + ") is not allowed. Available topics: " + Genre.availableGenres());
        }

        jmsMessageProducer.publish(topic, title, message);
    }
}
