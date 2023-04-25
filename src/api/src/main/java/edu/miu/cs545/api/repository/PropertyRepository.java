
package edu.miu.cs545.api.repository;

import edu.miu.cs545.api.entity.Property;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends CrudRepository<Property, Long> {

    List<Property> findByOrderByIdDesc();
}
