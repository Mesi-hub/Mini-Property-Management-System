package edu.miu.cs545.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

import org.hibernate.annotations.Where;



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
    @NotEmpty
    String title;
    @NotEmpty
    @Column(columnDefinition = "text")
    String description;
    @OneToOne(fetch = FetchType.LAZY)
    @JsonBackReference("property-address")
    Address address;
    @ManyToOne(fetch = FetchType.LAZY)
    Owner owner;
    @OneToMany(mappedBy = "property", fetch = FetchType.LAZY)
    @JsonBackReference("property-offers")
    List<Offer> offers;
    @Enumerated(EnumType.STRING)
    PropertyState status;

    @OneToMany(fetch = FetchType.LAZY)
    List<BlobStorageInfo> images;
}
