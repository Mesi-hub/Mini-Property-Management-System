package edu.miu.cs545.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class UserDto {
    Long id;
    String name;
    Boolean ownerApprovalPending;
    List<RoleDto> roles;
}
