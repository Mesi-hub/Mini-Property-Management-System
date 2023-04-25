package edu.miu.cs545.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AddressDto {
         Integer id;
         String street;
         String city;
         String state;
         String zip;
        Double longitude;
        Double latitude;
}
