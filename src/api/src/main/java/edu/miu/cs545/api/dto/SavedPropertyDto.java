package edu.miu.cs545.api.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import edu.miu.cs545.api.entity.Customer;
import edu.miu.cs545.api.entity.Property;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
public class SavedPropertyDto {
    Long id;
    LocalDate date;
    LocalTime time;
    PropertyDto property;
    CustomerDto customer;
}
