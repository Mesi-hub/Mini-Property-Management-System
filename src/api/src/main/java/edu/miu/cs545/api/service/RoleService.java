package edu.miu.cs545.api.service;

import edu.miu.cs545.api.dto.RoleDto;

import java.util.Collection;

public interface RoleService {
    Collection<RoleDto> findAllRoles();
}
