package edu.miu.cs545.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Owner extends Person{
    @OneToMany(mappedBy = "owner")
    List<Property> properties;
}
