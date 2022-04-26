package at.fhv.jazzers.backend.application.api;

import at.fhv.jazzers.shared.dto.MessageDTO;

import java.util.List;

public interface MessageConsumerService {
    List<MessageDTO> getMessagesFromSubscribedTopics(String userName);
}
