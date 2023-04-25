
package edu.miu.cs545.api.service.interfaces;

import edu.miu.cs545.api.dto.PropertyDto;
import edu.miu.cs545.api.entity.Property;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface PropertyService {

    public Property createProperty(Property property);
    public PropertyDto findPropertyById(Long id);
    public Property updateProperty(Property property);
    public void deleteProperty(long id) ;
    public List<PropertyDto> findAll() ;

}
