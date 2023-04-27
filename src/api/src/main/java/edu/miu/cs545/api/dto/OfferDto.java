package edu.miu.cs545.api.dto;

import edu.miu.cs545.api.entity.OfferState;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfferDto {
    Long id;
    LocalDate date;
    LocalTime time;
    Double offerAmount;
    PropertyDto property;
    CustomerDto customer;
    OfferState status;
}