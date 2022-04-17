package at.fhv.jazzers.backend.application.api;

public interface MessageConsumerService {
    String consume(String topic);
}
