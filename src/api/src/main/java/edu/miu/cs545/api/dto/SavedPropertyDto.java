package edu.miu.cs545.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class SavedPropertyDto {
    Long id;
    LocalDate date;
    LocalTime time;
    PropertyDto property;
    CustomerDto customer;    
    private List<ImageFileInfoDto> images;
}
