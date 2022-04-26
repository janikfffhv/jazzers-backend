package at.fhv.jazzers.backend.application.impl;

import at.fhv.jazzers.backend.ServiceRegistry;
import at.fhv.jazzers.backend.application.api.MessageConsumerService;
import at.fhv.jazzers.backend.domain.model.employee.Employee;
import at.fhv.jazzers.backend.domain.model.employee.EmployeeId;
import at.fhv.jazzers.backend.domain.repository.EmployeeRepository;
import at.fhv.jazzers.backend.infrastructure.JMSMessageConsumer;
import at.fhv.jazzers.shared.dto.MessageDTO;

import javax.jms.TextMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MessageConsumerServiceImpl implements MessageConsumerService {
    JMSMessageConsumer jmsMessageConsumer;

    public MessageConsumerServiceImpl() {
        jmsMessageConsumer = ServiceRegistry.jmsMessageConsumer();
    }

    @Override
    public List<MessageDTO> getMessagesFromSubscribedTopics(String userName) {
        EmployeeRepository employeeRepository = ServiceRegistry.employeeRepository();
        Optional<Employee> employee = employeeRepository.byId(new EmployeeId(userName));

        if (employee.isEmpty()) {
            throw new IllegalArgumentException("The employee with the user name " + userName + " does not exist.");
        }

        List<MessageDTO> messagesDTO = new ArrayList<>();

        try {
            for (TextMessage textMessage : jmsMessageConsumer.getMessagesFromSubscribedTopics(employee.get())) {
                MessageDTO messageDTO = new MessageDTO(textMessage.getStringProperty("topic"), textMessage.getStringProperty("title"), textMessage.getText());
                messagesDTO.add(messageDTO);
            }
        } catch (Exception e) {
            throw new RuntimeException("Unable to receive messages.");
        }

        return messagesDTO;
    }
}
