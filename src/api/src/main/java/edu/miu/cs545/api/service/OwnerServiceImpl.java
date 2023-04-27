package edu.miu.cs545.api.service;

import edu.miu.cs545.api.dto.OwnerDto;
import edu.miu.cs545.api.entity.Customer;
import edu.miu.cs545.api.entity.Owner;
import edu.miu.cs545.api.entity.User;
import edu.miu.cs545.api.repository.CustomerRepository;
import edu.miu.cs545.api.repository.OwnerRepository;
import jakarta.persistence.EntityExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {
    @Autowired
    OwnerRepository ownerRepository;
    @Autowired
    CustomerRepository customerRepository;
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
    public Long register(User user) {
        if(user.getAuthorities().stream().anyMatch(x->x.getAuthority().equals("OWNER"))){
            throw new EntityExistsException("User is already an owner");
        }
        Customer customer = customerRepository.getCustomersByUserId(user.getId());
        Owner owner = new Owner();
        owner.setApproved(false);
        owner.setEmail(customer.getEmail());
        owner.setAddress(customer.getAddress());
        owner.setFirstName(customer.getFirstName());
        owner.setLastName(customer.getLastName());
        owner.setUser(user);
        ownerRepository.save(owner);
        return owner.getId();
    }

}
