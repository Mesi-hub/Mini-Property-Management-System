package edu.miu.cs545.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Boolean approved;
    private AddressDto address;
    //To be sent from client when registering
    private String password;
    private Boolean blackListed;
    private List<PropertyDto> properties;
}
