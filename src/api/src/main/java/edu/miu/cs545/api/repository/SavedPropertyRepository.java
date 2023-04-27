package edu.miu.cs545.api.repository;

import edu.miu.cs545.api.dto.SavedPropertyDto;
import edu.miu.cs545.api.entity.SavedProperty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavedPropertyRepository extends JpaRepository<SavedProperty, Long> {
    List<SavedProperty> findSavedPropertiesByCustomerIdOrderByDateDescTimeDesc(Long id);
    SavedProperty findSavedPropertyByCustomerIdAndPropertyId(Long customerId, Long propertyId);
}
