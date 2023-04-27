package edu.miu.cs545.api.service;

import edu.miu.cs545.api.dto.SavedPropertyDto;
import edu.miu.cs545.api.entity.*;
import edu.miu.cs545.api.repository.CustomerRepository;
import edu.miu.cs545.api.repository.SavedPropertyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
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
        Long personId;
        if(user.getPerson() == null){
            personId = customerRepository.getCustomersByUserId(user.getId()).getId();
        }
        else {
            personId = user.getPerson().getId();
        }

        return savedPropertyRepository.findSavedPropertiesByCustomerIdOrderByDateDescTimeDesc(personId).stream().map(x->modelMapper.map(x, SavedPropertyDto.class)).toList();
    }

    @Override
    public SavedPropertyDto saveProperty(SavedPropertyDto savedPropertyDto, User user) {
        SavedProperty savedProperty = modelMapper.map(savedPropertyDto, SavedProperty.class);
        savedProperty.setDate(LocalDate.now());
        savedProperty.setTime(LocalTime.now());
        Customer customer = customerRepository.getCustomersByUserId(user.getId());
        SavedProperty existing = savedPropertyRepository.findSavedPropertyByCustomerIdAndPropertyId(customer.getId(), savedProperty.getProperty().getId());
        if(existing != null && existing.getId() != null)
            return modelMapper.map(existing, SavedPropertyDto.class);
        savedProperty.setCustomer(customer);
        return modelMapper.map(savedPropertyRepository.save(savedProperty), SavedPropertyDto.class);
    }

    @Override
    public void delete(Long id, User user) {
        SavedProperty existing = savedPropertyRepository.findById(id).orElseThrow();
        if(existing.getCustomer().getUser().getId() != user.getId())
            throw new EntityNotFoundException("The entity does not exist or the user does not have access to it.");
        savedPropertyRepository.delete(existing);
    }
}
