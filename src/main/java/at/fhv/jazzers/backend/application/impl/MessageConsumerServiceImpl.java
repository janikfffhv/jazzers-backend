package at.fhv.jazzers.backend.application.impl;

import at.fhv.jazzers.backend.ServiceRegistry;
import at.fhv.jazzers.backend.application.api.MessageConsumerService;
import at.fhv.jazzers.backend.domain.model.work.Genre;
import at.fhv.jazzers.backend.infrastructure.JMSMessageConsumer;

public class MessageConsumerServiceImpl implements MessageConsumerService {
    JMSMessageConsumer jmsMessageConsumer;

    public MessageConsumerServiceImpl() {
        jmsMessageConsumer = ServiceRegistry.jmsMessageConsumer();
    }

    @Override
    public String consume(String topic) {
        if (Genre.isNotMember(topic)) {
            throw new IllegalArgumentException("Your topic (" + topic + ") is not allowed. Available topics: " + Genre.availableGenres());
        }

        return jmsMessageConsumer.consume(topic);
    }
}
