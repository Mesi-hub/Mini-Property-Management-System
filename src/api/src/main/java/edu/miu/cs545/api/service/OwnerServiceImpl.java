package edu.miu.cs545.api.service;

import edu.miu.cs545.api.dto.CustomerDto;
import edu.miu.cs545.api.dto.OwnerDto;
import edu.miu.cs545.api.entity.*;
import edu.miu.cs545.api.repository.*;
import jakarta.persistence.EntityExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {
    @Autowired
    OwnerRepository ownerRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AddressRepository addressRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public List<OwnerDto> getAll() {
        return ownerRepository.findAll().stream()
                .map(x->modelMapper.map(x, OwnerDto.class))
                .toList();
    }

    @Override
    public Owner findById(long id) {
        return ownerRepository.findById(id).orElse(null);
    }


    @Override
    public boolean save(Owner owner) {
        ownerRepository.save(owner);
        return true;
    }

    @Override
    public Long register(OwnerDto ownerDto) {
        var user = new User();
        user.setName(ownerDto.getFirstName());
        user.setEmail(ownerDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(ownerDto.getPassword()));
        user = userRepo.save(user);

        Role role = roleRepository.findById(RoleTypes.OWNER.toString()).orElseThrow();
        List<User> users = role.getUsers() == null ? new ArrayList<>() : role.getUsers();
        users.add(user);
        role.setUsers(users);
        roleRepository.save(role);

        var owner = mapDtoToOwner(ownerDto);
        var address = addressRepo.save(owner.getAddress());
        owner.setAddress(address);
        owner.setUser(user);
        ownerRepository.save(owner);
        return owner.getId();
    }

    private Owner mapDtoToOwner(OwnerDto dto) {
        var owner = modelMapper.map(dto, Owner.class);
        owner.setAddress(modelMapper.map(dto.getAddress(), Address.class));

        /*if(dto.getUser() != null)
        userRepo.findById(dto.getUser().getId())
                .ifPresent(customer::setUser);*/

        return owner;
    }

}
