package edu.miu.cs545.api.repository;

import edu.miu.cs545.api.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
