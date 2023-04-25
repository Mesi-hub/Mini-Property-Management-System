package edu.miu.cs545.api.dto;

import edu.miu.cs545.api.entity.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PersonDto {
    Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    boolean blackListed;
    AdministratorDto blackListedBy;
}
