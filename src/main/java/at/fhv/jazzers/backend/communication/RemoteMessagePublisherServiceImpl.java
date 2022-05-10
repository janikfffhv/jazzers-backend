package at.fhv.jazzers.backend.communication;

import at.fhv.jazzers.backend.application.api.MessagePublisherService;
import at.fhv.jazzers.shared.api.RemoteMessagePublisherService;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class RemoteMessagePublisherServiceImpl implements RemoteMessagePublisherService {
    @EJB
    private MessagePublisherService messagePublisherService;

    public RemoteMessagePublisherServiceImpl() {

    }

    @Override
    public void publish(String topic, String title, String message) {
        messagePublisherService.publish(topic, title, message);
    }
}
