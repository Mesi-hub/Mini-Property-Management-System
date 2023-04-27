package edu.miu.cs545.api.repository;

import edu.miu.cs545.api.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    public Owner getOwnerByUserId(Long userId);
}
