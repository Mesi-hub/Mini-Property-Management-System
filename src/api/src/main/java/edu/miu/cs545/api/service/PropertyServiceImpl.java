
package edu.miu.cs545.api.service;

import edu.miu.cs545.api.dto.PropertyDto;
import edu.miu.cs545.api.dto.UserDto;
import edu.miu.cs545.api.entity.Property;
import edu.miu.cs545.api.entity.PropertyState;
import edu.miu.cs545.api.repository.PropertyRepository;
import edu.miu.cs545.api.service.interfaces.PropertyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    PropertyRepository propertyRepo;

    @Autowired
    ModelMapper modelMapper;

    public Property createProperty(Property property) {
        return propertyRepo.save(property);
    }

    public PropertyDto findPropertyById(Long id) {
        Property property = propertyRepo.findById(id).orElseGet(null);
        return modelMapper.map(property, PropertyDto.class);
    }

    public Property updateProperty(Property property) {
        return propertyRepo.save(property);
    }

    public void deleteProperty(long id) {
        Optional<Property> property = propertyRepo.findById(id);
        if (property.isPresent())
            if (property.get().getStatus() != PropertyState.PENDING)
                propertyRepo.delete(property.orElseGet(null));
    }

    public List<PropertyDto> findAll() {
        return propertyRepo.findByOrderByIdDesc().stream().map(x-> modelMapper.map(x, PropertyDto.class)).toList();
    }
}
