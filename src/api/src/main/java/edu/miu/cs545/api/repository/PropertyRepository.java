package edu.miu.cs545.api.repository;

import edu.miu.cs545.api.entity.Customer;
import edu.miu.cs545.api.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    List<Property> findByOwner(Customer owner);
    List<Property> findByOrderByIdDesc();
    Optional<Property> findById(Long id);





}
