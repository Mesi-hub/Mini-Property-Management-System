package edu.miu.cs545.api.service;

import edu.miu.cs545.api.dto.AdministratorDto;
import edu.miu.cs545.api.dto.CustomerDto;
import edu.miu.cs545.api.dto.OwnerDto;
import edu.miu.cs545.api.entity.Address;
import edu.miu.cs545.api.entity.Customer;
import edu.miu.cs545.api.entity.Owner;
import edu.miu.cs545.api.entity.User;
import edu.miu.cs545.api.repository.AdministratorRepository;
import edu.miu.cs545.api.repository.OwnerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {
    @Autowired
    OwnerRepository ownerRepository;
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
        return ownerRepository.getById(id);
    }


    @Override
    public boolean save(Owner owner) {
        ownerRepository.save(owner);
        return true;
    }

}
