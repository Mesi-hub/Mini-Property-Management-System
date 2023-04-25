package edu.miu.cs545.api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    LocalDate date;
    LocalTime time;
    Double offerAmount;
    @ManyToOne
    Property property;
    @ManyToOne
    Customer customer;
    @Enumerated(EnumType.STRING)
    OfferState status;
}
