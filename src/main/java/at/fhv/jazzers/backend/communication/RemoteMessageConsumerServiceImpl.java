package at.fhv.jazzers.backend.communication;

import at.fhv.jazzers.backend.application.api.MessageConsumerService;
import at.fhv.jazzers.shared.api.RemoteMessageConsumerService;
import at.fhv.jazzers.shared.dto.MessageDTO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class RemoteMessageConsumerServiceImpl implements RemoteMessageConsumerService {
    @EJB
    private MessageConsumerService messageConsumerService;

    public RemoteMessageConsumerServiceImpl() {

    }

    @Override
    public List<MessageDTO> getMessagesFromSubscribedTopics(String userName) {
        return messageConsumerService.getMessagesFromSubscribedTopics(userName);
    }
}
