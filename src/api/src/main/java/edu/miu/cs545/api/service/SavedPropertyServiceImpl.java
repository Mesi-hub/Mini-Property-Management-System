package edu.miu.cs545.api.service;

import edu.miu.cs545.api.dto.ImageFileInfoDto;
import edu.miu.cs545.api.dto.SavedPropertyDto;
import edu.miu.cs545.api.entity.*;
import edu.miu.cs545.api.repository.CustomerRepository;
import edu.miu.cs545.api.repository.SavedPropertyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SavedPropertyServiceImpl implements SavedPropertyService{
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    SavedPropertyRepository savedPropertyRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Value("${spring.cloud.azure.storage.blob.end-point}/${spring.cloud.azure.storage.blob.container-name}/${spring.cloud.azure.storage.blob.property.stockimage}")
    private String stockImagePath;
    @Value("${spring.cloud.azure.storage.blob.property.stockimage}")
    private String stockImageName;
    
    @Override
    public List<SavedPropertyDto> getAll() {
        return savedPropertyRepository.findAll().stream().map(x->addImageInfo(x)).toList();
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

        return savedPropertyRepository.findSavedPropertiesByCustomerIdOrderByDateDescTimeDesc(personId).stream().map(x->addImageInfo(x)).toList();
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
        return addImageInfo(savedPropertyRepository.save(savedProperty));
    }

    @Override
    public void delete(Long id, User user) {
        SavedProperty existing = savedPropertyRepository.findById(id).orElseThrow();
        if(existing.getCustomer().getUser().getId() != user.getId())
            throw new EntityNotFoundException("The entity does not exist or the user does not have access to it.");
        savedPropertyRepository.delete(existing);
    }
    private SavedPropertyDto addStockImage(SavedPropertyDto dto){
        if(dto == null || dto.getImages().size() == 0) {
            dto.setImages(getStockImageArray());
        }
        return dto;
    }

    private List<ImageFileInfoDto> getStockImageArray(){
        List<ImageFileInfoDto> stockFileInfo = new ArrayList<>();
        stockFileInfo.add(new ImageFileInfoDto(stockImageName, stockImagePath));       
        return stockFileInfo;
    }   

    private SavedPropertyDto addImageInfo(SavedProperty x) {
        SavedPropertyDto dto = modelMapper.map(x, SavedPropertyDto.class);
        dto.setImages(x.getProperty().getImages().stream().map(y-> modelMapper.map(y, ImageFileInfoDto.class)).toList());
        dto = addStockImage(dto);
        return dto;
    }
}
