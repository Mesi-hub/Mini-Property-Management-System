package edu.miu.cs545.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Customer extends Person {
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    @JsonBackReference(value="customer-offer")
    List<Offer> offers;
}
