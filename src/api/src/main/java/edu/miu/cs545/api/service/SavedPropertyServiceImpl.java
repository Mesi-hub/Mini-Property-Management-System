package edu.miu.cs545.api.service;

import edu.miu.cs545.api.dto.SavedPropertyDto;
import edu.miu.cs545.api.entity.*;
import edu.miu.cs545.api.repository.CustomerRepository;
import edu.miu.cs545.api.repository.SavedPropertyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavedPropertyServiceImpl implements SavedPropertyService{
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    SavedPropertyRepository savedPropertyRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public List<SavedPropertyDto> getAll() {
        return savedPropertyRepository.findAll().stream().map(x->modelMapper.map(x, SavedPropertyDto.class)).toList();
    }

    @Override
    public List<SavedPropertyDto> getSavedPropertiesForUserOrderByDateTimeDesc(User user) {
        return savedPropertyRepository.findSavedPropertiesByCustomerIdOrderByDateDescTimeDesc(user.getPerson().getId()).stream().map(x->modelMapper.map(x, SavedPropertyDto.class)).toList();
    }

    @Override
    public SavedPropertyDto saveProperty(SavedPropertyDto savedPropertyDto, User user) {
        SavedProperty savedProperty = modelMapper.map(savedPropertyDto, SavedProperty.class);
        Customer customer = customerRepository.getCustomersByUserId(user.getId());
        savedProperty.setCustomer(customer);
        return modelMapper.map(savedPropertyRepository.save(savedProperty), SavedPropertyDto.class);
    }
}
