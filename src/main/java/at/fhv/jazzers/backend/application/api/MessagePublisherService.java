package at.fhv.jazzers.backend.application.api;

import javax.ejb.Local;

@Local
public interface MessagePublisherService {
    void publish(String topic, String title, String message);
}
