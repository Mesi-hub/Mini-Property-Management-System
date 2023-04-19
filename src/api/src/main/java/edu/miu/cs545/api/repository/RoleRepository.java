package edu.miu.cs545.api.repository;


import edu.miu.cs545.api.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, String> {
}
