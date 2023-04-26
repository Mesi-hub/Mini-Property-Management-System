package edu.miu.cs545.api.repository;

import edu.miu.cs545.api.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    public Person getPersonByUserId(Long userId);
}
