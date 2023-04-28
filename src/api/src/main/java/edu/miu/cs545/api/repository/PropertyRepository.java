package edu.miu.cs545.api.repository;

import edu.miu.cs545.api.entity.Customer;
import edu.miu.cs545.api.entity.Property;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    @Query("SELECT property FROM Property property INNER JOIN property.owner o WHERE o.blackListed = false AND o = :owner")
    List<Property> findByOwner(Customer owner);
    @Query("SELECT property FROM Property property INNER JOIN property.owner o WHERE o.blackListed = false ORDER BY property.id DESC")
    List<Property> findByOrderByIdDesc();
    @Query("SELECT property FROM Property property INNER JOIN property.owner o WHERE o.blackListed = false")
    Optional<Property> findById(Long id);
}
