package edu.miu.cs545.api.service;

import edu.miu.cs545.api.dto.RoleDto;
import edu.miu.cs545.api.dto.UserDto;
import edu.miu.cs545.api.entity.Role;
import edu.miu.cs545.api.entity.User;
import edu.miu.cs545.api.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public UserDto getById(Long id) {
        return getUserDto(userRepository.findById(id).orElse(null));
    }

    @Override
    public UserDto add(UserDto userDto) {
        return getUserDto(userRepository.save(getUser(userDto)));
    }

    @Override
    public UserDto update(UserDto userDto) {
        return getUserDto(userRepository.save(getUser(userDto)));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Collection<UserDto> getAll() {
        Collection<UserDto> userDto = new ArrayList<>();
        userRepository.findAll().forEach(x->userDto.add(getUserDto(x)));
        return userDto;
    }

    private UserDto getUserDto(User user){
        UserDto userDto = modelMapper.map(user, UserDto.class);
        userDto.setRoles(user.getRoles().stream().map(x->modelMapper.map(x, RoleDto.class)).toList());
        return userDto;
    }

    private User getUser(UserDto userDto){
        User user = modelMapper.map(userDto, User.class);
        user.setRoles(userDto.getRoles().stream().map(x-> modelMapper.map(x, Role.class)).toList());
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElse(null);
    }
}
