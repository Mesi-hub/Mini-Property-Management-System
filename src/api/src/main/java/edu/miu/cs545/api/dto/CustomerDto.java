package edu.miu.cs545.api.dto;

import edu.miu.cs545.api.entity.Address;
import edu.miu.cs545.api.entity.Administrator;
import edu.miu.cs545.api.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    public String firstName;
    public String lastName;
    public String email;
    public AddressDto address;
    UserDto user;
}
