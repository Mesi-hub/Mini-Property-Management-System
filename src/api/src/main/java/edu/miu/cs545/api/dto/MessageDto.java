package edu.miu.cs545.api.dto;

import edu.miu.cs545.api.entity.Message;
import edu.miu.cs545.api.entity.Person;
import edu.miu.cs545.api.entity.Property;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    Long id;
    String message;
    Person recipient;
    Person sender;
    MessageDto replyTo;
    PropertyDto property;
    Long propertyId;
    Long recipientId;
    Long senderId;
    LocalDate date;
    LocalTime time;
}
