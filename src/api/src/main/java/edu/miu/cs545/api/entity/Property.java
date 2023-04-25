package edu.miu.cs545.api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Integer noOfBedrooms;
    Double noOfBathrooms;
    Double plotSize;
    Double price;
    Double area;
    String description;
    @OneToOne
    Address address;
    @ManyToOne
    Owner owner;
    @OneToMany(mappedBy = "property")
    List<Offer> offers;
    @Enumerated(EnumType.STRING)
    PropertyState status;
}
