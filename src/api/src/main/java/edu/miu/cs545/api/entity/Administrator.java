package edu.miu.cs545.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Administrator extends Person {
    @OneToMany(mappedBy = "blackListedBy")
    List<Person> blacklistedPersons;
}
