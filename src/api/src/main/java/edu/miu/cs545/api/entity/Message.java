package edu.miu.cs545.api.entity;

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
    Person recipient;
    @OneToOne
    @NotNull
    Person sender;
    @OneToOne
    Message replyTo;
    @ManyToOne
    Property property;
    LocalDate date;
    LocalTime time;
}
