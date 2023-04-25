package edu.miu.cs545.api.repository;

import edu.miu.cs545.api.entity.Customer;
import edu.miu.cs545.api.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findByOwner(Customer owner);

}
