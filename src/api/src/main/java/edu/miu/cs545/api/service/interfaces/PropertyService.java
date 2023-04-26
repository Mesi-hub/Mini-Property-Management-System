
package edu.miu.cs545.api.service.interfaces;

import edu.miu.cs545.api.dto.PropertyDto;
import edu.miu.cs545.api.entity.Property;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface PropertyService {

    public PropertyDto createProperty(PropertyDto property);
    public PropertyDto findPropertyById(Long id);
    public PropertyDto updateProperty(PropertyDto property);
    public void deleteProperty(long id) ;
    public List<PropertyDto> findAll() ;

    List<Property> findProperByFilter(String city, Double max, Double min, Integer room);

}
