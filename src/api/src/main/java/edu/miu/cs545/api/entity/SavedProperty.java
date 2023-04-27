package edu.miu.cs545.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
public class SavedProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    LocalDate date;
    LocalTime time;
    @ManyToOne
    @JsonBackReference(value="savedProperty-property")
    Property property;
    @ManyToOne
    @JsonBackReference(value="savedProperty-customer")
    Customer customer;
}
