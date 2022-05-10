package at.fhv.jazzers.backend.application.api;

import at.fhv.jazzers.shared.dto.MessageDTO;

import javax.ejb.Local;
import java.util.List;

@Local
public interface MessageConsumerService {
    List<MessageDTO> getMessagesFromSubscribedTopics(String userName);
}
