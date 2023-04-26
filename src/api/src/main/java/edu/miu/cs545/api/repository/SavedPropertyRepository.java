package edu.miu.cs545.api.repository;

import edu.miu.cs545.api.entity.SavedProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavedPropertyRepository extends JpaRepository<SavedProperty, Long> {
    List<SavedProperty> findSavedPropertiesByCustomerIdOrderByDateDescTimeDesc(Long id);
}
