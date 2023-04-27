package edu.miu.cs545.api.repository;

import edu.miu.cs545.api.entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
}
