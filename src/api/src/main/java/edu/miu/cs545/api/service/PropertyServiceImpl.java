
package edu.miu.cs545.api.service;

import edu.miu.cs545.api.dto.PropertyDto;
import edu.miu.cs545.api.dto.UserDto;
import edu.miu.cs545.api.entity.Property;
import edu.miu.cs545.api.entity.PropertyState;
import edu.miu.cs545.api.repository.PropertyRepository;
import edu.miu.cs545.api.service.interfaces.PropertyService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
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
    EntityManager em;
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

    @Override
    public List<Property> findProperByFilter(String city, Double max, Double min, Integer room) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Property> cq = cb.createQuery(Property.class);

        Root<Property> property = cq.from(Property.class);
        List<Predicate> predicates = new ArrayList<>();

        if (city != null && city != "") {
            predicates.add(cb.equal(property.get("address").get("city"), city));
        }
        if (max != null && max != 0) {
            predicates.add(cb.lessThanOrEqualTo(property.get("price"), max));
        }
        if (min != null && min != 0) {
            predicates.add(cb.greaterThanOrEqualTo(property.get("price"), min));
        }
        if (room != null && room != 0) {
            predicates.add(cb.greaterThanOrEqualTo(property.get("noOfBedrooms"), room));
        }
        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
    }

}
