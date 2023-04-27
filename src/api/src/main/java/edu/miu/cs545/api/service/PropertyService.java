
package edu.miu.cs545.api.service;

import edu.miu.cs545.api.dto.PropertyDto;
import edu.miu.cs545.api.entity.Property;
import edu.miu.cs545.api.entity.User;

import java.util.List;

public interface PropertyService {

    public PropertyDto createProperty(PropertyDto property, User user);
    public PropertyDto findPropertyById(Long id);
    public PropertyDto updateProperty(PropertyDto property);
    public void deleteProperty(long id) ;
    public List<PropertyDto> findAll() ;

    List<Property> findProperByFilter(String city, Double max, Double min, Integer room);

}
