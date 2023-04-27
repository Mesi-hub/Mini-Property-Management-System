
package edu.miu.cs545.api.service;

import edu.miu.cs545.api.dto.PropertyDto;
import edu.miu.cs545.api.entity.Property;

import java.util.List;
import java.util.Optional;

public interface PropertyService {

    public PropertyDto createProperty(PropertyDto property);
    public PropertyDto findPropertyById(Long id);
    public PropertyDto updateProperty(PropertyDto property);
    public void deleteProperty(long id) ;
    public List<PropertyDto> findAll() ;
    List<Property> findProperByFilter(String city, Double max, Double min, Integer room);

}
