package edu.miu.cs545.api.service;


import edu.miu.cs545.api.dto.RoleDto;
import edu.miu.cs545.api.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public Collection<RoleDto> findAllRoles() {
        List<RoleDto> roles = new ArrayList<>();
        roleRepository.findAll().forEach(x -> roles.add(modelMapper.map(x, RoleDto.class)));
        return roles;
    }
}
