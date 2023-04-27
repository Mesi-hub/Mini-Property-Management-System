package edu.miu.cs545.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotEmpty
    String message;
    @OneToOne
    @NotNull
    @JsonBackReference(value="message-recipient")
    Person recipient;
    @OneToOne
    @NotNull
    @JsonBackReference(value="message-sender")
    Person sender;
    @OneToOne
    @JsonBackReference(value="message-replyTo")
    Message replyTo;
    @ManyToOne
    @JsonBackReference(value="message-property")
    Property property;
    LocalDate date;
    LocalTime time;
}
