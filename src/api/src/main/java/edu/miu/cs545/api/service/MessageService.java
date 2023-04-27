package edu.miu.cs545.api.service;

import edu.miu.cs545.api.dto.MessageDto;
import edu.miu.cs545.api.dto.PropertyDto;
import edu.miu.cs545.api.entity.User;

import java.util.List;

public interface MessageService {
    List<MessageDto> getAll();
    MessageDto getById(Long id);
    List<MessageDto> getMessagesForUserOrderByDateTimeDesc(User user);
    List<MessageDto> getMessagesForUserForPropertyOrderByDateTimeDesc(User user, PropertyDto propertyDto);
    MessageDto postMessage(MessageDto messageDto, User user);
}
