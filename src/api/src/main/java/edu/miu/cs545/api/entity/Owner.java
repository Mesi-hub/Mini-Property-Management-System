package edu.miu.cs545.api.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Owner extends Person{
    boolean approved;
    @ManyToOne(fetch = FetchType.LAZY)
    Administrator approvedBy;
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    @JsonManagedReference
    List<Property> properties;
}
