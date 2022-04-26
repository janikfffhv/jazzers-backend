package at.fhv.jazzers.backend.communication.rmi;

import at.fhv.jazzers.backend.application.api.MessageConsumerService;
import at.fhv.jazzers.shared.api.RMI_MessageConsumerService;
import at.fhv.jazzers.shared.dto.MessageDTO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMI_MessageConsumerServiceImpl extends UnicastRemoteObject implements RMI_MessageConsumerService {
    private final MessageConsumerService messageConsumerService;

    public RMI_MessageConsumerServiceImpl(MessageConsumerService messageConsumerService) throws RemoteException {
        super(Integer.parseInt(System.getenv("JAZZERS_RMI_PORT")));
        this.messageConsumerService = messageConsumerService;
    }

    @Override
    public List<MessageDTO> getMessagesFromSubscribedTopics(String userName) throws RemoteException {
        return messageConsumerService.getMessagesFromSubscribedTopics(userName);
    }
}
