package edu.miu.cs545.api.service;

import edu.miu.cs545.api.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;

public interface UserService extends UserDetailsService {
    UserDto getById(Long id);
    UserDto add(UserDto user);
    UserDto update(UserDto user);
    void delete(Long id);
    Collection<UserDto> getAll();
}
