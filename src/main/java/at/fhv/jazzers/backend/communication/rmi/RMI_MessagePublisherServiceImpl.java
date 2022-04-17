package at.fhv.jazzers.backend.communication.rmi;

import at.fhv.jazzers.backend.application.api.MessagePublisherService;
import at.fhv.jazzers.shared.api.RMI_MessagePublisherService;

import javax.jms.JMSException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMI_MessagePublisherServiceImpl extends UnicastRemoteObject implements RMI_MessagePublisherService {
    private final MessagePublisherService messagePublisherService;

    protected RMI_MessagePublisherServiceImpl(MessagePublisherService messagePublisherService) throws RemoteException {
        super(Integer.parseInt(System.getenv("JAZZERS_RMI_PORT")));
        this.messagePublisherService = messagePublisherService;
    }

    @Override
    public void publish(String topic, String title, String message) throws RemoteException {
        messagePublisherService.publish(topic, title, message);
    }
}
