package at.fhv.jazzers.backend.application.api;

public interface MessagePublisherService {
    void publish(String topic, String title, String message);
}
