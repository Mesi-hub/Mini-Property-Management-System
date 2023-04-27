package edu.miu.cs545.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    Long id;
    private String firstName;
    private String lastName;
    private String email;
    private AddressDto address;
    //To be sent from client when registering
    private String password;

    //UserDto user;
    private Boolean blackListed;
}
