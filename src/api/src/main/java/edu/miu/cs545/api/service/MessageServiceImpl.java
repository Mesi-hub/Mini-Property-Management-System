package edu.miu.cs545.api.service;

import edu.miu.cs545.api.dto.MessageDto;
import edu.miu.cs545.api.dto.PropertyDto;
import edu.miu.cs545.api.entity.Message;
import edu.miu.cs545.api.entity.Person;
import edu.miu.cs545.api.entity.Property;
import edu.miu.cs545.api.entity.User;
import edu.miu.cs545.api.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OwnerRepository ownerRepository;
    @Autowired
    PropertyRepository propertyRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public List<MessageDto> getAll() {
        return messageRepository.findAll().stream().map(x->modelMapper.map(x, MessageDto.class)).toList();
    }

    @Override
    public List<MessageDto> getMessagesForUserOrderByDateTimeDesc(User user) {
        return messageRepository.findMessagesByRecipientIdOrderByDateDescTimeDesc(user.getId()).stream().map(x->modelMapper.map(x, MessageDto.class)).toList();
    }

    @Override
    public List<MessageDto> getMessagesForUserForPropertyOrderByDateTimeDesc(User user, PropertyDto propertyDto) {
        return messageRepository.findMessagesByRecipientIdAndPropertyIdOrderByDateDescTimeDesc(user.getId(), propertyDto.getId()).stream().map(x->modelMapper.map(x, MessageDto.class)).toList();
    }

    @Override
    public MessageDto postMessage(MessageDto messageDto, User user) {
        Message message = modelMapper.map(messageDto, Message.class);
        if(messageDto.getRecipient() != null && messageDto.getRecipient().getId() != null) {
            Person recipient = personRepository.getPersonByUserId(messageDto.getRecipient().getId());
            message.setRecipient(recipient);
        }

        if(messageDto.getProperty() != null && messageDto.getProperty().getId() != null) {
            Property property = propertyRepository.findById(messageDto.getProperty().getId() ).orElse(null);
            message.setProperty(property);
            message.setRecipient(property.getOwner());
        }
        message.setSender(user.getPerson());
        return modelMapper.map(messageRepository.save(message), MessageDto.class);
    }
}
