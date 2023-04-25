package edu.miu.cs545.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Customer extends Person {
    @OneToMany(mappedBy = "customer")
    List<Offer> offers;
}
